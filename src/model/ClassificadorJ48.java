package model;

import weka.classifiers.trees.J48;
import weka.core.Instances;


public class ClassificadorJ48{

    private J48 tree;

    public ClassificadorJ48() {
        this.tree = new J48();

    }

    public J48 getTree() {
        return tree;
    }

    public void setTree(J48 tree) {
        this.tree = tree;
    }

    public void trainModel(Instances datassetInstance) throws Exception{
        tree.buildClassifier(datassetInstance);
    }
    
}