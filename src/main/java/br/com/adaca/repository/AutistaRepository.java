package br.com.adaca.repository;

import br.com.adaca.entity.Autista;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AutistaRepository extends CrudRepository<Autista, Serializable> {

}
