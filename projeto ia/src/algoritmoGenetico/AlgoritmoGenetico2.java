package algoritmoGenetico;

public class AlgoritmoGenetico2 {
	public static final int[] RESPOSTA = {1,1,0,1,0,0,1,1,1,0};
	public static final int TAM_POPULACAO = 8;
	public static final int NUM_ELITE_CROMO = 1;
	public static final int TOURNAMENT_SELECTION_SIZE = 4;
	public static final double FREQUENCIA_MUTACAO = 0.25;
	
	public Populacao2 Evoluir(Populacao2 populacao) {
		return mutacaoPopulacao(crossoverPopulacao(populacao));
	}
	
	private Populacao2 crossoverPopulacao(Populacao2 populacao) {
		Populacao2 crossover = new Populacao2(populacao.getCromossomos().length);
		for(int i = 0; i< AlgoritmoGenetico2.NUM_ELITE_CROMO; i++) {
			crossover.getCromossomos()[i] = populacao.getCromossomos()[i];
		}
		for(int i = AlgoritmoGenetico2.NUM_ELITE_CROMO; i< populacao.getCromossomos().length; i++) {
			Cromossomo2 c1 = selecionarTournamentPop(populacao).getCromossomos()[0];
			Cromossomo2 c2 = selecionarTournamentPop(populacao).getCromossomos()[0];
			crossover.getCromossomos()[i] = CrossoverCromossomo(c1, c2);
		}
		return crossover;
	}
	
	private Populacao2 mutacaoPopulacao(Populacao2 populacao) {
		Populacao2 mutacao = new Populacao2(populacao.getCromossomos().length);
		for(int i = 0; i< AlgoritmoGenetico2.NUM_ELITE_CROMO; i++) {
			mutacao.getCromossomos()[i] = populacao.getCromossomos()[i];
		}
		for(int i = AlgoritmoGenetico2.NUM_ELITE_CROMO; i< populacao.getCromossomos().length; i++) {
			mutacao.getCromossomos()[i] = MutacaoCromossomo(populacao.getCromossomos()[i]);
		}
		return mutacao;
	}
	
	private Cromossomo2 CrossoverCromossomo(Cromossomo2 c1, Cromossomo2 c2) {
		Cromossomo2 crossCromo = new Cromossomo2(AlgoritmoGenetico2.RESPOSTA.length);
		for(int i = 0; i< c1.getGenes().length; i++) {
			if(Math.random() < 0.5) crossCromo.getGenes()[i] = c1.getGenes()[i];
			else crossCromo.getGenes()[i] = c2.getGenes()[i];
		}
		return crossCromo;
	} 
	
	private Populacao2 selecionarTournamentPop(Populacao2 pop) {
		Populacao2 tournamentPop = new Populacao2(TOURNAMENT_SELECTION_SIZE);
		for(int i = 0; i <AlgoritmoGenetico2.TOURNAMENT_SELECTION_SIZE; i ++) {
			tournamentPop.getCromossomos()[i] = 
					pop.getCromossomos()[(int)(Math.random()*pop.getCromossomos().length)];
		}
		tournamentPop.ordenarCromossomosPorAptidao();
		return tournamentPop;
	}
	
	private Cromossomo2 MutacaoCromossomo(Cromossomo2 cromo) {
			Cromossomo2 MutCromo = new Cromossomo2(RESPOSTA.length);
			for(int i = 0; i < cromo.getGenes().length; i++) {
				if(Math.random() < FREQUENCIA_MUTACAO) {
					if(Math.random() < 0.5) MutCromo.getGenes()[i] = 1;
					else MutCromo.getGenes()[i] = 0;
				}
				else {
					MutCromo.getGenes()[i] = cromo.getGenes()[i];
				}
			}
			return MutCromo;
	}

}