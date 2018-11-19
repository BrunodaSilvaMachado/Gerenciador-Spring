package br.com.adaca.service;

import br.com.adaca.model.Responsavel;
import br.com.adaca.repository.ResponsavelRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

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
        if(responsaveis.isEmpty()) throw new NotFoundException("Nenhum responsável encontrado!");
        return responsaveis;
    }

    /**
    * Lista todos os responsáveis cadastrados no banco de dados filtrados pela
    * criança
    *
    * @param id ID da criança para filtro da pesquisa
    * @return Lista com todos os responsáveis cadastrados
    */
    public List<Responsavel> listar(Integer id) {
        List<Responsavel> responsaveis = responsavelRepository.listByAutista(id);
        if(responsaveis.isEmpty()) throw new NotFoundException("Nenhum responsável encontrado!");
        return responsaveis;
    }

    /**
    * Efetua uma busca por ID do responsável cadastrado
    *
    * @param id ID do responsável já existente no banco de dados
    * @return Objeto do responsável encontrado
    */
    public Responsavel selecionar(Integer id) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        if (!responsavel.isPresent()) throw new NotFoundException("Responsável não encontrado! Id: " + id);
        return responsavel.get();
    }

    /**
    * Salva o responsável da criança no banco de dados
    *
    * @param responsavel Objeto preenchido do responsável a ser gravado
    * @return Objeto salvo
    */
    public Responsavel salvar(Responsavel responsavel) {
        if (responsavel.getId() != null) {
            Optional<Responsavel> op = responsavelRepository.findById(responsavel.getId());
            if (op.isPresent()) throw new ConflictException("O relatório já existe!");
        }
        return responsavelRepository.save(responsavel);
    }

    /**
    * Altera o cadastro do responsável no bando de dados
    *
    * @param responsavel Objeto preenchido com os dados já alterados
    * @return Objeto alterado
    */
    public Responsavel alterar(Responsavel responsavel) {
        Responsavel res = null;
        if(responsavel.getId() != null) {
            res = responsavelRepository.save(responsavel);
        }
        return res;
    }

    /**
    * Efetua uma busca por ID do responsável cadastrado e remove-o do banco de dados
    *
    * @param id ID do responsável já existente no banco de dados
    * @return
    */
    public void remover(Integer id) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        if (!responsavel.isPresent()) {
            throw new NotFoundException("Id: " + id);
        }
        else {
            responsavelRepository.delete(responsavel.get());
        }
    }

    /**
    * Remove o cadastro do responsável do banco de dados
    *
    * @param responsavel Objeto preenchido do cadastro já existente no banco de dados
    * @return
    */
    public void remover(Responsavel responsavel) {
        responsavelRepository.delete(responsavel);
    }
}
