package br.com.ada.crud.CONTROLLER.impl;

import br.com.ada.crud.CONTROLLER.Pais.PaisController;
import br.com.ada.crud.CONTROLLER.exception.PaisNaoEncontrado;
import br.com.ada.crud.MODEL.PAIS.Pais;

import java.util.*;

public class PaisArmazenamentoVolatilController implements PaisController {

    private Map<UUID, Pais> paises = new HashMap<>();


    public void cadastrar(Pais pais) {
        pais.setId(UUID.randomUUID());
        paises.put(pais.getId(), pais);
    }


    public Pais ler(UUID id) {
        Pais encontrado = paises.get(id);
        if (encontrado == null) {
            throw new PaisNaoEncontrado();
        }
        return encontrado;
    }


    public List<Pais> listar() {
        return new ArrayList<>(paises.values());
    }


    public void update(UUID id, Pais pais) {
        if (paises.containsKey(id)) {
            paises.put(id, pais);
        } else {
            throw new PaisNaoEncontrado();
        }
    }


    public Pais delete(UUID id) {
        Pais apagado = paises.remove(id);
        if (apagado == null) {
            throw new PaisNaoEncontrado();
        }
        return apagado;
    }



}
