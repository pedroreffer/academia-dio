package br.com.bancopan.academia.controller;

import br.com.bancopan.academia.controller.converter.AvaliacaoConverter;
import br.com.bancopan.academia.model.domain.AvaliacaoEntity;
import br.com.bancopan.academia.model.dto.AvaliacaoDto;
import br.com.bancopan.academia.service.AlunoService;
import br.com.bancopan.academia.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos/{alunoId}/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AvaliacaoConverter convert;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoDto create(@PathVariable Long alunoId, @RequestBody AvaliacaoDto input) {
        var avaliacao = new AvaliacaoEntity();

        avaliacao.setAluno(alunoService.findById(alunoId));
        avaliacao.setPeso(input.getPeso());
        avaliacao.setAltura(input.getAltura());

        return convert.toDto(service.save(avaliacao));
    }

    @GetMapping
    public List<AvaliacaoDto> findByid(@PathVariable Long alunoId) {
        return convert.toListDto(alunoService.findById(alunoId).getAvaliacoes());
    }

    @GetMapping("/{avaliacaoId}")
    public AvaliacaoDto findByIds(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        return convert.toDto(
                service.findByIds(alunoService.findById(alunoId).getId(), avaliacaoId));
    }

    @DeleteMapping("/{avaliacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        service.delete(alunoService.findById(alunoId).getId(), avaliacaoId);
    }
}
