package br.com.adaca.repository;

import br.com.adaca.model.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface AdministradorRepository extends CrudRepository<Administrador, Serializable> {

    @Query("SELECT a FROM Administrador a WHERE a.usuario = :usuario AND a.senha = :senha")
    public Administrador login(@Param("usuario") String usuario, @Param("senha") String senha);

    @Query("SELECT a.id, a.nome, a.usuario, a.nivelacesso FROM Administrador a")
    public List<Administrador> listAdministradores();
}
