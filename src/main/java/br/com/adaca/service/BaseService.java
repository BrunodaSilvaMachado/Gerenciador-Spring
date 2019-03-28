package br.com.adaca.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.adaca.dto.InterfaceDTO;

public interface BaseService<BaseDTO extends InterfaceDTO> {

    List<BaseDTO> listar();
    BaseDTO selecionar(Integer id);
    BaseDTO salvar(BaseDTO administrador);
    BaseDTO alterar(BaseDTO administrador);
    void remover(Integer id);
    void remover(BaseDTO id);
}
