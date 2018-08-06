import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Class CMEMotionDataInput
 * This class calls the input class in sequence for the user to 
 * input the data in the program
 * 
 */
public class CMEMotionDataInput {

	int flux;
	CMEMotionData dataInp1 = null;
	/*
	 * Class CMEMotionDataInput
	 * This class calculates the kinetic energy for a particular flux level.
	 */
	public CMEMotionDataInput()
	{
		System.out.print("Enter Flux (in MeV): ");
		String fluxString;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		try {
			fluxString = br.readLine();
			flux = (int) Float.parseFloat(fluxString); 
			dataInp1 = new CMEMotionData(flux);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
