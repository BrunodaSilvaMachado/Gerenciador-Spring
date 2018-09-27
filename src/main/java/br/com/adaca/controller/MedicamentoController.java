package br.com.adaca.controller;

import br.com.adaca.model.Medicamento;
import br.com.adaca.model.Autista;
import br.com.adaca.repository.MedicamentoRepository;
import br.com.adaca.repository.AutistaRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Gerenciador/Medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private AutistaRepository autistaRepository;

    /**
     * Salva o medicamento da criança no banco de dados
     *
     * @param medicamento Objeto preenchido do medicamento a ser gravado
     * @return Objeto salvo
     */
    @RequestMapping(value = "/salvarMedicamento", method = RequestMethod.POST)
    public Medicamento salvar(@RequestBody Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    /**
     * Salva uma lista de medicamentos da criança no banco de dados
     *
     * @param medicamentos Lista com os objetos preenchidos a serem gravados
     * @return Erro ou sucesso ao salvar
     */
    @RequestMapping(value = "/salvarMedicamentos", method = RequestMethod.POST)
    public Boolean salvar(@RequestBody List medicamentos) {
        medicamentoRepository.saveAll(medicamentos);
        return true;
    }

    //Conferir salvar com a lista

    /**
     * Efetua uma busca por ID do medicamento cadastrado e remove-o do banco de
     * dados
     *
     * @param medicamentoId ID do medicamento já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerMedicamento/{medicamentoId}", method = RequestMethod.GET)
    public Boolean remover(@PathVariable("medicamentoId") Integer medicamentoId) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(medicamentoId);
        if (medicamento.isPresent()) {
            medicamentoRepository.delete(medicamento.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove o cadastro do medicamento do banco de dados
     *
     * @param medicamento Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @RequestMapping(value = "/removerMedicamento", method = RequestMethod.POST)
    public Boolean remover(@RequestBody Medicamento medicamento) {
        medicamentoRepository.delete(medicamento);
        return true;
    }

    /**
     * Altera o cadastro do medicamento no bando de dados
     *
     * @param medicamento Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    @RequestMapping(value = "/alterarMedicamento", method = RequestMethod.POST)
    public Medicamento alterar(@RequestBody Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado
     *
     * @param medicamentoId ID do medicamento já existente no banco de dados
     * @return Objeto do medicamento encontrado
     */
    @RequestMapping(value = "/selecionarMedicamento/{medicamentoId}", method = RequestMethod.GET)
    public Medicamento selecionar(@PathVariable("medicamentoId") Integer medicamentoId) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(medicamentoId);
        if (medicamento.isPresent()) {
            return medicamento.get();
        } else {
            return null;
        }
    }

    /**
     * Lista todos os medicamentos cadastrados no banco de dados
     *
     * @return Lista com todos os medicamentos cadastrados
     */
    @RequestMapping(value = "/listarMedicamentos", method = RequestMethod.GET)
    public List<Medicamento> listar() {
        List<Medicamento> medicamentos = new ArrayList<>();
        Iterator<Medicamento> iterator = medicamentoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            medicamentos.add(iterator.next());
        }
        return medicamentos;
    }

    /**
     * Lista todos os medicamentos cadastrados no banco de dados filtrados pela
     * criança
     *
     * @param autistaId ID da criança para filtro da pesquisa
     * @return Lista com todos os medicamentos cadastrados
     */
    @RequestMapping(value = "/listarMedicamentos/{autistaId}", method = RequestMethod.GET)
    public List<Medicamento> listar(@PathVariable("autistaId") Integer autistaId) {
        return medicamentoRepository.listByAutista(autistaId);
    }

    /**
     * Lista todas as crianças que foram cadastradas como usando medicamentos
     *
     * @return Lista com todas as crianças que tem medicamento cadastrado
     */
    @RequestMapping(value = "/listarAutistas", method = RequestMethod.GET)
    public List<Autista> listarAutistas() {
        return autistaRepository.listAutistaMedicamentos();
    }
}
