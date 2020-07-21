package com.gusto.todo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private Connection connection;
	private static Conexao conexao;

	private final String URL = "jdbc:sqlite:db/tarefas.db";

	private Conexao() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Conexao getConexao() {
		if (conexao == null) {
			conexao = new Conexao();
		}

		return conexao;
	}

	public Connection conectar() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(URL);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
