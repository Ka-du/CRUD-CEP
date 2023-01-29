package br.com.ada.crud.view;

import br.com.ada.crud.CONTROLLER.Cidade.CidadeController;
import br.com.ada.crud.CONTROLLER.Cidade.CidadeControllerFactory;
import br.com.ada.crud.CONTROLLER.Estado.EstadoController;
import br.com.ada.crud.CONTROLLER.Estado.EstadoControllerFactory;
import br.com.ada.crud.CONTROLLER.Pais.PaisController;
import br.com.ada.crud.CONTROLLER.Pais.PaisControllerFactory;

import java.util.Scanner;

public class MenuView {

   Scanner scanner = new Scanner(System.in);

    public void MENU() {
        System.out.println("Informe qual dado deseja manipular:");
        System.out.println(" 1 - cidade 2 - estado 3 - pais 0 - sair");
        Integer opcao = scanner.nextInt();
        switch (opcao){
            case 1:
                CidadeController cidadeController = CidadeControllerFactory
                        .getInstance().criar();

                CidadeView viewCidade = new CidadeView(
                        cidadeController,
                        new Scanner(System.in)
                );
                viewCidade.exibirOpcoes();

                break;

            case 2:
                EstadoController estadoController = EstadoControllerFactory
                        .getInstance().criar();

                EstadoView viewEstado = new EstadoView(
                        estadoController,
                        new Scanner(System.in)
                );
                viewEstado.exibirOpcoes();

                break;

            case 3:
                PaisController paisController = PaisControllerFactory
                        .getInstance().criar();

                PaisView viewPais = new PaisView(
                        paisController,
                        new Scanner(System.in)
                );
                viewPais.exibirOpcoes();
                break;
            case 0:
                System.exit(0);
                break;
        }
        MENU();
    }


}
