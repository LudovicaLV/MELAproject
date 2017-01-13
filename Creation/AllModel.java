package Creation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import TotalFarms.Writing;


public class AllModel {	
	static String Model;
    static String statMC;
    
	public static void PrintAll(HashMap<Integer, Double> rangeMap, String LocalEvaluation, int gridNum, String kernel){	

    Model = Writing.ModelString(kernel, gridNum);
    statMC = Writing.statMCString(kernel, gridNum);
    int shape = TotalFarms.TotalFarms.shape;
    double rate = TotalFarms.TotalFarms.rate;    		
    		
    PrintWriter writer_model = null;
    PrintWriter writer_statMC = null;
	try {
		writer_model = new PrintWriter(Model+".mela", "UTF-8");	
		writer_statMC = new PrintWriter(statMC+".txt", "UTF-8");	
	} catch (FileNotFoundException | UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


  writer_model.println("#Space: TwoD(" + gridNum + "," + gridNum + ")\n" +
			"#Parameters");	

if (LocalEvaluation == "No"){		
for (int j=1; j <=shape; j++){
    for (Integer key : rangeMap.keySet()){
    	writer_model.println("c" + key + "_" + j +  "= " + rangeMap.get(key) + ";");
    	writer_model.println("p" + key + "_" + j + "= 1.0;");
}}	
for (int j=1; j <=shape; j++){
	writer_model.println("s" + j +  "= " + rate + ";");
}
writer_model.println("#Agents");

//this part is for S(l)
writer_model.print("S(l) := ");		    
int MapNumber =  rangeMap.size() - 1;
int initial = shape - 1;
for (int j=1; j <=initial; j++){
	for (int i=0; i < rangeMap.size(); i++){	
		writer_model.print("<-(contact" + (i+1) + "_" + j + ",p" + (i+1) + "_" + j + ").I1(l) + ");		
}};				
for (int j=1; j <=MapNumber; j++){			
	writer_model.print("<-(contact" + j + "_" +  shape + ",p" + j + "_" + shape + ").I1(l) + ");		
};	
writer_model.println("<-(contact" +  rangeMap.size() + "_" + shape + " ,p" +  rangeMap.size() + "_" + shape + ").I1(l);");	

//this part is for I(l) - initial ones
for (int j =1; j <= initial; j++){
writer_model.print("I" + j + "(l):= ");
for(int i=0; i < rangeMap.size(); i++){
	writer_model.print("->{N(" + (i+1) + ")}(contact" + (i+1) + "_" + j + ",c" + (i+1) + "_" + j + ").I" + j + "(l) +");
}
writer_model.println(" (step" + j + ",s" + j + ").I" + (j+1) + "(l);");  
}

// last I agent
writer_model.print("I" + shape + "(l):=");
for(int i=0; i < rangeMap.size(); i++){
	writer_model.print("->{N(" + (i+1) + ")}(contact" + (i+1) + "_" + shape + ",c" + (i+1) + "_" + shape + ").I" + shape + "(l) +");
}
writer_model.println(" (step" + shape + ",s" + shape + ").R(l);");
writer_model.println("R(l):=;");
}else{
if (LocalEvaluation == "Yes"){
	
	if (kernel == "K1"){
	for (int i=1; i <=shape; i++){
		writer_model.println("c_loc" + i + "= " + GiveRangeLocal(gridNum, "K1") + ";");
		writer_model.println("p_loc" + i + "= 1.0;");
	}}
	
	if (kernel == "K2"){
		for (int i=1; i <=shape; i++){
			writer_model.println("c_loc" + i + "= " + GiveRangeLocal(gridNum, "K2") + ";");
			writer_model.println("p_loc" + i + "= 1.0;");
		}}
	
	if (kernel == "K3"){
		for (int i=1; i <=shape; i++){
			writer_model.println("c_loc" + i + "= " + GiveRangeLocal(gridNum, "K3") + ";");
			writer_model.println("p_loc" + i + "= 1.0;");
		}}
	
	if (kernel == "K4"){
		for (int i=1; i <=shape; i++){
			writer_model.println("c_loc" + i + "= " + GiveRangeLocal(gridNum, "K4") + ";");
			writer_model.println("p_loc" + i + "= 1.0;");
		}}
		
	for (int j=1; j <=shape; j++){
	    for (Integer key : rangeMap.keySet()){
	    	writer_model.println("c" + key + "_" + j +  "= " + rangeMap.get(key) + ";");
	    	writer_model.println("p" + key + "_" + j + "= 1.0;");
	}}		
	for (int j=1; j <=shape; j++){
		writer_model.println("s" + j +  "=" + rate + ";");
	}
	writer_model.println("#Agents");

	//this part is for S(l)
	writer_model.print("S(l) := ");		    
	int MapNumber =  rangeMap.size() - 1;
	int initial = shape - 1;
	for (int j=1; j <=initial; j++){
		for (int i=0; i < rangeMap.size(); i++){	
			writer_model.print("<-(contact" + (i+1) + "_" + j + ",p" + (i+1) + "_" +  j + ").I1(l) + ");		
	}};				
	for (int j=1; j <=MapNumber; j++){			
		writer_model.print("<-(contact" + j + "_" +  shape + ",p" + j + "_" +  shape + ").I1(l) + ");		
	};	
	writer_model.println("<-(contact" +  rangeMap.size() + "_" + shape + " ,p" +  rangeMap.size() + "_" + shape + ").I1(l);");	

	//this part is for I(l) - initial ones
	for (int j =1; j <= initial; j++){
	writer_model.print("I" + j + "(l):= ");
	for(int i=0; i < rangeMap.size(); i++){
		writer_model.print("->{N(" + (i+1) + ")}(contact" + (i+1) + "_" + j + ",c" + (i+1) + "_" + j + ").I" + j + "(l) +");
	}
	writer_model.println(" (step" + j + ",s" + j + ").I" + (j+1) + "(l);");  
	}

	// last I agent
	writer_model.print("I" + shape + "(l):=");
	for(int i=0; i < rangeMap.size(); i++){
		writer_model.print("->{N(" + (i+1) + ")}(contact" + (i+1) + "_" + shape + ",c" + (i+1) + "_" + shape + ").I" + shape + "(l) +");
	}
	writer_model.println(" (step" + shape + ",s" + shape + ").R(l);");
	writer_model.println("R(l):=;");
	}
	
} 


ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		
		int valueX = InitCond.initialDimX/gridNum;
		int valueY = InitCond.initialDimY/gridNum;
		
		for (int i=0; i < InitCond.initLoc.size(); i++){
			int newValueX =  Math.floorDiv(InitCond.initLoc.get(i).get(0), valueX);
			int newValueY =  Math.floorDiv(InitCond.initLoc.get(i).get(1), valueY);		
			ArrayList<Integer> newLoc = new ArrayList<>();
			newLoc.add(newValueX);
			newLoc.add(newValueY);
			newList.add(newLoc);
			//InitCond.LocationHistoryInit.get(i).add(InitCond.ModelNumber, newLoc);
		}
				
		int newFarmValueX =  Math.floorDiv(InitCond.positionInitFarmX, valueX);
		int newFarmValueY =  Math.floorDiv(InitCond.positionInitFarmX, valueY);		
		
//		writer_model.println("--------------");
//		writer_model.println("#Space: TwoD(" + newX + "," + newY + ")");
		
//		writer_model.println("New points to consider:");	
//		for (int j=0; j < newList.size(); j++){
//			writer_model.print("(" + newList.get(j).get(0) + "," + newList.get(j).get(1) + "), ");
//		}
//		writer_model.print("(" + newFarmValueX + "," + newFarmValueY + ")");
		//counting in case of multiple farms
		HashMap<ArrayList<Integer>,Integer> Counting = new HashMap<>();
		
		Counting.put(newList.get(0), 1);
		
		for (int i=1; i < newList.size(); i++){
			if (Counting.keySet().contains(newList.get(i))){
				int newCount = Counting.get(newList.get(i)) + 1;
				Counting.replace(newList.get(i), newCount);
			}else{
				Counting.put(newList.get(i), 1);
			}
		}
			
//		writer_model.println(" ");
		writer_model.println("#Initial conditions");	
		for (ArrayList<Integer> key : Counting.keySet()){		
			writer_model.print("S(" + key.get(0) + "," + key.get(1) + ")[" + Counting.get(key) + "]||");
		}
		writer_model.print("I1(" + newFarmValueX + "," + newFarmValueY + ")[1]");
		writer_model.println(" ");
		
		
		
		writer_statMC.println("#Property: A(l) > 0");
        writer_statMC.print("#Agents: {");
	    for (ArrayList<Integer> key : Counting.keySet()){		
	    	writer_statMC.print("I1(" + key.get(0) + "," + key.get(1) + "), ");
		}
	    writer_statMC.print("I1(" + newFarmValueX + "," + newFarmValueY + ")}");
	    writer_statMC.println(" ");
		

writer_model.close();
writer_statMC.close();

}


public static double GiveRangeLocal (int m, String kernel){
	double value = 0.0;
	if (kernel == "K1"){
	double dist = 0.52140543316472067833 * (1.0/m) * InitCond.Xkm/10.0;
    value = Kernel.K1ValueComplete(dist);
    }else{
    	if (kernel == "K2"){
    		double dist = 0.52140543316472067833 * (1.0/m) * InitCond.Xkm/10.0;
    		value = Kernel.K2ValueComplete(dist);
    	}else{
    		if (kernel == "K3"){
    			double dist = 0.52140543316472067833 * (1.0/m) * InitCond.Xkm/10.0;
    			value = Kernel.K3ValueComplete(dist);
    		}else{
    			if(kernel == "K4"){
    				double dist = 0.52140543316472067833 * (1.0/m) * InitCond.Xkm/10.0;
    				value = Kernel.K1ValueComplete(dist);
    			}
    		}
    	}
    }
    return value;	
}


}
