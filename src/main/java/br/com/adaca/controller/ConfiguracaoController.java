package br.com.adaca.controller;

import br.com.adaca.model.Configuracao;
import br.com.adaca.service.ConfiguracaoService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Configuracoes")
public class ConfiguracaoController extends View<Configuracao> {

    @Autowired
    private ConfiguracaoService configuracaoService;

    public ConfiguracaoController() {
        super("Gerenciador/configuracoes", "Gerenciador/configuracaoAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Configuracao>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.listar());
    }

    @GetMapping("/{autistaId}/{tutorId}")
    public ResponseEntity<List<Configuracao>> listar(@PathVariable("autistaId") Integer autistaId, @PathVariable("tutorId") Integer tutorId) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.listarConfigAutistaTutor(autistaId, tutorId));
    }

    @GetMapping("/{configuracaoId}")
    public ResponseEntity<Configuracao> selecionar(@PathVariable("configuracaoId") Integer configuracaoId) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.selecionar(configuracaoId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Configuracao configuracao) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(configuracaoService.salvar(configuracao).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Configuracao> alterar(@RequestBody @Valid Configuracao configuracao) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.alterar(configuracao));
    }

    @DeleteMapping("/{configuracaoId}")
    public ResponseEntity<Void> remover(@PathVariable("configuracaoId") Integer configuracaoId) {
        configuracaoService.remover(configuracaoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Configuracao configuracao) {
        configuracaoService.remover(configuracao);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
