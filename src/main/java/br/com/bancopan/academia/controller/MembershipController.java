package br.com.bancopan.academia.controller;

import br.com.bancopan.academia.controller.converter.MembershipConverter;
import br.com.bancopan.academia.model.domain.MembershipEntity;
import br.com.bancopan.academia.model.dto.MembershipDto;
import br.com.bancopan.academia.service.MemberService;
import br.com.bancopan.academia.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MembershipController {

    @Autowired
    private MembershipService service;

    @Autowired
    private MemberService alunoService;

    @Autowired
    private MembershipConverter convert;

    @GetMapping
    public List<MembershipDto> findAll() {
        return convert.toListDto(service.findAll());
    }

    @GetMapping("/{data1}/{data2}")
    public List<MembershipDto> findByDate(@PathVariable LocalDate data1, @PathVariable LocalDate data2) {
        return convert.toListDto(service.buscarEntreDatas(data1.atStartOfDay(), data2.atStartOfDay()));
    }

    @GetMapping("/aluno/{alunoId}")
    public List<MembershipDto> findByAluno(@PathVariable Long alunoId) {
        var aluno = alunoService.findById(alunoId);
        return convert.toListDto(service.findByAlunoId(aluno.getId()));
    }

    @GetMapping("/{id}")
    public MembershipDto findById(@PathVariable Long id) {
        return convert.toDto(service.findById(id));
    }

    @PostMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MembershipDto matricular(@PathVariable Long alunoId) {
        var matricula = new MembershipEntity();
        matricula.setAluno(alunoService.findById(alunoId));

        return convert.toDto(service.save(matricula));
    }

    @PutMapping("/{alunoId}")
    public MembershipDto desmatricular(@PathVariable Long alunoId) {
        var aluno = alunoService.findById(alunoId);
        return convert.toDto(service.delete(aluno.getId()));
    }
}
