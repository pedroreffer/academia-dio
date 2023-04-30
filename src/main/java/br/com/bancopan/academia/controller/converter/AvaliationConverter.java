package br.com.bancopan.academia.controller.converter;

import br.com.bancopan.academia.model.domain.AvaliationEntity;
import br.com.bancopan.academia.model.dto.AvaliationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvaliationConverter {

    @Autowired
    private ModelMapper mapper;

    public AvaliationDto toDto(AvaliationEntity objectModel) {
        return mapper.map(objectModel, AvaliationDto.class);
    }

    public AvaliationEntity toEntity(AvaliationDto objectModel) {
        return mapper.map(objectModel, AvaliationEntity.class);
    }

    public List<AvaliationDto> toListDto(Collection<AvaliationEntity> collection) {
        return collection.stream().map(object -> toDto(object)).collect(Collectors.toList());
    }
}
