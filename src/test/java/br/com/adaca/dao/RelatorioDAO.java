package br.com.adaca.dao;

import br.com.adaca.entity.Relatorio;
import br.com.adaca.repository.RelatorioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioDAO {

    @Autowired
    private RelatorioRepository relatorioRepository;

    /**
     * Salva o relatorio da criança no banco de dados
     *
     * @param relatorio Objeto preenchido do relatorio a ser gravado
     * @return Objeto salvo
     */
    public Relatorio salvar(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    /**
     * Remove o relatório do banco de dados
     *
     * @param relatorio Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Relatorio relatorio) {
        relatorioRepository.delete(relatorio);
        return true;
    }

    /**
     * Efetua uma busca por ID do relatorio cadastrada e remove-a do banco de dados
     *
     * @param relatorioId ID do relatorio já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer relatorioId) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(relatorioId);
        if (relatorio.isPresent()) {
            relatorioRepository.delete(relatorio.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro do relatorio no bando de dados
     *
     * @param relatorio Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Relatorio alterar(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    /**
     * Efetua uma busca por ID do relatorio cadastrado
     *
     * @param relatorioId ID do relatorio já existente no banco de dados
     * @return Objeto do relatorio encontrado
     */
    public Relatorio selecionar(Integer relatorioId) {
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
    public List<Relatorio> listar(Integer autistaId) {
        return relatorioRepository.listByAutista(autistaId);
    }
}
