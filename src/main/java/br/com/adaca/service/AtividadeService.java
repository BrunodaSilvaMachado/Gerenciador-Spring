package br.com.adaca.service;

import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.model.Atividade;
import br.com.adaca.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    /**
    * Lista todas as atividades cadastradas no banco de dados
    *
    * @return Lista com todas as atividades cadastradas
    */
    public List<Atividade> listar() {
        List<Atividade> atividades = new ArrayList<>();
        for (Atividade atividade : atividadeRepository.findAll()) {
            atividades.add(atividade);
        }
        if (atividades.isEmpty()) throw new NotFoundException("Nenhuma atividade encontrada!");
        return (atividades);
    }

    /**
    * Efetua uma busca por ID de atividade cadastrada
    *
    * @param id ID de atividade já existente no banco de dados
    * @return Objeto da atividade encontrada
    */
    public Atividade selecionar(Integer id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        if (!atividade.isPresent()) throw new NotFoundException("Atividade não encontrada! Id: " + id);
        return (atividade.get());
    }

    /**
    * Salva o cadastro da atividade no banco de dados
    *
    * @param atividade Objeto preenchido do cadastro a ser gravado
    * @return Objeto da atividade salva
    */
    public Atividade salvar(Atividade atividade) {
        if (atividade.getId() != null) {
            Optional<Atividade> op = atividadeRepository.findById(atividade.getId());
            if (op.isPresent()) throw new ConflictException("A atividade já existe!");
        }
        return (atividadeRepository.save((atividade)));
    }

    /**
    * Altera o cadastro da atividade no bando de dados
    *
    * @param atividade Objeto preenchido com os dados já alterados
    * @return Objeto alterado
    */
    public Atividade alterar(Atividade atividade) {
        Atividade ati = null;
        if(atividade.getId() != null) {
            ati = (atividadeRepository.save((atividade)));
        }
        return ati;
    }

    /**
    * Efetua uma busca por ID de atividade cadastrada e remove-a do banco de dados
    *
    * @param id ID de atividade já existente no banco de dados
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
    */
    public void remover(Atividade atividade) {
        atividadeRepository.delete((atividade));
    }
}
