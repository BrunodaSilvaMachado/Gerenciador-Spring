package br.com.adaca.controller;

import java.util.List;

import br.com.adaca.dao.AdministradorDAO;
import br.com.adaca.entity.Administrador;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Gerenciador/Administradores")
public class AdministradorController {

    @Autowired
    private AdministradorDAO dao;

    @RequestMapping(value = "/listarAdministradores", method = RequestMethod.GET)
    public List<Administrador> listar() {
        return dao.listar();
    }

    @RequestMapping(value = "/selecionarAdministrador/{administradorId}", method = RequestMethod.GET)
    public Administrador selecionar(@PathVariable("administradorId") Integer administradorId) {
        return dao.selecionar(administradorId);
    }

    @RequestMapping(value = "/inserirAdministrador", method = RequestMethod.POST)
    public Administrador salvar(@RequestBody Administrador administrador) {
        return dao.salvar(administrador);
    }

    @RequestMapping(value = "/alterarAdministrador", method = RequestMethod.POST)
    public Administrador alterar(@RequestBody Administrador administrador) {
        return dao.alterar(administrador);
    }

    @RequestMapping(value = "/removerAdministrador/{administradorId}", method = RequestMethod.GET)
    public void remover(@PathVariable("administradorId") Integer administradorId) {
        dao.remover(administradorId);
    }
}
