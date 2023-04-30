package br.com.bancopan.academia.service;

import br.com.bancopan.academia.model.domain.AvaliationEntity;
import br.com.bancopan.academia.repository.AvaliationRepository;
import br.com.bancopan.academia.repository.MembershipRepository;
import br.com.bancopan.academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AvaliationService {

    @Autowired
    private AvaliationRepository avaliationRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Transactional
    public AvaliationEntity save(AvaliationEntity avaliacao) {
        var matricula = membershipRepository.findAtivaByAlunoId(avaliacao.getAluno().getId());

        if (matricula.isEmpty()) {
            throw new ObjectNotFoundException("O aluno informado não tem matrícula ativa para fazer avaliação");
        }
        return avaliationRepository.save(avaliacao);
    }

    public AvaliationEntity findByIds(Long alunoId, Long avaliacaoId) {
        return avaliationRepository.findByIds(alunoId, avaliacaoId).orElseThrow(()
                -> new ObjectNotFoundException(
                String.format("O aluno de ID %d não possui uma avaliação com ID %d", alunoId, avaliacaoId)));
    }

    @Transactional
    public void delete(Long alunoId, Long avaliacaoId) {
        avaliationRepository.delete(findByIds(alunoId, avaliacaoId));
        avaliationRepository.flush();
    }

}
