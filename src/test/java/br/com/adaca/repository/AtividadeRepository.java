package br.com.adaca.repository;

import br.com.adaca.entity.Atividade;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AtividadeRepository extends CrudRepository<Atividade, Serializable> {

}
