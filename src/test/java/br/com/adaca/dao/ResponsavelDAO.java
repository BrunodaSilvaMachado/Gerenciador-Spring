package br.com.adaca.dao;

import br.com.adaca.entity.Responsavel;
import br.com.adaca.repository.ResponsavelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelDAO {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    /**
     * Salva o responsável da criança no banco de dados
     *
     * @param responsavel Objeto preenchido do responsável a ser gravado
     * @return Objeto salvo
     */
    public Responsavel salvar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    /**
     * Remove o cadastro do responsável do banco de dados
     *
     * @param responsavel Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Responsavel responsavel) {
        responsavelRepository.delete(responsavel);
        return true;
    }

    /**
     * Efetua uma busca por ID do responsável cadastrado e remove-o do banco de
     * dados
     *
     * @param responsavelId ID do responsável já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer responsavelId) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(responsavelId);
        if (responsavel.isPresent()) {
            responsavelRepository.delete(responsavel.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro do responsável no bando de dados
     *
     * @param responsavel Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Responsavel alterar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    /**
     * Efetua uma busca por ID do responsável cadastrado
     *
     * @param responsavelId ID do responsável já existente no banco de dados
     * @return Objeto do responsável encontrado
     */
    public Responsavel selecionar(Integer responsavelId) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(responsavelId);
        if (responsavel.isPresent()) {
            return responsavel.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os responsáveis cadastrados no banco de dados
     *
     * @return Lista com todos os responsáveis cadastrados
     */
    public List<Responsavel> listar() {
        List<Responsavel> responsaveis = new ArrayList<>();
        Iterator<Responsavel> iterator = responsavelRepository.findAll().iterator();
        while (iterator.hasNext()) {
            responsaveis.add(iterator.next());
        }
        return responsaveis;
    }

    /**
     * Lista todos os responsáveis cadastrados no banco de dados filtrados pela
     * criança
     *
     * @param autistaId ID da criança para filtro da pesquisa
     * @return Lista com todos os responsáveis cadastrados
     */
    public List<Responsavel> listar(Integer autistaId) {
        return responsavelRepository.listByAutista(autistaId);
    }
}
