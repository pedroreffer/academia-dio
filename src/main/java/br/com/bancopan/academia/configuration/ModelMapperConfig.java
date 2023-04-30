package br.com.bancopan.academia.configuration;

import br.com.bancopan.academia.model.domain.AvaliationEntity;
import br.com.bancopan.academia.model.dto.AvaliationDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        var avaliacaoMapper = mapper.createTypeMap(AvaliationEntity.class, AvaliationDto.class);

        return mapper;
    }
}
