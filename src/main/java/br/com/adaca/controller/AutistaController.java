package br.com.adaca.controller;

import br.com.adaca.model.Autista;

import br.com.adaca.service.AutistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Autistas")
public class AutistaController {

    private AutistaService autistaService;

    /**
     * Lista todas as crianças cadastradas no banco de dados
     *
     * @return Lista com todas as crianças cadastradas
     */
    @GetMapping(value = "/listarAutistas")
    public ResponseEntity<List<Autista>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(autistaService.listar());
    }

    /**
     * Lista id e nome de todas as crianças cadastradas no banco de dados
     *
     * @return Lista de ids e nomes de todas as crianças cadastradas
     */
    @GetMapping(value = "/listarNomesIdAutistas")
    public  ResponseEntity<List<Autista>> listarNomesId() {
        return ResponseEntity.status(HttpStatus.OK).body(autistaService.listarNomesId());
    }

    /**
     * Efetua uma busca por ID da criança cadastrada
     *
     * @param autistaId ID da criança já existente no banco de dados
     * @return Objeto da criança encontrada
     */
    @GetMapping(value = "/selecionarAutista/{autistaId}")
    public ResponseEntity<Autista> selecionar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(autistaService.selecionar(autistaId));
    }

    /**
     * Salva o cadastro da criança no banco de dados
     *
     * @param autista Objeto preenchido do cadastro a ser gravado
     * @return Erro ou sucesso ao salvar
     */
    @PostMapping(value = "/salvarAutista")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Autista autista) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autistaService.salvar(autista).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro da criança no bando de dados
     *
     * @param autista Objeto preenchido com os dados já alterados
     * @return Erro ou sucesso ao alterar
     */
    @PutMapping(value = "/alterarAutista")
    public ResponseEntity<Autista> alterar(@RequestBody @Valid Autista autista) {
        return  ResponseEntity.status(HttpStatus.OK).body(autistaService.alterar(autista));
    }

    /**
     * Efetua uma busca por ID da criança cadastrada e remove-a do banco de
     * dados
     *
     * @param autistaId ID da criança já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping(value = "/removerAutista/{autistaId}")
    public ResponseEntity<Void> remover(@PathVariable("autistaId") Integer autistaId) {
        autistaService.remover(autistaId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro da criança do banco de dados
     *
     * @param autista Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping(value = "/removerAutista")
    public ResponseEntity<Void> remover(@RequestBody @Valid Autista autista) {
        autistaService.remover(autista);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}