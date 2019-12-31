package br.com.adaca.repository;

import br.com.adaca.model.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface AdministradorRepository extends CrudRepository<Administrador, Serializable> {

    @Query("SELECT a FROM Administrador a WHERE a.usuario = :usuario")
    Administrador findByUsuario(@Param("usuario") String usuario);
}
