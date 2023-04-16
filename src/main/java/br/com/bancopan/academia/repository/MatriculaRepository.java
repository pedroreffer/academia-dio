package br.com.bancopan.academia.repository;

import br.com.bancopan.academia.model.domain.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {

    List<MatriculaEntity> findByAlunoId(Long alunoId);

    @Query("FROM MatriculaEntity m WHERE m.aluno.id = :alunoId AND m.ativa = true")
    Optional<MatriculaEntity> findAtivaByAlunoId(Long alunoId);

    List<MatriculaEntity> findByDataMatriculaBetween(LocalDateTime data1, LocalDateTime data2);
}
