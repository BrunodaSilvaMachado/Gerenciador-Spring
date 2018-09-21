package br.com.adaca.repository;

import br.com.adaca.entity.Labirinto;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface LabirintoRepository extends CrudRepository<Labirinto, Serializable> {

}
