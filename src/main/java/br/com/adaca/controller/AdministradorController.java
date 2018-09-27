package br.com.adaca.controller;

import br.com.adaca.model.Administrador;
import br.com.adaca.repository.AdministradorRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Administradores")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    /**
     * Salva o cadastro do administrador no banco de dados
     *
     * @param administrador Objeto preenchido do cadastro a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarAdministrador", method = RequestMethod.POST)
    public Administrador salvar(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado e remove-o do banco
     * de dados
     *
     * @param administradorId ID de administrador já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerAdministrador/{administradorId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("administradorId") Integer administradorId) {
        Optional<Administrador> administrador = administradorRepository.findById(administradorId);
        if (administrador.isPresent()) {
            administradorRepository.delete(administrador.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro do administrador do banco de dados
     *
     * @param administrador Objeto preenchido do cadastro já existente no banco
     * de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerAdministrador", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Administrador administrador) {
        administradorRepository.delete(administrador);
        return true;
    }

    /**
     * Altera o cadastro do administrador no bando de dados
     *
     * @param administrador Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarAdministrador", method = RequestMethod.POST)
    public Administrador alterar(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado
     *
     * @param administradorId ID de administrador já existente no banco de dados
     * @return Objeto do administrador encontrado
     */
    @RequestMapping(value = "/selecionarAdministrador/{administradorId}", method = RequestMethod.GET)
    public Administrador selecionar(@PathVariable("administradorId") Integer administradorId) {
        Optional<Administrador> administrador = administradorRepository.findById(administradorId);
        if (administrador.isPresent()) {
            return administrador.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os administradores cadastrados no banco de dados
     *
     * @return Lista com todos os administradores cadastrados
     */
    @RequestMapping(value = "/listarAdministradores", method = RequestMethod.GET)
    public List<Administrador> listar() {
        return administradorRepository.listAdministradores();
    }

    @RequestMapping(value = "/login/{usuario}/{senha}", method = RequestMethod.GET)
    public Administrador login(@PathVariable("usuario") String usuario,@PathVariable("senha") String senha) {
        return administradorRepository.login(usuario,senha);
    }
}
