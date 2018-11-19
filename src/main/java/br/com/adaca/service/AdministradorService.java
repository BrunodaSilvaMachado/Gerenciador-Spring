package br.com.adaca.service;

import br.com.adaca.mapper.AdministradorMapper;
import br.com.adaca.model.Administrador;
import br.com.adaca.dto.AdministradorDTO;
import br.com.adaca.repository.AdministradorRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private AdministradorMapper administradorMapper;

    /**
    * Lista todos os administradores cadastrados no banco de dados
    *
    * @return Lista com todos os administradores cadastrados
    */
    public List<AdministradorDTO> listar() {
        List<Administrador> administradores = new ArrayList<>();
        Iterator<Administrador> iterator = administradorRepository.findAll().iterator();
        while (iterator.hasNext()) {
            administradores.add(iterator.next());
        }
        if (administradores.isEmpty()) throw new NotFoundException("Nenhum administrador encontrado!");
        return administradorMapper.toDto(administradores);
    }

    /**
    * Efetua uma busca por ID de administrador cadastrado
    *
    * @param id ID de administrador já existente no banco de dados
    * @return Objeto do administrador encontrado
    */
    public AdministradorDTO selecionar(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) throw new NotFoundException("Administrador não encontrado! Id: " + id);
        return administradorMapper.toDto(administrador.get());
    }

    /**
    * Salva o cadastro do administrador no banco de dados
    *
    * @param administrador Objeto preenchido do cadastro a ser gravado
    * @return Objeto do administador salvo
    */
    public AdministradorDTO salvar(AdministradorDTO administrador) {
        if (administrador.getId() != null) {
            Optional<Administrador> op = administradorRepository.findById(administrador.getId());
            if (op.isPresent()) throw new ConflictException("O administrador já existe!");
        }
        return administradorMapper.toDto(administradorRepository.save(administradorMapper.toEntity(administrador)));
    }

    /**
    * Altera o cadastro do administrador no bando de dados
    *
    * @param administrador Objeto preenchido com os dados já alterados
    * @return Objeto do administrador alterado
    */
    public AdministradorDTO alterar(AdministradorDTO administrador) {
        Administrador adm = null;
        if(administrador.getId() != null) {
            adm = administradorRepository.save(administradorMapper.toEntity(administrador));
        }
        return administradorMapper.toDto(adm);
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
    public void remover(AdministradorDTO administrador) {
        administradorRepository.delete(administradorMapper.toEntity(administrador));
    }
}
