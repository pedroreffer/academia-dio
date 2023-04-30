package br.com.bancopan.academia.repository;

import br.com.bancopan.academia.model.domain.GymMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<GymMemberEntity, Long> {

    List<GymMemberEntity> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a.id FROM AlunoEntity a WHERE a.cpf = :cpf")
    Long buscarIdAlunoPorCPF(@Param("cpf") String cpf);
}
