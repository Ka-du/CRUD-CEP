package br.com.ada.crud.MODEL.CIDADE.dao.impl;

import br.com.ada.crud.MODEL.CIDADE.Cidade;
import br.com.ada.crud.MODEL.CIDADE.dao.CidadeDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CidadeBinaryDAO implements CidadeDAO {

   String diretorioRaiz = "database/binario";
   String diretorioCidade = diretorioRaiz + "/cidades";

    @Override
    public void cadastrar(Cidade cidade) {
        Path diretorio = Paths.get(diretorioCidade);
        if(!diretorio.toFile().exists()){
           throw new RuntimeException("Diretorio nao disponivel");
        }

        File file = new File(
                diretorio.toAbsolutePath().toString(),
                cidade.getId().toString()+".dat"
        );

        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
           objectOutputStream.writeObject(cidade);
           objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao trabalhar com os arquivos", e);
        }


    }

    @Override
    public List<Cidade> listar() {
        FilenameFilter filter = ((dir, nome) -> nome.endsWith(".dat"));

        List<Cidade> cidades = new ArrayList<>();
        File diretorio = new File(diretorioCidade);
        for(File file: diretorio.listFiles(filter)){
            try(FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object object = objectInputStream.readObject();
                if(object instanceof Cidade){
                    Cidade cidade = new Cidade();
                    cidades.add(cidade);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Falha ao ler os aquivos" ,e);
            }
        }

        return cidades;
    }

    @Override
    public Cidade buscar(UUID id) {
        File file = new File(diretorioCidade, id.toString()+".dat");
        try(FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            Object object = objectInputStream.readObject();
            if(object instanceof Cidade){
                return (Cidade) object;
            }else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Falha ao ler os arquivos",e);
        }

    }

    @Override
    public void update(UUID id, Cidade cidade) {
        File file = new File(diretorioCidade, id.toString() + ".dat");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(cidade);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao atualizar",e);
        }
    }

    @Override
    public Cidade apagar(UUID id) {
        Cidade cidade = buscar(id);
        if(cidade != null){
            File file = new File(diretorioCidade, id.toString()+".dat");
            file.delete();
        }


        return cidade;
    }
}
