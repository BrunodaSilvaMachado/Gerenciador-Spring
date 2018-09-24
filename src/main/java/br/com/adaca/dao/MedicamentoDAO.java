package br.com.adaca.dao;

import br.com.adaca.entity.Medicamento;
import br.com.adaca.entity.Autista;
import br.com.adaca.repository.MedicamentoRepository;
import br.com.adaca.repository.AutistaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoDAO {

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
    public Medicamento salvar(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    /**
     * Salva uma lista de medicamentos da criança no banco de dados
     *
     * @param medicamentos Lista com os objetos preenchidos a serem gravados
     * @return Erro ou sucesso ao salvar
     */
    public Boolean salvar(List medicamentos) {
        for (Iterator<Medicamento> it = medicamentos.iterator(); it.hasNext();) {
            medicamentoRepository.save(it.next());
        }
        return true;
    }

    /**
     * Remove o cadastro do medicamento do banco de dados
     *
     * @param medicamento Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Medicamento medicamento) {
        medicamentoRepository.delete(medicamento);
        return true;
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado e remove-o do banco de
     * dados
     *
     * @param medicamentoId ID do medicamento já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    public Boolean remover(Integer medicamentoId) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(medicamentoId);
        if (medicamento.isPresent()) {
            medicamentoRepository.delete(medicamento.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Altera o cadastro do medicamento no bando de dados
     *
     * @param medicamento Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Medicamento alterar(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado
     *
     * @param medicamentoId ID do medicamento já existente no banco de dados
     * @return Objeto do medicamento encontrado
     */
    public Medicamento selecionar(Integer medicamentoId) {
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
    public List<Medicamento> listar(Integer autistaId) {
        return medicamentoRepository.listByAutista(autistaId);
    }

    /**
     * Lista todas as crianças que foram cadastradas como usando medicamentos
     *
     * @return Lista com todas as crianças que tem medicamento cadastrado
     */
    public List<Autista> listarAutistas() {
        return autistaRepository.listAutistaMedicamentos();
    }
}
