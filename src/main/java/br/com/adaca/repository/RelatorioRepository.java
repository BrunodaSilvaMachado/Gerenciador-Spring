package br.com.adaca.repository;

import br.com.adaca.model.Relatorio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface RelatorioRepository extends CrudRepository<Relatorio, Serializable> {

    @Query("SELECT r FROM Relatorio r WHERE r.idautista = :autistaId")
    List<Relatorio> findByAutista(@Param("autistaId") Integer autistaId);
}
