package perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A straight line is generated for each datasetGenerator.
 * Then, given a number of points, it generates a set of random points in 2D space 
 * and evaluates whether they are above or below the line as {-1, 1}.    
 *  
 * @author alisazhila
 *
 */


public class DatasetGenerator {

    private Line line; 
    public static final double X0 = 1d; 
    
    public DatasetGenerator(){       
       line = new Line();        
     }
    

    public List<List<Double>> generateDataset(int n){
      List<List<Double>> dataset = new ArrayList<List<Double>>();

      Random random = new Random(); 
      for (int i = 0; i < n; i++){        
        dataset.add(generateDataPoint(random));
      }      
      return dataset;       
    }
    
    private List<Double> generateDataPoint(Random random){
      
      List<Double> dataPoint = new ArrayList<Double>();  
      Double x1 = (random.nextDouble() - 0.5d)*2;
      Double x2 = (random.nextDouble() - 0.5d)*2;
      Double y = aboveLine(x1, x2);

      dataPoint.add(X0);
      dataPoint.add((Double)x1);
      dataPoint.add((Double)x2);
      dataPoint.add((Double)y);

      return dataPoint; 
    }
    
    public double aboveLine(double x, double y){
      if (y - (line.getK()*x + line.getB() ) >=0 ) return 1d; 
      return -1d; 
    }
    
    

    public Line getLine() {
      return line;
    }

    public void setLine(Line line) {
      this.line = line;
    } 
  
    public String toString(){
      
      if (line == null) 
        return "Not initialized"; 

      return "k = " + line.getK() + "; b = " + line.getB();
      
    }
    
}
