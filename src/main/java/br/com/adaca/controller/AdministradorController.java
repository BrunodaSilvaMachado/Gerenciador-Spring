package br.com.adaca.controller;

import br.com.adaca.model.Administrador;
import br.com.adaca.service.AdministradorService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Administradores")
public class AdministradorController extends View<Administrador> {

    @Autowired
    private AdministradorService administradorService;

    public AdministradorController() {
        super("Gerenciador/administradores", "Gerenciador/administradorAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.listar());
    }

    @GetMapping("/{administradorId}")
    public ResponseEntity<Administrador> selecionar(@PathVariable("administradorId") Integer administradorId) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.selecionar(administradorId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(administradorService.salvar(administrador).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Administrador> alterar(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.alterar(administrador));
    }

    @DeleteMapping("/{administradorId}")
    public ResponseEntity<Void> remover(@PathVariable("administradorId") Integer administradorId) {
        administradorService.remover(administradorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Administrador administrador) {
        administradorService.remover(administrador);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

/* https://lh3.googleusercontent.com/-cpYCrP36Nc8/VsWO7emBMRI/AAAAAAAAAyU/0rv7Lnl0aNI/s1600-h/image%25255B5%25255D.png
 * http://shengwangi.blogspot.com/2016/02/response-for-get-post-put-delete-in-rest.html*/
