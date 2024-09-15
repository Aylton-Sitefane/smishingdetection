package data;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class DatasetSmish {
    private Instances dataInstance;
    private DataSource dataSource;

    public DatasetSmish(String filePath) throws Exception{
        setDataSource(filePath);
        
        
    }

    public Instances getDataInstance() {
        return this.dataInstance;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(String filePath) throws Exception{
        this.dataSource = new DataSource(filePath);
        this.dataInstance = dataSource.getDataSet();
        this.dataInstance.setClassIndex(this.dataInstance.numAttributes() - 1);

    }

    

    
}
