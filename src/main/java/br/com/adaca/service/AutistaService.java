package br.com.adaca.service;

import br.com.adaca.mapper.AutistaMapper;
import br.com.adaca.model.Autista;
import br.com.adaca.dto.AutistaDTO;
import br.com.adaca.repository.AutistaRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutistaService {

    @Autowired
    private AutistaRepository autistaRepository;
    @Autowired
    private AutistaMapper autistaMapper;

    /**
    * Lista todas as crianças cadastradas no banco de dados
    *
    * @return Lista com todas as crianças cadastradas
    */
    public List<AutistaDTO> listar() {
        List<Autista> autistas = new ArrayList<>();
        for (Autista autista : autistaRepository.findAll()) {
            autistas.add(autista);
        }
        if (autistas.isEmpty()) throw new NotFoundException("Nenhuma criança encontrada!");
        return autistaMapper.toDto(autistas);
    }

    /*
        public List<AutistaDTO> listarNomesId() {
            List<Autista> autistas = autistaRepository.listNamesId();
            if (autistas.isEmpty()) throw new NotFoundException("Nenhuma criança encontrada!");
            return autistaMapper.toDto(autistas);
        }
    */

    /**
    * Efetua uma busca por ID da criança cadastrada
    *
    * @param id ID da criança já existente no banco de dados
    * @return Objeto da criança encontrada
    */
    public AutistaDTO selecionar(Integer id) {
        Optional<Autista> autista = autistaRepository.findById(id);
        if (autista.isEmpty()) throw new NotFoundException("Criança não encontrada! Id: " + id);
        return autistaMapper.toDto(autista.get());
    }

    /**
    * Salva o cadastro da criança no banco de dados
    *
    * @param autista Objeto preenchido do cadastro a ser gravado
    * @return Objeto da criança salva
    */
    public AutistaDTO salvar(AutistaDTO autista) {
        if (autista.getId() != null) {
            Optional<Autista> op = autistaRepository.findById(autista.getId());
            if (op.isPresent()) throw new ConflictException("A criança já existe!");
        }
        return autistaMapper.toDto(autistaRepository.save(autistaMapper.toEntity(autista)));
    }

    /**
     * Altera o cadastro da criança no bando de dados
     *
     * @param autista Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public AutistaDTO alterar(AutistaDTO autista) {
        AutistaDTO aut = null;
        if(autista.getId() != null) {
            aut = autistaMapper.toDto(autistaRepository.save(autistaMapper.toEntity(autista)));
        }
        return aut;
    }
    /**
     * Efetua uma busca por ID da criança cadastrada e remove-a do banco de dados
     *
     * @param id ID da criança já existente no banco de dados
     */
    public void remover(Integer id) {
        Optional<Autista> autista = autistaRepository.findById(id);
        if (autista.isEmpty()) {
            throw new NotFoundException("id: " + id);
        }
        else {
            autistaRepository.delete(autista.get());
        }
    }

    /**
     * Remove o cadastro da criança do banco de dados
     *
     * @param autista Objeto preenchido do cadastro já existente no banco de dados
     */
    public void remover(AutistaDTO autista) {
        autistaRepository.delete(autistaMapper.toEntity(autista));
    }
}
