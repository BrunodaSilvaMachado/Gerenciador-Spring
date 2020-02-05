package br.com.adaca.controller;

import br.com.adaca.model.Grafico;
import br.com.adaca.service.GraficoService;
import br.com.adaca.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Graficos")
public class GraficoController extends View<Grafico> {

    private GraficoService graficoService;

    @Autowired
    public GraficoController(GraficoService graficoService) {
        super("Gerenciador/graficos", "Gerenciador/graficoView");
        this.graficoService = graficoService;
    }

    @GetMapping()
    public ResponseEntity<List<Grafico>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.listar());
    }

    // ta no padrão REST ?
    @GetMapping("/autista/{autistaId}")
    public ResponseEntity<List<Grafico>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.listar(autistaId));
    }

    @GetMapping("/{graficoId}")
    public ResponseEntity<Grafico> selecionar(@PathVariable("graficoId") Integer graficoId) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.selecionar(graficoId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Grafico grafico) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(graficoService.salvar(grafico).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Grafico> alterar(@RequestBody @Valid Grafico grafico) {
        return ResponseEntity.status(HttpStatus.OK).body(graficoService.alterar(grafico));
    }

    @DeleteMapping("/{graficoId}")
    public ResponseEntity<Void> remover(@PathVariable("graficoId") Integer graficoId) {
        graficoService.remover(graficoId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Grafico grafico) {
        graficoService.remover(grafico);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    protected ModelAndView mvAlterar(Integer id) {
        return new ModelAndView("redirect:list");
    }

    @Override
    protected ModelAndView mvSave(@Valid Grafico grafico, @NotNull BindingResult bindingResult) {
        return new ModelAndView("redirect:list");
    }

    protected ModelAndView mvAdd(@Valid Grafico grafico) {
        return new ModelAndView("redirect:list");
    }

    @GetMapping("/view/{id}")
    private ModelAndView mvView(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Grafico grafico = selecionar(id).getBody();

        if (grafico == null) {
            grafico = new Grafico();
        }

        mv.setViewName("Gerenciador/graficoView");
        mv.addObject("entitys",
                graficoService.graficoToRelatorioEstatisticaDTOList(grafico)
        );

        return mv;
    }
}
