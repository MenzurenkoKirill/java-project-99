package hexlet.code.app.component;

import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CustomUserDetailsService userService;

    @Autowired
    private final TaskStatusRepository taskStatusRepository;

    private static final List<TaskStatus> STATUSES = List.of(
            getTaskStatus("Draft", "draft"),
            getTaskStatus("To review", "to_review"),
            getTaskStatus("To be fixed", "to_be_fixed"),
            getTaskStatus("To publish", "to_publish"),
            getTaskStatus("Published", "published"));


    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByEmail("hexlet@example.com").isEmpty()) {
            userService.createUser((UserDetails) generateAdmin());
        }
        for (var status : STATUSES) {
            if (taskStatusRepository.findBySlug(status.getSlug()).isEmpty()) {
                taskStatusRepository.save(status);
            }
        }
    }

    public static User generateAdmin() {
        var userData = new User();
        userData.setEmail("hexlet@example.com");
        userData.setPasswordDigest("qwerty");
        return userData;
    }
    public static TaskStatus getTaskStatus(String name, String slug) {
        var taskStatus = new TaskStatus();
        taskStatus.setName(name);
        taskStatus.setSlug(slug);
        return taskStatus;
    }

}
