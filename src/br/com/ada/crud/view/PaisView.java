package br.com.ada.crud.view;

import br.com.ada.crud.CONTROLLER.Pais.PaisController;
import br.com.ada.crud.MODEL.PAIS.Pais;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaisView {

    private PaisController controller;
    private Scanner scanner;

    public PaisView(
            PaisController controller,
            Scanner scanner
    ){
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar(){
        Pais pais = new Pais();

        System.out.println("Informe o nome do pais:");
        String nome = scanner.nextLine();
        pais.setNome(nome);

        System.out.println("Informe a sigla do pais:");
        String sigla = scanner.nextLine();
        pais.setSigla(sigla);

        controller.cadastrar(pais);

    }

    public void listar(){
        List<Pais> paises = controller.listar();
        System.out.println("Numero | Nome     | Sigla");
        for(int index = 0; index < paises.size(); index++){
            System.out.println((index+1) + "     ");
            exibirPais(paises.get(index));
        }
    }

    public void update(){
        listar();
        System.out.println("Informe o numero do pais que deseja atualizar:");
        Integer numeroPais = scanner.nextInt();
        Pais pais = controller.listar().get(numeroPais-1);
        update(pais);
    }

    public void update(Pais pais){
        exibirPais(pais);

        System.out.println("Informe o novo nome do pais:");
        String nome = scanner.next();
        pais.setNome(nome);

        System.out.println("Informe a sigla do pais:");
        String sigla = scanner.next();
        pais.setSigla(sigla);

        controller.update(pais.getId(), pais);

    }

    public void apagar(){
        listar();
        System.out.println("Informe o numero do pais que deseja apagar:");
        Integer numero = scanner.nextInt();
        Pais pais = controller.listar().get(numero - 1);
        apagar(pais.getId());
    }

    public void apagar(UUID id){
        Pais pais = controller.delete(id);
        System.out.println("A cidade apagada foi:");
        exibirPais(pais);
    }

    public void exibirPais(Pais pais){
        System.out.print("| ");
        System.out.print(pais.getNome());
        System.out.print(" |  ");

        System.out.print(" | ");
        System.out.print(pais.getSigla());
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
