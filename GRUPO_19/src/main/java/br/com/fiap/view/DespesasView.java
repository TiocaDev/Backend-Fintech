package br.com.fiap.view;
import br.com.fiap.dao.DespesaDao;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.model.Despesa;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DespesasView {
  public void exibeMenu(){
    Scanner scanner = new Scanner(System.in);
    DespesaDao dao;
    System.out.println("===================================================================================================");
    System.out.println("Bem vindo a manipulação da tabela de despesas");
    System.out.println("===================================================================================================");
    try {
      dao = new DespesaDao();
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
            pesquisarDespesa(scanner, dao);
            break;
          case 3:
            listar(dao);
            break;
          case 4:
            atualizar(scanner, dao);
            break;
          case 5:
            removerDespesa(scanner, dao);
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
  private static void cadastrar(Scanner scanner, DespesaDao dao) {

    System.out.println("Digite o nome da despesa:");
    String nome = scanner.next() + scanner.nextLine();

    System.out.println("Digite o  valor da despesa:");
    double valor = scanner.nextDouble();

    System.out.println("Digite a  descrição da despesa:");
    String descricao = scanner.next() + scanner.nextLine();

    Despesa novaDespesa = new Despesa(nome,valor,descricao,LocalDate.now());

    try {
      dao.cadastrar(novaDespesa);
      System.out.println("Despesa cadastrado com sucesso!");
    } catch (SQLException e) {
      System.err.println("Erro ao cadastrar despesa: " + e.getMessage());
    }
  }
  private static void pesquisarDespesa(Scanner scanner, DespesaDao dao) {
    System.out.println("Digite o código da despesa:");
    long codigo = scanner.nextLong();
    try {
      Despesa despesa = dao.pesquisar(codigo);
      System.out.println("Despesa encontrado:");
      System.out.println(despesa);
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao pesquisar despesa: " + e.getMessage());
    }
  }
  private static void listar(DespesaDao dao) {
    try {

      List<Despesa> despesas = dao.listar();
      System.out.println("Lista de despesas:");
      despesas.forEach(System.out::println);

    } catch (SQLException e) {
      System.err.println("Erro ao listar despesas: " + e.getMessage());
    }
  }
  private static void atualizar(Scanner scanner, DespesaDao dao) {
    System.out.println("Digite o código da despesa que deseja atualizar:");
    long codigo = scanner.nextLong();
    try {
      Despesa despesa = dao.pesquisar(codigo);

      System.out.println("Digite o novo nome da despesa:");
      String nome = scanner.next() + scanner.nextLine();

      System.out.println("Digite o novo valor da despesa:");
      double valor = scanner.nextDouble();

      System.out.println("Digite a nova descrição da despesa:");
      String descricao = scanner.next() + scanner.nextLine();

      despesa.setNome(nome);
      despesa.setDescricao(descricao);
      despesa.setValor(valor);

      dao.atualizar(despesa);

      System.out.println("Despesa atualizado com sucesso!");
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao atualizar despesa: " + e.getMessage());
    }
  }
  private static void removerDespesa(Scanner scanner, DespesaDao dao) {
    System.out.println("Digite o código da despesa que deseja remover:");
    long codigo = scanner.nextLong();
    try {
      dao.remover(codigo);
      System.out.println("Despesa removido com sucesso!");
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao remover despesa: " + e.getMessage());
    }
  }
}