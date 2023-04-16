package br.com.bancopan.academia.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlunoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "cpf", length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno")
    private List<AvaliacaoEntity> avaliacoes;
}
