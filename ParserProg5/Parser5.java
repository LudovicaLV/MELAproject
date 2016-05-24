package ParserProg5;

import java.io.FileOutputStream;
import java.io.PrintStream;

import Model.SimulatorR;

public class Parser5 {

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream(new FileOutputStream("/Users/ludovicaluisavissat/GitHub/MELAprojectR*/output.txt")));       
		MELAprog5 Parser= new MELAprog5();
		Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAprojectR*/R*.mela");
        System.out.println("Model parsed correctly. The result of the simulation is:");   
        SimulatorR.main(args);
	}
}