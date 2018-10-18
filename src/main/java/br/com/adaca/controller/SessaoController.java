package br.com.adaca.controller;

import br.com.adaca.model.Sessao;
import br.com.adaca.service.SessaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Sessoes")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @GetMapping("/listarSessoes")
    public ResponseEntity<List<Sessao>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.listar());
    }

    @GetMapping("/listarSessoes/{autistaId}")
    public ResponseEntity<List<Sessao>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.listar(autistaId));
    }

    @GetMapping("/selecionarSessao/{sessaoId}")
    public ResponseEntity<Sessao> selecionar(@PathVariable("sessaoId") Integer sessaoId) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.selecionar(sessaoId));
    }

    @PostMapping("/salvarSessao")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Sessao sessao) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sessaoService.salvar(sessao).getId()).toUri()).build();
    }

    @PutMapping("/alterarSessao")
    public ResponseEntity<Sessao> alterar(@RequestBody @Valid Sessao sessao) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.alterar(sessao));
    }

    @DeleteMapping("/removerSessao/{sessaoId}")
    public ResponseEntity<Void> remover(@PathVariable("sessaoId") Integer sessaoId) {
        sessaoService.remover(sessaoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping(value = "/removerSessao")
    public ResponseEntity<Void> remover(@RequestBody @Valid Sessao sessao) {
        sessaoService.remover(sessao);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
