package br.com.adaca.service;

import br.com.adaca.dto.ConfiguracaoDTO;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.mapper.ConfiguracaoMapper;
import br.com.adaca.model.Configuracao;
import br.com.adaca.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private ConfiguracaoMapper configuracaoMapper;

    /**
     * Lista todas as configurações gravadas no banco de dados
     *
     * @return Lista com todas as configurações gravadas
     */
    public List<ConfiguracaoDTO> listar() {
        List<Configuracao> configuracoes = new ArrayList<>();
        for (Configuracao configuracao : configuracaoRepository.findAll()) {
            configuracoes.add(configuracao);
        }
        if (configuracoes.isEmpty()) throw new NotFoundException("Nenhuma configuração encontrada!");
        return configuracaoMapper.toDto(configuracoes);
    }

    /**
     * Lista todas as configurações gravadas no banco de dados filtradas por autista e tutor
     *
     * @param autistaId ID da criança logada no jogo
     * @param tutorId   ID do tutor logado no jogo
     * @return Lista com todas as configurações gravadas para aquela criança e aquele tutor
     */
    public List<ConfiguracaoDTO> listarConfigAutistaTutor(Integer autistaId, Integer tutorId) {
        List<Configuracao> configuracoes = configuracaoRepository.listIdAutistaTutor(autistaId, tutorId);
        if (configuracoes.isEmpty()) throw new NotFoundException("Nenhuma configuração encontrada!");
        return configuracaoMapper.toDto(configuracoes);
    }

    /**
     * Efetua uma busca por ID da configuração dos jogos salva
     *
     * @param id ID da configuração já existente no banco de dados
     * @return Objeto da configuração encontrada
     */
    public ConfiguracaoDTO selecionar(Integer id) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(id);
        if (!configuracao.isPresent()) throw new NotFoundException("Configuração não encontrada! Id: " + id);
        return configuracaoMapper.toDto(configuracao.get());
    }

    /**
     * Salva a configuração do jogo no banco de dados
     *
     * @param configuracao Objeto preenchido da configuração a ser gravada
     * @return Objeto salvo
     */
    public ConfiguracaoDTO salvar(ConfiguracaoDTO configuracao) {
        if (configuracao.getId() != null) {
            Optional<Configuracao> op = configuracaoRepository.findById(configuracao.getId());
            if (op.isPresent()) throw new ConflictException("A configuração já existe!");
        }
        return configuracaoMapper.toDto(configuracaoRepository.save(configuracaoMapper.toEntity(configuracao)));
    }

    /**
     * Altera a configuração do jogo no bando de dados
     *
     * @param configuracao Objeto preenchido com a configuração já alterada
     * @return Objeto alterado
     */
    public ConfiguracaoDTO alterar(ConfiguracaoDTO configuracao) {
        ConfiguracaoDTO conf = null;
        if (configuracao.getId() != null) {
            conf = configuracaoMapper.toDto(configuracaoRepository.save(configuracaoMapper.toEntity(configuracao)));
        }
        return conf;
    }

    /**
     * Efetua uma busca por ID da configuração gravada e remove-a do banco de dados
     *
     * @param id ID da configuração já existente no banco de dados
     */
    public void remover(Integer id) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(id);
        if (!configuracao.isPresent()) {
            throw new NotFoundException("id: " + id);
        } else {
            configuracaoRepository.delete(configuracao.get());
        }
    }

    /**
     * Remove a configuração do jogo do banco de dados
     *
     * @param configuracao Objeto preenchido da configuração já existente no banco de dados
     */
    public void remover(ConfiguracaoDTO configuracao) {
        configuracaoRepository.delete(configuracaoMapper.toEntity(configuracao));
    }
}
