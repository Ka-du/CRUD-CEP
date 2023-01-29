package br.com.ada.crud.CONTROLLER.impl;

import br.com.ada.crud.CONTROLLER.Cidade.CidadeController;
import br.com.ada.crud.MODEL.CIDADE.Cidade;
import br.com.ada.crud.MODEL.CIDADE.dao.CidadeDAO;

import java.util.List;
import java.util.UUID;

public class CidadeArmazenamentoDefinitivoController implements CidadeController {


    private CidadeDAO cidadeDAO;

    public CidadeArmazenamentoDefinitivoController(
            CidadeDAO cidadeDAO
    ) {
        this.cidadeDAO = cidadeDAO;
    }

    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidadeDAO.cadastrar(cidade);
    }

    @Override
    public Cidade ler(UUID id) {
        return cidadeDAO.buscar(id);
    }

    @Override
    public List<Cidade> listar() {
        return cidadeDAO.listar();
    }

    @Override
    public void update(UUID id, Cidade cidade) {
        cidadeDAO.update(id,cidade);
    }

    @Override
    public Cidade delete(UUID id) {
        return cidadeDAO.apagar(id);
    }
}


