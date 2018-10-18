package br.com.adaca.controller;

import br.com.adaca.model.Relatorio;
import br.com.adaca.service.RelatorioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/listarRelatorios")
    public ResponseEntity<List<Relatorio>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.listar());
    }

    @GetMapping("/listarRelatorios/{autistaId}")
    public ResponseEntity<List<Relatorio>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.listar(autistaId));
    }

    @GetMapping("/selecionarRelatorio/{relatorioId}")
    public ResponseEntity<Relatorio> selecionar(@PathVariable("relatorioId") Integer relatorioId) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.selecionar(relatorioId));
    }

    @PostMapping("/salvarRelatorio")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Relatorio relatorio) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(relatorioService.salvar(relatorio).getId()).toUri()).build();
    }

    @PutMapping("/alterarRelatorio")
    public ResponseEntity<Relatorio> alterar(@RequestBody @Valid Relatorio relatorio) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.alterar(relatorio));
    }

    @DeleteMapping("/removerRelatorio/{relatorioId}")
    public ResponseEntity<Void> remover(@PathVariable("relatorioId") Integer relatorioId) {
        relatorioService.remover(relatorioId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerRelatorio")
    public ResponseEntity<Void> remover(@RequestBody @Valid Relatorio relatorio) {
        relatorioService.remover(relatorio);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
