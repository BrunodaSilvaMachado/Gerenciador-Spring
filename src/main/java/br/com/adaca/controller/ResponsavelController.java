package br.com.adaca.controller;

import br.com.adaca.model.Responsavel;
import br.com.adaca.service.ResponsavelService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Responsaveis")
public class ResponsavelController extends View<Responsavel> {

    @Autowired
    private ResponsavelService responsavelService;

    public ResponsavelController() {
        super("Gerenciador/responsaveis", "Gerenciador/responsavelAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Responsavel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.listar());
    }

    // ta no padrão REST ?
    @GetMapping("/autista/{autistaId}")
    public ResponseEntity<List<Responsavel>> listar(@PathVariable("autistalId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.listar(autistaId));
    }

    @GetMapping("/{responsavelId}")
    public ResponseEntity<Responsavel> selecionar(@PathVariable("responsavelId") Integer responsavelId) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.selecionar(responsavelId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Responsavel responsavel) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responsavelService.salvar(responsavel).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Responsavel> alterar(@RequestBody @Valid Responsavel responsavel) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.alterar(responsavel));
    }

    @DeleteMapping("/{responsavelId}")
    public ResponseEntity<Void> remover(@PathVariable("responsavelId") Integer responsavelId) {
        responsavelService.remover(responsavelId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Responsavel responsavel) {
        responsavelService.remover(responsavel);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
