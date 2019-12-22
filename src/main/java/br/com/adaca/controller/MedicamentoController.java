package br.com.adaca.controller;

import br.com.adaca.model.Autista;
import br.com.adaca.model.Medicamento;
import br.com.adaca.service.MedicamentoService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Medicamentos")
public class MedicamentoController extends View<Medicamento> {

    @Autowired
    private MedicamentoService medicamentoService;

    public MedicamentoController() {
        super("medicamentos", "medicamentoAdd");
    }

    @GetMapping()
    public ResponseEntity<List<Medicamento>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.listar());
    }

    // ta no padrão REST ?
    @GetMapping("/autista/{autistaId}")
    public ResponseEntity<List<Medicamento>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.listar(autistaId));
    }

    @GetMapping("/autista")
    public ResponseEntity<List<Autista>> listarAutistas() {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.listarAutistas());
    }

    @GetMapping("/{medicamentoId}")
    public ResponseEntity<Medicamento> selecionar(@PathVariable("medicamentoId") Integer medicamentoId) {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.selecionar(medicamentoId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Medicamento medicamento) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicamentoService.salvar(medicamento).getId()).toUri()).build();
    }

    @PostMapping("/salvarMedicamentos")
    public ResponseEntity<Void> salvar(@RequestBody @Valid List medicamentos) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicamentoService.salvar(medicamentos)).toUri()).build();
    }

    //Conferir salvar lista,

    @PutMapping()
    public ResponseEntity<Medicamento> alterar(@RequestBody @Valid Medicamento medicamento) {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.alterar(medicamento));
    }

    @DeleteMapping("/{medicamentoId}")
    public ResponseEntity<Void> remover(@PathVariable("medicamentoId") Integer medicamentoId) {
        medicamentoService.remover(medicamentoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Medicamento medicamento) {
        medicamentoService.remover(medicamento);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
