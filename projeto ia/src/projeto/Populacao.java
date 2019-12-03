package projeto;

import java.util.Arrays;

public class Populacao {
	private Cromossomo[] genes;
	
	public Populacao(int tamanho) {
		genes = new Cromossomo[tamanho];
	}
	
	// inicializa uma populacao aleatoriamente, testa a aptidao e ordena baseada nela
	public Populacao inicializarPopulacao() {
		for(int i=0; i< genes.length; i++) {
			genes[i] = new Cromossomo(AlgoritmoGenetico.BITS_QNT_ARVORES,AlgoritmoGenetico.BITS_PROFUNDIDADE,AlgoritmoGenetico.BITS_QNT_ATRIBUTOS).inizializarCromossomos();
		}
		ordenarCromossomosPorAptidao();
		return this;
	}
	
	public void ordenarCromossomosPorAptidao() {
		Arrays.sort(genes, (gene1,gene2) -> {
			int flag = 0;
			if(gene1.getAptidao() > gene2.getAptidao()) flag = -1;
			else if(gene1.getAptidao() < gene2.getAptidao()) flag = 1;
			return flag;
		});	
	}

	
	public Cromossomo[] getGenes() {
		return genes;
	}
	
}
