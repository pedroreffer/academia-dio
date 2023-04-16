package br.com.bancopan.academia.repository;

import br.com.bancopan.academia.model.domain.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long> {
}
