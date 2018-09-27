package br.com.adaca.repository;

import br.com.adaca.entity.Tutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;


public interface TutorRepository extends CrudRepository<Tutor, Serializable>  {

    @Query("SELECT t FROM Tutor t WHERE upper(t.usuario) = :usuario AND t.senha = :senha")
    public Tutor login(@Param("usuario") String usuario, @Param("senha") String senha);
}
