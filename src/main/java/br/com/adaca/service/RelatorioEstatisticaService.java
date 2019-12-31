package br.com.adaca.service;

import br.com.adaca.dto.RelatorioEstatisticaDTO;
import br.com.adaca.dto.RelatorioEstatisticaDTOListWrapper;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.model.RelatorioMisc;
import br.com.adaca.repository.RelatorioMiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RelatorioEstatisticaService {

    private RelatorioMiscRepository relatorioMiscRepository;

    @Autowired
    public RelatorioEstatisticaService(RelatorioMiscRepository relatorioMiscRepository) {
        this.relatorioMiscRepository = relatorioMiscRepository;
    }

    public RelatorioEstatisticaDTOListWrapper relatorioEstatisticaFactory(@NotNull Integer idtutor, @NotNull Integer idautista) {
        List<RelatorioEstatisticaDTO> relatorioEstatisticaDTOList = new ArrayList<>();

        List<RelatorioMisc> relatorioMiscList = matchRelatorioMisc(idtutor, idautista);

        HashSet<List<RelatorioMisc>> atividadeHashMap = mapRelatorioMiscToAtividades(relatorioMiscList);

        for (List<RelatorioMisc> relatorioMiscs : atividadeHashMap) {
            relatorioEstatisticaDTOList.add(atividadeStatistics(relatorioMiscs, idautista, idtutor));
        }
        return new RelatorioEstatisticaDTOListWrapper(relatorioEstatisticaDTOList);
    }

    private List<RelatorioMisc> matchRelatorioMisc(@NotNull Integer idtutor, @NotNull Integer idautista) {
        List<RelatorioMisc> relatorioMiscList = relatorioMiscRepository.matchRelatorioMisc(idtutor, idautista);

        if (relatorioMiscList.isEmpty()) {
            throw new NotFoundException("Não é possivel fabricar o relatorio para o idtutor=" + idtutor + " idautista=" +
                    idautista + ". Match não encontrado.");
        }

        return relatorioMiscList;
    }

    private RelatorioEstatisticaDTO atividadeStatistics(List<RelatorioMisc> relatorioMiscList, Integer idautista, Integer idtutor) {
        RelatorioEstatisticaDTO relatorioEstatisticaDTO = new RelatorioEstatisticaDTO();

        List<Integer> cliquesCertos = mapRelatorioMiscToCliquesCertos(relatorioMiscList);
        List<Integer> cliquesErrados = mapRelatorioMiscToCliquesErrados(relatorioMiscList);
        List<Integer> cliquesTotais = mapRelatorioMiscToCliquesTotais(cliquesCertos, cliquesErrados);
        List<Integer> dicas = mapRelatorioMiscToDicas(relatorioMiscList);
        List<Double> tempos = mapRelatorioMiscToTempo(relatorioMiscList);
        List<Double> assertividades = mapRelatorioMiscToAssertividade(cliquesCertos, cliquesErrados);
        List<Double> desempenhos = mapRelatorioMiscToDesempenho(cliquesTotais, dicas, assertividades, tempos);

        int nPartidas = relatorioMiscList.size();
        double tempoMax = tempos.stream().max(Double::compareTo).orElse(1.0);
        double tempoMin = tempos.stream().min(Double::compareTo).orElse(0.0);

        relatorioEstatisticaDTO.setIdAutista(idautista);
        relatorioEstatisticaDTO.setIdTutor(idtutor);
        relatorioEstatisticaDTO.setNome(relatorioMiscList.get(0).getNome());
        relatorioEstatisticaDTO.setClassificacao(relatorioMiscList.get(0).getClassificacao());
        relatorioEstatisticaDTO.setMask("Atividade_" + relatorioMiscList.get(0).getIdAtividade());
        relatorioEstatisticaDTO.setNPartidas(nPartidas);
        relatorioEstatisticaDTO.setTempoMax(tempoMax);
        relatorioEstatisticaDTO.setTempoMin(tempoMin);
        relatorioEstatisticaDTO.setTempoPorPartida(listAverage(tempos));
        relatorioEstatisticaDTO.setDicaPorPartida(listAverage(dicas));
        relatorioEstatisticaDTO.setMdPerCliquesCertos(100 * listAverage(mapRelatorioMiscToPerList(cliquesCertos, cliquesTotais)));
        relatorioEstatisticaDTO.setMdPerCliquesErrados(100 * listAverage(mapRelatorioMiscToPerList(cliquesErrados, cliquesTotais)));
        relatorioEstatisticaDTO.setMdCliquesTotais(listAverage(cliquesTotais));
        relatorioEstatisticaDTO.setMdAssertividade(listAverage(assertividades));
        relatorioEstatisticaDTO.setMdDesempenho(listAverage(desempenhos));

        return relatorioEstatisticaDTO;
    }

    private <T extends Number> Double listAverage(List<T> list) {
        return list.stream().mapToDouble(Number::doubleValue).average().orElse(0.0);
    }

    private List<Double> mapRelatorioMiscToPerList(List<Integer> cliqueList, List<Integer> cliqueTotaisList) {
        List<Double> perList = new ArrayList<>();
        for (int i = 0; i < cliqueTotaisList.size(); i++) {
            perList.add(
                    cliqueList.get(i) / (double) cliqueTotaisList.get(i)
            );
        }
        return perList;
    }

    private List<Double> mapRelatorioMiscToAssertividade(List<Integer> cliqueCertoList, List<Integer> cliqueErradoList) {
        List<Double> assertividades = new ArrayList<>();
        if (cliqueCertoList.size() != cliqueErradoList.size()) {
            throw new ArrayStoreException("Array cliqueCerto deve ter o mesmo tamanho que Array CliqueErrado");
        }

        IntStream.range(0, cliqueCertoList.size()).forEach(i ->
                assertividades.add(
                        cliqueCertoList.get(i) / (double) cliqueErradoList.get(i)
                )
        );
        return assertividades;
    }

    private List<Double> mapRelatorioMiscToDesempenho(List<Integer> cliquesTotaisList, List<Integer> dicaList,
                                                      List<Double> assertividadeList, List<Double> tempoList) {
        List<Double> desempenho = new ArrayList<>();

        for (int i = 0; i < cliquesTotaisList.size(); i++) {
            desempenho.add(
                    cliquesTotaisList.get(i) * (1 - dicaList.get(i) / 100.0) * assertividadeList.get(i) / tempoList.get(i)
            );
        }

        return desempenho;
    }

    private List<Integer> mapRelatorioMiscToCliquesTotais(List<Integer> cliqueCertoList, List<Integer> cliqueErradoList) {
        List<Integer> cliquesTotais = new ArrayList<>();

        if (cliqueCertoList.size() != cliqueErradoList.size()) {
            throw new ArrayStoreException("Array cliqueCerto deve ter o mesmo tamanho que Array CliqueErrado");
        }

        for (int i = 0; i < cliqueCertoList.size(); i++) {
            cliquesTotais.add(cliqueCertoList.get(i) + cliqueErradoList.get(i));
        }

        return cliquesTotais;
    }

    private List<Integer> mapRelatorioMiscToCliquesCertos(List<RelatorioMisc> relatorioMiscList) {
        return relatorioMiscList.stream()
                .map(RelatorioMisc::getCliqueCerto)
                .collect(Collectors.toList());
    }

    private List<Integer> mapRelatorioMiscToCliquesErrados(List<RelatorioMisc> relatorioMiscList) {
        return relatorioMiscList.stream()
                .map(RelatorioMisc::getCliqueErrado)
                .collect(Collectors.toList());
    }

    private List<Double> mapRelatorioMiscToTempo(List<RelatorioMisc> relatorioMiscList) {
        return relatorioMiscList.stream()
                .mapToDouble(RelatorioMisc::getTempo)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> mapRelatorioMiscToDicas(List<RelatorioMisc> relatorioMiscList) {
        return relatorioMiscList.stream()
                .map(RelatorioMisc::getDicas)
                .collect(Collectors.toList());
    }

    private HashSet<List<RelatorioMisc>> mapRelatorioMiscToAtividades(List<RelatorioMisc> relatorioMiscList) {
        HashSet<List<RelatorioMisc>> atividades = new HashSet<>();

        HashSet<Integer> listUnique = relatorioMiscList.stream().map(RelatorioMisc::getIdAtividade)
                .collect(Collectors.toCollection(HashSet::new));

        for (Integer i : listUnique) {
            atividades.add(relatorioMiscList.parallelStream()
                    .filter(relatorioMisc -> relatorioMisc.getIdAtividade().equals(i))
                    .collect(Collectors.toList())
            );
        }

        return atividades;
    }

}
