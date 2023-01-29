package br.com.ada.crud.MODEL.PAIS.dao.impl;


import br.com.ada.crud.MODEL.PAIS.Pais;
import br.com.ada.crud.MODEL.PAIS.dao.PaisDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisXMLDAO implements PaisDAO {

    private String diretorio = "database/xml/paises";

    @Override
    public void cadastrar(Pais pais) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document = builder.newDocument();
            Element elementPais = document.createElement("pais");
            document.appendChild(elementPais);

            Element elementId = document.createElement("id");
            elementId.setTextContent(pais.getId().toString());
            elementPais.appendChild(elementId);

            Element elementNome = document.createElement("nome");
            elementNome.setTextContent(pais.getNome());
            elementPais.appendChild(elementNome);

            Element elementSigla = document.createElement("sigla");
            elementSigla.setTextContent(pais.getSigla().toString());
            elementPais.appendChild(elementSigla);

            File arquivo = new File(diretorio, pais.getId().toString() + ".xml");
            try (FileOutputStream output = new FileOutputStream(arquivo)) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(output);

                transformer.transform(source, result);
            } catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pais> listar() {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(".xml");
        File diretoRaiz = new File(diretorio);
        List<Pais> paises = new ArrayList<>();
        for (File file : diretoRaiz.listFiles(filter)) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(file);

                Element elementPais = document.getDocumentElement();
                Node elementId = elementPais.getElementsByTagName("id").item(0);
                Node elementNome = elementPais.getElementsByTagName("nome").item(0);
                Node elementSigla = elementPais.getElementsByTagName("sigla").item(0);

                Pais pais = new Pais();
                pais.setId(UUID.fromString(elementId.getTextContent()));
                pais.setNome(elementNome.getTextContent());
                pais.setSigla(elementSigla.getTextContent());

                paises.add(pais);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return paises;

    }

    @Override
    public Pais buscar(UUID id) {
        File file = new File(diretorio, id.toString() + ".xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(file);
            Element elementPais = document.getDocumentElement();
            Node elementId = elementPais.getElementsByTagName("id").item(0);
            Node elementNome = elementPais.getElementsByTagName("nome").item(0);
            Node elementSigla = elementPais.getElementsByTagName("sigla").item(0);


            Pais pais = new Pais();
            pais.setId(UUID.fromString(elementId.getTextContent()));
            pais.setNome(elementNome.getTextContent());
            pais.setSigla(elementSigla.getTextContent());

            return pais;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UUID id, Pais pais) {
        File file = new File(diretorio, id.toString() + ".xml");
        file.delete();
        pais.setId(id);
        cadastrar(pais);
    }

    @Override
    public Pais apagar(UUID id) {
        Pais pais = buscar(id);
        if(pais != null){
            File file = new File(diretorio, id.toString() + ".xml");
            file.delete();
        }

        return pais;
    }
}

