package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Conta;
import Model.utill.Conexao;

public class ContaDaoImpl {

	Conexao conexao = new Conexao();

	/**
	 * Método responsável por inserir no banco
	 * 
	 * @param sql
	 */

	public void salvar(Conta conta) {

		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO CONTA(NUMERO, SALDO, LIMITE) " + "VALUES(?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, conta.getNumero());
			ps.setDouble(3, conta.getLimite());
			ps.setDouble(2, conta.getSaldo());
			ps.execute();
			System.out.println("Conta inserida com sucesso.");

		} catch (SQLException e) {
			System.out.println("Erro ao inserir conta no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	/**
	 * Método responsável por deletar do banco
	 * 
	 * @param sql
	 */

	public void remove(int numero) {

		Connection conn = conexao.getConnection();

		String sql = "DELETE FROM CONTA WHERE NUMERO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
			System.out.println("Conta deletada com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao deletar conta do banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void alterar(Conta conta) {
		Connection conn = conexao.getConnection();

		String sql = "UPDATE CONTA SET SALDO = ? , LIMITE = ? WHERE NUMERO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, conta.getSaldo());
			ps.setDouble(2, conta.getSaldo());
			ps.setInt(3, conta.getNumero());
			ps.executeUpdate();
			System.out.println("Conta atualizada com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar a conta no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public List<Conta> listarTodos() {

		Connection conn = conexao.getConnection();
		List<Conta> retorno = new ArrayList<Conta>();

		String sql = "SELECT * FROM CONTA";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
				conta.setNumero(rs.getInt("NUMERO"));
				retorno.add(conta);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar conta - " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

	public Conta pesquisar(Integer numero) {
		Connection conn = conexao.getConnection();
		Conta conta = new Conta();

		String sql = "SELECT * FROM CONTA WHERE NUMERO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar conta - " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return conta;
	}

}
