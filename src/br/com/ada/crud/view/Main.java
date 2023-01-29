package br.com.ada.crud.view;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) throws IOException {

        System.out.println("bem vindo ao sistema de cadastro");
        MenuView menu = new MenuView();
        menu.MENU();

    }

    /*public static void main(String[] args) throws IOException {
        CidadeController cidadeController = CidadeControllerFactory
                .getInstance().criar();

        CidadeView view = new CidadeView(
                cidadeController,
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }*/

}