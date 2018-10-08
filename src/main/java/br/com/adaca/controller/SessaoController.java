package br.com.adaca.controller;

import br.com.adaca.model.Sessao;
import br.com.adaca.service.SessaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Sessoes")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    /**
     * Lista todos os sessões cadastradas no banco de dados
     *
     * @return Lista com todos os sessões cadastrados
     */
    @GetMapping("/listarSessoes")
    public ResponseEntity<List<Sessao>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.listar());
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados filtradas pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os sessões cadastrados
     */
    @GetMapping("/listarSessoes/{autistaId}")
    public ResponseEntity<List<Sessao>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.listar(autistaId));
    }

    /**
     * Efetua uma busca por ID da sessao cadastrado
     *
     * @param sessaoId ID do sessao já existente no banco de dados
     * @return Objeto da sessão encontrado
     */
    @GetMapping("/selecionarSessao/{sessaoId}")
    public ResponseEntity<Sessao> selecionar(@PathVariable("sessaoId") Integer sessaoId) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.selecionar(sessaoId));
    }

    /**
     * Salva a sessão da criança no banco de dados
     *
     * @param sessao Objeto preenchido da sessão a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarSessao")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Sessao sessao) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sessaoService.salvar(sessao).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro da sessão no bando de dados
     *
     * @param sessao Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarSessao")
    public ResponseEntity<Sessao> alterar(@RequestBody @Valid Sessao sessao) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.alterar(sessao));
    }

    /**
     * Efetua uma busca por ID da sessão cadastrada e remove-a do banco de dados
     *
     * @param sessaoId ID do sessao já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerSessao/{sessaoId}")
    public ResponseEntity<Void> remover(@PathVariable("sessaoId") Integer sessaoId) {
        sessaoService.remover(sessaoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove a sessão do banco de dados
     *
     * @param sessao Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping(value = "/removerSessao")
    public ResponseEntity<Void> remover(@RequestBody @Valid Sessao sessao) {
        sessaoService.remover(sessao);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
