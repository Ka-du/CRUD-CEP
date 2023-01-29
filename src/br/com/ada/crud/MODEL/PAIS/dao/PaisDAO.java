package br.com.ada.crud.MODEL.PAIS.dao;


import br.com.ada.crud.MODEL.PAIS.Pais;

import java.util.List;
import java.util.UUID;

public interface PaisDAO {

    void cadastrar (Pais pais);

    List<Pais> listar();

    Pais buscar(UUID id);

    void update (UUID id, Pais pais);

    Pais apagar(UUID id);


}
