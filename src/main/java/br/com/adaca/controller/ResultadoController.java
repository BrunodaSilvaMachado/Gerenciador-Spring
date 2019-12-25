package br.com.adaca.controller;

import br.com.adaca.model.Resultado;
import br.com.adaca.service.ResultadoService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Resultados")
public class ResultadoController extends View<Resultado> {
    @Autowired
    private ResultadoService resultadoService;

    public ResultadoController() {
        super("resultados", "resultadoAdd");
    }

    @GetMapping("")
    public ResponseEntity<List<Resultado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.listar());
    }

    // ta no padrão REST ?
    @GetMapping("/sessao/{sessaoId}")
    public ResponseEntity<List<Resultado>> listar(@PathVariable("sessaoId") Integer sessaoId) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.listar(sessaoId));
    }

    @GetMapping("/{resultadoId}")
    public ResponseEntity<Resultado> selecionar(@PathVariable("resultadoId") Integer resultadoId) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.selecionar(resultadoId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Resultado resultado) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resultadoService.salvar(resultado).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Resultado> alterar(@RequestBody @Valid Resultado resultado) {
        return ResponseEntity.status(HttpStatus.OK).body(resultadoService.alterar(resultado));
    }

    @DeleteMapping("/{resultadoId}")
    public ResponseEntity<Void> remover(@PathVariable("resultadoId") Integer resultadoId) {
        resultadoService.remover(resultadoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Resultado resultado) {
        resultadoService.remover(resultado);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    protected ModelAndView mvListar() {
        List<Resultado> resultados = listar().getBody();

        assert resultados != null;
        resultados.forEach((r) -> {
            r.setIdsessao(null);
            r.setIdatividade(null);
            r.setMousepos(r.getMousepos().replace(";", " "));
            r.setPoserrado(r.getPoserrado().replace(";", " "));
        });

        return new ModelAndView(getHomeViewName()).addObject(getATTRIBUTE_NAME(), resultados);
    }
}
