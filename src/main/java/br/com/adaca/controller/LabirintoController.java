package br.com.adaca.controller;

import br.com.adaca.model.Labirinto;
import br.com.adaca.service.LabirintoService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Labirintos")
public class LabirintoController extends View<Labirinto> {

    @Autowired
    private LabirintoService labirintoService;

    public LabirintoController() {
        super("Gerenciador/labirintos", "Gerenciador/labirintoAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Labirinto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.listar());
    }

    @GetMapping("/{labirintoId}")
    public ResponseEntity<Labirinto> selecionar(@PathVariable("labirintoId") Integer labirintoId) {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.selecionar(labirintoId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Labirinto labirinto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(labirintoService.salvar(labirinto).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Labirinto> alterar(@RequestBody @Valid Labirinto labirinto) {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.alterar(labirinto));
    }

    @DeleteMapping("/{labirintoId}")
    public ResponseEntity<Void> remover(@PathVariable("labirintoId") Integer labirintoId) {
        labirintoService.remover(labirintoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody Labirinto labirinto) {
        labirintoService.remover(labirinto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
