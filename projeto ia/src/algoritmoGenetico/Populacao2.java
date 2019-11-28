package algoritmoGenetico;

import java.util.Arrays;

public class Populacao2 {
	private Cromossomo2[] cromossomos;
	public Populacao2(int tamanho) {
		cromossomos = new Cromossomo2[tamanho];
	}
	
	// inicializa uma populacao aleatoriamente, testa a aptidao e ordena baseada nela
	public Populacao2 inicializarPopulacao() {
		for(int i=0; i< cromossomos.length; i++) {
			cromossomos[i] = new Cromossomo2(AlgoritmoGenetico2.RESPOSTA.length).inizializarCromossomos();
		}
		ordenarCromossomosPorAptidao();
		return this;
	}
	
	public void ordenarCromossomosPorAptidao() {
		Arrays.sort(cromossomos, (cromossomo1,cromossomo2) -> {
			int flag = 0;
			if(cromossomo1.getAptidao() > cromossomo2.getAptidao()) flag = -1;
			else if(cromossomo1.getAptidao() < cromossomo2.getAptidao()) flag = 1;
			return flag;
		});
		
	}

	public Cromossomo2[] getCromossomos() {
		return cromossomos;
	}
	
}
