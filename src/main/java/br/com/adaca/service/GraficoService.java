package br.com.adaca.service;

import br.com.adaca.model.Grafico;
import br.com.adaca.repository.GraficoRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class GraficoService {

    @Autowired
    private GraficoRepository graficoRepository;

    public List<Grafico> listar() {
        List<Grafico> graficos = new ArrayList<>();
        Iterator<Grafico> iterator = graficoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            graficos.add(iterator.next());
        }
        if (graficos.isEmpty()) throw new NotFoundException("Nenhum gráfico encontrado!");
        return graficos;
    }

    public List<Grafico> listar(Integer autistaId) {
        List<Grafico> graficos = graficoRepository.listByAutista(autistaId);
        if (graficos.isEmpty()) throw new NotFoundException("Nenhum gráfico encontrado!");
        return graficos;
    }

    public Grafico selecionar(Integer id) {
        Optional<Grafico> grafico = graficoRepository.findById(id);
        if (!grafico.isPresent()) throw new NotFoundException("Gráfico não encontrado! Id: " + id);
        return grafico.get();
    }

    public Grafico salvar(Grafico grafico) {
        if (grafico.getId() != null) {
            Optional<Grafico> op = graficoRepository.findById(grafico.getId());
            if (op.isPresent()) throw new ConflictException("O gráfico já existe!");
        }
        return graficoRepository.save(grafico);
    }

    public Grafico alterar(Grafico grafico) {
        Grafico aut = null;
        if(grafico.getId() != null) {
            aut = graficoRepository.save(grafico);
        }
        return aut;
    }

    public void remover(Integer id) {
        Optional<Grafico> grafico = graficoRepository.findById(id);
        if (!grafico.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            graficoRepository.delete(grafico.get());
        }
    }

    public void remover(Grafico grafico) {
        graficoRepository.delete(grafico);
    }
}
