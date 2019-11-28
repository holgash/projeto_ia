package algoritmoGenetico;

import java.util.Arrays;

public class MainAG2 {

	public static void main(String[] args) {
		Populacao2 populacao = new Populacao2(AlgoritmoGenetico2.RESPOSTA.length).inicializarPopulacao();
		AlgoritmoGenetico2 ag = new AlgoritmoGenetico2();
		
		System.out.println("------------------------------------------------");
		System.out.println("Geração # 0 " + "| Cromossomo com Maior Aptidao: " + populacao.getCromossomos()[0].getAptidao());
		mostrarPopulacao(populacao, "Cromossomo Alvo: " + Arrays.toString(AlgoritmoGenetico2.RESPOSTA));
		
		int numGeracao = 0;
		while(populacao.getCromossomos()[0].getAptidao() < AlgoritmoGenetico2.RESPOSTA.length) {
			numGeracao++;
			populacao = ag.Evoluir(populacao);
			populacao.ordenarCromossomosPorAptidao();
			System.out.println("\n"+"Geração #"+ numGeracao + "| Cromossomo com Maior Aptidao: " + populacao.getCromossomos()[0].getAptidao());
			mostrarPopulacao(populacao, "Cromossomo Alvo: " + Arrays.toString(AlgoritmoGenetico2.RESPOSTA));
		}
		
	}
	
	public static void mostrarPopulacao(Populacao2 pop, String cabecalho) {
		System.out.println(cabecalho);
		System.out.println("------------------------------------------------");
		for(int i = 0; i < pop.getCromossomos().length; i++) {
			System.out.println("Cromossomo #"+ i + ":" + Arrays.toString(pop.getCromossomos()[i].getGenes()) +
					"| Aptidao : " + pop.getCromossomos()[i].getAptidao());
		}
	}

}
