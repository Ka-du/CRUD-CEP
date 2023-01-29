package br.com.ada.crud.CONTROLLER.impl;

import br.com.ada.crud.CONTROLLER.Estado.EstadoController;
import br.com.ada.crud.CONTROLLER.exception.EstadoNaoEncontrado;
import br.com.ada.crud.MODEL.ESTADO.Estado;


import java.util.*;

public class EstadoArmazenamentoVolatilController implements EstadoController {

    private Map<UUID, Estado> estados = new HashMap<>();


    public void cadastrar(Estado estado) {
        estado.setId(UUID.randomUUID());
        estados.put(estado.getId(), estado);
    }


    public Estado ler(UUID id) {
        Estado encontrado = estados.get(id);
        if (encontrado == null) {
            throw new EstadoNaoEncontrado();
        }
        return encontrado;
    }


    public List<Estado> listar() {
        return new ArrayList<>(estados.values());
    }


    public void update(UUID id, Estado estado) {
        if (estados.containsKey(id)) {
            estados.put(id, estado);
        } else {
            throw new EstadoNaoEncontrado();
        }
    }


    public Estado delete(UUID id) {
        Estado apagado = estados.remove(id);
        if (apagado == null) {
            throw new EstadoNaoEncontrado();
        }
        return apagado;
    }



}
