package br.com.ada.crud.MODEL.ESTADO.dao;

import br.com.ada.crud.MODEL.ESTADO.Estado;

import java.util.List;
import java.util.UUID;

public interface EstadoDAO {

    void cadastrar (Estado estado);

    List<Estado> listar();

    Estado buscar(UUID id);

    void update (UUID id, Estado estado);

    Estado apagar(UUID id);

}
