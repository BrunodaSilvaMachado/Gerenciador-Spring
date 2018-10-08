package br.com.adaca.service;

import br.com.adaca.model.Tutor;
import br.com.adaca.repository.TutorRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TutorService {


    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> listar() {
        List<Tutor> tutores = tutorRepository.listTutores();
        if(tutores.isEmpty()) throw new NotFoundException("Nenhum tutor encontrado!");
        return tutores;
    }

    public Tutor selecionar(Integer id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (!tutor.isPresent()) throw new NotFoundException("Tutor não encontrado! Id: " + id);
        return tutor.get();
    }

    public Tutor salvar(Tutor tutor) {
        if (tutor.getId() != null) {
            Optional<Tutor> op = tutorRepository.findById(tutor.getId());
            if (op.isPresent()) throw new ConflictException("O tutor já existe!");
        }
        return tutorRepository.save(tutor);
    }

    public Tutor alterar(Tutor tutor) {
        Tutor sess = null;
        if(tutor.getId() != null) {
            sess = tutorRepository.save(tutor);
        }
        return sess;
    }

    public void remover(Integer id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (!tutor.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            tutorRepository.delete(tutor.get());
        }
    }

    public void remover(Tutor tutor) {
        tutorRepository.delete(tutor);
    }
}
