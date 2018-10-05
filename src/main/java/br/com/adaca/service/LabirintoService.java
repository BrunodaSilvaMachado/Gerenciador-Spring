package br.com.adaca.service;

import br.com.adaca.model.Labirinto;
import br.com.adaca.repository.LabirintoRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class LabirintoService {

    @Autowired
    private LabirintoRepository labirintoRepository;

    public List<Labirinto> listar() {
        List<Labirinto> labirintos = new ArrayList<>();
        Iterator<Labirinto> iterator = labirintoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            labirintos.add(iterator.next());
        }
        if (labirintos.isEmpty()) throw new NotFoundException("Nenhum labirinto encontrado!");
        return labirintos;
    }

    public Labirinto selecionar(Integer id) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(id);
        if (!labirinto.isPresent()) throw new NotFoundException("Labirinto não encontrado! Id: " + id);
        return labirinto.get();
    }

    public Labirinto salvar(Labirinto labirinto) {
        if (labirinto.getId() != null) {
            Optional<Labirinto> op = labirintoRepository.findById(labirinto.getId());
            if (op.isPresent()) throw new ConflictException("O labirinto já existe!");
        }
        return labirintoRepository.save(labirinto);
    }

    public Labirinto alterar(Labirinto labirinto) {
        Labirinto aut = null;
        if(labirinto.getId() != null) {
            aut = labirintoRepository.save(labirinto);
        }
        return aut;
    }

    public void remover(Integer id) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(id);
        if (!labirinto.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            labirintoRepository.delete(labirinto.get());
        }
    }

    public void remover(Labirinto labirinto) {
        labirintoRepository.delete(labirinto);
    }
}
