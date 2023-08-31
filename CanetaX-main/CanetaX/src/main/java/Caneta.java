
public class Caneta {
 // atributos
	private int numSerie;
	private String cor;
	private double tamPonta;
	private boolean tampada;
	
 // metodos
	

	// construtores
	public Caneta() {
		this.setNumSerie(-1);
		this.setCor(null);;
		this.setTamPonta(0);
		this.setTampada(true);
	}// fim Caneta()
	
	public Caneta(int serie, String tom, double ponta, boolean estado) {
		this.setNumSerie(serie);
		this.setCor(tom);;
		this.setTamPonta(ponta);
		this.setTampada(estado);
	}// fim Caneta()

 // metodos especiais
	public int getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(int numSerie) {
		this.numSerie = numSerie;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public double getTamPonta() {
		return tamPonta;
	}

	public void setTamPonta(double tamPonta) {
		this.tamPonta = tamPonta;
	}

	public boolean isTampada() {
		return tampada;
	}

	public void setTampada(boolean tampada) {
		this.tampada = tampada;
	}

	@Override
	public String toString() {
		return "Caneta [codigo=" + numSerie + ", cor=" + cor + ", ponta =" + tamPonta + ", tamapada=" + tampada + "]";
	}	
	
}
