package br.com.bancopan.academia.service;

import br.com.bancopan.academia.model.domain.MatriculaEntity;
import br.com.bancopan.academia.repository.MatriculaRepository;
import br.com.bancopan.academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Transactional
    private MatriculaEntity save(MatriculaEntity matricula) {
        return matriculaRepository.save(matricula);
    }

    public List<MatriculaEntity> findAll() {
        return matriculaRepository.findAll();
    }

    public List<MatriculaEntity> findById(Long alunoId) {
        return matriculaRepository.findByAlunoId(alunoId);
    }

    public MatriculaEntity buscarPorId(Long id) {
        return matriculaRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Não foi possível encontrar uma matrícula com ID " + id));
    }

    public List<MatriculaEntity> buscarEntreDatas(LocalDateTime data1, LocalDateTime data2) {
        return matriculaRepository.findByDataMatriculaBetween(data1, data2);
    }

    @Transactional
    public MatriculaEntity delete(Long alunoId) {
        var optional = matriculaRepository.findAtivaByAlunoId(alunoId);

        if (optional.isEmpty()) {
            throw new ObjectNotFoundException("Não existe matrícula ativa para o aluno informado");
        }

        var matricula = optional.get();
        matricula.setAtiva(Boolean.FALSE);
        matricula.setDataDesmatricula(LocalDateTime.now());

        return save(matricula);
    }
}
