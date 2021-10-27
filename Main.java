package controller;

import java.sql.Connection;
import java.util.List;

import Model.Conta;
import Model.Endereco;
import Model.utill.Conexao;
import dao.impl.ContaDaoImpl;
import dao.impl.EnderecoDAOImpl;

public class Main {

	public static void main(String[] args) {

		Conexao conexao = new Conexao();
		Connection conn = conexao.getConnection();

		Conta conta = new Conta();
		conta.setNumero(1001);
		conta.setSaldo(45000d);
		conta.setLimite(10000d);

		ContaDaoImpl contaDao = new ContaDaoImpl();
		contaDao.salvar(conta); // Insere a conta
		contaDao.remove(1001); //Deleta a conta de numero 1001
		contaDao.alterar(conta);

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua");
		endereco.setNumero(334);
		endereco.setComplement("Comp");
		endereco.setId(152);

		EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl();

		enderecoDAO.salvar(endereco); //Insere o endereço
		enderecoDAO.remover(152); //Deleta o endereço
		enderecoDAO.alterar(endereco); //Altera o endereço
		List<Endereco> end = enderecoDAO.listarTodos();
		System.out.println(end); // Lista todos os endereços

		Endereco endereco1 = enderecoDAO.pesquisar(152);
		System.out.println(endereco1.toString());
	}
}
