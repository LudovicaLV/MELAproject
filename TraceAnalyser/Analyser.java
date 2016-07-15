package TraceAnalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Model.GlobalManager;

public class Analyser {

	public static int numberRuns;
	public static void main(String[] args) throws IOException {

		// names
		BufferedReader br = new BufferedReader(
				new FileReader("/Users/ludovicaluisavissat/anaconda/pyTSA/Output/Log/Output1_log.txt"));
		String line = br.readLine();
		String[] singleline = line.split(" ");
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0; i < singleline.length; i++) {
			names.add(singleline[i]);
		}
		
		//names to check
		String[] namesToCheck = {"I(0,0)", "I(0,1)", "I(0,2)", "I(0,3)", "I(0,4)", "I(0,5)", "I(0,6)", "I(0,7)", "I(0,8)", "I(0,9)", "I(1,0)", "I(1,1)", "I(1,2)", "I(1,3)", "I(1,4)", "I(1,5)", "I(1,6)", "I(1,7)", "I(1,8)", "I(1,9)", "I(2,0)", "I(2,1)", "I(2,2)", "I(2,3)", "I(2,4)", "I(2,5)", "I(2,6)", "I(2,7)", "I(2,8)", "I(2,9)", "I(3,0)", "I(3,1)", "I(3,2)", "I(3,3)", "I(3,4)", "I(3,5)", "I(3,6)", "I(3,7)", "I(3,8)", "I(3,9)", "I(4,0)", "I(4,1)", "I(4,2)", "I(4,3)", "I(4,4)", "I(4,5)", "I(4,6)", "I(4,7)", "I(4,8)", "I(4,9)", "I(5,0)", "I(5,1)", "I(5,2)", "I(5,3)", "I(5,4)", "I(5,5)", "I(5,6)", "I(5,7)", "I(5,8)", "I(5,9)", "I(6,0)", "I(6,1)", "I(6,2)", "I(6,3)", "I(6,4)", "I(6,5)", "I(6,6)", "I(6,7)", "I(6,8)", "I(6,9)", "I(7,0)", "I(7,1)", "I(7,2)", "I(7,3)", "I(7,4)", "I(7,5)", "I(7,6)", "I(7,7)", "I(7,8)", "I(7,9)", "I(8,0)", "I(8,1)", "I(8,2)", "I(8,3)", "I(8,4)", "I(8,5)", "I(8,6)", "I(8,7)", "I(8,8)", "I(8,9)", "I(9,0)", "I(9,1)", "I(9,2)", "I(9,3)", "I(9,4)", "I(9,5)", "I(9,6)", "I(9,7)", "I(9,8)", "I(9,9)"};
		int[] statMCValues = new int [namesToCheck.length];	
		
        for (int k=1; k<=numberRuns; k++){
		BufferedReader buf = new BufferedReader(
				new FileReader("/Users/ludovicaluisavissat/anaconda/pyTSA/Output/Data/Output" + k + "_data.txt"));
		ArrayList<String> values = new ArrayList<>();
		String lineJustFetched = null;
		String[] valuesArray;
		while (true) {
			lineJustFetched = buf.readLine();
			if (lineJustFetched == null) {
				break;
			} else {
				valuesArray = lineJustFetched.split("\t");
				for (String each : valuesArray) {
					if (!"".equals(each)) {
						values.add(each);
					}
				}
			}
		}
		buf.close();
		
		// time
		ArrayList<Double> timeValues = new ArrayList<>();
		for (int i = 0; i < values.size(); i++) {
			String[] newline = values.get(i).split(" ");
			double timeval = Double.parseDouble(newline[0]);
			timeValues.add(timeval);
		}
		
		
		
		// popValues
		ArrayList<ArrayList<Integer>> separateIntValues = new ArrayList<>();
		for (int i = 0; i < values.size(); i++) {
			String[] newline = values.get(i).split(" ");
			ArrayList<Integer> newone = new ArrayList<>();
			for (int j = 1; j < newline.length; j++) {
				int intvalue = Integer.parseInt(newline[j]);
				newone.add(intvalue);
			}
			separateIntValues.add(newone);
		}
		
		// analysis of each column, to see if I(l)>0
 		int[] statMC = new int[namesToCheck.length];
		for (int r=0; r < namesToCheck.length; r++){
			int index = names.indexOf(namesToCheck[r]);	
			for (int t=0; t< timeValues.size(); t++){				
				if (separateIntValues.get(t).get(index) > 0){
					statMC[r] = 1;
					//System.out.println(namesToCheck[r] + " got infected in simulation " + k);
					break;
				}else{
					statMC[r] = 0;
				}
	    }
		statMCValues[r] = statMCValues[r] + statMC[r];
		}
        }
   
//        for (int f=0; f< statMCValues.length; f++){
//        	System.out.println(statMCValues[f]);
//        }
        
        ArrayList<Double> StatMCPlot = new ArrayList<>();
        
        System.out.println("Stat MC:");
        for (int f=0; f< statMCValues.length; f++){
        	Integer valueToUse = statMCValues[f];
        	double statMCdouble = valueToUse.doubleValue();
        	double valueMC = statMCdouble/numberRuns; 
        	StatMCPlot.add(valueMC);
        	double conflevel = 1.96;
        	double root = Math.sqrt((1.0/numberRuns) * valueMC * (1 - valueMC));
        	double confinterval = conflevel * root;
        	System.out.println(valueMC + " \u00B1 " + confinterval);
        }
        
        GlobalManager.ForPlotting();
        for (int y=0; y < StatMCPlot.size(); y++){
        	System.out.print(StatMCPlot.get(y) + ", ");
        }
       
	}}
