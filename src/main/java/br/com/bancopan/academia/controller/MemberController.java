package br.com.bancopan.academia.controller;

import br.com.bancopan.academia.controller.converter.MemberConverter;
import br.com.bancopan.academia.model.dto.MemberEntity;
import br.com.bancopan.academia.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private MemberConverter convert;

    @GetMapping
    public List<MemberEntity> findAll() {
        return convert.toListDto(service.findAll());
    }

    @GetMapping("/{id}")
    public MemberEntity findById(@PathVariable Long id) {
        return convert.toDto(service.findById(id));
    }

    @GetMapping("/pesquisar/{nome}")
    public List<MemberEntity> findByNome(@PathVariable String nome) {
        return convert.toListDto(service.findByNome(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberEntity create(@RequestBody MemberEntity input) {
        return convert.toDto(service.create(convert.toEntity(input)));
    }

    @PutMapping("/{id}")
    public MemberEntity update(@PathVariable Long id, @RequestBody MemberEntity input) {
        var aluno = service.findById(id);
        convert.copyToEntity(input, aluno);
        return convert.toDto(service.create(aluno));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
