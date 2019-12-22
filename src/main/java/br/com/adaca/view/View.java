package br.com.adaca.view;

import br.com.adaca.util.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class View<O extends BaseId> {

    private final String ATTRIBUTE_NAME = "entitys";

    private String homeViewName;
    private String addViewName;

    protected abstract ResponseEntity<List<O>> listar();

    protected abstract ResponseEntity<O> selecionar(Integer integer);

    protected abstract ResponseEntity salvar(O o);

    protected abstract ResponseEntity<O> alterar(O o);

    protected abstract ResponseEntity remover(Integer integer);

    protected abstract ResponseEntity remover(O o);

    @GetMapping("/list")
    private ModelAndView mvListar(){
        ModelAndView mv = new ModelAndView(homeViewName);
        mv.addObject(ATTRIBUTE_NAME, listar().getBody());

        return mv;
    }

    @GetMapping("/list/{id}")
    public ModelAndView mvSelecionar(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView(homeViewName);
        mv.addObject(ATTRIBUTE_NAME, selecionar(id).getBody());

        return mv;
    }

    @GetMapping("/add")
    private ModelAndView mvAdd(O o) {

        ModelAndView mv = new ModelAndView(addViewName);
        mv.addObject(ATTRIBUTE_NAME, o);

        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView mvAlterar(@PathVariable("id") Integer id) {

        return mvAdd(selecionar(id).getBody());
    }

    @GetMapping("/delete/{id}")
    public ModelAndView mvRemover(@PathVariable("id") Integer id) {
        remover(id);
        return mvListar();
    }

    @PostMapping("/save")
    public ModelAndView mvSave(@ModelAttribute @Valid O o, BindingResult bindingResult) {
        List<O> oList;

        if (bindingResult.hasErrors()){
            return mvAdd(o);
        }

        oList= listar().getBody();
        assert oList != null;
        for (O adm: oList) {
            if(adm.getId().equals(o.getId())){
                alterar(o);
                return mvListar();
            }
        }

        salvar(o);
        return mvListar();
    }
}
