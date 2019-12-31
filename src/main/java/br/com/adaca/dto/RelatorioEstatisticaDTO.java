package br.com.adaca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioEstatisticaDTO implements Serializable {
    private String nome;
    private String classificacao;
    private String mask;
    private Integer idAutista;
    private Integer idTutor;
    private Integer nPartidas;
    private Double tempoMax;
    private Double tempoMin;
    private Double dicaPorPartida;
    private Double tempoPorPartida;
    private Double mdCliquesTotais;
    private Double mdPerCliquesCertos;
    private Double mdPerCliquesErrados;
    private Double mdAssertividade;
    private Double mdDesempenho;

    public static RelatorioEstatisticaDTO parseRelatorioConteiner(String rcs) {
        String[] strings = rcs.split("\n");
        RelatorioEstatisticaDTO relatorioEstatisticaDTO = new RelatorioEstatisticaDTO();

        relatorioEstatisticaDTO.setIdTutor(Integer.parseInt(strings[0].split(":")[1]));
        relatorioEstatisticaDTO.setIdAutista(Integer.parseInt(strings[1].split(":")[1]));
        relatorioEstatisticaDTO.setNome((strings[2].split(":")[1]));
        relatorioEstatisticaDTO.setMask((strings[3].split(":")[1]));
        relatorioEstatisticaDTO.setClassificacao((strings[4].split(":")[1]));
        relatorioEstatisticaDTO.setNPartidas(Integer.parseInt(strings[5].split(":")[1]));
        relatorioEstatisticaDTO.setTempoMax(Double.parseDouble(strings[6].split(":")[1]));
        relatorioEstatisticaDTO.setTempoMin(Double.parseDouble(strings[7].split(":")[1]));
        relatorioEstatisticaDTO.setTempoPorPartida(Double.parseDouble(strings[8].split(":")[1]));
        relatorioEstatisticaDTO.setDicaPorPartida(Double.parseDouble(strings[9].split(":")[1]));
        relatorioEstatisticaDTO.setMdCliquesTotais(Double.parseDouble(strings[10].split(":")[1]));
        relatorioEstatisticaDTO.setMdPerCliquesCertos(Double.parseDouble(strings[11].split(":")[1]));
        relatorioEstatisticaDTO.setMdPerCliquesErrados(Double.parseDouble(strings[12].split(":")[1]));
        relatorioEstatisticaDTO.setMdAssertividade(Double.parseDouble(strings[13].split(":")[1]));
        relatorioEstatisticaDTO.setMdDesempenho(Double.parseDouble(strings[14].split(":")[1]));

        return relatorioEstatisticaDTO;
    }

    @Override
    public String toString() {
        return "ID do tutor:" + getIdTutor() + "\n" +
                "ID da Criança:" + getIdAutista() + "\n" +
                "Nome da atividade:" + getNome() + "\n" +
                "Mascara da atividade:" + getMask() + "\n" +
                "Classificação:" + getClassificacao() + "\n" +
                "Numero de Partidas:" + getNPartidas() + "\n" +
                "Tempo máximo (s):" + getTempoMax() + "\n" +
                "Tempo mínimo (s):" + getTempoMin() + "\n" +
                "Tempo por partida (s):" + getTempoPorPartida() + "\n" +
                "Dicas por partida:" + getDicaPorPartida() + "\n" +
                "Média de cliques Totais:" + getMdCliquesTotais() + "\n" +
                "Média percentual de cliques certos (%):" + getMdPerCliquesCertos() + "\n" +
                "Média percentual de cliques errados (%):" + getMdPerCliquesErrados() + "\n" +
                "Média de assertividade:" + getMdAssertividade() + "\n" +
                "Média de desempenho (1/s):" + getMdDesempenho();
    }
}
