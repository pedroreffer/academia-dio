package br.com.bancopan.academia.service;

import br.com.bancopan.academia.model.domain.AvaliacaoEntity;
import br.com.bancopan.academia.repository.AvaliacaoRepository;
import br.com.bancopan.academia.repository.MatriculaRepository;
import br.com.bancopan.academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Transactional
    public AvaliacaoEntity save(AvaliacaoEntity avaliacao) {
        var matricula = matriculaRepository.findAtivaByAlunoId(avaliacao.getAluno().getId());

        if (matricula.isEmpty()) {
            throw new ObjectNotFoundException("O aluno informado não tem matrícula ativa para fazer avaliação");
        }
        return avaliacaoRepository.save(avaliacao);
    }

    public AvaliacaoEntity findByIds(Long alunoId, Long avaliacaoId) {
        return avaliacaoRepository.findByIds(alunoId, avaliacaoId).orElseThrow(()
                -> new ObjectNotFoundException(
                String.format("O aluno de ID %d não possui uma avaliação com ID %d", alunoId, avaliacaoId)));
    }

    @Transactional
    public void delete(Long alunoId, Long avaliacaoId) {
        avaliacaoRepository.delete(findByIds(alunoId, avaliacaoId));
        avaliacaoRepository.flush();
    }

}
