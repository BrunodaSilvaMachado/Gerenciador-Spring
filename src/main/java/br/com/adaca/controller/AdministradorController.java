package br.com.adaca.controller;

import br.com.adaca.exception.NotFoundException;
import br.com.adaca.service.AdministradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import br.com.adaca.dto.AdministradorDTO;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Administradores")
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @GetMapping()
    public ResponseEntity<List<AdministradorDTO>> listar() {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.listar());
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{administradorId}")
    public ResponseEntity<AdministradorDTO> selecionar(@PathVariable("administradorId") Integer administradorId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.selecionar(administradorId));
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid AdministradorDTO administrador) {
        try{
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(administradorService.salvar(administrador).getId()).toUri()).build();
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping()
    public ResponseEntity<AdministradorDTO> alterar(@RequestBody @Valid AdministradorDTO administrador) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.alterar(administrador));
    }
    @DeleteMapping("/{administradorId}")
    public ResponseEntity<Void> remover(@PathVariable("administradorId") Integer administradorId) {
        administradorService.remover(administradorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid AdministradorDTO administrador) {
        administradorService.remover(administrador);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}

/* https://lh3.googleusercontent.com/-cpYCrP36Nc8/VsWO7emBMRI/AAAAAAAAAyU/0rv7Lnl0aNI/s1600-h/image%25255B5%25255D.png
* http://shengwangi.blogspot.com/2016/02/response-for-get-post-put-delete-in-rest.html*/
