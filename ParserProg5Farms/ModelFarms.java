package ParserProg5Farms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import ParserProg5Farms.CSVUtils;

import Creation.InitCond;
import ParserRulesFarms.MELArules;
import ParserStatMCFarms.StatMCrules;
import TraceAnalyser.AnalyserFarms;

public class ModelFarms {
	
	public static int _SIMULATION_ID;
	public static int numberOfRuns = TotalFarms.TotalFarms.numberOfRuns;
	public static Integer ModelNumber;
	public static Integer KernelNumber;
	
	public static String ValueSMC;

	public static HashMap<ArrayList<Integer>, HashMap<ArrayList<Integer>, ArrayList<Double>>> Results = new HashMap<>();
    public static ArrayList<ArrayList<ArrayList<Integer>>> LocationHistory = new ArrayList<ArrayList<ArrayList<Integer>>>();	
	
	public static void main(String[] args) throws Exception {
		
		ValueSMC = TotalFarms.Writing.ValueSMCString(); 				
		String csvFile = TotalFarms.Writing.csvString();
		 String StringModel = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/Output/toPrint";
	    
		 FileWriter writer = new FileWriter(csvFile);    
	    
		PrintWriter writer_SMC = null, writerPrint = null;
		try {
			writer_SMC = new PrintWriter(ValueSMC+".txt", "UTF-8");
			writerPrint = new PrintWriter(StringModel+".txt", "UTF-8");	
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		//K1 100
//		KernelNumber = 1;
//		ModelNumber = 0;
//		for(int i = 1; i <= numberOfRuns; i++)
//		{   MELAprog5 Parser= new MELAprog5();
//			Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAFarmsModel/Models/K1_100/ModelK1_100.mela");
//	        System.out.println("Model parsed correctly.");  
//	        System.out.println("Simulation -> " + _SIMULATION_ID ); 
//			MELArules Parser2= new MELArules();
//			Parser2.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/Rules.txt");
//			_SIMULATION_ID++;
//		}					
//		    StatMCrules Parser3= new StatMCrules();
//		    Parser3.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAFarmsModel/Models/K1_100/rulesStatMCK1_100.txt");      
//			AnalyserFarms.numberRuns = numberOfRuns;
//			AnalyserFarms.main(null);
		

//		   ModelChoice(1,100); 
		   ModelChoice(1,50);
		   ModelChoice(1,25);
//		   ModelChoice(1,20);
	       ModelChoice(1,10);
	       ModelChoice(1,5);
//	       ModelChoice(1,4);
	       ModelChoice(1,2);
	       		   
//		   System.out.println(ModelFarms.LocationHistory.size());
//		   for (int i=0; i < ModelFarms.LocationHistory.get(0).size(); i++){
//		   System.out.println(ModelFarms.LocationHistory.get(0).get(i));}
	       
	       
//		   for (int i=0; i < ModelFarms.LocationHistory.size(); i++){
//			   //System.out.println(ModelFarms.LocationHistory.get(i).get(0) + " -> ");
//			   writer_SMC.println(ModelFarms.LocationHistory.get(i).get(0) + " -> ");
//			   for (int j=0; j <5; j++){
//			   ArrayList<Integer> newKey = new ArrayList<>();
//			   newKey.add(1);
//			   newKey.add(j);
//			   ArrayList<Integer> keyLoc = ModelFarms.LocationHistory.get(i).get(j);
//			   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap = ModelFarms.Results.get(newKey);
//		      // System.out.println(newMap.get(keyLoc));
//		       writer_SMC.println(newMap.get(keyLoc));
//		       
//		       String firstLoc = Integer.toString(ModelFarms.LocationHistory.get(i).get(j).get(0));
//		       String secondLoc = Integer.toString(ModelFarms.LocationHistory.get(i).get(j).get(1));
//		       String prob = Double.toString((newMap.get(keyLoc).get(0)));
//		       String ci = Double.toString((newMap.get(keyLoc).get(1)));
//		       
//		       CSVUtils.writeLine(writer, Arrays.asList(firstLoc, secondLoc, prob, ci));
//		   }
//			   
//			   CSVUtils.writeLine(writer, Arrays.asList(" "));
//		   }
//	       
//		   writer.flush();
//		   writer.close();
		   
		   for (int i=0; i < ModelFarms.LocationHistory.size(); i++){
				  // System.out.println(ModelFarms.LocationHistory.get(i).get(0) + " -> ");
				   writer_SMC.println(ModelFarms.LocationHistory.get(i).get(0) + " -> ");
				   
				   
				  //j=0
				   ArrayList<Integer> newKey = new ArrayList<>();
				   newKey.add(1);
				   newKey.add(0);
				   ArrayList<Integer> keyLoc = ModelFarms.LocationHistory.get(i).get(0);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap = ModelFarms.Results.get(newKey);
			     //  System.out.println(newMap.get(keyLoc));
			       writer_SMC.println(newMap.get(keyLoc));
			       
			       String firstLoc = Integer.toString(ModelFarms.LocationHistory.get(i).get(0).get(0));
			       String secondLoc = Integer.toString(ModelFarms.LocationHistory.get(i).get(0).get(1));
			       String prob = Double.toString((newMap.get(keyLoc).get(0)));
			       String ci = Double.toString((newMap.get(keyLoc).get(1)));
			       
			       //j=1
				   ArrayList<Integer> newKey1 = new ArrayList<>();
				   newKey1.add(1);
				   newKey1.add(1);
				   ArrayList<Integer> keyLoc1 = ModelFarms.LocationHistory.get(i).get(1);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap1 = ModelFarms.Results.get(newKey1);
			     //  System.out.println(newMap.get(keyLoc));
			       writer_SMC.println(newMap1.get(keyLoc1));
			       
			       String firstLoc1 = Integer.toString(ModelFarms.LocationHistory.get(i).get(1).get(0));
			       String secondLoc1 = Integer.toString(ModelFarms.LocationHistory.get(i).get(1).get(1));
			       String prob1 = Double.toString((newMap1.get(keyLoc1).get(0)));
			       String ci1 = Double.toString((newMap1.get(keyLoc1).get(1)));
			       
			       //j=2
				   ArrayList<Integer> newKey2 = new ArrayList<>();
				   newKey2.add(1);
				   newKey2.add(2);
				   ArrayList<Integer> keyLoc2 = ModelFarms.LocationHistory.get(i).get(2);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap2 = ModelFarms.Results.get(newKey2);
			     //  System.out.println(newMap.get(keyLoc));
			       writer_SMC.println(newMap2.get(keyLoc2));
			       
			       String firstLoc2 = Integer.toString(ModelFarms.LocationHistory.get(i).get(2).get(0));
			       String secondLoc2 = Integer.toString(ModelFarms.LocationHistory.get(i).get(2).get(1));
			       String prob2 = Double.toString((newMap2.get(keyLoc2).get(0)));
			       String ci2 = Double.toString((newMap2.get(keyLoc2).get(1)));
			       
			       //j=3
				   ArrayList<Integer> newKey3 = new ArrayList<>();
				   newKey3.add(1);
				   newKey3.add(3);
				   ArrayList<Integer> keyLoc3 = ModelFarms.LocationHistory.get(i).get(3);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap3 = ModelFarms.Results.get(newKey3);
			     //  System.out.println(newMap.get(keyLoc));
			       writer_SMC.println(newMap3.get(keyLoc3));
			       
			       String firstLoc3 = Integer.toString(ModelFarms.LocationHistory.get(i).get(3).get(0));
			       String secondLoc3 = Integer.toString(ModelFarms.LocationHistory.get(i).get(3).get(1));
			       String prob3 = Double.toString((newMap3.get(keyLoc3).get(0)));
			       String ci3 = Double.toString((newMap3.get(keyLoc3).get(1)));
			       
			       //j=4
				   ArrayList<Integer> newKey4 = new ArrayList<>();
				   newKey4.add(1);
				   newKey4.add(4);
				   ArrayList<Integer> keyLoc4 = ModelFarms.LocationHistory.get(i).get(4);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap4 = ModelFarms.Results.get(newKey4);
			     //  System.out.println(newMap.get(keyLoc));
			       writer_SMC.println(newMap4.get(keyLoc4));
			       
			       String firstLoc4 = Integer.toString(ModelFarms.LocationHistory.get(i).get(4).get(0));
			       String secondLoc4 = Integer.toString(ModelFarms.LocationHistory.get(i).get(4).get(1));
			       String prob4 = Double.toString((newMap4.get(keyLoc4).get(0)));
			       String ci4 = Double.toString((newMap4.get(keyLoc4).get(1)));
			       
			       CSVUtils.writeLine(writer, Arrays.asList(firstLoc, secondLoc, prob, ci, firstLoc1, secondLoc1, prob1, ci1, firstLoc2, secondLoc2, prob2, ci2, firstLoc3, secondLoc3, prob3, ci3, firstLoc4, secondLoc4, prob4, ci4));	   
				   
				   CSVUtils.writeLine(writer, Arrays.asList(" "));
			   }
			   
			   writer.flush();
			   writer.close();
		   
		   //Print results to plot
		   writerPrint.println("");
	       for (int j=0; j <5; j++){
	       writerPrint.print("X" + j + "=[");
		   for (int i=0; i < ModelFarms.LocationHistory.size(); i++){
			   ArrayList<Integer> newKey = new ArrayList<>();
			   newKey.add(1);
			   newKey.add(j);
			   ArrayList<Integer> keyLoc = ModelFarms.LocationHistory.get(i).get(j);
			   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap = ModelFarms.Results.get(newKey);		 
		       int firstLoc = ModelFarms.LocationHistory.get(i).get(j).get(0);
		       writerPrint.print(firstLoc + ",");}
	       writerPrint.println("]");}
		       
		    
	       for (int j=0; j <5; j++){
	    	   writerPrint.print("Y" + j + "=[");
			   for (int i=0; i < ModelFarms.LocationHistory.size(); i++){
				   ArrayList<Integer> newKey = new ArrayList<>();
				   newKey.add(1);
				   newKey.add(j);
				   ArrayList<Integer> keyLoc = ModelFarms.LocationHistory.get(i).get(j);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap = ModelFarms.Results.get(newKey);		 
				   int secondLoc = ModelFarms.LocationHistory.get(i).get(j).get(1);
				   writerPrint.print(secondLoc + ",");}
			   writerPrint.println("]");}
	       
	       for (int j=0; j <5; j++){
	    	   writerPrint.print("MC" + j + "=[");
			   for (int i=0; i < ModelFarms.LocationHistory.size(); i++){
				   ArrayList<Integer> newKey = new ArrayList<>();
				   newKey.add(1);
				   newKey.add(j);
				   ArrayList<Integer> keyLoc = ModelFarms.LocationHistory.get(i).get(j);
				   HashMap<ArrayList<Integer>, ArrayList<Double>> newMap = ModelFarms.Results.get(newKey);		 
				   double prob = (newMap.get(keyLoc).get(0));
				   writerPrint.print(prob + ",");}
			   writerPrint.println("]");}
	       
	       writerPrint.close();

	}
			             
	
      
	
	
	public static void ModelChoice(int Kernel, int Model) throws NumberFormatException, ParseException, TokenMgrError, ParserRulesFarms.ParseException, ParserRulesFarms.TokenMgrError, ParserStatMCFarms.ParseException, ParserStatMCFarms.TokenMgrError, IOException{
		KernelNumber = Kernel;
		ModelNumber = Model;
		_SIMULATION_ID = 1;
		for(int i = 1; i <= numberOfRuns; i++)
		{   MELAprog5 Parser= new MELAprog5();
		    Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAFarmsModel/ModelsAuto/K" + Kernel + "_" + Model + "/ModelK" + Kernel + "_" + Model + ".mela");
		    System.out.println("Model parsed correctly."); 
	        System.out.println("Simulation -> " + _SIMULATION_ID ); 
			MELArules Parser2= new MELArules();
			Parser2.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELATotalDistance/Rules.txt");
			_SIMULATION_ID++;
		}					
	  	    StatMCrules Parser3= new StatMCrules();
	  	    Parser3.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAFarmsModel/ModelsAuto/K"+ Kernel + "_" + Model + "/rulesStatMCK"+ Kernel + "_" + Model + ".txt");      
	  	    AnalyserFarms.numberRuns = numberOfRuns;
		    AnalyserFarms.KernelNumberAnalyser = Kernel;
		    AnalyserFarms.ModelNumberAnalyser = Model;
		    AnalyserFarms.main(null);		
	}
}




