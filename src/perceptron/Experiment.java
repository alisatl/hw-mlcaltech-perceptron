package perceptron;

import java.util.List;

public class Experiment {
  
  public static final int EXPERIMENT_ITERATIONS = 1000; 
   
  private int  iterations; 
  private Double error; 
     
  
  public Experiment(int numberOfTrainingPoints){
  
     DatasetGenerator dsGenerator = new DatasetGenerator();
     
     System.out.println(dsGenerator.toString());
     List<List<Double>> trainingSet = dsGenerator.generateDataset(numberOfTrainingPoints);
     
     /*
     for (List<Double> dp : trainingSet){
       PerceptronUtils.printDataPoint(dp); 
     } */
     
     
     Perceptron p = new Perceptron(trainingSet);
     p.train();  
     System.out.println(p.toString());
     
     iterations = p.getIterations();     
     error = PerceptronUtils.estimateError(dsGenerator, p);          
     System.out.println("Error rate:  " + error);     
     System.out.println("Iteration #: " + iterations); 
   }
  
  
   public int getIterations() {
    return iterations;
  }


  public Double getError() {
    return error;
  }


  public static void main(String[] args){
     
     Double avgError = 0d; 
     int avgIterations = 0; 
     //int numOfTrainingPoints = 10;
     int numOfTrainingPoints = 100;
     int exp_iterations = 1000;
     
     for (int i = 1; i <= exp_iterations; i++){
       System.out.println("Experiment # " + i + " running...");
       Experiment experiment = new Experiment(numOfTrainingPoints); 
       avgError += experiment.error;
       avgIterations += experiment.iterations;        
     }      
   
     avgError = avgError/exp_iterations; 
     avgIterations = avgIterations/exp_iterations; 
      
     System.out.println("Average error: " + avgError);     
     System.out.println("Average iteration #: " + avgIterations);
     
   } 
   
   

}
