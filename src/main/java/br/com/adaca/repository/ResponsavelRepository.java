package br.com.adaca.repository;

import br.com.adaca.entity.Responsavel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface ResponsavelRepository extends CrudRepository<Responsavel, Serializable> {

    @Query("SELECT r FROM Responsavel r WHERE r.idautista = :autistaId")
    public List<Responsavel> listByAutista(@Param("autistaId") Integer autistaId);
}
