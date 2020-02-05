package br.com.adaca.repository;

import br.com.adaca.model.RelatorioMisc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RelatorioMiscRepository extends CrudRepository<RelatorioMisc, Serializable> {
    @Query("select NEW br.com.adaca.model.RelatorioMisc(r.id, r.idsessao.id, r.idatividade.id, r.dicas, r.cliquecerto, r.cliqueerrado, time_to_sec(timediff(r.horafim,r.horainicio)), a.classificacao, a.nome) " +
            "from Resultado r, Atividade a where a.id = r.idatividade and r.idsessao in " +
            "(SELECT s.id from Sessao s where s.idtutor.id= :tutorId and s.idautista.id= :autistaId)")
    List<RelatorioMisc> matchRelatorioMisc(@Param("tutorId") Integer tutorId, @Param("autistaId") Integer autistaId);
}
