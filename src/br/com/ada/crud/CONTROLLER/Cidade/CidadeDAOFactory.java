package br.com.ada.crud.CONTROLLER.Cidade;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.MODEL.CIDADE.dao.CidadeDAO;
import br.com.ada.crud.MODEL.CIDADE.dao.impl.CidadeBinaryDAO;
import br.com.ada.crud.MODEL.CIDADE.dao.impl.CidadeXMLDAO;
import br.com.ada.crud.MODEL.CIDADE.dao.PersistenciaTipoCidade;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CidadeDAOFactory {

    private static final CidadeDAOFactory INSTANCE = new CidadeDAOFactory();
    private static final String PERSISTENCIA_TIPO = "cidade.persistencia.tipo";

    private PersistenciaTipoCidade tipo;

    private CidadeDAOFactory() {
    }

    public static CidadeDAOFactory getInstance() {
        return INSTANCE;
    }

    public CidadeDAO create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        CidadeDAO cidadeDAO = null;
        if (tipo == PersistenciaTipoCidade.BINARIA) {
            cidadeDAO = new CidadeBinaryDAO();
        } else if (tipo == PersistenciaTipoCidade.XML) {
            cidadeDAO = new CidadeXMLDAO();
        }
        return cidadeDAO;
    }

    private void carregarTipoPersistencia() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipoCidade.valueOf(valorNoArquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }


}
