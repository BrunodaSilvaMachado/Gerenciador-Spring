package br.com.adaca.repository;

import br.com.adaca.model.Relatorio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface RelatorioRepository extends CrudRepository<Relatorio, Serializable> {

    @Query("SELECT r FROM Relatorio r WHERE r.idautista = :autistaId")
    public Iterable<Relatorio> listByAutista(@Param("autistaId") Integer autistaId);
}
