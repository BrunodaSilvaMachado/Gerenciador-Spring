package br.com.adaca.controller;

import br.com.adaca.model.Administrador;
import br.com.adaca.service.AdministradorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    /**
     * Lista todos os administradores cadastrados no banco de dados
     *
     * @return Lista com todos os administradores cadastrados
     */
    @GetMapping(value = "/listarAdministradores")
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.listar());
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado
     *
     * @param administradorId ID de administrador já existente no banco de dados
     * @return Objeto do administrador encontrado
     */
    @GetMapping(value = "/selecionarAdministrador/{administradorId}")
    public ResponseEntity<Administrador> selecionar(@PathVariable("administradorId") Integer administradorId) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.selecionar(administradorId));
    }

    /**
     * Salva o cadastro do administrador no banco de dados
     *
     * @param administrador Objeto preenchido do cadastro a ser gravado
     * @return Erro ou Sucesso ao salvar
     */
    @PostMapping(value = "/salvarAdministrador")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(administradorService.salvar(administrador).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do administrador no bando de dados
     *
     * @param administrador Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping(value = "/alterarAdministrador")
    public ResponseEntity<Administrador> alterar(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.alterar(administrador));
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado e remove-o do banco
     * de dados
     *
     * @param administradorId ID de administrador já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping(value = "/removerAdministrador/{administradorId}")
    public ResponseEntity<Administrador> remover(@PathVariable("administradorId") Integer administradorId) {
        administradorService.remover(administradorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro do administrador do banco de dados
     *
     * @param administrador Objeto preenchido do cadastro já existente no banco
     * de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping(value = "/removerAdministrador")
    public ResponseEntity<Administrador> remover(@RequestBody @Valid Administrador administrador) {
        administradorService.remover(administrador);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
    @GetMapping(value = "/login/{usuario}/{senha}")
    public Administrador login(@PathVariable("usuario") String usuario,@PathVariable("senha") String senha) {
        return administradorService.login(usuario,senha);
    }
    */
}

/* https://lh3.googleusercontent.com/-cpYCrP36Nc8/VsWO7emBMRI/AAAAAAAAAyU/0rv7Lnl0aNI/s1600-h/image%25255B5%25255D.png */
