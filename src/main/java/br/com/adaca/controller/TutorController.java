package br.com.adaca.controller;

import br.com.adaca.model.Tutor;
import br.com.adaca.service.TutorService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Tutores")
public class TutorController extends View<Tutor> {

    @Autowired
    private TutorService tutorService;

    public TutorController() {
        super("Gerenciador/tutores", "Gerenciador/tutorAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Tutor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.listar());
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<Tutor> selecionar(@PathVariable("tutorId") Integer tutorId) {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.selecionar(tutorId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Tutor tutor) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tutorService.salvar(tutor).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Tutor> alterar(@RequestBody @Valid Tutor tutor) {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.alterar(tutor));
    }

    @DeleteMapping("/{tutorId}")
    public ResponseEntity<Void> remover(@PathVariable("tutorId") Integer tutorId) {
        tutorService.remover(tutorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Tutor tutor) {
        tutorService.remover(tutor);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
