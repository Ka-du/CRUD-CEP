package br.com.ada.crud.CONTROLLER.Cidade;

import br.com.ada.crud.MODEL.CIDADE.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeController {

    void cadastrar(Cidade cidade);

    Cidade ler(UUID id);

    List<Cidade> listar();

    void update(UUID id, Cidade cidade);

    Cidade delete(UUID id);
}
