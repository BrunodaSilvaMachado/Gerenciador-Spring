package br.com.adaca.repository;

import br.com.adaca.entity.Grafico;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface GraficoRepository extends CrudRepository<Grafico, Serializable> {

    @Query("SELECT g FROM Graficos g WHERE g.idautista = :autistaId")
    public List<Grafico> listByAutista(@Param("autistaId") Integer autistaId);
}
