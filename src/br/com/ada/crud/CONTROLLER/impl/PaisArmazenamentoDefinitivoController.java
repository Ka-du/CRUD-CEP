package br.com.ada.crud.CONTROLLER.impl;

import br.com.ada.crud.CONTROLLER.Pais.PaisController;
import br.com.ada.crud.MODEL.PAIS.Pais;
import br.com.ada.crud.MODEL.PAIS.dao.PaisDAO;

import java.util.List;
import java.util.UUID;

public class PaisArmazenamentoDefinitivoController implements PaisController {

    private PaisDAO paisDAO;

    public PaisArmazenamentoDefinitivoController(
            PaisDAO paisDAO
    ) {
        this.paisDAO = paisDAO;
    }

    @Override
    public void cadastrar(Pais pais) {
        pais.setId(UUID.randomUUID());
        paisDAO.cadastrar(pais);
    }

    @Override
    public Pais ler(UUID id) {
        return paisDAO.buscar(id);
    }

    @Override
    public List<Pais> listar() {
        return paisDAO.listar();
    }

    @Override
    public void update(UUID id, Pais pais) {
        paisDAO.update(id,pais);
    }

    @Override
    public Pais delete(UUID id) {
        return paisDAO.apagar(id);
    }
}





