package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class AgentManager {
	
	public static ArrayList<String> AgentNames = new ArrayList<>();    
    public static HashMap<String, Integer> MatrixAgent = new HashMap<>();	
      
	public static int[][] GlobalMatrixCreation() {
	       int[][] GlobalMatrix = new int[AgentNames.size()][LocationManager.AllLoc.size()];
	       return GlobalMatrix;

	}
       
    public static void MatrixAgent() {
    	for(int i=0; i< AgentNames.size(); i++){
    		MatrixAgent.put(AgentNames.get(i),i);
    	}
    }      
        
}


//  for(int i=0; i< AgentNames.size(); i++){
//	for(int j=0; j< AllLoc.size(); j++){
//        populations[i][j] = Init(AgentNames.get(i), AllLoc.get(j));
//	}
//}


