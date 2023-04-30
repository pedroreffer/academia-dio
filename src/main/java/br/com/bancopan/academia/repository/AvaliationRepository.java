package br.com.bancopan.academia.repository;

import br.com.bancopan.academia.model.domain.AvaliationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AvaliationRepository extends JpaRepository<AvaliationEntity, Long> {

    @Query("FROM AvaliacaoEntity af WHERE af.aluno.id = :alunoId AND af.id = :avaliacaoId")
    Optional<AvaliationEntity> findByIds(@Param("alunoId") Long alunoId, @Param("avaliacaoId") Long avaliacaoId);
}
