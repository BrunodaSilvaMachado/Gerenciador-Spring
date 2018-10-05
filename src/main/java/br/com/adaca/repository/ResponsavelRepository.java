package br.com.adaca.repository;

import br.com.adaca.model.Responsavel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Serializable> {

    @Query("SELECT r FROM Responsavel r WHERE r.idautista = :autistaId")
    public Iterable<Responsavel> listByAutista(@Param("autistaId") Integer autistaId);
}
