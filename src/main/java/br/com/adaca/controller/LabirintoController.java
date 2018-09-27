package br.com.adaca.controller;

import br.com.adaca.model.Labirinto;
import br.com.adaca.repository.LabirintoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Labirintos")
public class LabirintoController {

    @Autowired
    private LabirintoRepository labirintoRepository;

    /**
     * Salva o labirinto da criança no banco de dados
     *
     * @param labirinto Objeto preenchido do labirinto a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarLabirinto", method = RequestMethod.POST)
    public Labirinto salvar(@RequestBody Labirinto labirinto) {
        return labirintoRepository.save(labirinto);
    }

    /**
     * Efetua uma busca por ID do labirinto cadastrado e remove-o do banco de dados
     *
     * @param labirintoId ID do labirinto já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerLabirinto/{labirintoId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("labirintoId") Integer labirintoId) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(labirintoId);
        if (labirinto.isPresent()) {
            labirintoRepository.delete(labirinto.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro do labirinto do banco de dados
     *
     * @param labirinto Objeto preenchido do cadastro já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerLabirinto", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Labirinto labirinto) {
        labirintoRepository.delete(labirinto);
        return true;
    }

    /**
     * Altera o cadastro do labirinto no bando de dados
     *
     * @param labirinto Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarLabirinto", method = RequestMethod.POST)
    public Labirinto alterar(@RequestBody Labirinto labirinto) {
        return labirintoRepository.save(labirinto);
    }

    /**
     * Efetua uma busca por ID do labirinto cadastrado
     *
     * @param labirintoId ID do labirinto já existente no banco de dados
     * @return Objeto do labirinto encontrado
     */
    @RequestMapping(value = "/selecionarLabirinto/{labirintoId}", method = RequestMethod.GET)
    public Labirinto selecionar(@PathVariable("labirintoId") Integer labirintoId) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(labirintoId);
        if (labirinto.isPresent()) {
            return labirinto.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os labirintos cadastrados no banco de dados
     *
     * @return Lista com todos os labirintos cadastrados
     */
    @RequestMapping(value = "/listarLabirintos", method = RequestMethod.GET)
    public List<Labirinto> listar() {
        List<Labirinto> labirintos = new ArrayList<>();
        Iterator<Labirinto> iterator = labirintoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            labirintos.add(iterator.next());
        }
        return labirintos;
    }
}
