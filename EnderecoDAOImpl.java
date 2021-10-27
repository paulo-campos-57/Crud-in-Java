package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Endereco;
import Model.utill.Conexao;

public class EnderecoDAOImpl {

	Conexao conexao = new Conexao();

	public int getSequence() {

		Connection conn = conexao.getConnection();
		Integer retorno = null;

		String sql = "SELECT S_ID_ENDERECO.NEXTVAL AS SEQUENCE FROM DUAL";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				retorno = rs.getInt("SEQUENCE");
			}
		} catch (Exception e) {
			System.out.println("Erro ao sequence " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

	public void salvar(Endereco endereco) {

		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO ENDERECO(ID_ENDERECO, RUA, NUMERO, COMPLEMENTO) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, getSequence());
			ps.setString(2, endereco.getLogradouro());
			ps.setInt(3, endereco.getNumero());
			ps.setString(4, endereco.getComplement());
			ps.execute();
			System.out.println("Endereço inserido com sucesso");

		} catch (Exception e) {
			System.out.println("Erro ao inserir endereço no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(int id) {

		Connection conn = conexao.getConnection();

		String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Endereço deletado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao deletar o endereço no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void alterar(Endereco endereco) {

		Connection conn = conexao.getConnection();

		String sql = "UPDATE ENDERECO SET RUA = ? , NUMERO = ? , COMPLEMENTO = ?" + "WHERE ID_ENDERECO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getLogradouro());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplement());
			ps.setInt(4, endereco.getId());
			ps.executeUpdate();
			System.out.println("Endereço atualizado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o endereço no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public List<Endereco> listarTodos() {

		Connection conn = conexao.getConnection();
		List<Endereco> retorno = new ArrayList<Endereco>();

		String sql = "SELECT * FROM ENDERECO";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setComplement(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setLogradouro(rs.getString("RUA"));
				retorno.add(endereco);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar endereco - " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

	public Endereco pesquisar(Integer id) {

		Connection conn = conexao.getConnection();
		Endereco endereco = new Endereco();

		String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				endereco.setComplement(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setLogradouro(rs.getString("RUA"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar endereço - " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return endereco;
	}

}
