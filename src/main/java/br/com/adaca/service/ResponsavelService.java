package br.com.adaca.service;

import br.com.adaca.model.Responsavel;
import br.com.adaca.repository.ResponsavelRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public List<Responsavel> listar() {
        List<Responsavel> responsaveis = new ArrayList<>();
        Iterator<Responsavel> iterator = responsavelRepository.findAll().iterator();
        while (iterator.hasNext()) {
            responsaveis.add(iterator.next());
        }
        if(responsaveis.isEmpty()) throw new NotFoundException("Nenhum responsável encontrado!");
        return responsaveis;
    }

    public List<Responsavel> listar(Integer autistaId) {
        List<Responsavel> responsaveis = responsavelRepository.listByAutista(autistaId);
        if(responsaveis.isEmpty()) throw new NotFoundException("Nenhum responsável encontrado!");
        return responsaveis;
    }

    public Responsavel selecionar(Integer id) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        if (!responsavel.isPresent()) throw new NotFoundException("Responsável não encontrado! Id: " + id);
        return responsavel.get();
    }

    public Responsavel salvar(Responsavel responsavel) {
        if (responsavel.getId() != null) {
            Optional<Responsavel> op = responsavelRepository.findById(responsavel.getId());
            if (op.isPresent()) throw new ConflictException("O relatório já existe!");
        }
        return responsavelRepository.save(responsavel);
    }

    public Responsavel alterar(Responsavel responsavel) {
        Responsavel res = null;
        if(responsavel.getId() != null) {
            res = responsavelRepository.save(responsavel);
        }
        return res;
    }

    public void remover(Integer id) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        if (!responsavel.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            responsavelRepository.delete(responsavel.get());
        }
    }

    public void remover(Responsavel responsavel) {
        responsavelRepository.delete(responsavel);
    }
}
