package br.com.bancopan.academia.repository;

import br.com.bancopan.academia.model.domain.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {

    @Query("FROM AvaliacaoEntity af WHERE af.aluno.id = :alunoId AND af.id = :avaliacaoId")
    Optional<AvaliacaoEntity> findByIds(@Param("alunoId") Long alunoId, @Param("avaliacaoId") Long avaliacaoId);
}
