package br.com.fiap.coinmapping;

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
					Connection conexao = ConnectionFactory.getConnection();
					System.out.println("Conexão realizada!");
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}

	}
}
