package br.com.ada.crud.view;


import br.com.ada.crud.CONTROLLER.Estado.EstadoController;
import br.com.ada.crud.MODEL.ESTADO.Estado;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class EstadoView {

    private EstadoController controller;
    private Scanner scanner;

    public EstadoView(
            EstadoController controller,
            Scanner scanner
    ){
        this.controller = controller;
        this.scanner = scanner;
    }


    public void cadastrar(){
        Estado estado = new Estado();

        System.out.println("digite o nome do estado:");
        String nome = scanner.nextLine();
        estado.setNome(nome);

        System.out.println("digite a sigla do estado");
        String sigla = scanner.nextLine();
        estado.setSigla(sigla);

        System.out.println("digite o pais que o estado pertence:");
        String pais = scanner.nextLine();
        estado.setPais(pais);

        controller.cadastrar(estado);
    }

    public void listar(){
        List<Estado> estados = controller.listar();
        System.out.println("Numero | Nome     | Sigla     | Pais");
        for(int index = 0; index < estados.size(); index++){
            System.out.println((index +1) + "     ");
            exibirEstado(estados.get(index));

        }
    }

    public void update (){
        listar();
        System.out.println("Informe o numero do estado que deseja atualizar:");
        Integer numeroEstado = scanner.nextInt();
        Estado estado = controller.listar().get(numeroEstado - 1);
        update(estado);
    }

    public void update(Estado estado){
        exibirEstado(estado);

        System.out.println("Informe o novo nome do estado:");
        String nome = scanner.nextLine();
        estado.setNome(nome);

        System.out.println("Informe a nova sigla do estado:");
        String sigla = scanner.nextLine();
        estado.setSigla(sigla);

        System.out.println("Informe o novo pais:");
        String pais = scanner.nextLine();
        estado.setPais(pais);

        controller.update(estado.getId(), estado);
    }


    public void apagar(){
        listar();
        System.out.println("Informe o numero do estado que deseja apagar:");
        Integer numero = scanner.nextInt();

        Estado estado = controller.listar().get(numero-1);
        apagar(estado.getId());
    }

    public void apagar(UUID id){
        Estado estado = controller.delete(id);
        System.out.println("estado apagado foi:");
        exibirEstado(estado);
    }

    public void exibirEstado(Estado estado){
        System.out.print("| ");
        System.out.print(estado.getNome());
        System.out.print(" |  ");

        System.out.print(" | ");
        System.out.print(estado.getSigla());
        System.out.print(" |");

        System.out.print(" | ");
        System.out.print(estado.getPais());
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
