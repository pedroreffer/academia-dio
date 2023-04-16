package br.com.bancopan.academia.configuration;

import br.com.bancopan.academia.model.domain.AvaliacaoEntity;
import br.com.bancopan.academia.model.dto.AvaliacaoDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        var avaliacaoMapper = mapper.createTypeMap(AvaliacaoEntity.class, AvaliacaoDto.class);

        return mapper;
    }
}
