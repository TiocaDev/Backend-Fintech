package br.com.fiap;

import br.com.fiap.view.DespesasView;
import br.com.fiap.view.ReceitasView;
import br.com.fiap.view.ReservasView;

import java.util.Scanner;


public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("===================================================================================================");
    System.out.println("Bem vindo ao CoinMapping");
    System.out.println("===================================================================================================");

    ReceitasView receitas = new ReceitasView();
    DespesasView despesas = new DespesasView();
    ReservasView reservas = new ReservasView();
    int escolha;
    do {
      System.out.println("===================================================================================================");
      System.out.println("Escolha uma tabela para manipular:\n1-receitas\n2-despesas\n3-reservas\n0-sair");
      System.out.println("===================================================================================================");
      escolha = scanner.nextInt();
      switch (escolha) {
        case 1:
          receitas.exibeMenu();
          break;
        case 2:
          despesas.exibeMenu();
          break;
        case 3:
          reservas.exibeMenu();
        case 0:
          System.out.println("Saindo...");
          break;
        default:
          System.out.println("Opção inválida! Tente novamente.");
      }
    } while (escolha != 0);

  }
}