package br.com.adaca.controller;

import br.com.adaca.dto.AtividadeDTO;
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

    @GetMapping()
    public ResponseEntity<List<AtividadeDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.listar());
    }

    @GetMapping("/{atividadeId}")
    public ResponseEntity<AtividadeDTO> selecionar(@PathVariable("atividadeId") Integer atividadeId) {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.selecionar(atividadeId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid AtividadeDTO atividade) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atividadeService.salvar(atividade).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<AtividadeDTO> alterar(@RequestBody @Valid AtividadeDTO atividade) {
        return  ResponseEntity.status(HttpStatus.OK).body(atividadeService.alterar(atividade));
    }

    @DeleteMapping("/{atividadeId}")
    public ResponseEntity<Void> remover(@PathVariable("atividadeId")Integer atividadeId) {
        atividadeService.remover(atividadeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid AtividadeDTO atividade) {
        atividadeService.remover(atividade);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
