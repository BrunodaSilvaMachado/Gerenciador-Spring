package br.com.adaca.service;

import br.com.adaca.model.Labirinto;
import br.com.adaca.repository.LabirintoRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class LabirintoService {

    @Autowired
    private LabirintoRepository labirintoRepository;

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
        if (labirintos.isEmpty()) throw new NotFoundException("Nenhum labirinto encontrado!");
        return labirintos;
    }

    /**
    * Efetua uma busca por ID do labirinto cadastrado
    *
    * @param id ID do labirinto já existente no banco de dados
    * @return Objeto do labirinto encontrado
    */
    public Labirinto selecionar(Integer id) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(id);
        if (!labirinto.isPresent()) throw new NotFoundException("Labirinto não encontrado! Id: " + id);
        return labirinto.get();
    }

    /**
    * Salva o labirinto resolvido pela criança no banco de dados
    *
    * @param labirinto Objeto preenchido do labirinto a ser gravado
    * @return Objeto salvo
    */
    public Labirinto salvar(Labirinto labirinto) {
        if (labirinto.getId() != null) {
            Optional<Labirinto> op = labirintoRepository.findById(labirinto.getId());
            if (op.isPresent()) throw new ConflictException("O labirinto já existe!");
        }
        return labirintoRepository.save(labirinto);
    }

    /**
    * Altera o cadastro do labirinto no bando de dados
    *
    * @param labirinto Objeto preenchido com os dados já alterados
    * @return Objeto alterado
    */
    public Labirinto alterar(Labirinto labirinto) {
        Labirinto aut = null;
        if(labirinto.getId() != null) {
            aut = labirintoRepository.save(labirinto);
        }
        return aut;
    }

    /**
    * Efetua uma busca por ID do labirinto cadastrado e remove-o do banco de dados
    *
    * @param id ID do labirinto já existente no banco de dados
    * @return
    */
    public void remover(Integer id) {
        Optional<Labirinto> labirinto = labirintoRepository.findById(id);
        if (!labirinto.isPresent()) {
            throw new NotFoundException("Id: " + id);
        }
        else {
            labirintoRepository.delete(labirinto.get());
        }
    }

    /**
    * Remove o cadastro do labirinto do banco de dados
    *
    * @param labirinto Objeto preenchido do cadastro já existente no banco de dados
    * @return
    */
    public void remover(Labirinto labirinto) {
        labirintoRepository.delete(labirinto);
    }
}
