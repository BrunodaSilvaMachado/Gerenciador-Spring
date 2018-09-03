package br.com.adaca.repository;

import br.com.adaca.entity.Autista;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface AutistaRepository extends CrudRepository<Autista, Serializable> {

    @Query("SELECT a.id, a.nome FROM Autista a")
    public List<Autista> listNamesId();
}
