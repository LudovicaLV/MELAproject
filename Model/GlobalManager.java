package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Actions.Action;

public class GlobalManager {

	static ParamManager paramManager;
	
	public static void init(){	
			paramManager = new ParamManager();
			}

	public static ParamManager getParamManager() {
			return paramManager;
			}
	
	public static void PrintHashMap (HashMap<String, Double> Map){
		for (String key : Map.keySet()) {
			System.out.println("The value of parameter " + key + " is " + Map.get(key));
		}
	}	
	
	public static void PrintNames (ArrayList<String> List){
		System.out.println("We have the following agents:");
		for (String name : List) {
			System.out.println(name);
		}
	}
	
	public static void PrintActions (ArrayList<Agent> List){
		System.out.println("and they can perform these actions:");
		for (Agent agent : List) {
			ArrayList<Action> ListActions = agent.getActionList();
			System.out.println(agent.getName() + " --> ");
			for (Actions.Action action : ListActions) {
			System.out.println(action.getName() + " ");
			}
		}
	}
	
	public static void PrintLocations (ArrayList<ArrayList<Integer>> List){
		System.out.println("We have the following locations:");
		for (int i=0; i < List.size(); i++){
			System.out.print(List.get(i).get(0));
			for (int j=1; j < List.get(i).size(); j++){
				System.out.print("," + List.get(i).get(j));
			}
			System.out.println();
		}			
	}
	
	public static void PrintInitCondition(){
		for (int i=0; i < AgentManager.AgentNames.size(); i++){
			for (int j=0; j < LocationManager.AllLoc.size(); j++){
				System.out.print("The initial number of agent " + AgentManager.AgentNames.get(i) + " in location " + LocationManager.AllLoc.get(j).get(0));
				for (int k=1; k < LocationManager.AllLoc.get(j).size(); k++){
					System.out.print("," + LocationManager.AllLoc.get(j).get(k));
				}
				System.out.println(" is " + AgentManager.GlobalMatrix[i][j]);
			}
		}
	}
	
	
	

}