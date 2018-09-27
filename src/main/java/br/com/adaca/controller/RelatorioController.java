package br.com.adaca.controller;

import br.com.adaca.model.Relatorio;
import br.com.adaca.repository.RelatorioRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioRepository relatorioRepository;

    /**
     * Salva o relatorio da criança no banco de dados
     *
     * @param relatorio Objeto preenchido do relatorio a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarRelatorio", method = RequestMethod.POST)
    public Relatorio salvar(@RequestBody Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    /**
     * Efetua uma busca por ID do relatorio cadastrada e remove-a do banco de dados
     *
     * @param relatorioId ID do relatorio já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerRelatorio/{relatorioId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("relatorioId") Integer relatorioId) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(relatorioId);
        if (relatorio.isPresent()) {
            relatorioRepository.delete(relatorio.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o relatório do banco de dados
     *
     * @param relatorio Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerRelatorio", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Relatorio relatorio) {
        relatorioRepository.delete(relatorio);
        return true;
    }

    /**
     * Altera o cadastro do relatorio no bando de dados
     *
     * @param relatorio Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarRelatorio", method = RequestMethod.POST)
    public Relatorio alterar(@RequestBody Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    /**
     * Efetua uma busca por ID do relatorio cadastrado
     *
     * @param relatorioId ID do relatorio já existente no banco de dados
     * @return Objeto do relatorio encontrado
     */
    @RequestMapping(value = "/selecionarRelatorio/{relatorioId}", method = RequestMethod.GET)
    public Relatorio selecionar(@PathVariable("relatorioId") Integer relatorioId) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(relatorioId);
        if (relatorio.isPresent()) {
            return relatorio.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados
     *
     * @return Lista com todos os relatorios cadastrados
     */
    @RequestMapping(value = "/listarRelatorios", method = RequestMethod.GET)
    public List<Relatorio> listar() {
        List<Relatorio> relatorios = new ArrayList<>();
        Iterator<Relatorio> iterator = relatorioRepository.findAll().iterator();
        while (iterator.hasNext()) {
            relatorios.add(iterator.next());
        }
        return relatorios;
    }

    /**
     * Lista todos os relatórios cadastrados no banco de dados filtradas pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os relatorios cadastrados
     */
    @RequestMapping(value = "/listarRelatorios/{autistaId}", method = RequestMethod.GET)
    public List<Relatorio> listar(@PathVariable("autistaId") Integer autistaId) {
        return relatorioRepository.listByAutista(autistaId);
    }
}
