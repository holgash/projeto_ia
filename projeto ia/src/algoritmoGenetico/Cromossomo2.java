package algoritmoGenetico;

import java.util.Arrays;

public class Cromossomo2 {
	private int[] genes;
	private int aptidao = 0;
	private boolean aptidaoMudou = true;

	public Cromossomo2(int tamanho) {
		genes = new int[tamanho];
	}
	
	//inicializar aleatoriamente o cromossomo com 0 e 1
	public Cromossomo2 inizializarCromossomos() {
		for(int i =0; i < genes.length; i++) {
			if(Math.random() >= 0.5) {
				genes[i] = 1;
			}
			else {
				genes[i] = 0;
			}
		}
		return this;
	}

	public int[] getGenes() {
		aptidaoMudou = true;
		return genes;
	}

	
	public int getAptidao() {
		if(aptidaoMudou) {
			aptidao = recalcularAptidao();
			aptidaoMudou = false;
		}
		return aptidao;
	}
	
	public int recalcularAptidao() {
		int aptidaoCromossomo = 0;
		for(int i =0 ; i< genes.length; i++) {
			if(genes[i] == AlgoritmoGenetico2.RESPOSTA[i]) {
				aptidaoCromossomo++;
			}
		}
		return aptidaoCromossomo;
	}

	public String toString() {
		return Arrays.toString(this.genes);
	}
	
	
	
}
