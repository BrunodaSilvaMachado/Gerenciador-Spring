package br.com.adaca.service;

import br.com.adaca.model.Relatorio;
import br.com.adaca.repository.RelatorioRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {
    
    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<Relatorio> listar() {
        List<Relatorio> relatorios = new ArrayList<>();
        Iterator<Relatorio> iterator = relatorioRepository.findAll().iterator();
        while (iterator.hasNext()) {
            relatorios.add(iterator.next());
        }
        if(relatorios.isEmpty()) throw new NotFoundException("Nenhum relatório encontrado!");
        return relatorios;
    }

    public List<Relatorio> listar(Integer autistaId) {
        List<Relatorio> relatorios = relatorioRepository.listByAutista(autistaId);
        if(relatorios.isEmpty()) throw new NotFoundException("Nenhum relatório encontrado!");
        return relatorios;
    }

    public Relatorio selecionar(Integer id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);
        if (!relatorio.isPresent()) throw new NotFoundException("Relatório não encontrado! Id: " + id);
        return relatorio.get();
    }

    public Relatorio salvar(Relatorio relatorio) {
        if (relatorio.getId() != null) {
            Optional<Relatorio> op = relatorioRepository.findById(relatorio.getId());
            if (op.isPresent()) throw new ConflictException("O relatório já existe!");
        }
        return relatorioRepository.save(relatorio);
    }

    public Relatorio alterar(Relatorio relatorio) {
        Relatorio rel = null;
        if(relatorio.getId() != null) {
            rel = relatorioRepository.save(relatorio);
        }
        return rel;
    }

    public void remover(Integer id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);
        if (!relatorio.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            relatorioRepository.delete(relatorio.get());
        }
    }

    public void remover(Relatorio relatorio) {
        relatorioRepository.delete(relatorio);
    }
}
