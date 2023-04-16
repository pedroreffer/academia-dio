package br.com.bancopan.academia.controller;

import br.com.bancopan.academia.controller.converter.MatriculaConverter;
import br.com.bancopan.academia.model.domain.MatriculaEntity;
import br.com.bancopan.academia.model.dto.MatriculaDto;
import br.com.bancopan.academia.service.AlunoService;
import br.com.bancopan.academia.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MatriculaConverter convert;

    @GetMapping
    public List<MatriculaDto> findAll() {
        return convert.toListDto(service.findAll());
    }

    @GetMapping("/{data1}/{data2}")
    public List<MatriculaDto> findByDate(@PathVariable LocalDate data1, @PathVariable LocalDate data2) {
        return convert.toListDto(service.buscarEntreDatas(data1.atStartOfDay(), data2.atStartOfDay()));
    }

    @GetMapping("/aluno/{alunoId}")
    public List<MatriculaDto> findByAluno(@PathVariable Long alunoId) {
        var aluno = alunoService.findById(alunoId);
        return convert.toListDto(service.findByAlunoId(aluno.getId()));
    }

    @GetMapping("/{id}")
    public MatriculaDto findById(@PathVariable Long id) {
        return convert.toDto(service.findById(id));
    }

    @PostMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaDto matricular(@PathVariable Long alunoId) {
        var matricula = new MatriculaEntity();
        matricula.setAluno(alunoService.findById(alunoId));

        return convert.toDto(service.save(matricula));
    }

    @PutMapping("/{alunoId}")
    public MatriculaDto desmatricular(@PathVariable Long alunoId) {
        var aluno = alunoService.findById(alunoId);
        return convert.toDto(service.delete(aluno.getId()));
    }
}
