package br.com.adaca.controller;

import br.com.adaca.dto.AutistaDTO;
import br.com.adaca.service.AutistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Autistas")
public class AutistaController {

    @Autowired
    private AutistaService autistaService;

    @GetMapping("/listarAutistas")
    public ResponseEntity<List<AutistaDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(autistaService.listar());
    }

    /*
        @GetMapping("/listarNomesIdAutistas")
        public  ResponseEntity<List<AutistaDTO>> listarNomesId() {
            return ResponseEntity.status(HttpStatus.OK).body(autistaService.listarNomesId());
        }
    */

    @GetMapping("/selecionarAutista/{autistaId}")
    public ResponseEntity<AutistaDTO> selecionar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(autistaService.selecionar(autistaId));
    }

    @PostMapping("/salvarAutista")
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutistaDTO autista) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autistaService.salvar(autista).getId()).toUri()).build();
    }

    @PutMapping("/alterarAutista")
    public ResponseEntity<AutistaDTO> alterar(@RequestBody @Valid AutistaDTO autista) {
        return ResponseEntity.status(HttpStatus.OK).body(autistaService.alterar(autista));
    }

    @DeleteMapping("/removerAutista/{autistaId}")
    public ResponseEntity<Void> remover(@PathVariable("autistaId") Integer autistaId) {
        autistaService.remover(autistaId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerAutista")
    public ResponseEntity<Void> remover(@RequestBody @Valid AutistaDTO autista) {
        autistaService.remover(autista);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}