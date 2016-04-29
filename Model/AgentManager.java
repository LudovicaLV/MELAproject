package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class AgentManager {
	  
	public static ArrayList<Agent> Agents = new ArrayList<>(); 
	public static ArrayList<String> AgentNames = new ArrayList<>(); 
    public static HashMap<String, Integer> MatrixAgent = new HashMap<>();	
    public static int[][] GlobalMatrix;  
    public static ArrayList<ArrayList<Integer>> Global = new ArrayList<ArrayList<Integer>>();
    
	public static void GlobalMatrixCreation() {
	       GlobalMatrix = new int[AgentNames.size()][LocationManager.AllLoc.size()];
	}
       
//	public static int[][] GlobalMatrixCreationversion2() {
//	       int[][] newMatrix = new int[AgentNames.size()][LocationManager.AllLoc.size()];
//	       GlobalMatrix = newMatrix;
//	       return GlobalMatrix;
//	}
		
    public static HashMap<String, Integer> MatrixAgentCreation() {
    	for(int i=0; i< AgentNames.size(); i++){
    		MatrixAgent.put(AgentNames.get(i),i);
    	}
    	return MatrixAgent;
    }
    
    public static void addAgent(Agent a) {
    	Agents.add(a);
    }
    
	 public static void addAgentName(String Name){
		AgentNames.add(Name);
	}
	 
        
}


