package br.com.bancopan.academia.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoDto {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;
}
