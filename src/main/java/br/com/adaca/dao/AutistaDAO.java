package br.com.adaca.dao;

import br.com.adaca.entity.Autista;
import br.com.adaca.repository.AutistaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AutistaDAO {
    @Autowired
    private AutistaRepository autistaRepository;

    /**
     * Salva o cadastro da criança no banco de dados
     *
     * @param autista Objeto preenchido do cadastro a ser gravado
     * @return Erro ou sucesso ao salvar
     */
    public Autista salvar(Autista autista) {
        return autistaRepository.save(autista);
    }

    /**
     * Efetua uma busca por ID da criança cadastrada e remove-a do banco de
     * dados
     *
     * @param autistaId ID da criança já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer autistaId) {
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
    public Boolean remover(Autista autista) {
        autistaRepository.delete(autista);
        return true;
    }

    /**
     * Altera o cadastro da criança no bando de dados
     *
     * @param autista Objeto preenchido com os dados já alterados
     * @return Erro ou sucesso ao alterar
     */
    public Autista alterar(Autista autista) {
        return autistaRepository.save(autista);
    }

    /**
     * Efetua uma busca por ID da criança cadastrada
     *
     * @param autistaId ID da criança já existente no banco de dados
     * @return Objeto da criança encontrada
     */
    public Autista selecionar(Integer autistaId) {
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
    public List<Autista> listarNomesId() {
        return autistaRepository.listNamesId();
    }
}


//http://www.thejavageek.com/2017/06/16/crud-application-using-angular-4-spring-rest-web-services-spring-data-jpa/