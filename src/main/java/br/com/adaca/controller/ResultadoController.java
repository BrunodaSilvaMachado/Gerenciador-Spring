package br.com.adaca.controller;

import br.com.adaca.model.Resultado;
import br.com.adaca.service.ResultadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping("/listarResultados")
    public ResponseEntity<List<Resultado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.listar());
    }

    @GetMapping("/listarResultados/{sessaoId}")
    public ResponseEntity<List<Resultado>> listar(@PathVariable("autistalId") Integer sessaoId) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.listar(sessaoId));
    }

    @GetMapping("/selecionarResultado/{resultadoId}")
    public ResponseEntity<Resultado> selecionar(@PathVariable("resultadoId") Integer resultadoId) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.selecionar(resultadoId));
    }

    @PostMapping("/salvarResultado")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Resultado resultado) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resultadoService.salvar(resultado).getId()).toUri()).build();
    }

    @PutMapping("/alterarResultado")
    public ResponseEntity<Resultado> alterar(@RequestBody @Valid Resultado resultado) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.alterar(resultado));
    }

    @DeleteMapping("/removerResultado/{resultadoId}")
    public ResponseEntity<Void> remover(@PathVariable("resultadoId") Integer resultadoId) {
        resultadoService.remover(resultadoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerResultado")
    public ResponseEntity<Void> remover(@RequestBody @Valid Resultado resultado) {
        resultadoService.remover(resultado);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
