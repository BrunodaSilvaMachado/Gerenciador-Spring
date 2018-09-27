package br.com.adaca.controller;

import br.com.adaca.model.Autista;
import br.com.adaca.repository.AutistaRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Autistas")
public class AutistaController {

    @Autowired
    private AutistaRepository autistaRepository;

    /**
     * Salva o cadastro da criança no banco de dados
     *
     * @param autista Objeto preenchido do cadastro a ser gravado
     * @return Erro ou sucesso ao salvar
     */
    @RequestMapping(value = "/salvarAutista", method = RequestMethod.POST)
    public Autista salvar(@RequestBody Autista autista) {
        return autistaRepository.save(autista);
    }

    /**
     * Efetua uma busca por ID da criança cadastrada e remove-a do banco de
     * dados
     *
     * @param autistaId ID da criança já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerAutista/{autistaId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("autistaId") Integer autistaId) {
        Optional<Autista> autista = autistaRepository.findById(autistaId);
        if (autista.isPresent()) {
            autistaRepository.delete(autista.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro da criança do banco de dados
     *
     * @param autista Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerAutista", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Autista autista) {
        autistaRepository.delete(autista);
        return true;
    }

    /**
     * Altera o cadastro da criança no bando de dados
     *
     * @param autista Objeto preenchido com os dados já alterados
     * @return Erro ou sucesso ao alterar
     */
    @RequestMapping(value = "/alterarAutista", method = RequestMethod.POST)
    public Autista alterar(@RequestBody Autista autista) {
        return autistaRepository.save(autista);
    }

    /**
     * Efetua uma busca por ID da criança cadastrada
     *
     * @param autistaId ID da criança já existente no banco de dados
     * @return Objeto da criança encontrada
     */
    @RequestMapping(value = "/selecionarAutista/{autistaId}", method = RequestMethod.GET)
    public Autista selecionar(@PathVariable("autistaId") Integer autistaId) {
        Optional<Autista> autista = autistaRepository.findById(autistaId);
        if (autista.isPresent()) {
            return autista.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todas as crianças cadastradas no banco de dados
     *
     * @return Lista com todas as crianças cadastradas
     */
    @RequestMapping(value = "/listarAutistas", method = RequestMethod.GET)
    public List<Autista> listar() {
        List<Autista> autistas = new ArrayList<>();
        Iterator<Autista> iterator = autistaRepository.findAll().iterator();
        while (iterator.hasNext()) {
            autistas.add(iterator.next());
        }
        return autistas;
    }

    /**
     * Lista id e nome de todas as crianças cadastradas no banco de dados
     *
     * @return Lista de ids e nomes de todas as crianças cadastradas
     */
    @RequestMapping(value = "/listarNomesIdAutistas", method = RequestMethod.GET)
    public List<Autista> listarNomesId() {
        return autistaRepository.listNamesId();
    }
}