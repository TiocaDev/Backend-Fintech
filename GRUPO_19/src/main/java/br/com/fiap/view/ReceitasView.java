package br.com.fiap.view;
import br.com.fiap.dao.ReceitaDao;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.model.Receita;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReceitasView {
  public void exibeMenu() {
    Scanner scanner = new Scanner(System.in);
    ReceitaDao dao;
    System.out.println("===================================================================================================");
    System.out.println("Bem vindo a manipulação da tabela de receitas");
    System.out.println("===================================================================================================");
    try {
      dao = new ReceitaDao();
      int escolha;
      do {
        System.out.println("===================================================================================================");
        System.out.println("Escolha uma opção:\n1-Cadastrar\n2-Pesquisar por Código\n3-Listar\n4-Atualizar\n5-Remover\n0-Sair");
        System.out.println("===================================================================================================");
        escolha = scanner.nextInt();
        switch (escolha) {
          case 1:
            cadastrar(scanner, dao);
            break;
          case 2:
            pesquisarReceita(scanner, dao);
            break;
          case 3:
            listar(dao);
            break;
          case 4:
            atualizar(scanner, dao);
            break;
          case 5:
            removerReceita(scanner, dao);
            break;
          case 0:
            System.out.println("Saindo...");
            break;
          default:
            System.out.println("Opção inválida! Tente novamente.");
        }
      } while (escolha != 0);
      dao.fecharConexao();
    } catch (SQLException e) {
      System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
    }
  }
  private static void cadastrar(Scanner scanner, ReceitaDao dao) {

    System.out.println("Digite o nome da receita:");
    String nome = scanner.next() + scanner.nextLine();

    System.out.println("Digite o  valor da receita:");
    double valor = scanner.nextDouble();

    System.out.println("Digite a  descrição da receita:");
    String descricao = scanner.next() + scanner.nextLine();

    Receita novaReceita = new Receita(nome,valor,descricao,LocalDate.now());

    try {
      dao.cadastrar(novaReceita);
      System.out.println("Receita cadastrado com sucesso!");
    } catch (SQLException e) {
      System.err.println("Erro ao cadastrar receita: " + e.getMessage());
    }
  }
  private static void pesquisarReceita(Scanner scanner, ReceitaDao dao) {
    System.out.println("Digite o código da receita:");
    long codigo = scanner.nextLong();
    try {
      Receita receita = dao.pesquisar(codigo);
      System.out.println("Receita encontrado:");
      System.out.println(receita);
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao pesquisar receita: " + e.getMessage());
    }
  }
  private static void listar(ReceitaDao dao) {
    try {

      List<Receita> receitas = dao.listar();
      System.out.println("Lista de receitas:");
      receitas.forEach(System.out::println);

    } catch (SQLException e) {
      System.err.println("Erro ao listar receitas: " + e.getMessage());
    }
  }
  private static void atualizar(Scanner scanner, ReceitaDao dao) {
    System.out.println("Digite o código da receita que deseja atualizar:");
    long codigo = scanner.nextLong();
    try {
      Receita receita = dao.pesquisar(codigo);

      System.out.println("Digite o novo nome da receita:");
      String nome = scanner.next() + scanner.nextLine();

      System.out.println("Digite o novo valor da receita:");
      double valor = scanner.nextDouble();

      System.out.println("Digite a nova descrição da receita:");
      String descricao = scanner.next() + scanner.nextLine();

      receita.setNome(nome);
      receita.setDescricao(descricao);
      receita.setValor(valor);

      dao.atualizar(receita);

      System.out.println("Receita atualizado com sucesso!");
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao atualizar receita: " + e.getMessage());
    }
  }
  private static void removerReceita(Scanner scanner, ReceitaDao dao) {
    System.out.println("Digite o código da receita que deseja remover:");
    long codigo = scanner.nextLong();
    try {
      dao.remover(codigo);
      System.out.println("Receita removido com sucesso!");
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao remover receita: " + e.getMessage());
    }
  }


}