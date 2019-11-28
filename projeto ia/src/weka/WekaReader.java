package weka;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

public class WekaReader {

	Instances weather = null;
	RandomForest rf;
	
	public void loadARFF(String arffPath) throws Exception{
		DataSource source = null;
		try {
			source = new DataSource(arffPath);
			weather = source.getDataSet();
			weather.setClassIndex(weather.numAttributes()-1);
		} catch (Exception e) {		
		}
	}
	
	public void buildFilteredClassifier() {
		rf = new RandomForest();
		Remove rm = new Remove();
		rm.setAttributeIndices("1");
		FilteredClassifier fc = new FilteredClassifier();
		fc.setFilter(rm);
		fc.setClassifier(rf);
		int certo = 0,total = 0;
		try {
			fc.buildClassifier(weather);
			for(int i=0; i< weather.numInstances();i++) {
				double pred = fc.classifyInstance(weather.instance(i));
				System.out.println("given value: " + 
				weather.classAttribute().value((int) weather.instance(i).classValue()));
				System.out.println("---predicted value: " +
						weather.classAttribute().value((int) pred));
				if(weather.classAttribute().value((int) weather.instance(i).classValue()) == weather.classAttribute().value((int) pred)) {
					certo++;
					total++;
				}
				else {
					total++;
				}
			}
			System.out.println("Certo = "+certo);
			System.out.println("Total = "+total);
			System.out.println("Acerto = "+(double)certo/total);
			
		} catch(Exception e) {
		}
	}
	
	public static void main(String[] args) {
		WekaReader teste = new WekaReader();
		try {
			teste.loadARFF("D:\\PICHAU\\Documents\\projeto ia\\baseDados\\diabetes.arff");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teste.buildFilteredClassifier();
	}
}
