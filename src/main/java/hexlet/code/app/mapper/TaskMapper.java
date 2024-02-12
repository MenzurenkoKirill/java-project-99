package hexlet.code.app.mapper;

import hexlet.code.app.dto.tasks.TaskCreateDTO;
import hexlet.code.app.dto.tasks.TaskDTO;
import hexlet.code.app.dto.tasks.TaskUpdateDTO;
import hexlet.code.app.model.Task;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.repository.LabelRepository;
import hexlet.code.app.repository.TaskStatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {
    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    @Mapping(target = "taskStatus", source = "status", qualifiedByName = "slugToTaskStatus")
    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "labels", source = "taskLabelIds", qualifiedByName = "taskLabelIdsToLabels")
    public abstract Task map(TaskCreateDTO dto);

    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    @Mapping(target = "taskStatus", source = "status", qualifiedByName = "slugToTaskStatus")
    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "labels", source = "taskLabelIds", qualifiedByName = "taskLabelIdsToLabels")
    public abstract void map(TaskUpdateDTO dto, @MappingTarget Task model);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "description")
    @Mapping(target = "status", source = "taskStatus.slug")
    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "taskLabelIds", source = "labels", qualifiedByName = "labelsToLabelIds")
    public abstract TaskDTO map(Task model);

    public abstract List<TaskDTO> map(List<Task> models);

}
