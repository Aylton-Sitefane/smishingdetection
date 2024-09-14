package config;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class MessageRule {

    Instance instance;

    public MessageRule() {
        this.instance = new DenseInstance(2);

    }

    public Instance getInstance() {
        return instance;
    }

    public String isSmisging(Instances datasetSmish, String message, J48 classifier) throws Exception {
        this.instance.setValue(datasetSmish.attribute(1), message);
        this.instance.setDataset(datasetSmish);

        double result = classifier.classifyInstance(instance);
        return datasetSmish.classAttribute().value((int) result);
    }

    
    
    
}
