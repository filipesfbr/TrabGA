package TrabGA;

public class No {

	public int item;
	public No direita;
	public No esquerda;
	public int altura;
	
	public No(int item) {
		this.item = item;
		this.direita = this.esquerda = null;
	}
	
	
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public No getDireita() {
		return direita;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public void setDireita(No direita) {
		this.direita = direita;
	}
	public No getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
	
}
