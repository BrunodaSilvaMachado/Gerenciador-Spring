package br.com.adaca.controller;

import br.com.adaca.model.Tutor;
import br.com.adaca.service.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    /**
     * Lista todos os tutores cadastrados no banco de dados
     *
     * @return Lista com todos os tutores cadastrados
     */
    @GetMapping("/listarTutores")
    public ResponseEntity<List<Tutor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.listar());
    }

    /**
     * Efetua uma busca por ID do tutor cadastrado
     *
     * @param tutorId ID do tutor já existente no banco de dados
     * @return Objeto do tutor encontrado
     */
    @GetMapping("/selecionarTutor/{tutorId}")
    public ResponseEntity<Tutor> selecionar(@PathVariable("tutorId") Integer tutorId) {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.selecionar(tutorId));
    }

    /**
     * Salva o tutor da criança no banco de dados
     *
     * @param tutor Objeto preenchido do tutor a ser gravado
     * @return Objeto salvo
     */
    @PostMapping("/salvarTutor")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Tutor tutor) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tutorService.salvar(tutor).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do tutor no bando de dados
     *
     * @param tutor Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @PutMapping("/alterarTutor")
    public ResponseEntity<Tutor> alterar(@RequestBody @Valid Tutor tutor) {
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.alterar(tutor));
    }

    /**
     * Efetua uma busca por ID do tutor cadastrado e remove-o do banco de dados
     *
     * @param tutorId ID do tutor já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerTutor/{tutorId}")
    public ResponseEntity<Void> remover(@PathVariable("tutorId") Integer tutorId) {
        tutorService.remover(tutorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o cadastro do tutor do banco de dados
     *
     * @param tutor Objeto preenchido do cadastro já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerTutor")
    public ResponseEntity<Void> remover(@RequestBody @Valid Tutor tutor) {
        tutorService.remover(tutor);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
    @RequestMapping(value = "/login/{usuario}/{senha}", method = RequestMethod.GET)
    public Tutor login(@PathVariable("usuario") String usuario,@PathVariable("senha")  String senha) {
        return tutorRepository.login(usuario,senha);
    }
    */
}
