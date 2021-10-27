package Model;

public class Pessoa {

	private String cpf;
	private String nome;
	private int idade;
	private String sexo;
	private Endereco endreco;
	private Conta conta;

	public Pessoa(String cpf, String nome, int idade, String sexo, Endereco endreco, Conta conta) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.endreco = endreco;
		this.conta = conta;
	}

	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndreco() {
		return endreco;
	}

	public void setEndreco(Endereco endreco) {
		this.endreco = endreco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Pessoa [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", endreco=" + endreco
				+ ", conta=" + conta + "]";
	}

}
