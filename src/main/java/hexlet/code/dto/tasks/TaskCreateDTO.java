package hexlet.code.dto.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class TaskCreateDTO {
    @NotBlank
    private String title;

    private Integer index;

    private String content;

    @NotNull
    private String status;

    @JsonProperty("assignee_id")
    private Long assigneeId;

    private Set<Long> taskLabelIds;
}
