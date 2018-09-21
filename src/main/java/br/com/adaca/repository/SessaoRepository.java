package br.com.adaca.repository;

import br.com.adaca.entity.Sessao;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;


public interface SessaoRepository extends CrudRepository<Sessao, Serializable>  {

}
