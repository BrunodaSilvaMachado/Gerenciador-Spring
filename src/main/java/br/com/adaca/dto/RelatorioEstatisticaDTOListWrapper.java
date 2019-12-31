package br.com.adaca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioEstatisticaDTOListWrapper implements Serializable {
    private List<RelatorioEstatisticaDTO> wrapper;

    public static RelatorioEstatisticaDTOListWrapper parseRelatorioConteinerListWrapper(String relatorioConteinerListString) {
        String[] rclStrings = relatorioConteinerListString.split(";");
        List<RelatorioEstatisticaDTO> relatorioEstatisticaDTOList = new ArrayList<>();

        for (String rcls : rclStrings) {
            relatorioEstatisticaDTOList.add(RelatorioEstatisticaDTO.parseRelatorioConteiner(rcls));
        }

        return new RelatorioEstatisticaDTOListWrapper(relatorioEstatisticaDTOList);
    }

    @Override
    public String toString() {
        StringBuilder relatorioConteinerListString = new StringBuilder();

        for (RelatorioEstatisticaDTO rc : wrapper) {
            relatorioConteinerListString.append(rc.toString()).append(";");
        }

        return relatorioConteinerListString.toString();
    }
}
