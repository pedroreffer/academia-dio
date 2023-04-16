package br.com.bancopan.academia.controller.converter;

import br.com.bancopan.academia.model.domain.MatriculaEntity;
import br.com.bancopan.academia.model.dto.MatriculaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatriculaConverter {

    @Autowired
    private ModelMapper mapper;

    public MatriculaDto toDto(MatriculaEntity objectModel) {
        return mapper.map(objectModel, MatriculaDto.class);
    }

    public List<MatriculaDto> toListDto(Collection<MatriculaEntity> collection) {
        return collection.stream().map(object -> toDto(object)).collect(Collectors.toList());
    }
}
