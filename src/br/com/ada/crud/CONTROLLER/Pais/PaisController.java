package br.com.ada.crud.CONTROLLER.Pais;


import br.com.ada.crud.MODEL.PAIS.Pais;

import java.util.List;
import java.util.UUID;

public interface PaisController {

    void cadastrar(Pais pais);

    Pais ler(UUID id);

    List<Pais> listar();

    void update(UUID id, Pais pais);

    Pais delete(UUID id);

}
