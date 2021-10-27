package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Pessoa;
import Model.utill.Conexao;

public class PessoaDAOImpl {

	Conexao conexao = new Conexao();
	EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl();
	ContaDaoImpl contaDAO = new ContaDaoImpl();

	public void salvar(Pessoa pessoa) {

		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO PESSOA(NOME, CPF, SEXO, IDADE, ID_ENDERECO, NUMERO_CONTA) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			this.contaDAO.salvar(pessoa.getConta());
			pessoa.getEndreco().setId(this.enderecoDAO.getSequence());
			this.enderecoDAO.salvar(pessoa.getEndreco());

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getCpf());
			ps.setString(3, pessoa.getSexo());
			ps.setInt(4, pessoa.getIdade());
			ps.setInt(5, pessoa.getEndreco().getId());
			ps.setInt(6, pessoa.getConta().getNumero());
			ps.execute();
			System.out.println("Inserido com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(String cpf) {

		Connection conn = conexao.getConnection();
		Pessoa p = pesquisar(cpf);

		String sql = "DELETE FROM PESSOA WHERE CPF = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.execute();
			System.out.println("Pessoa deletado com sucesso");
			contaDAO.remove(p.getConta().getNumero());
			enderecoDAO.remover(p.getEndreco().getId());

		} catch (Exception e) {
			System.out.println("Erro ao deletar pessoa do banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void alterar(Pessoa pessoa) {

		Connection conn = conexao.getConnection();
		String sql = "UPDATE PESSOA SET NOME = ? , SEXO = ?, IDADE = ?, ID_ENDERECO = ?, "
				+ "NUMERO_CONTA = ? WHERE CPF = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getSexo());
			ps.setInt(3, pessoa.getIdade());
			ps.setInt(4, pessoa.getEndreco().getId());
			ps.setInt(5, pessoa.getConta().getNumero());
			ps.executeUpdate();
			System.out.println("Atualizado com sucesso");

		} catch (Exception e) {
			System.out.println("Erro ao atualizar no banco " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public List<Pessoa> ListarTodos() {

		Connection conn = conexao.getConnection();
		List<Pessoa> retorno = new ArrayList<Pessoa>();

		String sql = "SELECT * FROM PESSOA";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("SEXO"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setEndreco(this.enderecoDAO.pesquisar(rs.getInt("ID_ENDERECO")));
				pessoa.setConta(this.contaDAO.pesquisar(rs.getInt("NUMERO")));
				retorno.add(pessoa);
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

	public Pessoa pesquisar(String cpf) {

		Connection conn = conexao.getConnection();
		Pessoa pessoa = new Pessoa();

		String sql = "SELECT * FROM PESSOA WHERE CPF = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("SEXO"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setEndreco(this.enderecoDAO.pesquisar(rs.getInt("ID_ENDERECO")));
				pessoa.setConta(this.contaDAO.pesquisar(rs.getInt("NUMERO_CONTA")));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pessoa - " + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return pessoa;
	}

}
