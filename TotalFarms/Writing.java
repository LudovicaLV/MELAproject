package TotalFarms;

import ParserProg5Farms.ModelFarms;

public class Writing {
	
//used in AllModel	
public static String ModelString (String kernel, int gridNum){	
	String StringModel = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/ModelsAuto/" + kernel + "_" + gridNum + "/Model" + kernel + "_" + gridNum;
    return StringModel;
}

//used in AllModel
public static String statMCString (String kernel, int gridNum){	
	String StatMC = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/ModelsAuto/" + kernel + "_" + gridNum + "/rulesStatMC" + kernel + "_" + gridNum;
    return StatMC;
}

//used in SimulatorFarms
public static String Log (){	
	String log = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/Output/Log/Output_Kernel" + ModelFarms.KernelNumber + "Model" + ModelFarms.ModelNumber + "_" + ModelFarms._SIMULATION_ID;
	return log;
}

//used in SimulatorFarms
public static String Meta (){	
	String meta = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/Output/Meta/Output_Kernel" + ModelFarms.KernelNumber + "Model" + ModelFarms.ModelNumber + "_" + ModelFarms._SIMULATION_ID;
	return meta;
}


//used in SimulatorFarms
public static String Data (){	
	String data = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/Output/Data/Output_Kernel" + ModelFarms.KernelNumber + "Model" + ModelFarms.ModelNumber + "_" + ModelFarms._SIMULATION_ID;
    return data;
}


//used in ModelFarms
public static String ValueSMCString () {
	String ValueSMC = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/ModelsAuto/Results";
	return ValueSMC;
}


//used in ModelFarms
public static String csvString () {
	String csvFile = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/ResultsCSVfile.csv";
	return csvFile;
}

//used in AnalyserFarms
public static String csvStringAnalyser (int KernelNumberAnalyser, int ModelNumberAnalyser) {
	 String csvFile = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/MELAFarmsModel/ModelsAuto/K" + KernelNumberAnalyser + "_" + ModelNumberAnalyser + "/ModelK" + KernelNumberAnalyser + "_" + ModelNumberAnalyser + "CSVfile.csv";
	 return csvFile;	   
}

public static String LogAnalyser (int KernelNumberAnalyser, int ModelNumberAnalyser){
   String log = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/Output/Log/Output_Kernel" + KernelNumberAnalyser + "Model" + ModelNumberAnalyser + "_1_log.txt";
   return log;
}

public static String DataAnalyser (int KernelNumberAnalyser, int ModelNumberAnalyser, int k){
   String data = "/afs/inf.ed.ac.uk/user/s13/s1372511/Desktop/FARMS_MODELS/Output/Data/Output_Kernel" + KernelNumberAnalyser + "Model" + ModelNumberAnalyser + "_" + k + "_data.txt";
   return data;
}


}



