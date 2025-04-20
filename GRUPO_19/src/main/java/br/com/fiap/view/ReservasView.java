package br.com.fiap.view;
import br.com.fiap.dao.ReservaDao;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.model.Reserva;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class ReservasView {
  public void exibeMenu() {
    Scanner scanner = new Scanner(System.in);
    ReservaDao dao;
    System.out.println("===================================================================================================");
    System.out.println("Bem vindo a manipulação da tabela de reservas");
    System.out.println("===================================================================================================");
    try {
      dao = new ReservaDao();
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
            pesquisarReserva(scanner, dao);
            break;
          case 3:
            listar(dao);
            break;
          case 4:
            atualizar(scanner, dao);
            break;
          case 5:
            removerReserva(scanner, dao);
            break;
          case 0:

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
  private static void cadastrar(Scanner scanner, ReservaDao dao) {

    System.out.println("Digite o nome da reserva:");
    String nome = scanner.next() + scanner.nextLine();

    System.out.println("Digite o  valor da reserva:");
    double valor = scanner.nextDouble();

    System.out.println("Digite a  descrição da reserva:");
    String descricao = scanner.next() + scanner.nextLine();

    System.out.println("Digite o  prazo final da reserva em meses:");
    int prazo = scanner.nextInt();

    Reserva novaReserva = new Reserva(nome , valor,descricao,LocalDate.now(),LocalDate.now().plusMonths(prazo));
    try {
      dao.cadastrar(novaReserva);
      System.out.println("Reserva cadastrado com sucesso!");
    } catch (SQLException e) {
      System.err.println("Erro ao cadastrar reserva: " + e.getMessage());
    }
  }
  private static void pesquisarReserva(Scanner scanner, ReservaDao dao) {
    System.out.println("Digite o código da reserva:");
    long codigo = scanner.nextLong();
    try {
      Reserva reserva = dao.pesquisar(codigo);
      System.out.println("Reserva encontrado:");
      System.out.println(reserva);
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao pesquisar reserva: " + e.getMessage());
    }
  }
  private static void listar(ReservaDao dao) {
    try {

      List<Reserva> reservas = dao.listar();
      System.out.println("Lista de reservas:");
      reservas.forEach(System.out::println);

    } catch (SQLException e) {
      System.err.println("Erro ao listar reservas: " + e.getMessage());
    }
  }
  private static void atualizar(Scanner scanner, ReservaDao dao) {
    System.out.println("Digite o código da reserva que deseja atualizar:");
    long codigo = scanner.nextLong();
    try {
      Reserva reserva = dao.pesquisar(codigo);

      System.out.println("Digite o novo nome da reserva:");
      String nome = scanner.next() + scanner.nextLine();

      System.out.println("Digite o novo valor da reserva:");
      double valor = scanner.nextDouble();

      System.out.println("Digite a nova descrição da reserva:");
      String descricao = scanner.next() + scanner.nextLine();

      System.out.println("Digite o novo prazo da reserva em meses:");
      int prazo = scanner.nextInt();

      reserva.setNome(nome);
      reserva.setDescricao(descricao);
      reserva.setValor(valor);
      reserva.setDataPrazo(LocalDate.now().plusMonths(prazo));

      dao.atualizar(reserva);

      System.out.println("Reserva atualizado com sucesso!");
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao atualizar reserva: " + e.getMessage());
    }
  }
  private static void removerReserva(Scanner scanner, ReservaDao dao) {
    System.out.println("Digite o código da reserva que deseja remover:");
    long codigo = scanner.nextLong();
    try {
      dao.remover(codigo);
      System.out.println("Reserva removido com sucesso!");
    } catch (SQLException | EntidadeNaoEncontradaException e) {
      System.err.println("Erro ao remover reserva: " + e.getMessage());
    }
  }
}