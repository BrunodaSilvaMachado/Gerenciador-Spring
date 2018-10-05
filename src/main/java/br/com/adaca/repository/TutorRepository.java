package br.com.adaca.repository;

import br.com.adaca.model.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface TutorRepository extends CrudRepository<Tutor, Serializable>  {

    @Query("SELECT t FROM Tutor t WHERE upper(t.usuario) = :usuario AND t.senha = :senha")
    public Tutor login(@Param("usuario") String usuario, @Param("senha") String senha);

    @Query("SELECT t.id, t.nome, t.usuario, t.area FROM Tutor t")
    public Iterable<Tutor> listTutores();
}
