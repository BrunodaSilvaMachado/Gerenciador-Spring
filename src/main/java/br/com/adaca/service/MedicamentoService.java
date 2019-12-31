package br.com.adaca.service;

import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.model.Autista;
import br.com.adaca.model.Medicamento;
import br.com.adaca.repository.AutistaRepository;
import br.com.adaca.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private AutistaRepository autistaRepository;

    /**
     * Lista todos os medicamentos cadastrados no banco de dados
     *
     * @return Lista com todos os medicamentos cadastrados
     */
    public List<Medicamento> listar() {
        List<Medicamento> medicamentos = new ArrayList<>();
        for (Medicamento medicamento : medicamentoRepository.findAll()) {
            medicamentos.add(medicamento);
        }
        if (medicamentos.isEmpty()) throw new NotFoundException("Nenhum medicamento encontrado!");
        return medicamentos;
    }

    /**
     * Lista todos os medicamentos cadastrados no banco de dados filtrados pela criança
     *
     * @param autistaId ID da criança para filtro da pesquisa
     * @return Lista com todos os medicamentos cadastrados
     */
    public List<Medicamento> listar(Integer autistaId) {
        List<Medicamento> medicamentos = medicamentoRepository.listByAutista(autistaId);
        if (medicamentos.isEmpty()) throw new NotFoundException("Nenhum medicamento encontrado!");
        return medicamentos;
    }

    /**
     * Lista todas as crianças que foram cadastradas como usando medicamentos
     *
     * @return Lista com todas as crianças que tem medicamento cadastrado
     */
    public List<Autista> listarAutistas() {
        List<Autista> autistas = autistaRepository.listAutistaMedicamentos();
        if (autistas.isEmpty()) throw new NotFoundException("Nenhuma criança encontrada!");
        return autistas;
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado
     *
     * @param id ID do medicamento já existente no banco de dados
     * @return Objeto do medicamento encontrado
     */
    public Medicamento selecionar(Integer id) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
        if (!medicamento.isPresent()) throw new NotFoundException("Medicamento não encontrado! Id: " + id);
        return medicamento.get();
    }

    /**
     * Salva o medicamento da criança no banco de dados
     *
     * @param medicamento Objeto preenchido do medicamento a ser gravado
     * @return Objeto do medicamento salvo
     */
    public Medicamento salvar(Medicamento medicamento) {
        if (medicamento.getId() != null) {
            Optional<Medicamento> op = medicamentoRepository.findById(medicamento.getId());
            if (op.isPresent()) throw new ConflictException("O medicamento já existe!");
        }
        return medicamentoRepository.save(medicamento);
    }

    /**
     * Salva uma lista de medicamentos da criança no banco de dados
     *
     * @param medicamentos Lista com os objetos preenchidos a serem gravados
     * @return Erro ou sucesso ao salvar
     */
    public Boolean salvar(List<Medicamento> medicamentos) {
        Iterable iterable = medicamentoRepository.saveAll(medicamentos);
        if (iterable.equals(null)) throw new ConflictException("Algum medicamento já existe!");
        return true;
    }

    //Conferir salvar com a lista

    /**
     * Altera o cadastro do medicamento no bando de dados
     *
     * @param medicamento Objeto preenchido com os dados já alterados
     * @return Objeto alterado
     */
    public Medicamento alterar(Medicamento medicamento) {
        Medicamento med = null;
        if (medicamento.getId() != null) {
            med = medicamentoRepository.save(medicamento);
        }
        return med;
    }

    /**
     * Efetua uma busca por ID do medicamento cadastrado e remove-o do banco de dados
     *
     * @param id ID do medicamento já existente no banco de dados
     */
    public void remover(Integer id) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
        if (!medicamento.isPresent()) {
            throw new NotFoundException("Id: " + id);
        } else {
            medicamentoRepository.delete(medicamento.get());
        }
    }

    /**
     * Remove o cadastro do medicamento do banco de dados
     *
     * @param medicamento Objeto preenchido do medicamento já existente no banco de dados
     */
    public void remover(Medicamento medicamento) {
        medicamentoRepository.delete(medicamento);
    }
}
