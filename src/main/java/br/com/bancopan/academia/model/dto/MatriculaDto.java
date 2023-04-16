package br.com.bancopan.academia.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatriculaDto {

    private Long id;
    private Boolean ativa;
    private LocalDateTime dataMatricula;
    private LocalDateTime dataDesmatricula;
    private AlunoDto aluno;
}
