package br.com.adaca.controller;

import java.util.List;

import br.com.adaca.dao.AutistaDAO;
import br.com.adaca.entity.Autista;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Gerenciador/Autistas")
public class AutistaController {
    @Autowired
    private AutistaDAO dao;

    @RequestMapping(value = "/listarAutistas", method = RequestMethod.GET)
    public List<Autista> listar() {
        return dao.listar();
    }

    @RequestMapping(value = "/selecionarAutista/{autistaId}", method = RequestMethod.GET)
    public Autista selecionar(@PathVariable("autistaId") Integer autistaId) {
        return dao.selecionar(autistaId);
    }

    @RequestMapping(value = "/inserirAutista", method = RequestMethod.POST)
    public Autista salvar(@RequestBody Autista autista) {
        return dao.salvar(autista);
    }

    @RequestMapping(value = "/alterarAutista", method = RequestMethod.POST)
    public Autista alterar(@RequestBody Autista autista) {
        return dao.alterar(autista);
    }

    @RequestMapping(value = "/removerAutista/{autistaId}", method = RequestMethod.GET)
    public void remover(@PathVariable("autistaId") Integer autistaId) {
        dao.remover(autistaId);
    }
}