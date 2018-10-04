package br.com.adaca.service;

import br.com.adaca.model.Atividade;
import br.com.adaca.repository.AtividadeRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<Atividade> listar() {
        List<Atividade> atividades = new ArrayList<>();
        Iterator<Atividade> iterator = atividadeRepository.findAll().iterator();
        while (iterator.hasNext()) {
            atividades.add(iterator.next());
        }
        if (atividades.isEmpty()) throw new NotFoundException("Nenhuma atividade encontrada!");
        return atividades;
    }

    public Atividade selecionar(Integer id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (!atividade.isPresent()) throw new NotFoundException("Atividade não encontrada! Id: " + id);
        return atividade.get();
    }

    public Atividade salvar(Atividade atividade) {
        if (atividade.getId() != null) {
            Optional<Atividade> op = atividadeRepository.findById(atividade.getId());
            if (op.isPresent()) throw new ConflictException("A atividade já existe!");
        }
        return atividadeRepository.save(atividade);
    }

    public Atividade alterar(Atividade atividade) {
        Atividade ati = null;
        if(atividade.getId() != null) {
            ati = atividadeRepository.save(atividade);
        }
        return ati;
    }

    public void remover(Integer id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (!atividade.isPresent()){
            throw new NotFoundException("id: " + id);
        }
        else {
            atividadeRepository.delete(atividade.get());
        }
    }

    public void remover(Atividade atividade) {
        atividadeRepository.delete(atividade);
    }
}
