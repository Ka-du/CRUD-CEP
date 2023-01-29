package br.com.ada.crud.CONTROLLER.impl;

import br.com.ada.crud.CONTROLLER.Cidade.CidadeController;
import br.com.ada.crud.CONTROLLER.exception.CidadeNaoEncontrada;
import br.com.ada.crud.MODEL.CIDADE.Cidade;

import java.util.*;

public class CidadeArmazenamentoVolatilController implements CidadeController {

    private Map<UUID, Cidade> cidades = new HashMap<>();


    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidades.put(cidade.getId(), cidade);
    }


    public Cidade ler(UUID id) {
        Cidade encontrada = cidades.get(id);
        if (encontrada == null) {
            throw new CidadeNaoEncontrada();
        }
        return encontrada;
    }


    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }


    public void update(UUID id, Cidade cidade) {
        if (cidades.containsKey(id)) {
            cidades.put(id, cidade);
        } else {
            throw new CidadeNaoEncontrada();
        }
    }


    public Cidade delete(UUID id) {
        Cidade apagada = cidades.remove(id);
        if (apagada == null) {
            throw new CidadeNaoEncontrada();
        }
        return apagada;
    }


}
