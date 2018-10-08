package br.com.adaca.repository;

import br.com.adaca.model.Medicamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface MedicamentoRepository  extends CrudRepository<Medicamento, Serializable> {

    @Query("SELECT m FROM Medicamento m WHERE m.idautista = :autistaId")
    public List<Medicamento> listByAutista(@Param("autistaId") Integer autistaId);
}
