package br.com.bancopan.academia.controller.converter;

import br.com.bancopan.academia.model.domain.MembershipEntity;
import br.com.bancopan.academia.model.dto.MembershipDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipConverter {

    @Autowired
    private ModelMapper mapper;

    public MembershipDto toDto(MembershipEntity objectModel) {
        return mapper.map(objectModel, MembershipDto.class);
    }

    public List<MembershipDto> toListDto(Collection<MembershipEntity> collection) {
        return collection.stream().map(object -> toDto(object)).collect(Collectors.toList());
    }
}
