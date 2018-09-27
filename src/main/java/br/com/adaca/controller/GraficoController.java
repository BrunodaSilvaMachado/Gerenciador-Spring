package br.com.adaca.controller;

import br.com.adaca.model.Grafico;
import br.com.adaca.repository.GraficoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Graficos")
public class GraficoController {

    @Autowired
    private GraficoRepository graficoRepository;

    /**
     * Salva a gráfico da criança no banco de dados
     *
     * @param grafico Objeto preenchido do sessao a ser gravado
     * @return Objeto do gráfico salvo
     */
    @RequestMapping(value = "/salvarGrafico", method = RequestMethod.POST)
    public Grafico salvar(@RequestBody Grafico grafico) {
        return graficoRepository.save(grafico);
    }

    /**
     * Efetua uma busca por ID da sessão cadastrada e remove-a do banco de dados
     *
     * @param graficoId ID do reltório já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerGrafico/{graficoId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("graficoId") Integer graficoId) {
        Optional<Grafico> grafico = graficoRepository.findById(graficoId);
        if (grafico.isPresent()) {
            graficoRepository.delete(grafico.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a sessão do banco de dados
     *
     * @param grafico Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerGrafico", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Grafico grafico) {
        graficoRepository.delete(grafico);
        return true;
    }

    /**
     * Altera o cadastro da sessão no bando de dados
     *
     * @param grafico Objeto preenchido com os dados já alterados
     * @return objeto do Grafico removido
     */
    @RequestMapping(value = "/alterarGrafico", method = RequestMethod.POST)
    public Grafico alterar(@RequestBody Grafico grafico) {
        return graficoRepository.save(grafico);
    }

    /**
     * Efetua uma busca por ID da sessao cadastrado
     *
     * @param graficoId ID do gráfico já existente no banco de dados
     * @return Objeto do gráfico encontrado
     */
    @RequestMapping(value = "/selecionarGrafico/{graficoId}", method = RequestMethod.GET)
    public Grafico selecionar(@PathVariable("graficoId") Integer graficoId) {
        Optional<Grafico> grafico = graficoRepository.findById(graficoId);
        if (grafico.isPresent()) {
            return grafico.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados
     *
     * @return Lista com todos os gráficos cadastrados
     */
    @RequestMapping(value = "/listarGraficos", method = RequestMethod.GET)
    public List<Grafico> listar() {
        List<Grafico> graficos = new ArrayList<>();
        Iterator<Grafico> iterator = graficoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            graficos.add(iterator.next());
        }
        return graficos;
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados filtradas pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os gráficos cadastrados
     */
    @RequestMapping(value = "/listarGraficos/autistaId", method = RequestMethod.GET)
    public List<Grafico> listar(@PathVariable("autistaId")Integer autistaId) {
        return graficoRepository.listByAutista(autistaId);
    }
}
