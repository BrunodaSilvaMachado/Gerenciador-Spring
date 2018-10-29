package br.com.adaca.controller;

import br.com.adaca.mapper.AdministradorMapper;
import br.com.adaca.model.Administrador;
import br.com.adaca.dto.AdministradorDTO;
import br.com.adaca.service.AdministradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/listarAdministradores")
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.listar());
    }

    @GetMapping("/selecionarAdministrador/{administradorId}")
    public ResponseEntity<Administrador> selecionar(@PathVariable("administradorId") Integer administradorId) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.selecionar(administradorId));
    }

    @PostMapping("/salvarAdministrador")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(administradorService.salvar(administrador).getId()).toUri()).build();
    }

    @PutMapping("/alterarAdministrador")
    public ResponseEntity<Administrador> alterar(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.alterar(administrador));
    }
    @DeleteMapping("/removerAdministrador/{administradorId}")
    public ResponseEntity<Void> remover(@PathVariable("administradorId") Integer administradorId) {
        administradorService.remover(administradorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerAdministrador")
    public ResponseEntity<Void> remover(@RequestBody @Valid Administrador administrador) {
        administradorService.remover(administrador);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
    @GetMapping("/login/{usuario}/{senha}")
    public Administrador login(@PathVariable("usuario") String usuario,@PathVariable("senha") String senha) {
        return administradorService.login(usuario,senha);
    }
    */
}

/* https://lh3.googleusercontent.com/-cpYCrP36Nc8/VsWO7emBMRI/AAAAAAAAAyU/0rv7Lnl0aNI/s1600-h/image%25255B5%25255D.png */
