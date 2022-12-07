package fhv.ws22.se.skyward.domain;

import fhv.ws22.se.skyward.domain.dtos.AbstractDto;
import fhv.ws22.se.skyward.domain.model.AbstractModel;
import org.modelmapper.ModelMapper;

public class DtoMapper {
    private ModelMapper modelMapper;

    public DtoMapper() {
        modelMapper = new ModelMapper();
    }

    public <T extends AbstractModel> T toModel(AbstractDto dto, Class<T> modelClass) {
        return modelMapper.map(dto, modelClass);
    }

    public <T extends AbstractDto> T toDto(AbstractModel model, Class<T> dtoClass) {
        return modelMapper.map(model, dtoClass);
    }
}
