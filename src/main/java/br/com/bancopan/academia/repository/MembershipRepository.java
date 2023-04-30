package br.com.bancopan.academia.repository;

import br.com.bancopan.academia.model.domain.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {

    List<MembershipEntity> findByAlunoId(Long alunoId);

    @Query("FROM MatriculaEntity m WHERE m.aluno.id = :alunoId AND m.ativa = true")
    Optional<MembershipEntity> findAtivaByAlunoId(Long alunoId);

    List<MembershipEntity> findByDataMatriculaBetween(LocalDateTime data1, LocalDateTime data2);
}
