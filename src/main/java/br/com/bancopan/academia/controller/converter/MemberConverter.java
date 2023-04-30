package br.com.bancopan.academia.controller.converter;

import br.com.bancopan.academia.model.domain.GymMemberEntity;
import br.com.bancopan.academia.model.dto.MemberEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberConverter {

    @Autowired
    private ModelMapper mapper;

    public MemberEntity toDto(GymMemberEntity objectModel) {
        return mapper.map(objectModel, MemberEntity.class);
    }

    public GymMemberEntity toEntity(MemberEntity input) {
        return mapper.map(input, GymMemberEntity.class);
    }

    public List<MemberEntity> toListDto(Collection<GymMemberEntity> collection) {
        return collection.stream().map(object -> toDto(object)).collect(Collectors.toList());
    }

    public void copyToEntity(MemberEntity input, GymMemberEntity objectModel) {
        mapper.map(input, objectModel);
    }
}
