package br.com.adaca.service;

import br.com.adaca.model.Administrador;
import br.com.adaca.repository.AdministradorRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    /**
    * Lista todos os administradores cadastrados no banco de dados
    *
    * @return Lista com todos os administradores cadastrados
    */
    public List<Administrador> listar() {
        List<Administrador> administradores = administradorRepository.listAdministradores();
        if (administradores.isEmpty()) throw new NotFoundException("Nenhum administrador encontrado!");
        return administradores;
    }

    /**
    * Efetua uma busca por ID de administrador cadastrado
    *
    * @param id ID de administrador já existente no banco de dados
    * @return Objeto do administrador encontrado
    */
    public Administrador selecionar(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) throw new NotFoundException("Administrador não encontrado! Id: " + id);
        return administrador.get();
    }

    /**
    * Salva o cadastro do administrador no banco de dados
    *
    * @param administrador Objeto preenchido do cadastro a ser gravado
    * @return Objeto do administador salvo
    */
    public Administrador salvar(Administrador administrador) {
        if (administrador.getId() != null) {
            Optional<Administrador> op = administradorRepository.findById(administrador.getId());
            if (op.isPresent()) throw new ConflictException("O administrador já existe!");
        }
        return administradorRepository.save(administrador);
    }

    /**
    * Altera o cadastro do administrador no bando de dados
    *
    * @param administrador Objeto preenchido com os dados já alterados
    * @return Objeto do administrador alterado
    */
    public Administrador alterar(Administrador administrador) {
        Administrador adm = null;
        if(administrador.getId() != null) {
            adm = administradorRepository.save(administrador);
        }
        return adm;
    }


    /**
    * Efetua uma busca por ID de administrador cadastrado e remove-o do banco
    * de dados
    *
    * @param id ID de administrador já existente no banco de dados
    * @return
    */
    public void remover(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) {
            throw new NotFoundException("id: " + id);
        }
        else {
            administradorRepository.delete(administrador.get());
        }
    }

    /**
    * Remove o cadastro do administrador do banco de dados
    *
    * @param administrador Objeto preenchido do cadastro já existente no banco de dados
    * @return
    */
    public void remover(Administrador administrador) {
        administradorRepository.delete(administrador);
    }
}
