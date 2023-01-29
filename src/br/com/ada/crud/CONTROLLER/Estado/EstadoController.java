package br.com.ada.crud.CONTROLLER.Estado;

import br.com.ada.crud.MODEL.ESTADO.Estado;

import java.util.List;
import java.util.UUID;

public interface EstadoController {

    void cadastrar(Estado estado);

    Estado ler(UUID id);

    List<Estado> listar();

    void update(UUID id, Estado estado);

    Estado delete(UUID id);

}
