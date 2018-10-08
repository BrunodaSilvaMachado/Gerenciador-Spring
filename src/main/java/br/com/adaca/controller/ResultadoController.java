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

    /**
     * Lista todos os resultadoes cadastrados no banco de dados
     *
     * @return Lista com todos os resultados cadastrados
     */
    @GetMapping("/listarResultados")
    public ResponseEntity<List<Resultado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.listar());
    }

    /**
     * Lista todos os resultados cadastrados no banco de dados filtrados pela
     * criança
     *
     * @param autistaId ID da criança para filtro da pesquisa
     * @return Lista com todos os responsáveis cadastrados
     */
    @GetMapping("/listarResultados/{sessaoId}")
    public ResponseEntity<List<Resultado>> listar(@PathVariable("autistalId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.listar(autistaId));
    }

    /**
     * Efetua uma busca por ID do resultado cadastrado
     *
     * @param resultadoId ID do resultado já existente no banco de dados
     * @return Objeto do resultado encontrado
     */
    @GetMapping("/selecionarResultado/{resultadoId}")
    public ResponseEntity<Resultado> selecionar(@PathVariable("resultadoId") Integer resultadoId) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.selecionar(resultadoId));
    }

    /**
     * Salva o resultado da criança no banco de dados
     *
     * @param resultado Objeto preenchido do resultado a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarResultado")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Resultado resultado) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resultadoService.salvar(resultado).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do resultado no bando de dados
     *
     * @param resultado Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarResultado")
    public ResponseEntity<Resultado> alterar(@RequestBody @Valid Resultado resultado) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.alterar(resultado));
    }

    /**
     * Efetua uma busca por ID do resultado cadastrado e remove-o do banco de
     * dados
     *
     * @param resultadoId ID do resultado já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerResultado/{resultadoId}")
    public ResponseEntity<Void> remover(@PathVariable("resultadoId") Integer resultadoId) {
        resultadoService.remover(resultadoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro do resultado do banco de dados
     *
     * @param resultado Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerResultado")
    public ResponseEntity<Void> remover(@RequestBody @Valid Resultado resultado) {
        resultadoService.remover(resultado);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
