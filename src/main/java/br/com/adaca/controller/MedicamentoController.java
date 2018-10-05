package br.com.adaca.controller;

import br.com.adaca.model.Medicamento;
import br.com.adaca.model.Autista;
import br.com.adaca.service.MedicamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    /**
     * Lista todos os medicamentos cadastrados no banco de dados
     *
     * @return Lista com todos os medicamentos cadastrados
     */
    @GetMapping("/listarMedicamentos")
    public ResponseEntity<List<Medicamento>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.listar());
    }

    /**
     * Lista todos os medicamentos cadastrados no banco de dados filtrados pela
     * criança
     *
     * @param autistaId ID da criança para filtro da pesquisa
     * @return Lista com todos os medicamentos cadastrados
     */
    @GetMapping("/listarMedicamentos/{autistaId}")
    public ResponseEntity<List<Medicamento>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.listar(autistaId));
    }

    /**
     * Lista todas as crianças que foram cadastradas como usando medicamentos
     *
     * @return Lista com todas as crianças que tem medicamento cadastrado
     */
    @GetMapping("/listarAutistas")
    public ResponseEntity<List<Autista>> listarAutistas() {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.listarAutistas());
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado
     *
     * @param medicamentoId ID do medicamento já existente no banco de dados
     * @return Objeto do medicamento encontrado
     */
    @GetMapping("/selecionarMedicamento/{medicamentoId}")
    public ResponseEntity<Medicamento> selecionar(@PathVariable("medicamentoId") Integer medicamentoId) {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.selecionar(medicamentoId));
    }

    /**
     * Salva o medicamento da criança no banco de dados
     *
     * @param medicamento Objeto preenchido do medicamento a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarMedicamento")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Medicamento medicamento) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicamentoService.salvar(medicamento).getId()).toUri()).build();
    }

    /**
     * Salva uma lista de medicamentos da criança no banco de dados
     *
     * @param medicamentos Lista com os objetos preenchidos a serem gravados
     * @return Erro ou sucesso ao salvar
     */
    @PostMapping("/salvarMedicamentos")
    public ResponseEntity<Void> salvar(@RequestBody @Valid List medicamentos) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicamentoService.salvar(medicamentos)).toUri()).build();
    }

    //Conferir salvar lista

    /**
     * Altera o cadastro do medicamento no bando de dados
     *
     * @param medicamento Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarMedicamento")
    public ResponseEntity<Medicamento> alterar(@RequestBody @Valid Medicamento medicamento) {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.alterar(medicamento));
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado e remove-o do banco de
     * dados
     *
     * @param medicamentoId ID do medicamento já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerMedicamento/{medicamentoId}")
    public ResponseEntity<Void> remover(@PathVariable("medicamentoId") Integer medicamentoId) {
        medicamentoService.remover(medicamentoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro do medicamento do banco de dados
     *
     * @param medicamento Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerMedicamento")
    public ResponseEntity<Void> remover(@RequestBody @Valid Medicamento medicamento) {
        medicamentoService.remover(medicamento);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
