package perceptron;

import java.util.List;

public class PerceptronUtils {
    
  
    public static final double LEFT_BOUND = -1.0;
    public static final double RIGHT_BOUND = 1.0;
    public static final int N = 1000; 
    
    
    public static double estimateError(DatasetGenerator dsGenerator, Perceptron perceptron){
      System.out.println("Estimating error rate...");
      
      List<List<Double>> testDataset = dsGenerator.generateDataset(N);
      //System.out.println(dsGenerator.toString()); 
      double errorCount = 0.0; 
      
      for (List<Double> dataPoint :testDataset){        
        Double hypothesis = perceptron.estimateHypothesis(dataPoint);
        //System.out.println("h="+hypothesis+" y="+dataPoint.get(dataPoint.size()-1));
        if  (!hypothesis.equals(dataPoint.get(dataPoint.size()-1)))          
           errorCount++;
           //System.out.println("Found errors: "+errorCount);
      }      
      return errorCount/N; 
    }
      
  
    public static String printDataPoint(List<Double> dataPoint){
      StringBuffer sb = new StringBuffer(); 
      for (int i=0; i<dataPoint.size()-1; i++){
        sb.append("x"+i+"="+dataPoint.get(i)+", ");        
      }
      sb.append("y="+dataPoint.get(dataPoint.size()-1));
      
      System.out.println(sb.toString());      
      return sb.toString();
    }
    
    
}
