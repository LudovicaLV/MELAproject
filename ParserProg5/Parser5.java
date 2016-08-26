package ParserProg5;

import ParserRules.MELArules;
import ParserStatMC.StatMCrules;
import TraceAnalyser.Analyser;
import TraceAnalyser.SignalAnalysis;

public class Parser5 {

	public static int _SIMULATION_ID = 1;
	
	public static int numberOfRuns = 10;
	
	public static void main(String[] args) throws Exception {
	
		
		for(int i = 1; i <= numberOfRuns; i++)
		{   MELAprog5 Parser= new MELAprog5();
			Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/NewModel2.mela");
	        System.out.println("Model parsed correctly.");      
			MELArules Parser2= new MELArules();
			Parser2.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/Rules.txt");
			_SIMULATION_ID++;
		}	
				
		    StatMCrules Parser3= new StatMCrules();
		    Parser3.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/RulesStatMC.txt");
        
//			Analyser.numberRuns = numberOfRuns;
//			Analyser.main(null);
		    
		    SignalAnalysis.numberRuns = numberOfRuns;
		    SignalAnalysis.main(null);

	}	
}


