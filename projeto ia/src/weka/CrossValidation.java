package weka;

import java.util.Random;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class CrossValidation {
	Instances data = null;
	int seed = 1;
	int folds = 4;
	
	
	public void stratifyData(String path) {
		DataSource ds;
		Random random = new Random(seed);
		Instances randData = null;
		try {
			ds = new DataSource(path);
			data = ds.getDataSet();
			randData = new Instances(data);
			randData.randomize(random);
			randData.stratify(folds);
			
			for(int i = 0; i< folds;i++) {
				Instances train = randData.trainCV(folds, i,random);
				Instances test = randData.trainCV(folds, i);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
