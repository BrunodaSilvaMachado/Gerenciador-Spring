package br.com.adaca.dao;

import br.com.adaca.entity.Tutor;
import br.com.adaca.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TutorDAO {

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Salva o tutor da criança no banco de dados
     *
     * @param tutor Objeto preenchido do tutor a ser gravado
     * @return Objeto salvo
     */
    public Tutor salvar(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Remove o cadastro do tutor do banco de dados
     *
     * @param tutor Objeto preenchido do cadastro já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Tutor tutor) {
        tutorRepository.delete(tutor);
        return true;
    }

    /**
     * Efetua uma busca por ID do tutor cadastrado e remove-o do banco de dados
     *
     * @param tutorId ID do tutor já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer tutorId) {
        Optional<Tutor> tutor = tutorRepository.findById(tutorId);
        if (tutor.isPresent()) {
            tutorRepository.delete(tutor.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro do tutor no bando de dados
     *
     * @param tutor Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Tutor alterar(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Efetua uma busca por ID do tutor cadastrado
     *
     * @param tutorId ID do tutor já existente no banco de dados
     * @return Objeto do tutor encontrado
     */
    public Tutor selecionar(Integer tutorId) {
        Optional<Tutor> tutor = tutorRepository.findById(tutorId);
        if (tutor.isPresent()) {
            return tutor.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os tutores cadastrados no banco de dados
     *
     * @return Lista com todos os tutores cadastrados
     */
    public List<Tutor> listar() {
        List<Tutor> tutores = new ArrayList<>();
        Iterator<Tutor> iterator = tutorRepository.findAll().iterator();
        while (iterator.hasNext()) {
            tutores.add(iterator.next());
        }
        return tutores;
    }

    /**
     * Verifica se existe o usuário e senha passados
     *
     * @param usuario Usuário do tutor cadastrado
     * @param senha Senha do tutor cadastrado
     * @return Objeto do tutor
     */
    public Tutor login(String usuario, String senha) {
        return tutorRepository.login(usuario,senha);
    }
}
