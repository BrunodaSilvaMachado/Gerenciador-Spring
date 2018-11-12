package br.com.adaca.service;

import br.com.adaca.mapper.AtividadeMapper;
import br.com.adaca.model.Atividade;
import br.com.adaca.dto.AtividadeDTO;
import br.com.adaca.repository.AtividadeRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;
    @Autowired
    private AtividadeMapper atividadeMapper;

    /**
    * Lista todas as atividades cadastradas no banco de dados
    *
    * @return Lista com todas as atividades cadastradas
    */
    public List<AtividadeDTO> listar() {
        List<Atividade> atividades = new ArrayList<>();
        Iterator<Atividade> iterator = atividadeRepository.findAll().iterator();
        while (iterator.hasNext()) {
            atividades.add(iterator.next());
        }
        if (atividades.isEmpty()) throw new NotFoundException("Nenhuma atividade encontrada!");
        return atividadeMapper.toDto(atividades);
    }

    /**
    * Efetua uma busca por ID de atividade cadastrada
    *
    * @param id ID de atividade já existente no banco de dados
    * @return Objeto da atividade encontrada
    */
    public AtividadeDTO selecionar(Integer id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (!atividade.isPresent()) throw new NotFoundException("Atividade não encontrada! Id: " + id);
        return atividadeMapper.toDto(atividade.get());
    }

    /**
    * Salva o cadastro da atividade no banco de dados
    *
    * @param atividade Objeto preenchido do cadastro a ser gravado
    * @return Objeto da atividade salva
    */
    public AtividadeDTO salvar(AtividadeDTO atividade) {
        if (atividade.getId() != null) {
            Optional<Atividade> op = atividadeRepository.findById(atividade.getId());
            if (op.isPresent()) throw new ConflictException("A atividade já existe!");
        }
        return atividadeMapper.toDto(atividadeRepository.save(atividadeMapper.toEntity(atividade)));
    }

    /**
    * Altera o cadastro da atividade no bando de dados
    *
    * @param atividade Objeto preenchido com os dados já alterados
    * @return Objeto alterado
    */
    public AtividadeDTO alterar(AtividadeDTO atividade) {
        Atividade ati = null;
        if(atividade.getId() != null) {
            ati = atividadeRepository.save(atividadeMapper.toEntity(atividade));
        }
        return atividadeMapper.toDto(ati);
    }

    /**
    * Efetua uma busca por ID de atividade cadastrada e remove-a do banco de dados
    *
    * @param id ID de atividade já existente no banco de dados
    * @return
    */
    public void remover(Integer id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (!atividade.isPresent()) {
            throw new NotFoundException("id: " + id);
        }
        else {
            atividadeRepository.delete(atividade.get());
        }
    }

    /**
    * Remove o cadastro da atividade do banco de dados
    *
    * @param atividade Objeto preenchido do cadastro já existente no banco de dados
    * @return
    */
    public void remover(AtividadeDTO atividade) {
        atividadeRepository.delete(atividadeMapper.toEntity(atividade));
    }
}
