package cme;

import java.io.IOException;

public class CMEMotion {

	/*
	 * Main entry point to the program
	 */
	public static void main(String[] args) throws IOException {
		System.out.print("Flux analyzer");
		CMEMotionDataInput dataInput= new CMEMotionDataInput();
		CMEMotionDataAnalysis dataAnalysis = new CMEMotionDataAnalysis(dataInput);
	}

}
