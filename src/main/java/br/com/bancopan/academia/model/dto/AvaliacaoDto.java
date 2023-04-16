package br.com.bancopan.academia.model.dto;

import br.com.bancopan.academia.model.domain.AlunoEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvaliacaoDto {

    private Long id;
    @NotNull
    private Double peso;

    @NotNull
    private Double altura;

    private AlunoEntity aluno;
    private LocalDateTime dataAvaliacao;
}
