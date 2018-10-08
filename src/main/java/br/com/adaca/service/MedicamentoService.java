package br.com.adaca.service;

import br.com.adaca.model.Medicamento;
import br.com.adaca.model.Autista;
import br.com.adaca.repository.MedicamentoRepository;
import br.com.adaca.repository.AutistaRepository;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private AutistaRepository autistaRepository;

    public List<Medicamento> listar() {
        List<Medicamento> medicamentos = new ArrayList<>();
        Iterator<Medicamento> iterator = medicamentoRepository.findAll().iterator();
        while (iterator.hasNext()) {
            medicamentos.add(iterator.next());
        }
        if(medicamentos.isEmpty()) throw new NotFoundException("Nenhum medicamento encontrado!");
        return medicamentos;
    }

    public List<Medicamento> listar(Integer autistaId) {
        List<Medicamento> medicamentos = medicamentoRepository.listByAutista(autistaId);
        if(medicamentos.isEmpty()) throw new NotFoundException("Nenhum medicamento encontrado!");
        return medicamentos;
    }

    public List<Autista> listarAutistas() {
        List<Autista> autistas = autistaRepository.listAutistaMedicamentos();
        if(autistas.isEmpty()) throw new NotFoundException("Nenhuma criança encontrada!");
        return autistas;
    }

    public Medicamento selecionar(Integer id) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
        if (!medicamento.isPresent()) throw new NotFoundException("Medicamento não encontrado! Id: " + id);
        return medicamento.get();
    }

    public Medicamento salvar(Medicamento medicamento) {
        if (medicamento.getId() != null) {
            Optional<Medicamento> op = medicamentoRepository.findById(medicamento.getId());
            if (op.isPresent()) throw new ConflictException("O medicamento já existe!");
        }
        return medicamentoRepository.save(medicamento);
    }

    public Boolean salvar(List medicamentos) {
        Iterable iterable = medicamentoRepository.saveAll(medicamentos);
        if (iterable.equals(null)) throw new ConflictException("Algum medicamento já existe!");
        return true;
    }

    //Conferir salvar com a lista

    public Medicamento alterar(Medicamento medicamento) {
        Medicamento med = null;
        if(medicamento.getId() != null) {
            med = medicamentoRepository.save(medicamento);
        }
        return med;
    }

    public void remover(Integer id) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
        if (!medicamento.isPresent()){
            throw new NotFoundException("Id: " + id);
        }
        else {
            medicamentoRepository.delete(medicamento.get());
        }
    }

    public void remover(Medicamento medicamento) {
        medicamentoRepository.delete(medicamento);
    }
}
