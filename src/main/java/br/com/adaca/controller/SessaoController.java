package br.com.adaca.controller;

import br.com.adaca.model.Sessao;
import br.com.adaca.repository.SessaoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Sessoes")
public class SessaoController {

    @Autowired
    private SessaoRepository sessaoRepository;

    /**
     * Salva a sessão da criança no banco de dados
     *
     * @param sessao Objeto preenchido do sessao a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarSessao", method = RequestMethod.POST)
    public Sessao salvar(@RequestBody Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    /**
     * Efetua uma busca por ID da sessão cadastrada e remove-a do banco de dados
     *
     * @param sessaoId ID do sessao já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerSessao/{sessaoId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("sessaoId") Integer sessaoId) {
        Optional<Sessao> sessao = sessaoRepository.findById(sessaoId);
        if (sessao.isPresent()) {
            sessaoRepository.delete(sessao.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a sessão do banco de dados
     *
     * @param sessao Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerSessao", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Sessao sessao) {
        sessaoRepository.delete(sessao);
        return true;
    }

    /**
     * Altera o cadastro da sessão no bando de dados
     *
     * @param sessao Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarSessao", method = RequestMethod.POST)
    public Sessao alterar(@RequestBody Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    /**
     * Efetua uma busca por ID da sessao cadastrado
     *
     * @param sessaoId ID do sessao já existente no banco de dados
     * @return Objeto do sessao encontrado
     */
    @RequestMapping(value = "/selecionarSessao/{sessaoId}", method = RequestMethod.GET)
    public Sessao selecionar(@PathVariable("sessaoId") Integer sessaoId) {
        Optional<Sessao> sessao = sessaoRepository.findById(sessaoId);
        if (sessao.isPresent()) {
            return sessao.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados
     *
     * @return Lista com todos os sessões cadastrados
     */
    @RequestMapping(value = "/listarSessoes", method = RequestMethod.GET)
    public List<Sessao> listar() {
        List<Sessao> sessoes = new ArrayList<>();
        Iterator<Sessao> iterator = sessaoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            sessoes.add(iterator.next());
        }
        return sessoes;
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados filtradas pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os sessões cadastrados
     */
    @RequestMapping(value = "/listarSessoes/{autistaId}", method = RequestMethod.GET)
    public List<Sessao> listar(@PathVariable("autistaId") Integer autistaId) {
        List<Sessao> sessoes = new ArrayList<>();
        Iterator<Sessao> iterator = sessaoRepository.listByAutista(autistaId).iterator();
        while (iterator.hasNext()) {
            sessoes.add(iterator.next());
        }
        return sessoes;
    }
}
