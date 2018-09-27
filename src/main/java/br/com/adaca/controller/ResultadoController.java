package br.com.adaca.controller;

import br.com.adaca.model.Resultado;
import br.com.adaca.repository.ResultadoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Resultados")
public class ResultadoController {

    @Autowired
    private ResultadoRepository resultadoRepository;

    /**
     * Salva o resultado da criança no banco de dados
     *
     * @param resultado Objeto preenchido do resultado a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarResultado", method = RequestMethod.POST)
    public Resultado salvar(@RequestBody Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    /**
     * Efetua uma busca por ID do resultado cadastrado e remove-o do banco de
     * dados
     *
     * @param resultadoId ID do resultado já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerResultado/{resultadoId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("resultadoId") Integer resultadoId) {
        Optional<Resultado> resultado = resultadoRepository.findById(resultadoId);
        if (resultado.isPresent()) {
            resultadoRepository.delete(resultado.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro do resultado do banco de dados
     *
     * @param resultado Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerResultado", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Resultado resultado) {
        resultadoRepository.delete(resultado);
        return true;
    }

    /**
     * Altera o cadastro do resultado no bando de dados
     *
     * @param resultado Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarResultado", method = RequestMethod.POST)
    public Resultado alterar(@RequestBody Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    /**
     * Efetua uma busca por ID do resultado cadastrado
     *
     * @param resultadoId ID do resultado já existente no banco de dados
     * @return Objeto do resultado encontrado
     */
    @RequestMapping(value = "/selecionarResultado/{resultadoId}", method = RequestMethod.GET)
    public Resultado selecionar(@PathVariable("resultadoId") Integer resultadoId) {
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
    @RequestMapping(value = "/listarResultados", method = RequestMethod.GET)
    public List<Resultado> listar() {
        List<Resultado> resultados = new ArrayList<>();
        Iterator<Resultado> iterator = resultadoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            resultados.add(iterator.next());
        }
        return resultados;
    }
}
