package Model;

public class Endereco {

	private String logradouro;
	private int numero;
	private String complement;
	private int id;

	public Endereco(String logradouro, int numero, String complement, int id) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complement = complement;
		this.id = id;
	}

	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", complement=" + complement + ", id=" + id
				+ "]";
	}

}
