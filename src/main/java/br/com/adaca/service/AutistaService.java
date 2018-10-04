package br.com.adaca.service;

import br.com.adaca.model.Autista;
import br.com.adaca.repository.AutistaRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AutistaService {

    @Autowired
    private AutistaRepository autistaRepository;

    public List<Autista> listar() {
        List<Autista> autistas = new ArrayList<>();
        Iterator<Autista> iterator = autistaRepository.findAll().iterator();
        while (iterator.hasNext()) {
            autistas.add(iterator.next());
        }
        if (autistas.isEmpty()) throw new NotFoundException("Nenhuma criança encontrada!");
        return autistas;
    }

    public List<Autista> listarNomesId() {
        List<Autista> autistas = autistaRepository.listNamesId();
        if (autistas.isEmpty()) throw new NotFoundException("Nenhuma criança encontrada!");
        return autistas;
    }

    public Autista selecionar(Integer id) {
        Optional<Autista> autista = autistaRepository.findById(id);
        if (!autista.isPresent()) throw new NotFoundException("Criança não encontrada! Id: " + id);
        return autista.get();
    }

    public Autista salvar(Autista autista) {
        if (autista.getId() != null) {
            Optional<Autista> op = autistaRepository.findById(autista.getId());
            if (op.isPresent()) throw new ConflictException("A criança já existe!");
        }
        return autistaRepository.save(autista);
    }

    public Autista alterar(Autista autista) {
        Autista aut = null;
        if(autista.getId() != null) {
            aut = autistaRepository.save(autista);
        }
        return aut;
    }

    public void remover(Integer id) {
        Optional<Autista> autista = autistaRepository.findById(id);
        if (!autista.isPresent()){
            throw new NotFoundException("id: " + id);
        }
        else {
            autistaRepository.delete(autista.get());
        }
    }

    public void remover(Autista autista) {
        autistaRepository.delete(autista);
    }
}
