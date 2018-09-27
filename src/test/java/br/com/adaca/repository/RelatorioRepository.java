package br.com.adaca.repository;

import br.com.adaca.entity.Relatorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface RelatorioRepository extends CrudRepository<Relatorio, Serializable> {

    @Query("SELECT r FROM Relatorio r WHERE r.idautista = :autistaId")
    public List<Relatorio> listByAutista(@Param("autistaId") Integer autistaId);
}
