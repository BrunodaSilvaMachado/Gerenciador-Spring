package br.com.adaca.service;

import br.com.adaca.model.Configuracao;
import br.com.adaca.repository.ConfiguracaoRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoService {
    
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public List<Configuracao> listar() {
        List<Configuracao> configuracoes = new ArrayList<>();
        Iterator<Configuracao> iterator = configuracaoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            configuracoes.add(iterator.next());
        }
        if (configuracoes.isEmpty()) throw new NotFoundException("Nenhuma configuração encontrada!");
        return configuracoes;
    }

    public List<Configuracao> listarConfigAutistaTutor(Integer autistaId, Integer tutorId) {
        List<Configuracao> configuracoes = configuracaoRepository.listIdAutistaTutor(autistaId, tutorId);
        if (configuracoes.isEmpty()) throw new NotFoundException("Nenhuma configuração encontrada!");
        return configuracoes;
    }

    public Configuracao selecionar(Integer id) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(id);
        if (!configuracao.isPresent()) throw new NotFoundException("Configuração não encontrada! Id: " + id);
        return configuracao.get();
    }

    public Configuracao salvar(Configuracao configuracao) {
        if (configuracao.getId() != null) {
            Optional<Configuracao> op = configuracaoRepository.findById(configuracao.getId());
            if (op.isPresent()) throw new ConflictException("A configuração já existe!");
        }
        return configuracaoRepository.save(configuracao);
    }

    public Configuracao alterar(Configuracao configuracao) {
        Configuracao aut = null;
        if(configuracao.getId() != null) {
            aut = configuracaoRepository.save(configuracao);
        }
        return aut;
    }

    public void remover(Integer id) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(id);
        if (!configuracao.isPresent()){
            throw new NotFoundException("id: " + id);
        }
        else {
            configuracaoRepository.delete(configuracao.get());
        }
    }

    public void remover(Configuracao configuracao) {
        configuracaoRepository.delete(configuracao);
    }
}
