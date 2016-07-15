package ParserProg5;

<<<<<<< Updated upstream
import java.io.FileOutputStream;
import java.io.PrintStream;

import Model.SimulatorR;
=======
import ParserRules.MELArules;
import TraceAnalyser.Analyser;
>>>>>>> Stashed changes

public class Parser5 {

	public static int _SIMULATION_ID = 1;
	
	public static int numberOfRuns = 1;
	
	public static void main(String[] args) throws Exception {
<<<<<<< Updated upstream
		System.setOut(new PrintStream(new FileOutputStream("/Users/ludovicaluisavissat/GitHub/MELAprojectR*/output.txt")));       
		MELAprog5 Parser= new MELAprog5();
		Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAprojectR*/R*.mela");
        System.out.println("Model parsed correctly. The result of the simulation is:");   
        SimulatorR.main(args);
	}
}
=======
	
		
		for(int i = 1; i <= numberOfRuns; i++)
		{   MELAprog5 Parser= new MELAprog5();
			Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/NewModel.mela");
	        System.out.println("Model parsed correctly.");      
			MELArules Parser2= new MELArules();
			Parser2.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/Rules.txt");
			_SIMULATION_ID++;
		}	
			
		//	Analyser Analysis = new Analyser();
			Analyser.numberRuns = numberOfRuns;
			Analyser.main(null);

	}	
}


>>>>>>> Stashed changes
