package br.com.adaca.repository;

import br.com.adaca.entity.Medicamento;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface MedicamentoRepository  extends CrudRepository<Medicamento, Serializable> {

}
