package projeto;

public class Main {

	public static void main(String[] args) {
		Populacao pop = new Populacao(AlgoritmoGenetico.TAM_POPULACAO).inicializarPopulacao();
		AlgoritmoGenetico ag = new AlgoritmoGenetico("D:\\PICHAU\\Documents\\projeto ia\\baseDados\\diabetes.arff");
		
		System.out.println("------------------------------------------------");
		System.out.println("Geração # 0 " + "| Cromossomo com Maior Aptidao: " + pop.getGenes()[0].getAptidao());
		String cabecalho = "Cromossomo mais apto de todos: ";
		System.out.println(cabecalho + pop.getGenes()[0].toString());
		
		int numGeracao = 0;
		Cromossomo maisApto = pop.getGenes()[0];
		for(int i = 0; i< AlgoritmoGenetico.MAX_GERACAO; i++) {
			numGeracao++;
			pop = ag.evoluirPop(pop);
			pop.ordenarCromossomosPorAptidao();
			if(maisApto.getAptidao() < pop.getGenes()[0].getAptidao()) {
				maisApto = pop.getGenes()[0];
			}
			System.out.println("\n"+"Geração #"+ numGeracao + "| Cromossomo com Maior Aptidao: " + pop.getGenes()[0].getAptidao());
			mostrarPopulacao(pop, cabecalho + maisApto.toString());
		}
	}

	public static void mostrarPopulacao(Populacao pop, String cabecalho) {
		System.out.println(cabecalho);
		System.out.println("------------------------------------------------");
		for(int i = 0; i < pop.getGenes().length; i++) {
			System.out.println("Cromossomo #"+ i + ":" + pop.getGenes()[i] +
					"| Aptidao : " + pop.getGenes()[i].getAptidao());
		}
	}
}
