package model;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class ClassificadorJ48{

    private J48 tree;
    private StringToWordVector filterStringVector;

    public ClassificadorJ48() {
        this.tree = new J48();

    }

    public J48 getTree() {
        return tree;
    }

    public void setTree(J48 tree) {
        this.tree = tree;
    }

    public StringToWordVector getFilter(){
        return this.filterStringVector;
    }

    public void trainModel(Instances datassetInstance) throws Exception{
        filterStringVector = new StringToWordVector();
        filterStringVector.setInputFormat(datassetInstance);
        Instances filteredData = Filter.useFilter(datassetInstance, filterStringVector);

        tree.buildClassifier(filteredData);
    }

    public String getTreeRules(){
        return this.tree.toString();
    }
    
}