package br.com.adaca.service;

import br.com.adaca.model.Tutor;
import br.com.adaca.repository.TutorRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {


    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
    * Lista todos os tutores cadastrados no banco de dados
    *
    * @return Lista com todos os tutores cadastrados
    */
    public List<Tutor> listar() {
        List<Tutor> tutores = tutorRepository.listTutores();
        if(tutores.isEmpty()) throw new NotFoundException("Nenhum tutor encontrado!");
        return tutores;
    }

    /**
    * Efetua uma busca por ID do tutor cadastrado
    *
    * @param id ID do tutor já existente no banco de dados
    * @return Objeto do tutor encontrado
    */
    public Tutor selecionar(Integer id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (!tutor.isPresent()) throw new NotFoundException("Tutor não encontrado! Id: " + id);
        return tutor.get();
    }

    /**
    * Salva o tutor da criança no banco de dados
    *
    * @param tutor Objeto preenchido do tutor a ser gravado
    * @return Objeto salvo
    */
    public Tutor salvar(Tutor tutor) {
        if (tutor.getId() != null) {
            Optional<Tutor> op = tutorRepository.findById(tutor.getId());
            if (op.isPresent()) throw new ConflictException("O tutor já existe!");
        }
        tutor.setSenha(passwordEncoder.encode(tutor.getSenha()));
        return tutorRepository.save(tutor);
    }

    /**
    * Altera o cadastro do tutor no bando de dados
    *
    * @param tutor Objeto preenchido com os dados já alterados
    * @return Objeto alterado
    */
    public Tutor alterar(Tutor tutor) {
        Tutor sess = null;
        if(tutor.getId() != null) {
            sess = tutorRepository.save(tutor);
        }
        return sess;
    }

    /**
    * Efetua uma busca por ID do tutor cadastrado e remove-o do banco de dados
    *
    * @param id ID do tutor já existente no banco de dados
    */
    public void remover(Integer id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (!tutor.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            tutorRepository.delete(tutor.get());
        }
    }

    /**
    * Remove o cadastro do tutor do banco de dados
    *
    * @param tutor Objeto preenchido do cadastro já existente no banco de dados
    */
    public void remover(Tutor tutor) {
        tutorRepository.delete(tutor);
    }
}
