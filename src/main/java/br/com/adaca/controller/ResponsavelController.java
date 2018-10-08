package br.com.adaca.controller;

import br.com.adaca.model.Responsavel;
import br.com.adaca.service.ResponsavelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    /**
     * Lista todos os responsáveis cadastrados no banco de dados
     *
     * @return Lista com todos os responsáveis cadastrados
     */
    @GetMapping("/listarResponsaveis")
    public ResponseEntity<List<Responsavel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.listar());
    }

    /**
     * Lista todos os responsáveis cadastrados no banco de dados filtrados pela
     * criança
     *
     * @param autistaId ID da criança para filtro da pesquisa
     * @return Lista com todos os responsáveis cadastrados
     */
    @GetMapping("/listarResponsaveis/{autistaId}")
    public ResponseEntity<List<Responsavel>> listar(@PathVariable("autistalId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.listar(autistaId));
    }

    /**
     * Efetua uma busca por ID do responsável cadastrado
     *
     * @param responsavelId ID do responsável já existente no banco de dados
     * @return Objeto do responsável encontrado
     */
    @GetMapping("/selecionarResponsavel/{responsavelId}")
    public ResponseEntity<Responsavel> selecionar(@PathVariable("responsavelId") Integer responsavelId) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.selecionar(responsavelId));
    }

    /**
     * Salva o responsável da criança no banco de dados
     *
     * @param responsavel Objeto preenchido do responsável a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarResponsavel")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Responsavel responsavel) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responsavelService.salvar(responsavel).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do responsável no bando de dados
     *
     * @param responsavel Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarResponsavel")
    public ResponseEntity<Responsavel> alterar(@RequestBody @Valid Responsavel responsavel) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelService.alterar(responsavel));
    }

    /**
     * Efetua uma busca por ID do responsável cadastrado e remove-o do banco de
     * dados
     *
     * @param responsavelId ID do responsável já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerResponsavel/{responsavelId}")
    public ResponseEntity<Void> remover(@PathVariable("responsavelId") Integer responsavelId) {
        responsavelService.remover(responsavelId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro do responsável do banco de dados
     *
     * @param responsavel Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerResponsavel")
    public ResponseEntity<Void> remover(@RequestBody @Valid Responsavel responsavel) {
        responsavelService.remover(responsavel);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
