package br.com.adaca.controller;

import br.com.adaca.model.Atividade;
import br.com.adaca.service.AtividadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping("/listarAtividades")
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.listar());
    }

    @GetMapping("/selecionarAdministrador/{atividadeId}")
    public ResponseEntity<Atividade> selecionar(@PathVariable("atividadeId") Integer atividadeId) {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.selecionar(atividadeId));
    }

    @PostMapping("/salvarAtividade")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Atividade atividade) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atividadeService.salvar(atividade).getId()).toUri()).build();
    }

    @PutMapping("/alterarAtividade")
    public ResponseEntity<Atividade> alterar(@RequestBody @Valid Atividade atividade) {
        return  ResponseEntity.status(HttpStatus.OK).body(atividadeService.alterar(atividade));
    }

    @DeleteMapping("/removerAtividade/{atividadeId}")
    public ResponseEntity<Void> remover(Integer atividadeId) {
        atividadeService.remover(atividadeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerAtividade")
    public ResponseEntity<Void> remover(@RequestBody @Valid Atividade atividade) {
        atividadeService.remover(atividade);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
