package br.com.adaca.repository;

import br.com.adaca.entity.Resultado;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface ResultadoRepository extends CrudRepository<Resultado, Serializable> {

}
