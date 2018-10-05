package br.com.adaca.controller;

import br.com.adaca.model.Configuracao;
import br.com.adaca.service.ConfiguracaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Configuracoes")
public class ConfiguracaoController {

    private ConfiguracaoService configuracaoService;

    /**
     * Lista todas as configurações gravadas no banco de dados
     *
     * @return Lista com todas as configurações gravadas
     */
    @GetMapping("/listarConfiguracoes")
    public ResponseEntity<List<Configuracao>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.listar());
    }

    /**
     * Lista todas as configurações gravadas no banco de dados filtradas por
     * autista e tutor
     *
     * @param autistaId ID da criança logada no jogo
     * @param tutorId ID do tutor logado no jogo
     * @return Lista com todas as configurações gravadas para aquela criança e
     * aquele tutor
     */
    @GetMapping("/listarConfiguracoesAutistaTutor/{autistaId}/{tutorId}")
    public ResponseEntity<List<Configuracao>> listar(@PathVariable("autistaId") Integer autistaId,@PathVariable("tutorId") Integer tutorId) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.listarConfigAutistaTutor(autistaId,tutorId));
    }

    /**
     * Efetua uma busca por ID da configuração dos jogos salva
     *
     * @param configuracaoId ID da configuração já existente no banco de dados
     * @return Objeto da configuração encontrada
     */
    @GetMapping("/selecionarConfiguracao/{configuracaoId}")
    public ResponseEntity<Configuracao> selecionar(@PathVariable("configuracaoId") Integer configuracaoId) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.selecionar(configuracaoId));
    }

    /**
     * Salva a configuração do jogo no banco de dados
     *
     * @param configuracao Objeto preenchido da configuração a ser gravada
     * @return Objeto salvo
     */
    @PostMapping("/salvarConfiguracao")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Configuracao configuracao) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(configuracaoService.salvar(configuracao).getId()).toUri()).build();
    }

    /**
     * Altera a configuração do jogo no bando de dados
     *
     * @param configuracao Objeto preenchido com a configuração já alterada
     * @return Objeto alterado
     */
    @PutMapping("/alterarConfiguracao")
    public ResponseEntity<Configuracao> alterar(@RequestBody @Valid Configuracao configuracao) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoService.alterar(configuracao));
    }

    /**
     * Efetua uma busca por ID da configuração gravada e remove-a do banco de
     * dados
     *
     * @param configuracaoId ID da configuração já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerConfiguracao/{configuracaoId}")
    public ResponseEntity<Void> remover(@PathVariable("configuracaoId") Integer configuracaoId) {
        configuracaoService.remover(configuracaoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove a configuração do jogo do banco de dados
     *
     * @param configuracao Objeto preenchido da configuração já existente no
     * banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerConfiguracao")
    public ResponseEntity<Void> remover(@RequestBody @Valid Configuracao configuracao) {
        configuracaoService.remover(configuracao);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
