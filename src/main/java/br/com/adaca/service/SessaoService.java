package br.com.adaca.service;

import br.com.adaca.model.Sessao;
import br.com.adaca.repository.SessaoRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    public List<Sessao> listar() {
        List<Sessao> sessoes = new ArrayList<>();
        Iterator<Sessao> iterator = sessaoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            sessoes.add(iterator.next());
        }
        if(sessoes.isEmpty()) throw new NotFoundException("Nenhuma sessão encontrada!");
        return sessoes;
    }

    public List<Sessao> listar(Integer autistaId) {
        List<Sessao> sessoes = sessaoRepository.listByAutista(autistaId);
        if(sessoes.isEmpty()) throw new NotFoundException("Nenhuma sessão encontrada!");
        return sessoes;
    }

    public Sessao selecionar(Integer id) {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        if (!sessao.isPresent()) throw new NotFoundException("Sessão não encontrada! Id: " + id);
        return sessao.get();
    }

    public Sessao salvar(Sessao sessao) {
        if (sessao.getId() != null) {
            Optional<Sessao> op = sessaoRepository.findById(sessao.getId());
            if (op.isPresent()) throw new ConflictException("A sessão já existe!");
        }
        return sessaoRepository.save(sessao);
    }

    public Sessao alterar(Sessao sessao) {
        Sessao sess = null;
        if(sessao.getId() != null) {
            sess = sessaoRepository.save(sessao);
        }
        return sess;
    }

    public void remover(Integer id) {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        if (!sessao.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            sessaoRepository.delete(sessao.get());
        }
    }

    public void remover(Sessao sessao) {
        sessaoRepository.delete(sessao);
    }
}
