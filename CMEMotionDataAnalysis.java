package cme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Class CMEMotionDataAnalysis
 * This class does the data analysis for the CME
 */
public class CMEMotionDataAnalysis {
	int flux;
	int hundKenArr[][]={{2,0,6},{-6,2,5},{7,4,4},{-4,6,3},{1,8,2},{-3,8,1},{-2,9,0}};
	int thouKenArr[][]={{-3,0,6},{9,3,5},{-1,7,4},{5,9,3},{-1,12,2},{2,14,1},{-9,15,0}};
	int thopKenArr[][]={{6,0,6},{-1,5,5},{3,8,4},{3,12,3},{-2,16,2},{3,19,1},{-1,22,0}};
	int tntpKenArr[][]={{-7,16,4},{7,21,3},{-2,26,2},{3,30,1},{-2,34,0}};
	
	int massArr[][]={{7,0,6},{-2,3,5},{2,5,4},{-1,7,3},{4,8,2},{-4,9,1},{3,10,0}};
	int thouMasArr[][]={{-28,0,6},{84309,0,5},{-1,8,4},{6,10,3},{-2,13,2},{3,15,1},{-1,17,0}};
	int thopMasArr[][]={{2,-4,6},{6,0,5},{-6,4,4},{3,8,3},{-8,11,2},{1,15,1},{-4,17,0}};
	int tntpMasArr[][]={{-3,0,4},{3,5,3},{-1,10,2},{2,14,1},{-9,17,0}};
	
	int accelerationArr[][]={{-2,-9,6},{1,-6,5},{-3,-4,4},{3,-2,3},{-1,0,2},{3,1,1},{-1,3,0}};
	int thouAccArr[][]={{-2,-13,6},{3,-10,5},{-3,-7,4},{1,-4,3},{-3,-1,2},{3,0,1},{-2,0,0}};
	int thopAccArr[][]={{-3,-18,6},{7,-14,5},{-5,-10,4},{2,-6,3},{-5,-3,2},{5,0,1},{-2,3,0}};
	int tntpAccArr[][]={{2,-14,4},{-1,-9,3},{4,-4,2},{-4,1,1},{2,3,0}};
	
	
	/*
	 * Function power
	 * This function calculates the value of x power y.
	 */	
	float power(float x, int y)
	{
	    float temp;
	    // x to the power 0 is 1
	    if( y == 0)
	       return 1;
	    temp = power(x, y/2); 
	    // If y is even number
	    if (y%2 == 0)
	        return temp*temp;
	    else
	    {
	        if(y > 0)
	            return x*temp*temp;
	        else
	            return (temp*temp)/x;
	    }
	}  
	
	
	/*
	 * Function processData
	 * This function calculates the parameter value for a particular flux level.
	 */
	float processData(int flux, int parmArray[][], int pwr, int iter)
	{
		float kiEn = 0;
		float kiEn2 = 0;
		for (int i=0; i < iter; i++)
		{
			kiEn2 = parmArray[i][0] * (power( 10 , parmArray[i][1])) * (power( flux , parmArray[i][2]));
			kiEn += kiEn2;
		}
		kiEn = kiEn * (power( 10 , pwr));	
		return Math.abs(kiEn);
	}
	
	/*
	 * Class Constructor: CMEMotionDataAnalysis
	 * Parameters: Object CMEMotionDataInput
	 */
	public CMEMotionDataAnalysis(CMEMotionDataInput dataInput) throws IOException
	{
		String fluxString;
		float kineticEngery =0;
		float mass = 0;
		float acceleration =0;
		float velocity =0;
		
		flux = dataInput.flux;
		
		// For flux < 100
		if(flux <100)
		{
			System.out.print("Flux: " + flux + "\n");
			kineticEngery = processData(flux, hundKenArr, 22, 7);
			System.out.print("K.E.: " + kineticEngery + "\n");
			
			mass = processData(flux, massArr, 6, 7);
			System.out.print("Mass: " + mass+ "\n");
			
			acceleration = processData(flux, accelerationArr, -1, 7);
			System.out.print("Acc.: " + acceleration+ "\n");			
		}
		else if (flux < 1000) // Flux > 100 and < 1000
		{
			System.out.print("Flux: " + flux + "\n");
			kineticEngery = processData(flux, thouKenArr, 16, 7);
			System.out.print("K.E.: " + kineticEngery + "\n");
			
			mass = processData(flux, thouMasArr, 0, 7);
			System.out.print("Mass: " + mass+ "\n");
			
			acceleration = processData(flux, thouAccArr, -3, 7);
			System.out.print("Acc.: " + acceleration+ "\n");				
		}
		else if (flux < 10000)
		{
			System.out.print("Flux: " + flux + "\n");
			 kineticEngery = processData(flux, thopKenArr, 11, 7);
			System.out.print("K.E.: " + kineticEngery + "\n");
			
			mass = processData(flux, thopMasArr, 0, 7);
			System.out.print("Mass: " + mass+ "\n");
			
			acceleration = processData(flux, thopAccArr, -3, 7);
			System.out.print("Acc.: " + acceleration+ "\n");					
		}
		else
		{
			System.out.print("Flux: " + flux + "\n");
			kineticEngery = processData(flux, tntpKenArr, -2, 5);
			System.out.print("K.E.: " + kineticEngery + "\n");
			
			mass = processData(flux, tntpMasArr, -2, 5);
			System.out.print("Mass: " + mass+ "\n");
			
			acceleration = processData(flux, tntpAccArr, -4, 5);
			System.out.print("Acc.: " + acceleration+ "\n");					
		}
		
		velocity = (float) Math.sqrt(2 * kineticEngery / (mass * 10000)) / 1000;
		System.out.print("Vel.: " + velocity+ "\n\n");
		
		float a = (float) (acceleration * 0.001);
		float b = 2 * velocity;
		float c = -2 * 150000000;
		float d = (b * b) - (4 * a * c);
//			System.out.print("d: " + d+ "\n");
		float root1 = (float) (( - b + Math.sqrt(d))/(2*a)) *3;
//            float root2 = (float) (( -b - Math.sqrt(d))/(2*a));
		
		
		System.out.print("Seconds: " + root1+ "\n");
		System.out.print("Minutes: " + root1/60+ "\n");
		System.out.print("Hours  : " + root1/3600+ "\n");
		System.out.print("Days   : " + root1/3600/24+ "\n");
//            System.out.print("root2: " + root2+ "\n");		

	}


}
