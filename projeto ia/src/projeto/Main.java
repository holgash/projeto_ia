package projeto;

public class Main {

	public static void main(String[] args) {
		Populacao pop = new Populacao(AlgoritmoGenetico.TAM_POPULACAO).inicializarPopulacao();
		String treinamento = "D:\\PICHAU\\Documents\\projeto ia\\baseDados\\soybeanTreinamento.arff";
		String validacao = "D:\\PICHAU\\Documents\\projeto ia\\baseDados\\soybeanValidacao.arff";
		String teste = "D:\\PICHAU\\Documents\\projeto ia\\baseDados\\soybeanTeste.arff";
		AlgoritmoGenetico ag = new AlgoritmoGenetico(treinamento,validacao);
		pop = ag.evoluirPop(pop);
		pop.ordenarCromossomosPorAptidao();

		String cabecalho = "Cromossomo mais apto de todos: ";
		
		int numGeracao = 0;
		Cromossomo maisApto = pop.getGenes()[0];
		for(int i = 0; i< pop.getGenes().length;i++) {
			if(pop.getGenes()[i].getAptidao() != 1 && pop.getGenes()[i].getAptidao() > maisApto.getAptidao()) {
				maisApto = pop.getGenes()[i];
			}
		}
		
		System.out.println("\n"+"Geração #1" + "| Cromossomo com Maior Aptidao: " + pop.getGenes()[0].getAptidao());
		mostrarPopulacao(pop, cabecalho + maisApto.toString());
		
		for(int i = 0; i< AlgoritmoGenetico.MAX_GERACAO; i++) {
			numGeracao++;
			pop = ag.evoluirPop(pop);
			pop.ordenarCromossomosPorAptidao();
			if(maisApto.getAptidao() < pop.getGenes()[0].getAptidao() && pop.getGenes()[0].getAptidao() != 1) {
				maisApto = pop.getGenes()[0];
			}
		}
		System.out.println("\n"+"Geração #"+ numGeracao + "| Cromossomo com Maior Aptidao: " + pop.getGenes()[0].getAptidao());
		mostrarPopulacao(pop, cabecalho + maisApto.toString());
		System.out.println("\nCromossomo mais apto:"+maisApto);
		ag.calcularResultado(maisApto,teste);
		System.out.println("\nAptidão do mais apto na base de dados de teste: "+maisApto.getAptidao());
	}

	public static void mostrarPopulacao(Populacao pop, String cabecalho) {
		System.out.println(cabecalho);
		System.out.println("------------------------------------------------");
		for(int i = 0; i < pop.getGenes().length; i++) {
			System.out.println("Cromossomo #"+ i + ":" + pop.getGenes()[i]);
		}
	}
}
