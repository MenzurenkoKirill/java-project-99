package hexlet.code.controller;

import hexlet.code.dto.users.UserCreateDTO;
import hexlet.code.dto.users.UserDTO;
import hexlet.code.dto.users.UserUpdateDTO;
import hexlet.code.service.UserService;
import hexlet.code.util.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDTO>> getList() {
        var result = userService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO show(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO create(@Valid @RequestBody UserCreateDTO userData) {
        return userService.create(userData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO update(@RequestBody UserUpdateDTO userData, @PathVariable Long id) {
        var currentUser = userUtils.getCurrentUser();
        if (currentUser.getId().equals(id)) {
            return userService.update(userData, id);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        var currentUser = userUtils.getCurrentUser();
        if (currentUser.getId().equals(id)) {
            userService.delete(id);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }
}
