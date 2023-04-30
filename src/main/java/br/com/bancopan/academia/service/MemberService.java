package br.com.bancopan.academia.service;

import br.com.bancopan.academia.model.domain.GymMemberEntity;
import br.com.bancopan.academia.repository.MemberRepository;
import br.com.bancopan.academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository alunoRepository;

    public List<GymMemberEntity> findAll(){
        return alunoRepository.findAll();
    }

    public GymMemberEntity create(GymMemberEntity aluno) {
        aluno = alunoRepository.save(aluno);
        return aluno;
    }

    public GymMemberEntity findById(Long id) {
        Optional<GymMemberEntity> obj = alunoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + GymMemberEntity.class.getName()));
    }

    public List<GymMemberEntity> findByNome(String nome){
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
