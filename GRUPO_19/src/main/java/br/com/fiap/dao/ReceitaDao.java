package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Receita;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDao {
  private Connection conexao;

  public ReceitaDao() throws SQLException {
    conexao = ConnectionFactory.getConnection();
  }

  public void cadastrar(Receita receita) throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("INSERT INTO T_FIN_RECEITAS (ID_RECEITA,  NOME_RECEITA,  VALOR, DESCRICAO, DATA_INCLUSAO) VALUES (seq_receita.nextval, ?, ?, ?, ?)");
    stm.setString(1, receita.getNome());
    stm.setDouble(2, receita.getValor());
    stm.setString(3, receita.getDescricao());
    stm.setDate(4, Date.valueOf(receita.getDataInclusao()));

    stm.executeUpdate();
  }

  public Receita pesquisar(long codigo) throws SQLException, EntidadeNaoEncontradaException {
    PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_FIN_RECEITAS WHERE ID_RECEITA  = ?");
    stm.setLong(1, codigo);
    ResultSet result = stm.executeQuery();

    if (!result.next())
      throw new EntidadeNaoEncontradaException("Receita não encontrado");

    return parseReceita(result);
  }

  public void fecharConexao() throws SQLException {
    conexao.close();
  }


  public List<Receita> listar() throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_FIN_RECEITAS");
    ResultSet result = stm.executeQuery();
    List<Receita> lista = new ArrayList<>();
    while (result.next()){
      Receita receita = parseReceita(result);
      lista.add(receita);
    }
    return lista;
  }

  public void atualizar(Receita receita) throws SQLException {
    PreparedStatement stm = conexao.prepareStatement("UPDATE T_FIN_RECEITAS SET NOME_RECEITA = ?,  VALOR = ?,  DESCRICAO = ? WHERE ID_RECEITA = ? ");
    stm.setString(1, receita.getNome());
    stm.setDouble(2, receita.getValor());
    stm.setString(3, receita.getDescricao());
    stm.setLong(4,receita.getId() );
    stm.executeUpdate();
  }

  public void remover(long codigo) throws SQLException, EntidadeNaoEncontradaException {
    PreparedStatement stm = conexao.prepareStatement("DELETE from T_FIN_RECEITAS where ID_RECEITA = ?");
    stm.setLong(1, codigo);
    int linha = stm.executeUpdate();
    if (linha == 0)
      throw new EntidadeNaoEncontradaException("Receita não encontrado para ser removido");
  }

  private Receita parseReceita(ResultSet result) throws SQLException {
    Long id = result.getLong("ID_RECEITA");
    String nome = result.getString("NOME_RECEITA");
    double valor = result.getDouble("VALOR");
    String descricao = result.getString("DESCRICAO");
    LocalDate dataInclusao = result.getDate("DATA_INCLUSAO").toLocalDate();

    return new Receita(id, nome, valor, descricao, dataInclusao);
  }
}
