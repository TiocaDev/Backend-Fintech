package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DespesaDao {
  private Connection conexao;

  public DespesaDao() throws SQLException {
    conexao = ConnectionFactory.getConnection();
  }

  public void cadastrar(Despesa despesa) throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("INSERT INTO T_FIN_DESPESAS (ID_DESPESA,  NOME_DESPESA,  VALOR, DESCRICAO, DATA_INCLUSAO) VALUES (seq_despesa.nextval, ?, ?, ?, ?)");
    stm.setString(1, despesa.getNome());
    stm.setDouble(2, despesa.getValor());
    stm.setString(3, despesa.getDescricao());
    stm.setDate(4, Date.valueOf(despesa.getDataInclusao()));

    stm.executeUpdate();
  }

  public Despesa pesquisar(long codigo) throws SQLException, EntidadeNaoEncontradaException {
    PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_FIN_DESPESAS WHERE ID_DESPESA  = ?");
    stm.setLong(1, codigo);
    ResultSet result = stm.executeQuery();

    if (!result.next())
      throw new EntidadeNaoEncontradaException("Despesa não encontrado");

    return parseDespesa(result);
  }

  public void fecharConexao() throws SQLException {
    conexao.close();
  }


  public List<Despesa> listar() throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_FIN_DESPESAS");
    ResultSet result = stm.executeQuery();
    List<Despesa> lista = new ArrayList<>();
    while (result.next()){
      Despesa despesa = parseDespesa(result);
      lista.add(despesa);
    }
    return lista;
  }

  public void atualizar(Despesa despesa) throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("UPDATE T_FIN_DESPESAS SET NOME_DESPESA = ?,  VALOR = ?,  DESCRICAO = ? WHERE ID_DESPESA = ? ");
    stm.setString(1, despesa.getNome());
    stm.setDouble(2, despesa.getValor());
    stm.setString(3, despesa.getDescricao());
    stm.setLong(4,despesa.getId() );
    stm.executeUpdate();
  }

  public void remover(long codigo) throws SQLException, EntidadeNaoEncontradaException {
    PreparedStatement stm = conexao.prepareStatement("DELETE from T_FIN_DESPESAS where ID_DESPESA = ?");
    stm.setLong(1, codigo);
    int linha = stm.executeUpdate();
    if (linha == 0)
      throw new EntidadeNaoEncontradaException("Despesa não encontrado para ser removido");
  }

  private Despesa parseDespesa(ResultSet result) throws SQLException {
    Long id = result.getLong("ID_DESPESA");
    String nome = result.getString("NOME_DESPESA");
    double valor = result.getDouble("VALOR");
    String descricao = result.getString("DESCRICAO");
    LocalDate dataInclusao = result.getDate("DATA_INCLUSAO").toLocalDate();

    return new Despesa(id, nome, valor, descricao, dataInclusao);
  }
}
