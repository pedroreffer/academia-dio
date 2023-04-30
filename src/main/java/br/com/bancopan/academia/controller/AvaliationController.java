package br.com.bancopan.academia.controller;

import br.com.bancopan.academia.controller.converter.AvaliationConverter;
import br.com.bancopan.academia.model.domain.AvaliationEntity;
import br.com.bancopan.academia.model.dto.AvaliationDto;
import br.com.bancopan.academia.service.MemberService;
import br.com.bancopan.academia.service.AvaliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos/{alunoId}/avaliacoes")
public class AvaliationController {

    @Autowired
    private AvaliationService service;

    @Autowired
    private MemberService alunoService;

    @Autowired
    private AvaliationConverter convert;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliationDto create(@PathVariable Long alunoId, @RequestBody AvaliationDto input) {
        var avaliacao = new AvaliationEntity();

        avaliacao.setAluno(alunoService.findById(alunoId));
        avaliacao.setPeso(input.getPeso());
        avaliacao.setAltura(input.getAltura());

        return convert.toDto(service.save(avaliacao));
    }

    @GetMapping
    public List<AvaliationDto> findByid(@PathVariable Long alunoId) {
        return convert.toListDto(alunoService.findById(alunoId).getAvaliacoes());
    }

    @GetMapping("/{avaliacaoId}")
    public AvaliationDto findByIds(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        return convert.toDto(
                service.findByIds(alunoService.findById(alunoId).getId(), avaliacaoId));
    }

    @DeleteMapping("/{avaliacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        service.delete(alunoService.findById(alunoId).getId(), avaliacaoId);
    }
}
