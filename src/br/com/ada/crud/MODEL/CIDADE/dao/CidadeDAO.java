package br.com.ada.crud.MODEL.CIDADE.dao;

import br.com.ada.crud.MODEL.CIDADE.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeDAO {

    void cadastrar (Cidade cidade);

    List<Cidade> listar();

    Cidade buscar(UUID id);

    void update (UUID id, Cidade cidade);

    Cidade apagar(UUID id);


}
