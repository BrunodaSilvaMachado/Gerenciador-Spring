package br.com.adaca.repository;

import br.com.adaca.entity.Sessao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;


public interface SessaoRepository extends CrudRepository<Sessao, Serializable>  {

    @Query("SELECT s FROM Sessao s WHERE s.idautista = :autistaId")
    public List<Sessao> listByAutista(@Param("autistaId") Integer autistaId);
}
