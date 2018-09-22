package br.com.adaca.dao;

import br.com.adaca.entity.Configuracao;
import br.com.adaca.repository.ConfiguracaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoDAO {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    /**
     * Salva a configuração do jogo no banco de dados
     *
     * @param configuracao Objeto preenchido da configuração a ser gravada
     * @return Objeto salvo
     */
    public Configuracao salvar(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    /**
     * Remove a configuração do jogo do banco de dados
     *
     * @param configuracao Objeto preenchido da configuração já existente no
     * banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Configuracao configuracao) {
        configuracaoRepository.delete(configuracao);
        return true;
    }

    /**
     * Efetua uma busca por ID da configuração gravada e remove-a do banco de
     * dados
     *
     * @param configuracaoId ID da configuração já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer configuracaoId) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(configuracaoId);
        if (configuracao.isPresent()) {
            configuracaoRepository.delete(configuracao.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera a configuração do jogo no bando de dados
     *
     * @param configuracao Objeto preenchido com a configuração já alterada
     * @return Objeto alterado
     */
    public Configuracao alterar(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    /**
     * Efetua uma busca por ID da configuração dos jogos salva
     *
     * @param configuracaoId ID da configuração já existente no banco de dados
     * @return Objeto da configuração encontrada
     */
    public Configuracao selecionar(Integer configuracaoId) {
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
     * @param idautista ID da criança logada no jogo
     * @param idtutor ID do tutor logado no jogo
     * @return Lista com todas as configurações gravadas para aquela criança e
     * aquele tutor
     */
    public List<Configuracao> listar(Integer idautista, Integer idtutor) {
        return configuracaoRepository.listIdAutistaTutor(idautista,idtutor);
    }
}
