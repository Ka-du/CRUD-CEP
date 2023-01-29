package br.com.ada.crud.CONTROLLER.Pais;


import br.com.ada.crud.Constantes;
import br.com.ada.crud.MODEL.PAIS.dao.PaisDAO;
import br.com.ada.crud.MODEL.PAIS.dao.PersistenciaTipoPais;
import br.com.ada.crud.MODEL.PAIS.dao.impl.PaisBinaryDAO;
import br.com.ada.crud.MODEL.PAIS.dao.impl.PaisXMLDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PaisDAOFactory {

    private static final PaisDAOFactory INSTANCE = new PaisDAOFactory();
    private static final String PERSISTENCIA_TIPO = "pais.persistencia.tipo";

    private PersistenciaTipoPais tipo;

    private PaisDAOFactory() {
    }

    public static PaisDAOFactory getInstance() {
        return INSTANCE;
    }

    public PaisDAO create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        PaisDAO paisDAO = null;
        if (tipo == PersistenciaTipoPais.BINARIA) {
            paisDAO = new PaisBinaryDAO();
        } else if (tipo == PersistenciaTipoPais.XML) {
            paisDAO = new PaisXMLDAO();
        }
        return paisDAO;
    }

    private void carregarTipoPersistencia() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipoPais.valueOf(valorNoArquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }



}
