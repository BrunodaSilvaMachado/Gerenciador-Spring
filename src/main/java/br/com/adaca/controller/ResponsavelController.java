package br.com.adaca.controller;

import br.com.adaca.model.Responsavel;
import br.com.adaca.service.ResponsavelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping("/listarResponsaveis")
    public ResponseEntity<List<Responsavel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.listar());
    }

    @GetMapping("/listarResponsaveis/{autistaId}")
    public ResponseEntity<List<Responsavel>> listar(@PathVariable("autistalId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.listar(autistaId));
    }

    @GetMapping("/selecionarResponsavel/{responsavelId}")
    public ResponseEntity<Responsavel> selecionar(@PathVariable("responsavelId") Integer responsavelId) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.selecionar(responsavelId));
    }

    @PostMapping("/salvarResponsavel")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Responsavel responsavel) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responsavelService.salvar(responsavel).getId()).toUri()).build();
    }

    @PutMapping("/alterarResponsavel")
    public ResponseEntity<Responsavel> alterar(@RequestBody @Valid Responsavel responsavel) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.alterar(responsavel));
    }

    @DeleteMapping("/removerResponsavel/{responsavelId}")
    public ResponseEntity<Void> remover(@PathVariable("responsavelId") Integer responsavelId) {
        responsavelService.remover(responsavelId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerResponsavel")
    public ResponseEntity<Void> remover(@RequestBody @Valid Responsavel responsavel) {
        responsavelService.remover(responsavel);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
