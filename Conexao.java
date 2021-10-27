package Model.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "DATABASE_NAME";
	private String password = "DATABASE_PASSWORD";

	/**
	 * Método responsável por conectar no banco.
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 */

	public Connection getConnection() {
		Connection conn = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			/*
			 * DriverManager = Gerenciador de conexões, responsável por conectar
			 * com o banco
			 */
			conn = DriverManager.getConnection(url, user, password);

			if (conn != null) {
				System.out.println("Banco conectado");
			}
		} catch (Exception e) {
			System.out.println("Erro na conexão com o banco. " + e.getMessage());
		}
		return conn;
	}

	/**
	 * fechar conexao
	 * 
	 * @param conn
	 */

	public void fecharConexao(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexao. " + e.getMessage());
		}
	}
}
