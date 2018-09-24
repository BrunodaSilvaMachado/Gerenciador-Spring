package br.com.adaca.dao;

import br.com.adaca.entity.Labirinto;
import br.com.adaca.repository.LabirintoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class LabirintoDAO {

    @Autowired
    private LabirintoRepository labirintoRepository;

    /**
     * Salva o labirinto da criança no banco de dados
     *
     * @param labirinto Objeto preenchido do labirinto a ser gravado
     * @return Objeto salvo
     */
    public Labirinto salvar(Labirinto labirinto) {
        return labirintoRepository.save(labirinto);
    }

    /**
     * Remove o cadastro do labirinto do banco de dados
     *
     * @param labirinto Objeto preenchido do cadastro já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Labirinto labirinto) {
        labirintoRepository.delete(labirinto);
        return true;
    }

    /**
     * Efetua uma busca por ID do labirinto cadastrado e remove-o do banco de dados
     *
     * @param labirintoId ID do labirinto já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer labirintoId) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(labirintoId);
        if (labirinto.isPresent()) {
            labirintoRepository.delete(labirinto.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro do labirinto no bando de dados
     *
     * @param labirinto Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Labirinto alterar(Labirinto labirinto) {
        return labirintoRepository.save(labirinto);
    }

    /**
     * Efetua uma busca por ID do labirinto cadastrado
     *
     * @param labirintoId ID do labirinto já existente no banco de dados
     * @return Objeto do labirinto encontrado
     */
    public Labirinto selecionar(Integer labirintoId) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(labirintoId);
        if (labirinto.isPresent()) {
            return labirinto.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os labirintos cadastrados no banco de dados
     *
     * @return Lista com todos os labirintos cadastrados
     */
    public List<Labirinto> listar() {
        List<Labirinto> labirintos = new ArrayList<>();
        Iterator<Labirinto> iterator = labirintoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            labirintos.add(iterator.next());
        }
        return labirintos;
    }
}
