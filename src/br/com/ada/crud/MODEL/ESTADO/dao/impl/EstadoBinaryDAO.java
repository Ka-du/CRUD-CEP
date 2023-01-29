package br.com.ada.crud.MODEL.ESTADO.dao.impl;

import br.com.ada.crud.MODEL.ESTADO.Estado;
import br.com.ada.crud.MODEL.ESTADO.dao.EstadoDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoBinaryDAO implements EstadoDAO {
    String diretorioRaiz = "database/binario";
    String diretorioEstado = diretorioRaiz + "/estados";

    @Override
    public void cadastrar(Estado estado) {
        Path diretorio = Paths.get(diretorioEstado);
        if(!diretorio.toFile().exists()){
            throw new RuntimeException("Diretorio nao disponivel");
        }

        File file = new File(
                diretorio.toAbsolutePath().toString(),
                estado.getId().toString()+".dat"
        );

        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(estado);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao trabalhar com os arquivos", e);
        }


    }


    @Override
    public List<Estado> listar() {
        FilenameFilter filter = ((dir, nome) -> nome.endsWith(".dat"));

        List<Estado> estados = new ArrayList<>();
        File diretorio = new File(diretorioEstado);
        for(File file: diretorio.listFiles(filter)){
            try(FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object object = objectInputStream.readObject();
                if(object instanceof Estado){
                    Estado estado = new Estado();
                    estados.add(estado);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Falha ao ler os aquivos" ,e);
            }
        }

        return estados;
    }

    @Override
    public Estado buscar(UUID id) {
        File file = new File(diretorioEstado, id.toString()+".dat");
        try(FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            Object object = objectInputStream.readObject();
            if(object instanceof Estado){
                return (Estado) object;
            }else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Falha ao ler os arquivos",e);
        }

    }


    @Override
    public void update(UUID id, Estado estado) {
        File file = new File(diretorioEstado, id.toString() + ".dat");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(estado);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao atualizar",e);
        }
    }

    @Override
    public Estado apagar(UUID id) {
        Estado estado = buscar(id);
        if(estado != null){
            File file = new File(diretorioEstado, id.toString()+".dat");
            file.delete();
        }


        return estado;
    }

}
