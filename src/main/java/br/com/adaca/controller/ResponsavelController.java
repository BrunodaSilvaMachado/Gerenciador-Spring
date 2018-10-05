package br.com.adaca.controller;

import br.com.adaca.model.Responsavel;
import br.com.adaca.repository.ResponsavelRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    /**
     * Salva o responsável da criança no banco de dados
     *
     * @param responsavel Objeto preenchido do responsável a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarResponsavel", method = RequestMethod.POST)
    public Responsavel salvar(@RequestBody Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    /**
     * Efetua uma busca por ID do responsável cadastrado e remove-o do banco de
     * dados
     *
     * @param responsavelId ID do responsável já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerResponsavel/{responsavelId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("responsavelId") Integer responsavelId) {
        Optional<Responsavel> responsavel = responsavelRepository.findById(responsavelId);
        if (responsavel.isPresent()) {
            responsavelRepository.delete(responsavel.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro do responsável do banco de dados
     *
     * @param responsavel Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerResponsavel", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Responsavel responsavel) {
        responsavelRepository.delete(responsavel);
        return true;
    }

    /**
     * Altera o cadastro do responsável no bando de dados
     *
     * @param responsavel Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarResponsavel", method = RequestMethod.POST)
    public Responsavel alterar(@RequestBody Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    /**
     * Efetua uma busca por ID do responsável cadastrado
     *
     * @param responsavelId ID do responsável já existente no banco de dados
     * @return Objeto do responsável encontrado
     */
    @RequestMapping(value = "/selecionarResponsavel/{responsavelId}", method = RequestMethod.GET)
    public Responsavel selecionar(@PathVariable("responsavelId") Integer responsavelId) {
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
    @RequestMapping(value = "/listarResponsaveis", method = RequestMethod.GET)
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
    @RequestMapping(value = "/listarResponsaveis/{autistaId}", method = RequestMethod.GET)
    public List<Responsavel> listar(@PathVariable("autistalId") Integer autistaId) {
        List<Responsavel> responsaveis = new ArrayList<>();
        Iterator<Responsavel> iterator = responsavelRepository.listByAutista(autistaId).iterator();
        while (iterator.hasNext()) {
            responsaveis.add(iterator.next());
        }
        return responsaveis;
    }
}
