package br.com.bancopan.academia.service;

import br.com.bancopan.academia.model.domain.MembershipEntity;
import br.com.bancopan.academia.repository.MembershipRepository;
import br.com.bancopan.academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MembershipService {

    @Autowired
    MembershipRepository membershipRepository;

    @Transactional
    public MembershipEntity save(MembershipEntity matricula) {
        return membershipRepository.save(matricula);
    }

    public List<MembershipEntity> findAll() {
        return membershipRepository.findAll();
    }

    public List<MembershipEntity> findByAlunoId(Long alunoId) {
        return membershipRepository.findByAlunoId(alunoId);
    }

    public MembershipEntity findById(Long id) {
        return membershipRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Não foi possível encontrar uma matrícula com ID " + id));
    }

    public List<MembershipEntity> buscarEntreDatas(LocalDateTime data1, LocalDateTime data2) {
        return membershipRepository.findByDataMatriculaBetween(data1, data2);
    }

    @Transactional
    public MembershipEntity delete(Long alunoId) {
        var optional = membershipRepository.findAtivaByAlunoId(alunoId);

        if (optional.isEmpty()) {
            throw new ObjectNotFoundException("Não existe matrícula ativa para o aluno informado");
        }

        var matricula = optional.get();
        matricula.setAtiva(Boolean.FALSE);
        matricula.setDataDesmatricula(LocalDateTime.now());

        return save(matricula);
    }
}
