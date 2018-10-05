package br.com.adaca.repository;

import br.com.adaca.model.Autista;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AutistaRepository extends CrudRepository<Autista, Serializable> {

    @Query("SELECT a.id, a.nome FROM Autista a")
    public Iterable<Autista> listNamesId();

    @Query("SELECT a FROM Autista a WHERE a.medicamentos = 1")
    public  Iterable<Autista> listAutistaMedicamentos();
}
