package br.com.bancopan.academia.controller.converter;

import br.com.bancopan.academia.model.domain.AvaliacaoEntity;
import br.com.bancopan.academia.model.dto.AvaliacaoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvaliacaoConverter {

    @Autowired
    private ModelMapper mapper;

    public AvaliacaoDto toDto(AvaliacaoEntity objectModel) {
        return mapper.map(objectModel, AvaliacaoDto.class);
    }

    public AvaliacaoEntity toEntity(AvaliacaoDto objectModel) {
        return mapper.map(objectModel, AvaliacaoEntity.class);
    }

    public List<AvaliacaoDto> toListDto(Collection<AvaliacaoEntity> collection) {
        return collection.stream().map(object -> toDto(object)).collect(Collectors.toList());
    }
}
