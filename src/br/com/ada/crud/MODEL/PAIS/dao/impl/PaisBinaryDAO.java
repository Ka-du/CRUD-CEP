package br.com.ada.crud.MODEL.PAIS.dao.impl;

import br.com.ada.crud.MODEL.PAIS.Pais;
import br.com.ada.crud.MODEL.PAIS.dao.PaisDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisBinaryDAO implements PaisDAO {


    String diretorioRaiz = "database/binario";
    String diretorioPais = diretorioRaiz + "/paises";

    @Override
    public void cadastrar(Pais pais) {
        Path diretorio = Paths.get(diretorioPais);
        if(!diretorio.toFile().exists()){
            throw new RuntimeException("Diretorio nao disponivel");
        }

        File file = new File(
                diretorio.toAbsolutePath().toString(),
                pais.getId().toString()+".dat"
        );

        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(pais);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao trabalhar com os arquivos", e);
        }


    }


    @Override
    public List<Pais> listar() {
        FilenameFilter filter = ((dir, nome) -> nome.endsWith(".dat"));

        List<Pais> paises = new ArrayList<>();
        File diretorio = new File(diretorioPais);
        for(File file: diretorio.listFiles(filter)){
            try(FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object object = objectInputStream.readObject();
                if(object instanceof Pais){
                    Pais pais = new Pais();
                    paises.add(pais);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Falha ao ler os aquivos" ,e);
            }
        }

        return paises;
    }

    @Override
    public Pais buscar(UUID id) {
        File file = new File(diretorioPais, id.toString()+".dat");
        try(FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            Object object = objectInputStream.readObject();
            if(object instanceof Pais){
                return (Pais) object;
            }else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Falha ao ler os arquivos",e);
        }

    }


    @Override
    public void update(UUID id, Pais pais) {
        File file = new File(diretorioPais, id.toString() + ".dat");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(pais);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao atualizar",e);
        }
    }

    @Override
    public Pais apagar(UUID id) {
        Pais pais = buscar(id);
        if(pais != null){
            File file = new File(diretorioPais, id.toString()+".dat");
            file.delete();
        }


        return pais;
    }

}
