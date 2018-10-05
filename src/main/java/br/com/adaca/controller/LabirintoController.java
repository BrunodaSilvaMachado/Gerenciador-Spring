package br.com.adaca.controller;

import br.com.adaca.model.Labirinto;
import br.com.adaca.service.LabirintoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Labirintos")
public class LabirintoController {

    @Autowired
    private LabirintoService labirintoService;

    /**
     * Lista todos os labirintos cadastrados no banco de dados
     *
     * @return Lista com todos os labirintos cadastrados
     */
    @GetMapping("/listarLabirintos")
    public ResponseEntity<List<Labirinto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.listar());
    }

    /**
     * Efetua uma busca por ID do labirinto cadastrado
     *
     * @param labirintoId ID do labirinto já existente no banco de dados
     * @return Objeto do labirinto encontrado
     */
    @GetMapping("/selecionarLabirinto/{labirintoId}")
    public ResponseEntity<Labirinto> selecionar(@PathVariable("labirintoId") Integer labirintoId) {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.selecionar(labirintoId));
    }

    /**
     * Salva o labirinto resolvido pela criança no banco de dados
     *
     * @param labirinto Objeto preenchido do labirinto a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarLabirinto")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Labirinto labirinto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(labirintoService.salvar(labirinto).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do labirinto no bando de dados
     *
     * @param labirinto Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarLabirinto")
    public ResponseEntity<Labirinto> alterar(@RequestBody @Valid Labirinto labirinto) {
        return ResponseEntity.status(HttpStatus.OK).body(labirintoService.alterar(labirinto));
    }

    /**
     * Efetua uma busca por ID do labirinto cadastrado e remove-o do banco de dados
     *
     * @param labirintoId ID do labirinto já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerLabirinto/{labirintoId}")
    public ResponseEntity<Void> remover(@PathVariable("labirintoId") Integer labirintoId) {
        labirintoService.remover(labirintoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro do labirinto do banco de dados
     *
     * @param labirinto Objeto preenchido do cadastro já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerLabirinto")
    public ResponseEntity<Void> remover(@RequestBody Labirinto labirinto) {
        labirintoService.remover(labirinto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
