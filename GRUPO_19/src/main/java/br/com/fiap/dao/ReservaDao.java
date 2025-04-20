package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Reserva;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {
  private Connection conexao;

  public ReservaDao() throws SQLException {
    conexao = ConnectionFactory.getConnection();
  }

  public void cadastrar(Reserva reserva) throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("INSERT INTO T_FIN_RESERVAS (ID_RESERVA,  NOME_RESERVA,  VALOR_TOTAL, DESCRICAO, DATA_INICIO, DATA_PRAZO) VALUES (seq_reserva.nextval, ?, ?, ?, ?, ?)");
    stm.setString(1, reserva.getNome());
    stm.setDouble(2, reserva.getValor());
    stm.setString(3, reserva.getDescricao());
    stm.setDate(4, Date.valueOf(reserva.getDataInicio()));
    stm.setDate(5, Date.valueOf(reserva.getDataPrazo()));
    stm.executeUpdate();
  }

  public Reserva pesquisar(long codigo) throws SQLException, EntidadeNaoEncontradaException {
    PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_FIN_RESERVAS WHERE ID_RESERVA  = ?");
    stm.setLong(1, codigo);
    ResultSet result = stm.executeQuery();

    if (!result.next())
      throw new EntidadeNaoEncontradaException("Reserva não encontrado");

    return parseReserva(result);
  }

  public void fecharConexao() throws SQLException {
    conexao.close();
  }


  public List<Reserva> listar() throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_FIN_RESERVAS");
    ResultSet result = stm.executeQuery();
    List<Reserva> lista = new ArrayList<>();
    while (result.next()){
      Reserva reserva = parseReserva(result);
      lista.add(reserva);
    }
    return lista;
  }

  public void atualizar(Reserva reserva) throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("UPDATE T_FIN_RESERVAS SET NOME_RESERVA = ?,  VALOR_TOTAL = ?,  DESCRICAO = ?, DATA_PRAZO = ? WHERE ID_RESERVA = ? ");
    stm.setString(1, reserva.getNome());
    stm.setDouble(2, reserva.getValor());
    stm.setString(3, reserva.getDescricao());
    stm.setDate(4, Date.valueOf(reserva.getDataPrazo()));
    stm.setLong(5,reserva.getId() );
    stm.executeUpdate();
  }

  public void remover(long codigo) throws SQLException, EntidadeNaoEncontradaException {
    PreparedStatement stm = conexao.prepareStatement("DELETE from T_FIN_RESERVAS where ID_RESERVA = ?");
    stm.setLong(1, codigo);
    int linha = stm.executeUpdate();
    if (linha == 0)
      throw new EntidadeNaoEncontradaException("Reserva não encontrado para ser removido");
  }

  private Reserva parseReserva(ResultSet result) throws SQLException {
    Long id = result.getLong("ID_RESERVA");
    String nome = result.getString("NOME_RESERVA");
    double valor = result.getDouble("VALOR_TOTAL");
    String descricao = result.getString("DESCRICAO");
    LocalDate dataInicio = result.getDate("DATA_INICIO").toLocalDate();
    LocalDate dataPrazo = result.getDate("DATA_PRAZO").toLocalDate();

    return new Reserva(id, nome, valor, descricao, dataInicio,dataPrazo);
  }
}
