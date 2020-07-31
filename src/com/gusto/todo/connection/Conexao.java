package com.gusto.todo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	private static Conexao conexao;
	
	private Connection connection;

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
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
		}

		return connection;
	}
}
