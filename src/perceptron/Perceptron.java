package perceptron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class Perceptron {

  
  public final static int ITERATION_LIMIT = 20000; 
  
  private List<Double> w; 
  private List<List<Double>> trainingSet; 
  private int dimension; 
  private int iterations = 0; 
  
  public Perceptron(List<List<Double>> trainingSet){
    
    this.trainingSet = trainingSet; 
    w = new ArrayList<Double>(); 
    
    if (trainingSet.isEmpty() || trainingSet.size() == 0){
      w = Arrays.asList(0.0);
    }
    dimension = trainingSet.get(0).size(); 
    for (int i = 0; i < dimension-1; i++){
      w.add(0.0);    
    }
    
  }
  
  public List<Double> train(){
    List<List<Double>> misclassifiedPoints = trainingSet; 
    iterations = 0 ; 
    boolean misclassified = true;  
    
    
    while (misclassifiedPoints.size() > 0  && iterations < ITERATION_LIMIT ) {
      iterations++; 
      List<Double> hypotheses = estimateHypotheses(misclassifiedPoints);
      misclassifiedPoints = findMisclassifieds(hypotheses, misclassifiedPoints);
      
      if (misclassifiedPoints != null && misclassifiedPoints.size() > 0) {
        List<Double> pickedPoint = pickMisclassifiedPoint(misclassifiedPoints);
          optimizeW(pickedPoint); 
      }      
    }//while
   
    /*
    while (misclassified && iterations < ITERATION_LIMIT){
      misclassified = false; 
      for (List<Double> dataPoint: trainingSet) {
        iterations++;  
        //System.out.println(PerceptronUtils.printDataPoint(dataPoint));
        Double hypothesis = estimateHypothesis(dataPoint);
        //System.out.println("Hypothesis = " + hypothesis);
        if (!hypothesis.equals(dataPoint.get(dataPoint.size()-1))) {
           misclassified = true;
           optimizeW(dataPoint);
        }
        //System.out.println(this.toString());
      }//for      
    }//while
    */
    
    return w;     
  }
    
  private List<Double> pickMisclassifiedPoint(List<List<Double>>misclassifiedPoints){
     List<Double> pickedPoint;
     Random random = new Random(); 
     int index = random.nextInt(misclassifiedPoints.size());
     pickedPoint = misclassifiedPoints.get(index);      
     return pickedPoint; 
  }
  
  
  public String toString(){
    StringBuffer sb = new StringBuffer(); 
    sb.append("Perceptron: ");
    
    for (int i = 0; i < w.size(); i++){      
      sb.append("w" + i + " = " + w.get(i) +" ");
    }
    
    return sb.toString();
  }
  
  
  private List<Double> estimateHypotheses(List<List<Double>> dataPoints){ 
  
    List<Double> hypotheses = new ArrayList<Double>();
        
    for (List<Double> dataPoint : dataPoints){
      double h  = estimateHypothesis(dataPoint); 
      /*
      Double estimatedY = 0.0; 
      for(int i = 0; i < dimension-1; i++){
        estimatedY += w.get(i)*dataPoint.get(i);         
      } */      
      hypotheses.add(h);      
    } 
  
    return hypotheses; 
  }
  
  public Double estimateHypothesis(List<Double> dataPoint){ 
    
      Double hypothesis = 0.0; 
      for(int i = 0; i < dimension-1; i++){
        hypothesis += w.get(i)*dataPoint.get(i);
      }
    return sign(hypothesis); 
  }
  
  
  
  
  private List<List<Double>> findMisclassifieds(List<Double>hypotheses, List<List<Double>>points){
    List<List<Double>> misclassifiedPoints = new ArrayList<List<Double>>();

    if (hypotheses.size() != points.size()){
      System.out.println("[ERROR]CSizes of hypotheses vector and pointd don't coincide");
      return null; 
    }
    
     for (int i = 0; i < hypotheses.size(); i++ ){
       if (!hypotheses.get(i).equals(points.get(i).get(dimension-1))){
         misclassifiedPoints.add(points.get(i)); 
       }        
     } 
    
    return misclassifiedPoints;
  }
    
  
  private void optimizeW(List<Double> dataPoint){
    //System.out.println("Optimizng w...");
    double y = dataPoint.get(dimension-1);
    for (int d = 0; d< dimension -1; d++){
      double wd = w.get(d) +  y*dataPoint.get(d); 
      w.set(d, wd);      
    }
  }
  
  
  private Double sign(Double num){
    if (num > 0 ) return new Double(1);
    if (num == 0) return new Double(0); 
    else return new Double(-1); 
  }

  public int getIterations() {
    return iterations;
  }
  
}
