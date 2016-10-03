package perceptron;

import java.util.Random;


/*
 *  Generating a random line from 2 random points within [-1, 1]x[-1,1] 
 *  line equation y = kx+b
 */
public class Line {
  
    private double k = 0;
    private double b = 0;
  
    public Line(){
     Random random = new Random();
     
     double firstPointX = (random.nextDouble() - 0.5d)*2;
     double firstPointY = (random.nextDouble() - 0.5d)*2;
     double secondPointX = (random.nextDouble() - 0.5d)*2;
     double secondPointY = (random.nextDouble() - 0.5d)*2;
     
     k =  (firstPointY - secondPointY)/(firstPointX-secondPointX);     
     b =  firstPointY - firstPointX*k;     
    }
  
    
    public double getK() {
      return k;
    }

    public double getB() {
      return b;
    }
    
}
