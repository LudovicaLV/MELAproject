package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class AgentManager {
	  
	public static ArrayList<Agent> Agents = new ArrayList<>(); 
	public static ArrayList<String> AgentNames = new ArrayList<>(); 
    public static HashMap<String, Integer> MatrixAgent = new HashMap<>();	
    public static int[][] GlobalMatrix;  
    
	public static int[][] GlobalMatrixCreation() {
	       int[][] GlobalMatrix = new int[AgentNames.size()][LocationManager.AllLoc.size()];
	       return GlobalMatrix;
	}
       
    public static void MatrixAgent() {
    	for(int i=0; i< AgentNames.size(); i++){
    		MatrixAgent.put(AgentNames.get(i),i);
    	}
    }
    
    public static void addAgent(Agent a) {
    	Agents.add(a);
    }
    
	 public static void addAgentName(String Name){
		AgentNames.add(Name);
	}
	 
        
}


