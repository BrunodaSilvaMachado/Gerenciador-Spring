package br.com.adaca.controller;

import br.com.adaca.dto.RelatorioEstatisticaDTOListWrapper;
import br.com.adaca.model.Administrador;
import br.com.adaca.model.Grafico;
import br.com.adaca.model.Relatorio;
import br.com.adaca.model.TutorAndAutista;
import br.com.adaca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/Gerenciador/Relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;
    @Autowired
    private RelatorioEstatisticaService relatorioEstatisticaService;
    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AutistaService autistaService;
    @Autowired
    private GraficoService graficoService;

    @GetMapping()
    public ResponseEntity<List<Relatorio>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.listar());
    }

    // ta no padrão REST ?
    @GetMapping("/autista/{autistaId}")
    public ResponseEntity<List<Relatorio>> listar(@PathVariable("autistaId") Integer autistaId) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.listar(autistaId));
    }

    @GetMapping("/{relatorioId}")
    public ResponseEntity<Relatorio> selecionar(@PathVariable("relatorioId") Integer relatorioId) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.selecionar(relatorioId));
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody @Valid Relatorio relatorio) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(relatorioService.salvar(relatorio).getId()).toUri()).build();
    }

    @PutMapping()
    public ResponseEntity<Relatorio> alterar(@RequestBody @Valid Relatorio relatorio) {
        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.alterar(relatorio));
    }

    @DeleteMapping("/{relatorioId}")
    public ResponseEntity<Void> remover(@PathVariable("relatorioId") Integer relatorioId) {
        relatorioService.remover(relatorioId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remover(@RequestBody @Valid Relatorio relatorio) {
        relatorioService.remover(relatorio);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/list")
    private ModelAndView mvListar() {
        ModelAndView mv = new ModelAndView("Gerenciador/relatorios");

        try {
            mv.addObject("autistas", autistaService.listar());
            mv.addObject("tutores", tutorService.listar());
            mv.addObject("entitys", listar().getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mv.addObject("consultas", new TutorAndAutista());

        return mv;
    }

    @GetMapping("/view/{id}")
    private ModelAndView mvView(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Relatorio relatorio = selecionar(id).getBody();

        if (relatorio == null) {
            relatorio = new Relatorio();
        }

        mv.setViewName("Gerenciador/relatorioView");
        mv.addObject("entitys",
                relatorioService.relatorioToRelatorioConteinerDTOListWrapper(relatorio)
        );

        return mv;
    }

    @PostMapping("/add")
    public ModelAndView mvAdd(@ModelAttribute @Valid TutorAndAutista tutorAndAutista, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:invalid-request");
        }

        int idtutor = tutorAndAutista.getIdtutor();
        int idautista = tutorAndAutista.getIdautista();

        ModelAndView mv = new ModelAndView("Gerenciador/relatorioAdd");
        mv.addObject("entitys",
                relatorioEstatisticaService.relatorioEstatisticaFactory(idtutor, idautista)
        );
        return mv;
    }

    @PostMapping("/save")
    private ModelAndView mvSave(@Valid @ModelAttribute RelatorioEstatisticaDTOListWrapper relatorioEstatisticaDTOListWrapper,
                                @NotNull BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:invalid-request");
        }

        salvarRelatorioGrafico(relatorioEstatisticaDTOListWrapper, principal.getName());

        return new ModelAndView("redirect:list");
    }

    private void salvarRelatorioGrafico(RelatorioEstatisticaDTOListWrapper relatorioEstatisticaDTOListWrapper, String name) {
        Grafico grafico = new Grafico();

        Administrador administrador = administradorService.findByUsuario(name);
        Relatorio relatorio = relatorioService.relatorioConteinerDTOListWrapperToRelatorio(relatorioEstatisticaDTOListWrapper);

        relatorio.setIdadministrador(administrador);
        grafico.setIdadministrador(administrador);
        grafico.setIdautista(relatorio.getIdautista());
        grafico.setDatagerado(relatorio.getDatagerado());
        grafico.setTipografico("Canva");
        grafico.setGrafico(relatorio.getRelatorio());

        salvar(relatorio);
        graficoService.salvar(grafico);
    }

    @GetMapping("/delete/{id}")
    protected ModelAndView mvRemover(@PathVariable("id") Integer id) {
        remover(id);
        return mvListar();
    }

    @RequestMapping(value = "/invalid-request", method = RequestMethod.GET)
    public ModelAndView invalidRequest() {
        return new ModelAndView("error/400");
    }

}
