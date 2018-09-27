package br.com.adaca.dao;

import br.com.adaca.entity.Resultado;
import br.com.adaca.repository.ResultadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ResultadoDAO {

    @Autowired
    private ResultadoRepository resultadoRepository;

    /**
     * Salva o resultado da criança no banco de dados
     *
     * @param resultado Objeto preenchido do resultado a ser gravado
     * @return Objeto salvo
     */
    public Resultado salvar(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    /**
     * Remove o cadastro do resultado do banco de dados
     *
     * @param resultado Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Resultado resultado) {
        resultadoRepository.delete(resultado);
        return true;
    }

    /**
     * Efetua uma busca por ID do resultado cadastrado e remove-o do banco de
     * dados
     *
     * @param resultadoId ID do resultado já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer resultadoId) {
        Optional<Resultado> resultado = resultadoRepository.findById(resultadoId);
        if (resultado.isPresent()) {
            resultadoRepository.delete(resultado.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro do resultado no bando de dados
     *
     * @param resultado Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Resultado alterar(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    /**
     * Efetua uma busca por ID do resultado cadastrado
     *
     * @param resultadoId ID do resultado já existente no banco de dados
     * @return Objeto do resultado encontrado
     */
    public Resultado selecionar(Integer resultadoId) {
        Optional<Resultado> resultado = resultadoRepository.findById(resultadoId);
        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os resultadoes cadastrados no banco de dados
     *
     * @return Lista com todos os resultados cadastrados
     */
    public List<Resultado> listar() {
        List<Resultado> resultados = new ArrayList<>();
        Iterator<Resultado> iterator = resultadoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            resultados.add(iterator.next());
        }
        return resultados;
    }
}
