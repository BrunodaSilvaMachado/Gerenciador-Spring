package br.com.adaca.dao;

import br.com.adaca.entity.Sessao;
import br.com.adaca.repository.SessaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoDAO {

    @Autowired
    private SessaoRepository sessaoRepository;

    /**
     * Salva a sessão da criança no banco de dados
     *
     * @param sessao Objeto preenchido do sessao a ser gravado
     * @return Objeto salvo
     */
    public Sessao salvar(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    /**
     * Remove a sessão do banco de dados
     *
     * @param sessao Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Sessao sessao) {
        sessaoRepository.delete(sessao);
        return true;
    }

    /**
     * Efetua uma busca por ID da sessão cadastrada e remove-a do banco de dados
     *
     * @param sessaoId ID do sessao já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer sessaoId) {
        Optional<Sessao> sessao = sessaoRepository.findById(sessaoId);
        if (sessao.isPresent()) {
            sessaoRepository.delete(sessao.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro da sessão no bando de dados
     *
     * @param sessao Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Sessao alterar(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    /**
     * Efetua uma busca por ID da sessao cadastrado
     *
     * @param sessaoId ID do sessao já existente no banco de dados
     * @return Objeto do sessao encontrado
     */
    public Sessao selecionar(Integer sessaoId) {
        Optional<Sessao> sessao = sessaoRepository.findById(sessaoId);
        if (sessao.isPresent()) {
            return sessao.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados
     *
     * @return Lista com todos os sessões cadastrados
     */
    public List<Sessao> listar() {
        List<Sessao> sessoes = new ArrayList<>();
        Iterator<Sessao> iterator = sessaoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            sessoes.add(iterator.next());
        }
        return sessoes;
    }

    /**
     * Lista todos os sessões cadastradas no banco de dados filtradas pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os sessões cadastrados
     */
    public List<Sessao> listar(Integer autistaId) {
        return sessaoRepository.listByAutista(autistaId);
    }
}
