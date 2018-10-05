package br.com.adaca.controller;

import br.com.adaca.model.Tutor;
import br.com.adaca.repository.TutorRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Salva o tutor da criança no banco de dados
     *
     * @param tutor Objeto preenchido do tutor a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarTutor", method = RequestMethod.POST)
    public Tutor salvar(@RequestBody Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Efetua uma busca por ID do tutor cadastrado e remove-o do banco de dados
     *
     * @param tutorId ID do tutor já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerTutor/{tutorId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("tutorId") Integer tutorId) {
        Optional<Tutor> tutor = tutorRepository.findById(tutorId);
        if (tutor.isPresent()) {
            tutorRepository.delete(tutor.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro do tutor do banco de dados
     *
     * @param tutor Objeto preenchido do cadastro já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerTutor", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Tutor tutor) {
        tutorRepository.delete(tutor);
        return true;
    }

    /**
     * Altera o cadastro do tutor no bando de dados
     *
     * @param tutor Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarTutor", method = RequestMethod.POST)
    public Tutor alterar(@RequestBody Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Efetua uma busca por ID do tutor cadastrado
     *
     * @param tutorId ID do tutor já existente no banco de dados
     * @return Objeto do tutor encontrado
     */
    @RequestMapping(value = "/selecionarTutor/{tutorId}", method = RequestMethod.GET)
    public Tutor selecionar(@PathVariable("tutorId") Integer tutorId) {
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
    @RequestMapping(value = "/listarTutores", method = RequestMethod.GET)
    public List<Tutor> listar() {
        List<Tutor> tutores = new ArrayList<>();
        Iterator<Tutor> iterator = tutorRepository.listTutores().iterator();
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
    @RequestMapping(value = "/login/{usuario}/{senha}", method = RequestMethod.GET)
    public Tutor login(@PathVariable("usuario") String usuario,@PathVariable("senha")  String senha) {
        return tutorRepository.login(usuario,senha);
    }
}
