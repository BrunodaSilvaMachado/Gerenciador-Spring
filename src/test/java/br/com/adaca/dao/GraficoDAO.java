package br.com.adaca.dao;

import br.com.adaca.entity.Grafico;
import br.com.adaca.repository.GraficoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class GraficoDAO {

    @Autowired
    private GraficoRepository graficoRepository;

    /**
     * Salva a gráfico da criança no banco de dados
     *
     * @param grafico Objeto preenchido do sessao a ser gravado
     * @return Objeto do gráfico salvo
     */
    public Grafico salvar(Grafico grafico) {
        return graficoRepository.save(grafico);
    }

    /**
     * Remove a sessão do banco de dados
     *
     * @param grafico Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Grafico grafico) {
        graficoRepository.delete(grafico);
        return true;
    }

    /**
     * Efetua uma busca por ID da sessão cadastrada e remove-a do banco de dados
     *
     * @param graficoId ID do reltório já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer graficoId) {
        Optional<Grafico> grafico = graficoRepository.findById(graficoId);
        if (grafico.isPresent()) {
            graficoRepository.delete(grafico.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro da sessão no bando de dados
     *
     * @param grafico Objeto preenchido com os dados já alterados
     * @return objeto do Grafico removido
     */
    public Grafico alterar(Grafico grafico) {
        return graficoRepository.save(grafico);
    }

    /**
     * Efetua uma busca por ID da sessao cadastrado
     *
     * @param graficoId ID do gráfico já existente no banco de dados
     * @return Objeto do gráfico encontrado
     */
    public Grafico selecionar(Integer graficoId) {
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
    public List<Grafico> listar(Integer autistaId) {
        return graficoRepository.listByAutista(autistaId);
    }
}
