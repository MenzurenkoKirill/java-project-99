package hexlet.code.app.mapper;

import hexlet.code.app.dto.labels.LabelCreateDTO;
import hexlet.code.app.dto.labels.LabelDTO;
import hexlet.code.app.dto.labels.LabelUpdateDTO;
import hexlet.code.app.model.Label;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class LabelMapper {
    public abstract Label map(LabelCreateDTO dto);

    public abstract void map(LabelUpdateDTO dto, @MappingTarget Label model);

    public abstract LabelDTO map(Label model);

    public abstract List<LabelDTO> map(List<Label> models);
}
