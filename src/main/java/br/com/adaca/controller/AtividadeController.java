package br.com.adaca.controller;

import br.com.adaca.model.Atividade;
import br.com.adaca.repository.AtividadeRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Atividades")
public class AtividadeController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    /**
     * Salva o cadastro da atividade no banco de dados
     *
     * @param atividade Objeto preenchido do cadastro a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarAtividade", method = RequestMethod.POST)
    public Atividade salvar(@RequestBody Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    /**
     * Efetua uma busca por ID de atividade cadastrada e remove-a do banco de
     * dados
     *
     * @param atividadeId ID de atividade já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerAtividade/{atividadeId}", method = RequestMethod.GET)
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
     * Remove o cadastro da atividade do banco de dados
     *
     * @param atividade Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerAtividade", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Atividade atividade) {
        atividadeRepository.delete(atividade);
        return true;
    }

    /**
     * Altera o cadastro da atividade no bando de dados
     *
     * @param atividade Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarAtividade", method = RequestMethod.POST)
    public Atividade alterar(@RequestBody Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    /**
     * Efetua uma busca por ID de atividade cadastrada
     *
     * @param atividadeId ID de atividade já existente no banco de dados
     * @return Objeto da atividade encontrada
     */
    @RequestMapping(value = "/selecionarAdministrador/{atividadeId}", method = RequestMethod.GET)
    public Atividade selecionar(@PathVariable("atividadeId") Integer atividadeId) {
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
    @RequestMapping(value = "/listarAtividades", method = RequestMethod.GET)
    public List<Atividade> listar() {
        List<Atividade> atividades = new ArrayList<>();
        Iterator<Atividade> iterator = atividadeRepository.findAll().iterator();
        while (iterator.hasNext()) {
            atividades.add(iterator.next());
        }
        return atividades;
    }
}
