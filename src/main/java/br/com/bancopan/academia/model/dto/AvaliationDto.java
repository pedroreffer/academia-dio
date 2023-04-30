package br.com.bancopan.academia.model.dto;

import br.com.bancopan.academia.model.domain.GymMemberEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvaliationDto {

    private Long id;
    @NotNull
    private Double peso;

    @NotNull
    private Double altura;

    private GymMemberEntity aluno;
    private LocalDateTime dataAvaliacao;
}
