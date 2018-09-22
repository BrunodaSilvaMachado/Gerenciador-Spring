package br.com.adaca.dao;

import br.com.adaca.entity.Administrador;
import br.com.adaca.repository.AdministradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorDAO {
    @Autowired
    private AdministradorRepository administradorRepository;

    /**
     * Salva o cadastro do administrador no banco de dados
     *
     * @param administrador Objeto preenchido do cadastro a ser gravado
     * @return Objeto salvo
     */
    public Administrador salvar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado e remove-o do banco
     * de dados
     *
     * @param administradorId ID de administrador já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer administradorId) {
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
    public Boolean remover(Administrador administrador) {
        administradorRepository.delete(administrador);
        return true;
    }

    /**
     * Altera o cadastro do administrador no bando de dados
     *
     * @param administrador Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Administrador alterar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado
     *
     * @param administradorId ID de administrador já existente no banco de dados
     * @return Objeto do administrador encontrado
     */
    public Administrador selecionar(Integer administradorId) {
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
    public List<Administrador> listar() {
        List<Administrador> administradores = new ArrayList<>();
        Iterator<Administrador> iterator = administradorRepository.findAll().iterator();
        while (iterator.hasNext()) {
            administradores.add(iterator.next());
        }
        return administradores;
    }

    public Administrador login(String usuario, String senha) {
        return administradorRepository.login(usuario,senha);
    }
}