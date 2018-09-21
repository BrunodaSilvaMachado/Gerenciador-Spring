package br.com.adaca.repository;

import br.com.adaca.entity.Relatorio;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface RelatorioRepository extends CrudRepository<Relatorio, Serializable> {

}
