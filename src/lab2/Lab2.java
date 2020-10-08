package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Lab2 
{
    public static void main(String[] args) 
    {
        // declaring the 2D arrays
        int[][] classOne = new int[7][3];
        
        // declaring the dataset files
        File dataset1 = new File("src/lineClass1.txt");
        
        try
        {
            // declaring the reader 
            FileReader fr = new FileReader(dataset1);
            BufferedReader br = new BufferedReader(fr);
            
            String currentLine;
            int counter = 0;
            
            // while there is a line to read, get the data
            while ((currentLine = br.readLine()) != null) 
            {
                String[] data = currentLine.split(",");
                   
                // getting the x value
                classOne[counter][0] = Integer.parseInt(data[0]);
                // getting the y value
                classOne[counter][1] = Integer.parseInt(data[1]);
                
                if (data[2].compareTo("A") == 0)
                {   
                    classOne[counter][2] = 1;   // if A = 1
                }
                else
                {                    
                    classOne[counter][2] = -1;  // if B = -1
                }                                
                counter++;
            }          
            br.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }               
        
        printClassifier(classOne);
        midPoint(classOne);
    }
    
    // printing the classifier
    public static void printClassifier(int[][] _classOne)
    {
        System.out.println("*************************");
        System.out.println("Printing Classifier");
        
        for (int i=0; i<_classOne.length; i++)
        {
            System.out.println("x = " + _classOne[i][0] + "; y = " + _classOne[i][1] + "; class = " + _classOne[i][2]);
        }
    }
    
    // calculate midpoints and the line passing through them
    public static void midPoint(int[][] _classOne)
    {
        int[] lowestValA = {100,100};
        int[] biggestValA = {0,0};
        int[] lowestValB = {100,100};
        int[] biggestValB = {0,0};
        double[] lowestMid = {0.0,0.0};
        double[] biggestMid = {0.0,0.0};
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double result = 0.0;
        int classification = 0;
        
        // get the x and y values of the A and B with lowest values AND those with the biggest values
        for (int i=0; i<_classOne.length; i++)
        {
            if (_classOne[i][2] == 1)   // if A
            {
                if ((_classOne[i][0] + _classOne[i][1]) < (lowestValA[0] + lowestValA[1]))  // if the sum of x and y are lower, assign these new values to lowestVal for A
                {
                    lowestValA[0] = _classOne[i][0];
                    lowestValA[1] = _classOne[i][1];
                }
                if ((_classOne[i][0] + _classOne[i][1]) > (biggestValA[0] + biggestValA[1]))    // if the sum of x and y are bigger, assign these new values to biggestVal for A
                {
                    biggestValA[0] = _classOne[i][0];
                    biggestValA[1] = _classOne[i][1];
                }
            }
            else    // if B
            {
                if ((_classOne[i][0] + _classOne[i][1]) < lowestValB[0] + lowestValB[1])  // if the sum of x and y are lower, assign these new values to lowestVal for A
                {
                    lowestValB[0] = _classOne[i][0];
                    lowestValB[1] = _classOne[i][1];
                }
                if ((_classOne[i][0] + _classOne[i][1]) > biggestValB[0] + biggestValB[1])    // if the sum of x and y are bigger, assign these new values to biggestVal for A
                {
                    biggestValB[0] = _classOne[i][0];
                    biggestValB[1] = _classOne[i][1];
                }
            }
        }
       
        // calculating the midpoints of the lower and biggest values
        lowestMid[0] = (lowestValA[0] + lowestValB[0]) / 2.0;
        lowestMid[1] = (lowestValA[1] + lowestValB[1]) / 2.0;
        biggestMid[0] = (biggestValA[0] + biggestValB[0]) / 2.0;
        biggestMid[1] = (biggestValA[1] + biggestValB[1]) / 2.0;
        
        // formula -> ax + by + c = 0
        // a = (p1y - p2y)
        // b = (p2x - p1x)
        // c = (p1x . p2y - p2x . p1y)
        // p1 = lowest ... p2 = biggest
        a = lowestMid[1] - biggestMid[1];
        b = biggestMid[0] - lowestMid[0];
        c = ((lowestMid[0]*biggestMid[1]) - (biggestMid[0]*lowestMid[1]));
        
        // for checking purpose
        System.out.println("*********checking*********");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        
        System.out.println("*************************");
        System.out.println("Printing MidPoints Line Result");
            
        // obtaining the final classification based on the midpoints line
        for (int i=0; i<_classOne.length; i++)
        {
            // ax + by + c = 0
            result = (a * _classOne[i][0]) + (b * _classOne[i][1]) + c;
            
            if (result > 0)
            {
                classification = 1;
            }
            else
            {
                classification = -1;
            }
                        
            System.out.println("x = " + _classOne[i][0] + "; y = " + _classOne[i][1] + "; class = " + classification);
        }        
    }
    
}
