package config;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class MessageRule {

    Instance instance;

    public MessageRule() {
        this.instance = new DenseInstance(2);

    }

    public Instance getInstance() {
        return this.instance;
    }

    public String isSmisging(Instances datasetSmish, String message, J48 classifier, StringToWordVector filter) throws Exception {
        Instances filteredDatInstances = Filter.useFilter(datasetSmish, filter);
        
        this.instance = new DenseInstance(filteredDatInstances.numAttributes());
        this.instance.setValue(datasetSmish.attribute(0), message);
        this.instance.setDataset(filteredDatInstances);

        double result = classifier.classifyInstance(this.instance);
        return datasetSmish.classAttribute().value((int) result);
    }

    
    
    
}
