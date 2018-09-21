package br.com.adaca.repository;

import br.com.adaca.entity.Configuracao;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface ConfiguracaoRepository extends CrudRepository<Configuracao, Serializable> {

}
