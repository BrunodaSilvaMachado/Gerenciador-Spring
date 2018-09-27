package br.com.adaca.controller;

import br.com.adaca.model.Configuracao;
import br.com.adaca.repository.ConfiguracaoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Configuracoes")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    /**
     * Salva a configuração do jogo no banco de dados
     *
     * @param configuracao Objeto preenchido da configuração a ser gravada
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarConfiguracao", method = RequestMethod.POST)
    public Configuracao salvar(@RequestBody Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    /**
     * Efetua uma busca por ID da configuração gravada e remove-a do banco de
     * dados
     *
     * @param configuracaoId ID da configuração já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerConfiguracao/{configuracaoId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("configuracaoId") Integer configuracaoId) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(configuracaoId);
        if (configuracao.isPresent()) {
            configuracaoRepository.delete(configuracao.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a configuração do jogo do banco de dados
     *
     * @param configuracao Objeto preenchido da configuração já existente no
     * banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerConfiguracao", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Configuracao configuracao) {
        configuracaoRepository.delete(configuracao);
        return true;
    }

    /**
     * Altera a configuração do jogo no bando de dados
     *
     * @param configuracao Objeto preenchido com a configuração já alterada
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarConfiguracao", method = RequestMethod.POST)
    public Configuracao alterar(@RequestBody Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    /**
     * Efetua uma busca por ID da configuração dos jogos salva
     *
     * @param configuracaoId ID da configuração já existente no banco de dados
     * @return Objeto da configuração encontrada
     */
    @RequestMapping(value = "/selecionarConfiguracao/{configuracaoId}", method = RequestMethod.GET)
    public Configuracao selecionar(@PathVariable("configuracaoId") Integer configuracaoId) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(configuracaoId);
        if (configuracao.isPresent()) {
            return configuracao.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todas as configurações gravadas no banco de dados
     *
     * @return Lista com todas as configurações gravadas
     */
    @RequestMapping(value = "/listarConfiguracoes", method = RequestMethod.GET)
    public List<Configuracao> listar() {
        List<Configuracao> configuracoes = new ArrayList<>();
        Iterator<Configuracao> iterator = configuracaoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            configuracoes.add(iterator.next());
        }
        return configuracoes;
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
    @RequestMapping(value = "/listarConfiguracoesAutistaTutor/{autistaId}/{tutorId}", method = RequestMethod.GET)
    public List<Configuracao> listar(@PathVariable("autistaId") Integer autistaId,@PathVariable("tutorId") Integer tutorId) {
        return configuracaoRepository.listIdAutistaTutor(autistaId,tutorId);
    }
}
