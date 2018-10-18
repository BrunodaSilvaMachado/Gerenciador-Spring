package br.com.adaca.controller;

import br.com.adaca.model.Tutor;
import br.com.adaca.service.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping("/listarTutores")
    public ResponseEntity<List<Tutor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.listar());
    }

    @GetMapping("/selecionarTutor/{tutorId}")
    public ResponseEntity<Tutor> selecionar(@PathVariable("tutorId") Integer tutorId) {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.selecionar(tutorId));
    }

    @PostMapping("/salvarTutor")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Tutor tutor) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tutorService.salvar(tutor).getId()).toUri()).build();
    }

    @PutMapping("/alterarTutor")
    public ResponseEntity<Tutor> alterar(@RequestBody @Valid Tutor tutor) {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.alterar(tutor));
    }

    @DeleteMapping("/removerTutor/{tutorId}")
    public ResponseEntity<Void> remover(@PathVariable("tutorId") Integer tutorId) {
        tutorService.remover(tutorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerTutor")
    public ResponseEntity<Void> remover(@RequestBody @Valid Tutor tutor) {
        tutorService.remover(tutor);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
    @RequestMapping(value = "/login/{usuario}/{senha}", method = RequestMethod.GET)
    public Tutor login(@PathVariable("usuario") String usuario,@PathVariable("senha")  String senha) {
        return tutorRepository.login(usuario,senha);
    }
    */
}
