package br.com.bancopan.academia.controller.converter;

import br.com.bancopan.academia.model.domain.AlunoEntity;
import br.com.bancopan.academia.model.dto.AlunoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlunoConverter {

    @Autowired
    private ModelMapper mapper;

    public AlunoDto toDto(AlunoEntity objectModel) {
        return mapper.map(objectModel, AlunoDto.class);
    }

    public AlunoEntity toEntity(AlunoDto input) {
        return mapper.map(input, AlunoEntity.class);
    }

    public List<AlunoDto> toListDto(Collection<AlunoEntity> collection) {
        return collection.stream().map(object -> toDto(object)).collect(Collectors.toList());
    }

    public void copyToEntity(AlunoDto input, AlunoEntity objectModel) {
        mapper.map(input, objectModel);
    }
}
