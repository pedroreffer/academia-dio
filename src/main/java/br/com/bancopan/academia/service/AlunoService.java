package br.com.bancopan.academia.service;

import br.com.bancopan.academia.model.domain.AlunoEntity;
import br.com.bancopan.academia.repository.AlunoRepository;
import br.com.bancopan.academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoEntity> findAll(){
        return alunoRepository.findAll();
    }

    public AlunoEntity create(AlunoEntity aluno) {
        aluno = alunoRepository.save(aluno);
        return aluno;
    }

    public AlunoEntity findById(Long id) {
        Optional<AlunoEntity> obj = alunoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + AlunoEntity.class.getName()));
    }

    public List<AlunoEntity> findByNome(String nome){
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public void delete(Long id) {
        try {
            alunoRepository.delete(findById(id));
            alunoRepository.flush();

        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectNotFoundException("Não foi possível encontrar um aluno com ID " + id);
        }
    }
}
