package br.com.adaca.controller;

import br.com.adaca.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adaca.service.BaseService;
import br.com.adaca.dto.InterfaceDTO;

import javax.validation.Valid;

public abstract class DefaultAbstractController<TBaseDTO extends InterfaceDTO> {

    @Autowired
    private BaseService<TBaseDTO> service;

    /*public DefaultAbstractController(BaseService<TBaseDTO> baseService) {
        this.service = baseService;
    }*/

    @GetMapping()
    public ResponseEntity<List<TBaseDTO>> listar() {
        try
        {
            service.listar();
            return ResponseEntity.status(HttpStatus.OK).body(service.listar());
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TBaseDTO> selecionar(@PathVariable("id") Integer id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.selecionar(id));
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid TBaseDTO baseDTO) {
        try{
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.salvar(baseDTO).getId()).toUri()).build();
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<TBaseDTO> alterar(@RequestBody @Valid TBaseDTO baseDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(baseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Integer id) {
        service.remover(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid TBaseDTO baseDTO) {
        service.remover(baseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}