package br.com.ada.crud.CONTROLLER.Estado;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.MODEL.ESTADO.dao.EstadoDAO;
import br.com.ada.crud.MODEL.ESTADO.dao.PersistenciaTipoEstado;
import br.com.ada.crud.MODEL.ESTADO.dao.impl.EstadoBinaryDAO;
import br.com.ada.crud.MODEL.ESTADO.dao.impl.EstadoXMLDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EstadoDAOFactory {


    private static final EstadoDAOFactory INSTANCE = new EstadoDAOFactory();
    private static final String PERSISTENCIA_TIPO = "estado.persistencia.tipo";

    private PersistenciaTipoEstado tipo;

    private EstadoDAOFactory() {
    }

    public static EstadoDAOFactory getInstance() {
        return INSTANCE;
    }

    public EstadoDAO create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        EstadoDAO estadoDAO = null;
        if (tipo == PersistenciaTipoEstado.BINARIA) {
            estadoDAO = new EstadoBinaryDAO();
        } else if (tipo == PersistenciaTipoEstado.XML) {
            estadoDAO = new EstadoXMLDAO();
        }
        return estadoDAO;
    }

    private void carregarTipoPersistencia() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipoEstado.valueOf(valorNoArquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }




}
