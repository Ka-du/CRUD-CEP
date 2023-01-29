package br.com.ada.crud.view;

import br.com.ada.crud.CONTROLLER.Cidade.CidadeController;
import br.com.ada.crud.MODEL.CIDADE.Cidade;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CidadeView {

    private CidadeController controller;
    private Scanner scanner;

    public CidadeView(
        CidadeController controller,
        Scanner scanner
    ){
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar(){
        Cidade cidade = new Cidade();

        System.out.println("Informe o nome da cidade:");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.println("Informe a sigla do estado que a cidade pertence:");
        String estado = scanner.nextLine();
        cidade.setEstado(estado);

        controller.cadastrar(cidade);

    }

    public void listar(){
        List<Cidade> cidades = controller.listar();
        System.out.println("Numero | Nome     | Estado");
        for(int index = 0; index < cidades.size(); index++){
            System.out.print((index+1) + "     ");
            exibirCidade(cidades.get(index));
        }
    }

    public void update(){
        listar();
        System.out.println("Informe o numero da cidade que deseja atualizar:");
        Integer numeroCidade = scanner.nextInt();
        Cidade cidade = controller.listar().get(numeroCidade-1);
        update(cidade);
    }

    public void update(Cidade cidade){
        exibirCidade(cidade);

        System.out.println("Informe o novo nome da cidade:");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.println("Informe a sigla do estado:");
        String sigla = scanner.nextLine();
        scanner.nextLine();
        cidade.setEstado(sigla);
        System.out.println("atualizando.....");



        controller.update(cidade.getId(), cidade);

    }

    public void apagar(){
        listar();
        System.out.println("Informe o numero da cidade que deseja apagar:");
        Integer numero = scanner.nextInt();
        Cidade cidade = controller.listar().get(numero - 1);
        apagar(cidade.getId());
    }

    public void apagar(UUID id){
        Cidade cidade = controller.delete(id);
        System.out.println("A cidade apagada foi:");
        exibirCidade(cidade);
    }

    public void exibirCidade(Cidade cidade){
        System.out.print("| ");
        System.out.print(cidade.getNome());
        System.out.print(" |  ");

        System.out.print(" | ");
        System.out.print(cidade.getEstado());
        System.out.println(" |");

    }

    public void exibirOpcoes(){
        System.out.println("Informe a operacao desejada:");
        System.out.println("1 - cadastrar 2 - Listar 3 - Atualizar 4 - apagar 0 - sair");
        Integer opcao = scanner.nextInt();
        switch (opcao){
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                update();
                break;
            case 4:
                apagar();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opcao invalida!!!");
        }
        exibirOpcoes();
    }


}
