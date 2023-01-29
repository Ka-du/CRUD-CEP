package br.com.ada.crud.CONTROLLER.Pais;

import br.com.ada.crud.CONTROLLER.impl.PaisArmazenamentoDefinitivoController;
import br.com.ada.crud.CONTROLLER.impl.PaisArmazenamentoVolatilController;
import br.com.ada.crud.Constantes;
import br.com.ada.crud.MODEL.PAIS.dao.PaisDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PaisControllerFactory {


    private static final PaisControllerFactory INSTANCE = new PaisControllerFactory();
    private static final String CONTROLLER_TIPO = "pais.controller.tipo";
    private PaisArmazenamentoTipo tipo;

    private PaisControllerFactory() {
    }

    public static PaisControllerFactory getInstance() {
        return INSTANCE;
    }

    public PaisController criar() {
        if (tipo == null) {
            carregarTipoArmazenamento();
        }

        PaisDAOFactory daoFactory = PaisDAOFactory.getInstance();
        PaisController controller = null;

        if (tipo == PaisArmazenamentoTipo.VOLATIL) {
            controller = new PaisArmazenamentoVolatilController();
        } else if (tipo == PaisArmazenamentoTipo.DEFINITIVO) {
            PaisDAO paisDAO = daoFactory.create();
            controller = new PaisArmazenamentoDefinitivoController(paisDAO);
        } else {
            throw new RuntimeException("Nenhuma implementação disponível");
        }
        return controller;
    }

    private void carregarTipoArmazenamento() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorDoArquivo = properties.getProperty(CONTROLLER_TIPO);
            tipo = PaisArmazenamentoTipo.valueOf(valorDoArquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Falha no carregamento do arquivo de propriedaes.", ex);
        }
    }

}
