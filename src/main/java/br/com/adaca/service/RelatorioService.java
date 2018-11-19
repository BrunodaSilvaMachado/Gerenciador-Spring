package br.com.adaca.service;

import br.com.adaca.model.Relatorio;
import br.com.adaca.repository.RelatorioRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {
    
    @Autowired
    private RelatorioRepository relatorioRepository;

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
        if(relatorios.isEmpty()) throw new NotFoundException("Nenhum relatório encontrado!");
        return relatorios;
    }

    /**
    * Lista todos os relatórios cadastrados no banco de dados filtradas pela criança
    *
    * @param autistaId ID da criança para o filtro
    * @return Lista com todos os relatorios cadastrados
    */
    public List<Relatorio> listar(Integer autistaId) {
        List<Relatorio> relatorios = relatorioRepository.listByAutista(autistaId);
        if(relatorios.isEmpty()) throw new NotFoundException("Nenhum relatório encontrado!");
        return relatorios;
    }

    /**
    * Efetua uma busca por ID do relatorio cadastrado
    *
    * @param id ID do relatorio já existente no banco de dados
    * @return Objeto do relatorio encontrado
    */
    public Relatorio selecionar(Integer id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);
        if (!relatorio.isPresent()) throw new NotFoundException("Relatório não encontrado! Id: " + id);
        return relatorio.get();
    }

    /**
    * Salva o relatorio da criança no banco de dados
    *
    * @param relatorio Objeto preenchido do relatorio a ser gravado
    * @return Objeto salvo
    */
    public Relatorio salvar(Relatorio relatorio) {
        if (relatorio.getId() != null) {
            Optional<Relatorio> op = relatorioRepository.findById(relatorio.getId());
            if (op.isPresent()) throw new ConflictException("O relatório já existe!");
        }
        return relatorioRepository.save(relatorio);
    }

    /**
    * Altera o cadastro do relatorio no bando de dados
    *
    * @param relatorio Objeto preenchido com os dados já alterados
    * @return Objeto alterado
    */
    public Relatorio alterar(Relatorio relatorio) {
        Relatorio rel = null;
        if(relatorio.getId() != null) {
            rel = relatorioRepository.save(relatorio);
        }
        return rel;
    }

    /**
    * Efetua uma busca por ID do relatorio cadastrada e remove-a do banco de dados
    *
    * @param id ID do relatorio já existente no banco de dados
    * @return
    */
    public void remover(Integer id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);
        if (!relatorio.isPresent()) {
            throw new NotFoundException("Id: " + id);
        }
        else {
            relatorioRepository.delete(relatorio.get());
        }
    }

    /**
    * Remove o relatório do banco de dados
    *
    * @param relatorio Objeto preenchido do cadastro já existente no banco de dados
    * @return
    */
    public void remover(Relatorio relatorio) {
        relatorioRepository.delete(relatorio);
    }
}
