package br.com.bancopan.academia.controller;

import br.com.bancopan.academia.controller.converter.AlunoConverter;
import br.com.bancopan.academia.model.dto.AlunoDto;
import br.com.bancopan.academia.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private AlunoConverter convert;

    @GetMapping
    public List<AlunoDto> findAll() {
        return convert.toListDto(service.findAll());
    }

    @GetMapping("/{id}")
    public AlunoDto findById(@PathVariable Long id) {
        return convert.toDto(service.findById(id));
    }

    @GetMapping("/pesquisar/{nome}")
    public List<AlunoDto> findByNome(@PathVariable String nome) {
        return convert.toListDto(service.findByNome(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoDto create(@RequestBody AlunoDto input) {
        return convert.toDto(service.create(convert.toEntity(input)));
    }

    @PutMapping("/{id}")
    public AlunoDto update(@PathVariable Long id, @RequestBody AlunoDto input) {
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
