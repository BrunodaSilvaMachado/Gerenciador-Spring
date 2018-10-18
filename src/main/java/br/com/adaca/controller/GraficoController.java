package br.com.adaca.controller;

import br.com.adaca.model.Grafico;
import br.com.adaca.service.GraficoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Graficos")
public class GraficoController {

    @Autowired
    private GraficoService graficoService;

    @GetMapping("/listarGraficos")
    public ResponseEntity<List<Grafico>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.listar());
    }

    @GetMapping("/listarGraficos/autistaId")
    public ResponseEntity<List<Grafico>> listar(@PathVariable("autistaId")Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.listar(autistaId));
    }

    @GetMapping("/selecionarGrafico/{graficoId}")
    public ResponseEntity<Grafico> selecionar(@PathVariable("graficoId") Integer graficoId) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.selecionar(graficoId));
    }

    @PostMapping("/salvarGrafico")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Grafico grafico) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(graficoService.salvar(grafico).getId()).toUri()).build();
    }

    @PutMapping("/alterarGrafico")
    public ResponseEntity<Grafico> alterar(@RequestBody @Valid Grafico grafico) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.alterar(grafico));
    }

    @DeleteMapping("/removerGrafico/{graficoId}")
    public ResponseEntity<Void> remover(@PathVariable("graficoId") Integer graficoId) {
        graficoService.remover(graficoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerGrafico")
    public ResponseEntity<Void> remover(@RequestBody @Valid Grafico grafico) {
        graficoService.remover(grafico);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
