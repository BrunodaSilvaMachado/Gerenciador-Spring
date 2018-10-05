package br.com.adaca.controller;

import br.com.adaca.model.Atividade;
import br.com.adaca.service.AtividadeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Atividades")
public class AtividadeController {

    private AtividadeService atividadeService;

    /**
     * Lista todas as atividades cadastradas no banco de dados
     *
     * @return Lista com todos as atividades cadastradas
     */
    @GetMapping("/listarAtividades")
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.listar());
    }

    /**
     * Efetua uma busca por ID de atividade cadastrada
     *
     * @param atividadeId ID de atividade já existente no banco de dados
     * @return Objeto da atividade encontrada
     */
    @GetMapping("/selecionarAdministrador/{atividadeId}")
    public ResponseEntity<Atividade> selecionar(@PathVariable("atividadeId") Integer atividadeId) {
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.selecionar(atividadeId));
    }

    /**
     * Salva o cadastro da atividade no banco de dados
     *
     * @param atividade Objeto preenchido do cadastro a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarAtividade")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Atividade atividade) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atividadeService.salvar(atividade).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro da atividade no bando de dados
     *
     * @param atividade Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarAtividade")
    public ResponseEntity<Atividade> alterar(@RequestBody @Valid Atividade atividade) {
        return  ResponseEntity.status(HttpStatus.OK).body(atividadeService.alterar(atividade));
    }

    /**
     * Efetua uma busca por ID de atividade cadastrada e remove-a do banco de
     * dados
     *
     * @param atividadeId ID de atividade já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerAtividade/{atividadeId}")
    public ResponseEntity<Void> remover(Integer atividadeId) {
        atividadeService.remover(atividadeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro da atividade do banco de dados
     *
     * @param atividade Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerAtividade")
    public ResponseEntity<Void> remover(@RequestBody @Valid Atividade atividade) {
        atividadeService.remover(atividade);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
