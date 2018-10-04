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

    public List<Administrador> listar() {
        List administradores = administradorRepository.listAdministradores();
        if (administradores.isEmpty()) throw new NotFoundException("Nenhum administrador encontrado!");
        return administradores;
    }

    public Administrador selecionar(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) throw new NotFoundException("Administrador não encontrado! Id: " + id);
        return administrador.get();
    }

    public Administrador salvar(Administrador administrador) {
        if (administrador.getId() != null) {
            Optional<Administrador> op = administradorRepository.findById(administrador.getId());
            if (op.isPresent()) throw new ConflictException("O administrador já existe!");
        }
        return administradorRepository.save(administrador);
    }

    public Administrador alterar(Administrador administrador) {
        Administrador adm = null;
        if(administrador.getId() != null) {
            adm = administradorRepository.save(administrador);
        }
        return adm;
    }

    public void remover(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()){
            throw new NotFoundException("id: " + id);
        }
        else {
            administradorRepository.delete(administrador.get());
        }
    }

    public void remover(Administrador administrador) {
        administradorRepository.delete(administrador);
    }
}
