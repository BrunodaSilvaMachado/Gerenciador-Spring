package br.com.adaca.dao;

import br.com.adaca.entity.Atividade;
import br.com.adaca.repository.AtividadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeDAO {

    @Autowired
    private AtividadeRepository atividadeRepository;

    /**
     * Salva o cadastro da atividade no banco de dados
     *
     * @param atividade Objeto preenchido do cadastro a ser gravado
     * @return Objeto salvo
     */
    public Atividade salvar(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    /**
     * Remove o cadastro da atividade do banco de dados
     *
     * @param atividade Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Atividade atividade) {
        atividadeRepository.delete(atividade);
        return true;
    }

    /**
     * Efetua uma busca por ID de atividade cadastrada e remove-a do banco de
     * dados
     *
     * @param atividadeId ID de atividade já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer atividadeId) {
        Optional<Atividade> atividade = atividadeRepository.findById(atividadeId);
        if (atividade.isPresent()) {
            atividadeRepository.delete(atividade.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro da atividade no bando de dados
     *
     * @param atividade Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Atividade alterar(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    /**
     * Efetua uma busca por ID de atividade cadastrada
     *
     * @param atividadeId ID de atividade já existente no banco de dados
     * @return Objeto da atividade encontrada
     */
    public Atividade selecionar(Integer atividadeId) {
        Optional<Atividade> atividade = atividadeRepository.findById(atividadeId);
        if (atividade.isPresent()) {
            return atividade.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todas as atividades cadastradas no banco de dados
     *
     * @return Lista com todos as atividades cadastradas
     */
    public List<Atividade> listar() {
        List<Atividade> atividades = new ArrayList<>();
        Iterator<Atividade> iterator = atividadeRepository.findAll().iterator();
        while (iterator.hasNext()) {
            atividades.add(iterator.next());
        }
        return atividades;
    }
}
