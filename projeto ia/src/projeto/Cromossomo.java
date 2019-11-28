package projeto;

import java.util.Arrays;

public class Cromossomo {
	private int[] arvores;
	private int[] profundidade;
	private int[] atributos;
	private int tamanho = 0;
	private double aptidao = 0;
	private boolean aptidaoMudou = true;
	
	
	public Cromossomo(int tamArv, int tamProf, int tamAtri) {
		arvores = new int[tamArv];
		profundidade = new int[tamProf];
		atributos = new int[tamAtri];
		tamanho = tamArv+tamProf+tamAtri;
	}
	
	//inicializar aleatoriamente o cromossomo com 0 e 1
	public Cromossomo inizializarCromossomos() {
		for(int i =0; i < arvores.length; i++) {
			if(Math.random() >= 0.5) {
				arvores[i] = 1;
			}
			else {
				arvores[i] = 0;
			}
		}
		for(int i =0; i < atributos.length; i++) {
			if(Math.random() >= 0.5) {
				atributos[i] = 1;
			}
			else {
				atributos[i] = 0;
			}
		}
		for(int i =0; i < profundidade.length; i++) {
			if(Math.random() >= 0.5) {
				profundidade[i] = 1;
			}
			else {
				profundidade[i] = 0;
			}
		}
		return this;
	}

		
	public int[] getArvores() {
		aptidaoMudou = true;
		return arvores;
	}

	public int[] getProfundidade() {
		aptidaoMudou = true;
		return profundidade;
	}

	public int[] getAtributos() {
		aptidaoMudou = true;
		return atributos;
	}

	public double getAptidao() {
		if(aptidaoMudou) {
			//aptidao = recalcularAptidao();
			aptidaoMudou = false;
		}
		return aptidao;
	}
	
	public void setAptidao(double aptidao) {
		this.aptidao = aptidao;
	}

	/*private double recalcularAptidao() {
		double aptidaoCromo = 0;
		// TODO Auto-generated method stub
		return aptidaoCromo;
	}*/

	
	public boolean isAptidaoMudou() {
		return aptidaoMudou;
	}

	public String toString() {
		return Arrays.toString(arvores) + Arrays.toString(profundidade) + Arrays.toString(atributos) + " Aptidão = " + aptidao;
	}

	
	public int getTamanho() {
		
		return tamanho;
	}
}
