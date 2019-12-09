package weka;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.instance.StratifiedRemoveFolds;

public class SepararBaseDados {
	private Instances train = null;
	
	public SepararBaseDados(String path) {
		StratifiedRemoveFolds fold = new StratifiedRemoveFolds();
        Instances subTrain;
		try {
			DataSource ds = new DataSource(path);
			train = ds.getDataSet();
			train.setClassIndex(train.numAttributes()-1);
			fold.setInputFormat( train );
			fold.setSeed( 1 );
			fold.setNumFolds( 5 );
			fold.setFold( 5 );
			fold.setInvertSelection( true );
			subTrain = Filter.useFilter( train, fold );
			 // add this code here:

	         fold = new StratifiedRemoveFolds();
	         fold.setInputFormat( train );
	         fold.setSeed( 1 );
	         fold.setNumFolds( 5 );
	         fold.setFold( 5 );

	         fold.setInvertSelection( false );
	        Instances holdoutTrain = Filter.useFilter( train, fold ); // should get the other 20%
	        System.out.println( "Size of train = " + train.numInstances() );	 

	        System.out.println( "Size of subTrain = " + subTrain.numInstances() );
	        System.out.println( "Size of holdoutTrain = " + holdoutTrain.numInstances() );
		
		} catch (Exception e) {
		}
	}
       
	
	public static void main(String[] args) {
		SepararBaseDados dados = new SepararBaseDados("D:\\PICHAU\\Documents\\projeto ia\\baseDados\\diabetes.arff");
	}
	
}	
