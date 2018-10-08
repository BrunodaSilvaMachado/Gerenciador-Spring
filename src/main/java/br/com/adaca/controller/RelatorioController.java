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

    /**
     * Lista todos os sessões cadastradas no banco de dados
     *
     * @return Lista com todos os relatorios cadastrados
     */
    @GetMapping("/listarRelatorios")
    public ResponseEntity<List<Relatorio>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.listar());
    }

    /**
     * Lista todos os relatórios cadastrados no banco de dados filtradas pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os relatorios cadastrados
     */
    @GetMapping("/listarRelatorios/{autistaId}")
    public ResponseEntity<List<Relatorio>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.listar(autistaId));
    }

    /**
     * Efetua uma busca por ID do relatorio cadastrado
     *
     * @param relatorioId ID do relatorio já existente no banco de dados
     * @return Objeto do relatorio encontrado
     */
    @GetMapping("/selecionarRelatorio/{relatorioId}")
    public ResponseEntity<Relatorio> selecionar(@PathVariable("relatorioId") Integer relatorioId) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.selecionar(relatorioId));
    }

    /**
     * Salva o relatorio da criança no banco de dados
     *
     * @param relatorio Objeto preenchido do relatorio a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarRelatorio")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Relatorio relatorio) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(relatorioService.salvar(relatorio).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do relatorio no bando de dados
     *
     * @param relatorio Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarRelatorio")
    public ResponseEntity<Relatorio> alterar(@RequestBody @Valid Relatorio relatorio) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.alterar(relatorio));
    }

    /**
     * Efetua uma busca por ID do relatorio cadastrada e remove-a do banco de dados
     *
     * @param relatorioId ID do relatorio já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerRelatorio/{relatorioId}")
    public ResponseEntity<Void> remover(@PathVariable("relatorioId") Integer relatorioId) {
        relatorioService.remover(relatorioId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o relatório do banco de dados
     *
     * @param relatorio Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerRelatorio")
    public ResponseEntity<Void> remover(@RequestBody @Valid Relatorio relatorio) {
        relatorioService.remover(relatorio);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
