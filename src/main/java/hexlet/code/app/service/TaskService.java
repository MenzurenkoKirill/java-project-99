package hexlet.code.app.service;

import hexlet.code.app.dto.tasks.TaskCreateDTO;
import hexlet.code.app.dto.tasks.TaskDTO;
import hexlet.code.app.dto.tasks.TaskUpdateDTO;
import hexlet.code.app.mapper.TaskMapper;
import hexlet.code.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDTO> getAll(TaskDTO params) {
        var result = taskRepository.findAll();
        return taskMapper.map(result);
    }

    public TaskDTO findById(Long id) {
        var taskStatus = taskRepository.findById(id).orElseThrow();
        return taskMapper.map(taskStatus);
    }

    public TaskDTO create(TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public TaskDTO update(TaskUpdateDTO taskData, Long id) {
        var task = taskRepository.findById(id).orElseThrow();
        taskMapper.map(taskData, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
