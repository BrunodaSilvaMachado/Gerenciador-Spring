package br.com.adaca.repository;

import br.com.adaca.entity.Tutor;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;


public interface TutorRepository extends CrudRepository<Tutor, Serializable>  {

}
