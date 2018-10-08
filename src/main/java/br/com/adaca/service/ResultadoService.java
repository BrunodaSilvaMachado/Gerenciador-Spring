package br.com.adaca.service;

import br.com.adaca.model.Resultado;
import br.com.adaca.repository.ResultadoRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    public List<Resultado> listar() {
        List<Resultado> resultados = new ArrayList<>();
        Iterator<Resultado> iterator = resultadoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            resultados.add(iterator.next());
        }
        if(resultados.isEmpty()) throw new NotFoundException("Nenhum resultado encontrado!");
        return resultados;
    }

    public List<Resultado> listar(Integer sessaoId) {
        List<Resultado> resultados = resultadoRepository.listBySessao(sessaoId);
        if(resultados.isEmpty()) throw new NotFoundException("Nenhum resultado encontrado!");
        return resultados;
    }

    public Resultado selecionar(Integer id) {
        Optional<Resultado> resultado = resultadoRepository.findById(id);
        if (!resultado.isPresent()) throw new NotFoundException("Resultado não encontrado! Id: " + id);
        return resultado.get();
    }

    public Resultado salvar(Resultado resultado) {
        if (resultado.getId() != null) {
            Optional<Resultado> op = resultadoRepository.findById(resultado.getId());
            if (op.isPresent()) throw new ConflictException("O resultado já existe!");
        }
        return resultadoRepository.save(resultado);
    }

    public Resultado alterar(Resultado resultado) {
        Resultado res = null;
        if(resultado.getId() != null) {
            res = resultadoRepository.save(resultado);
        }
        return res;
    }

    public void remover(Integer id) {
        Optional<Resultado> resultado = resultadoRepository.findById(id);
        if (!resultado.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            resultadoRepository.delete(resultado.get());
        }
    }

    public void remover(Resultado resultado) {
        resultadoRepository.delete(resultado);
    }
}
