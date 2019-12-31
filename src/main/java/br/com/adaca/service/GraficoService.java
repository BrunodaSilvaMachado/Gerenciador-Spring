package br.com.adaca.service;

import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.model.Grafico;
import br.com.adaca.repository.GraficoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GraficoService {

    @Autowired
    private GraficoRepository graficoRepository;

    /**
     * Lista todos os gráficos cadastrados no banco de dados
     *
     * @return Lista com todos os gráficos cadastrados
     */
    public List<Grafico> listar() {
        List<Grafico> graficos = new ArrayList<>();
        for (Grafico grafico : graficoRepository.findAll()) {
            graficos.add(grafico);
        }
        if (graficos.isEmpty()) throw new NotFoundException("Nenhum gráfico encontrado!");
        return graficos;
    }

    /**
     * Lista todos os gráficos cadastrados no banco de dados filtrados pela criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os gráficos cadastrados
     */
    public List<Grafico> listar(Integer autistaId) {
        List<Grafico> graficos = graficoRepository.listByAutista(autistaId);
        if (graficos.isEmpty()) throw new NotFoundException("Nenhum gráfico encontrado!");
        return graficos;
    }

    /**
     * Efetua uma busca por ID do gráfico cadastrado
     *
     * @param id ID do gráfico já existente no banco de dados
     * @return Objeto do gráfico encontrado
     */
    public Grafico selecionar(Integer id) {
        Optional<Grafico> grafico = graficoRepository.findById(id);
        if (!grafico.isPresent()) throw new NotFoundException("Gráfico não encontrado! Id: " + id);
        return grafico.get();
    }

    /**
     * Salva o gráfico no banco de dados
     *
     * @param grafico Objeto preenchido do gráfico a ser gravado
     * @return Objeto do gráfico salvo
     */
    public Grafico salvar(Grafico grafico) {
        if (grafico.getId() != null) {
            Optional<Grafico> op = graficoRepository.findById(grafico.getId());
            if (op.isPresent()) throw new ConflictException("O gráfico já existe!");
        }
        return graficoRepository.save(grafico);
    }

    /**
     * Altera o cadastro do gráfico no bando de dados
     *
     * @param grafico Objeto preenchido com os dados já alterados
     * @return objeto do Grafico alterado
     */
    public Grafico alterar(Grafico grafico) {
        Grafico aut = null;
        if (grafico.getId() != null) {
            aut = graficoRepository.save(grafico);
        }
        return aut;
    }

    /**
     * Efetua uma busca por ID do gráfico cadastrado e remove-o do banco de dados
     *
     * @param id ID do reltório já existente no banco de dados
     */
    public void remover(Integer id) {
        Optional<Grafico> grafico = graficoRepository.findById(id);
        if (!grafico.isPresent()) {
            throw new NotFoundException("Id: " + id);
        } else {
            graficoRepository.delete(grafico.get());
        }
    }

    /**
     * Remove o gráfico do banco de dados
     *
     * @param grafico Objeto preenchido do cadastro já existente no banco de dados
     */
    public void remover(Grafico grafico) {
        graficoRepository.delete(grafico);
    }
}
