package br.com.adaca.controller;

import br.com.adaca.model.Grafico;
import br.com.adaca.service.GraficoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Graficos")
public class GraficoController {

    @Autowired
    private GraficoService graficoService;

    /**
     * Lista todos os gráficos cadastrados no banco de dados
     *
     * @return Lista com todos os gráficos cadastrados
     */
    @GetMapping("/listarGraficos")
    public ResponseEntity<List<Grafico>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.listar());
    }

    /**
     * Lista todos os gráficos cadastrados no banco de dados filtrados pela
     * criança
     *
     * @param autistaId ID da criança para o filtro
     * @return Lista com todos os gráficos cadastrados
     */
    @GetMapping("/listarGraficos/autistaId")
    public ResponseEntity<List<Grafico>> listar(@PathVariable("autistaId")Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.listar(autistaId));
    }

    /**
     * Efetua uma busca por ID do gráfico cadastrado
     *
     * @param graficoId ID do gráfico já existente no banco de dados
     * @return Objeto do gráfico encontrado
     */
    @GetMapping("/selecionarGrafico/{graficoId}")
    public ResponseEntity<Grafico> selecionar(@PathVariable("graficoId") Integer graficoId) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.selecionar(graficoId));
    }

    /**
     * Salva o gráfico da criança no banco de dados
     *
     * @param grafico Objeto preenchido do gráfico a ser gravado
     * @return Objeto do gráfico salvo
     */
    @PostMapping("/salvarGrafico")
    public ResponseEntity<Void> salvar(@RequestBody @Valid Grafico grafico) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(graficoService.salvar(grafico).getId()).toUri()).build();
    }

    /**
     * Altera o cadastro do gráfico no bando de dados
     *
     * @param grafico Objeto preenchido com os dados já alterados
     * @return objeto do Grafico removido
     */
    @PutMapping("/alterarGrafico")
    public ResponseEntity<Grafico> alterar(@RequestBody @Valid Grafico grafico) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.alterar(grafico));
    }

    /**
     * Efetua uma busca por ID do gráfico cadastrado e remove-o do banco de dados
     *
     * @param graficoId ID do reltório já existente no banco de dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerGrafico/{graficoId}")
    public ResponseEntity<Void> remover(@PathVariable("graficoId") Integer graficoId) {
        graficoService.remover(graficoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Remove o gráfico do banco de dados
     *
     * @param grafico Objeto preenchido do cadastro já existente no banco de
     * dados
     * @return Erro ou sucesso ao remover
     */
    @DeleteMapping("/removerGrafico")
    public ResponseEntity<Void> remover(@RequestBody @Valid Grafico grafico) {
        graficoService.remover(grafico);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
