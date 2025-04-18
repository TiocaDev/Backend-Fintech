package br.com.fiap.coinmapping.domain.reserva;

import br.com.fiap.coinmapping.domain.reserva.Reserva;
import br.com.fiap.coinmapping.service.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReservaDao {
  private Connection conexao;

  public ReservaDao() throws SQLException {
    conexao = ConnectionFactory.getConnection();
  }

  public void cadastrar(Reserva reserva) throws SQLException {
    Statement stm = conexao.createStatement();
    stm.executeUpdate(
            "INSERT INTO T_FIN_RESERVAS (ID_RESERVA, NOME_RESERVA, VALOR_TOTAL, DESCRICAO, DATA_INICIO, DATA_PRAZO) " +
                    "VALUES (SEQ_RESERVA.NEXTVAL, '" + reserva.getNome() + "', " + reserva.getValor() + ", '" +
                    reserva.getDescricao() + "', TO_DATE('" + reserva.getDataInicio() + "', 'YYYY-MM-DD'), TO_DATE('" +
                    reserva.getDataPrazo() + "', 'YYYY-MM-DD'))"
    );

  }

  public void fecharConexao() throws  SQLException {
    conexao.close();
  }

  public Reserva pesquisar(long id){
    return null;
  }
  public List<Reserva> listar(){
    return null;
  }
  public void atualizar(Reserva reserva){
  }
  public void remover(long id){
  }
}
