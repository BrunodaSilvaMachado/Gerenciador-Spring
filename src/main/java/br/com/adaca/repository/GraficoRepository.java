package br.com.adaca.repository;

import br.com.adaca.entity.Grafico;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface GraficoRepository extends CrudRepository<Grafico, Serializable> {

}
