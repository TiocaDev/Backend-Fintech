package br.com.fiap.coinmapping;

import br.com.fiap.coinmapping.domain.reserva.Reserva;
import br.com.fiap.coinmapping.domain.reserva.ReservaDao;
import br.com.fiap.coinmapping.service.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class CoinmappingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CoinmappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				try {
					ReservaDao dao = new ReservaDao();
					Reserva reserva = new Reserva("Carro",199.99,"Quero realizar meu sonho","2025-04-18","2025-05-18");
					dao.cadastrar(reserva);
					dao.fecharConexao();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}

	}
}
