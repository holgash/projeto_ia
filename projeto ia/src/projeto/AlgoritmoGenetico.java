package projeto;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AlgoritmoGenetico {
	public static final int BITS_QNT_ARVORES = 7;
	public static final int BITS_PROFUNDIDADE = 2;
	public static final int BITS_QNT_ATRIBUTOS = 2;
	public static final int TAM_POPULACAO = 8;
	public static final int NUM_ELITE_CROMO = 1;
	public static final int TOURNAMENT_SELECTION_SIZE = 4;
	public static final double FREQUENCIA_MUTACAO = 0.005;
	public static final int MAX_GERACAO = 1;
	
	private RandomForest rf;
	private FilteredClassifier fc;
	private Instances instances = null;
	
	public AlgoritmoGenetico(String path) {
		this.rf = new RandomForest();
		this.fc = new FilteredClassifier();
		fc.setClassifier(rf);
		
		DataSource ds = null;
		try {
			ds = new DataSource(path);
			instances = ds.getDataSet();
			instances.setClassIndex(instances.numAttributes()-1);
		}catch(Exception e) {
		}
		
	}

	public Populacao evoluirPop(Populacao pop) {
		Populacao evolucao = mutacaoPop(crossoverPop(pop));
		
		for(int i = 0; i<evolucao.getGenes().length;i++) {
			int certo = 0,total = 0;
			rf.setNumIterations(Conversor.binToDec(evolucao.getGenes()[i].getArvores()));
			rf.setMaxDepth(Conversor.binToDec(evolucao.getGenes()[i].getArvores()));
			rf.setNumFeatures(Conversor.binToDec(evolucao.getGenes()[i].getAtributos()));
			
			try {
				fc.buildClassifier(instances);
				for(int j = 0; j<instances.numInstances();j++) {
					double pred = fc.classifyInstance(instances.instance(i));
					if(instances.classAttribute().value((int) instances.instance(i).classValue()) == 
							instances.classAttribute().value((int) pred)) {
						certo++;
						total++;
					}
					else {
						total++;
					}
				}
			}catch(Exception e) {
			}
			
			System.out.println("\n\nteste\n\nCerto: " + certo + "\nTotal: "+total);
			evolucao.getGenes()[i].setAptidao((double)(certo/total));  
		}
		
		return evolucao;
	}

	private Populacao mutacaoPop(Populacao pop) {
		Populacao mutacao = new Populacao(pop.getGenes().length);
		for(int i = 0; i<NUM_ELITE_CROMO; i++) {
			mutacao.getGenes()[i] = pop.getGenes()[i]; 
		}
		for(int i = NUM_ELITE_CROMO; i<pop.getGenes().length; i++) {
			mutacao.getGenes()[i] = mutacaoCromo(pop.getGenes()[i]);
		}
		return mutacao;
	}

	private Populacao crossoverPop(Populacao pop) {
		Populacao crossPop = new Populacao(pop.getGenes().length);
		for(int i = 0; i<NUM_ELITE_CROMO; i++) {
			crossPop.getGenes()[i] = pop.getGenes()[i];
		}
		for(int i = NUM_ELITE_CROMO; i< pop.getGenes().length; i++) {
			Cromossomo c1 = tournamentPop(pop).getGenes()[0];
			Cromossomo c2 = tournamentPop(pop).getGenes()[0];
			crossPop.getGenes()[i] = crossoverCromo(c1, c2);
		}
		return crossPop;
	}
	
	private Populacao tournamentPop(Populacao pop) {
		Populacao tournamentPop = new Populacao(TOURNAMENT_SELECTION_SIZE);
		for(int i = 0; i<TOURNAMENT_SELECTION_SIZE; i++) {
			tournamentPop.getGenes()[i] = pop.getGenes()[(int)(Math.random()*pop.getGenes().length)];
		}
		tournamentPop.ordenarCromossomosPorAptidao();
		return tournamentPop;
	}

	private Cromossomo crossoverCromo(Cromossomo cromo1, Cromossomo cromo2) {
		Cromossomo crossCromo = new Cromossomo(BITS_QNT_ARVORES, BITS_PROFUNDIDADE, BITS_QNT_ATRIBUTOS);
		for(int i = 0; i<cromo1.getArvores().length; i++) {
			if(Math.random() > 0.5) {
				crossCromo.getArvores()[i] = cromo1.getArvores()[i];
			}else {
				crossCromo.getArvores()[i] = cromo2.getArvores()[i];	
			}
		}
		for(int i = 0; i<cromo1.getProfundidade().length; i++) {
			if(Math.random() > 0.5) {
				crossCromo.getProfundidade()[i] = cromo1.getProfundidade()[i];
			}else {
				crossCromo.getProfundidade()[i] = cromo2.getProfundidade()[i];	
			}
		}
		for(int i = 0; i<cromo1.getAtributos().length; i++) {
			if(Math.random() > 0.5) {
				crossCromo.getAtributos()[i] = cromo1.getAtributos()[i];
			}else {
				crossCromo.getAtributos()[i] = cromo2.getAtributos()[i];	
			}
		}
		
		return crossCromo;
	}

	private Cromossomo mutacaoCromo(Cromossomo cromo) {
		Cromossomo mutCromo = new Cromossomo(BITS_QNT_ARVORES, BITS_PROFUNDIDADE, BITS_QNT_ATRIBUTOS);
		for(int i = 0; i<cromo.getArvores().length; i++) {
			if(Math.random() < FREQUENCIA_MUTACAO) {
				if(Math.random() > 0.5) {
					mutCromo.getArvores()[i] = 1;
				}else {
					mutCromo.getArvores()[i] = 0;
				}
			}else {
				mutCromo.getArvores()[i] = cromo.getArvores()[i]; 
			}
		}
		
		for(int i = 0; i<cromo.getProfundidade().length; i++) {
			if(Math.random() < FREQUENCIA_MUTACAO) {
				if(Math.random() > 0.5) {
					mutCromo.getProfundidade()[i] = 1;
				}else {
					mutCromo.getProfundidade()[i] = 0;
				}
			}else {
				mutCromo.getProfundidade()[i] = cromo.getProfundidade()[i]; 
			}
		}
		
		for(int i = 0; i<cromo.getAtributos().length; i++) {
			if(Math.random() < FREQUENCIA_MUTACAO) {
				if(Math.random() > 0.5) {
					mutCromo.getAtributos()[i] = 1;
				}else {
					mutCromo.getAtributos()[i] = 0;
				}
			}else {
				mutCromo.getAtributos()[i] = cromo.getAtributos()[i]; 
			}
		}
		return mutCromo;
	}
	
}