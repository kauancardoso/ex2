
public class Produto {
 // atributos
	private int numSerie;
	private String nome;
	private double quantidade;
	private boolean disponivelEstoque;
	
 // metodos
	

	// construtores
	public Produtos() {
		this.setNumSerie(-1);
		this.setnome(null);
		this.setquantidade(0);
		this.setdisponivelEstoque(true);
	}// fim Produtos()
	
	public Produtos(int serie, String tam, double qnt, boolean estoque) {
		this.setNumSerie(serie);
		this.setnome(tam);
		this.setquantidade(qnt);
		this.setdisponivelEstoque(estoque);
	}// fim Produtos()

 // metodos especiais
	public int getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(int numSerie) {
		this.numSerie = numSerie;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public double getquantidade() {
		return quantidade;
	}

	public void setquantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isdisponivelEstoque() {
		return disponivelEstoque;
	}

	public void setdisponivelEstoque(boolean disponivelEstoque) {
		this.disponivelEstoque = disponivelEstoque;
	}

	@Override
	public String toString() {
		return "Produtos [numSerie=" + numSerie + ", Nome=" + nome + ", Quantidade =" + quantidade + ", Estoque=" + disponivelEstoque + "]";
	}	
	
}
