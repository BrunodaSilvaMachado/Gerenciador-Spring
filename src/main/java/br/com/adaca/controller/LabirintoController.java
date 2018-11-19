package br.com.adaca.controller;

import br.com.adaca.model.Labirinto;
import br.com.adaca.service.LabirintoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Labirintos")
public class LabirintoController {

    @Autowired
    private LabirintoService labirintoService;

    @GetMapping("/listarLabirintos")
    public ResponseEntity<List<Labirinto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.listar());
    }

    @GetMapping("/selecionarLabirinto/{labirintoId}")
    public ResponseEntity<Labirinto> selecionar(@PathVariable("labirintoId") Integer labirintoId) {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.selecionar(labirintoId));
    }

    @PostMapping("/salvarLabirinto")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Labirinto labirinto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(labirintoService.salvar(labirinto).getId()).toUri()).build();
    }

    @PutMapping("/alterarLabirinto")
    public ResponseEntity<Labirinto> alterar(@RequestBody @Valid Labirinto labirinto) {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.alterar(labirinto));
    }

    @DeleteMapping("/removerLabirinto/{labirintoId}")
    public ResponseEntity<Void> remover(@PathVariable("labirintoId") Integer labirintoId) {
        labirintoService.remover(labirintoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerLabirinto")
    public ResponseEntity<Void> remover(@RequestBody Labirinto labirinto) {
        labirintoService.remover(labirinto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
