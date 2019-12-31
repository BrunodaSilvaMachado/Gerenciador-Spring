package br.com.adaca.repository;

import br.com.adaca.model.RelatorioMisc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RelatorioMiscRepository extends JpaRepository<RelatorioMisc, Serializable> {
    @Query(value = "SELECT tb_resultado.ID, IDSESSAO, IDATIVIDADE, DICAS, CLIQUECERTO, CLIQUEERRADO, time_to_sec(timediff(HORAFIM,HORAINICIO)) as TEMPO, NOME, CLASSIFICACAO " +
            "FROM tb_atividade, tb_resultado WHERE tb_atividade.ID=IDATIVIDADE AND IDSESSAO in " +
            "(SELECT id FROM tb_sessao WHERE IDTUTOR=idtutor AND IDAUTISTA=idautista) order by IDATIVIDADE;", nativeQuery = true)
    List<RelatorioMisc> matchRelatorioMisc(@Param("idtutor") Integer idtutor, @Param("idautista") Integer idautista);
}
