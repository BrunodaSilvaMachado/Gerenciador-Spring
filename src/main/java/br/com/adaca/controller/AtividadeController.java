package br.com.adaca.controller;

import br.com.adaca.model.Atividade;
import br.com.adaca.service.AtividadeService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Atividades")
public class AtividadeController extends View<Atividade> {
    @Autowired
    private AtividadeService atividadeService;

    public AtividadeController() {
        super("atividades", "atividadeAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.listar());
    }

    @GetMapping("/{atividadeId}")
    public ResponseEntity<Atividade> selecionar(@PathVariable("atividadeId") Integer atividadeId) {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.selecionar(atividadeId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Atividade atividade) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atividadeService.salvar(atividade).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Atividade> alterar(@RequestBody @Valid Atividade atividade) {
        return  ResponseEntity.status(HttpStatus.OK).body(atividadeService.alterar(atividade));
    }

    @DeleteMapping("/{atividadeId}")
    public ResponseEntity<Void> remover(@PathVariable("atividadeId")Integer atividadeId) {
        atividadeService.remover(atividadeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Atividade atividade) {
        atividadeService.remover(atividade);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
