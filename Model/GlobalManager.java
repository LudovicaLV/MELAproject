package Model;

import java.util.ArrayList;
import java.util.HashMap;
import Actions.Action;

public class GlobalManager {
	static AgentManager agentManager;
	static LocationManager locationManager;
	static ParamManager paramManager;
		
	public static void init(){
			agentManager = new AgentManager();
			locationManager = new LocationManager();
			paramManager = new ParamManager();		
		}		

	public static ParamManager getParamManager() {
			return paramManager;
			}
	
	public static LocationManager getLocationManager() {
		return locationManager;
		}
	
	public static AgentManager getAgentManager() {
		return agentManager;
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
			System.out.println(agent.getName() + ":");
			for (Actions.Action action : ListActions) {
			System.out.println("- " + action.getName() + " ");
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
		System.out.println("The initial number of each type of agent in each location:");
		for (int i=0; i < GlobalManager.getAgentManager().AgentNames.size(); i++){
			for (int j=0; j < GlobalManager.getLocationManager().AllLoc.size(); j++){
				System.out.print("The initial number of agent " + GlobalManager.getAgentManager().AgentNames.get(i) + " in location " + GlobalManager.getLocationManager().AllLoc.get(j).get(0));
				for (int k=1; k < GlobalManager.getLocationManager().AllLoc.get(j).size(); k++){
					System.out.print("," + GlobalManager.getLocationManager().AllLoc.get(j).get(k));
				}
				System.out.println(" is " + GlobalManager.getAgentManager().GlobalMatrix[i][j]);
			}
		}
	}
	
	
	public static void PrintNoZeroInitCondition(){
		System.out.println("Just the no-zero initial number:");
		for (int i=0; i < GlobalManager.getAgentManager().AgentNames.size(); i++){
			for (int j=0; j < GlobalManager.getLocationManager().AllLoc.size(); j++){
				if (GlobalManager.getAgentManager().GlobalMatrix[i][j] != 0){
				System.out.print("The initial number of agent " + GlobalManager.getAgentManager().AgentNames.get(i) + " in location (" + GlobalManager.getLocationManager().AllLoc.get(j).get(0));
				for (int k=1; k < GlobalManager.getLocationManager().AllLoc.get(j).size(); k++){
					System.out.print("," + GlobalManager.getLocationManager().AllLoc.get(j).get(k));
				}
				System.out.println(") is " + GlobalManager.getAgentManager().GlobalMatrix[i][j]);}
			}
		}
	}
		
	public static boolean isInteger(String s) {
		try
		{
		Integer.parseInt(s);
		// s is a valid integer
		return true;
		}
		catch (NumberFormatException ex)
		{System.out.println("The initial value has to be integer");
		return false;
		}
		}
	
	public static ArrayList<Integer> createListName (int name){
		 ArrayList<Integer> ListName = new ArrayList<Integer>();
		 ListName.add(name);
		 return ListName;
		}
	
	
	public static ArrayList<Integer> createListOneD (int x){
		 ArrayList<Integer> ListOneD = new ArrayList<Integer>();
		 ListOneD.add(x);
		 return ListOneD;
		}
	
		public static ArrayList<Integer> createListTwoD (int x, int y){
		 ArrayList<Integer> ListTwoD = new ArrayList<Integer>();
		 ListTwoD.add(x);
		 ListTwoD.add(y);
		 return ListTwoD;
		}

		public static ArrayList<Integer> createListThreeD (int x, int y, int z){
		 ArrayList<Integer> ListThreeD = new ArrayList<Integer>();
		 ListThreeD.add(x);
		 ListThreeD.add(y);
		 ListThreeD.add(z);
		 return ListThreeD;
		}

		public static ArrayList<Integer> createListZero (){
		ArrayList<Integer> Zero = new ArrayList<Integer>();
		Zero.add(0);
		return Zero;
		}	 
		

		public static ArrayList<ArrayList<Integer>> createAllLocOneD(int x){
		 for (int i = 0; i < x; i++){
			 ArrayList<Integer> newList = createListOneD(i);	 
			 GlobalManager.getLocationManager().AllLoc.add(newList);
		 }
		 return GlobalManager.getLocationManager().AllLoc;
		}

		public static ArrayList<ArrayList<Integer>> createAllLocTwoD(int x, int y){
		 for (int i = 0; i < x; i++){
			   for (int j = 0; j < y; j++){
				   ArrayList<Integer> newList = createListTwoD(i, j);
				   GlobalManager.getLocationManager().AllLoc.add(newList);
			   }
		    }
		 return GlobalManager.getLocationManager().AllLoc;
		}

		public static ArrayList<ArrayList<Integer>> createAllLocThreeD(int x, int y, int z){
		 for (int i = 0; i < x; i++){
			   for (int j = 0; j < y; j++){
				   for (int k = 0; k < z; k++){
				   ArrayList<Integer> newList = createListThreeD(i,j,k);
				   GlobalManager.getLocationManager().AllLoc.add(newList);
			   }
		    }
		 }
		 return GlobalManager.getLocationManager().AllLoc;
		}	
	   
		
		public static int SumPopulation (String name){
			int sum = 0;
			for (int j = 0; j < GlobalManager.getAgentManager().GlobalMatrix[0].length; j++){
		         sum = sum + GlobalManager.getAgentManager().GlobalMatrix[GlobalManager.getAgentManager().MatrixAgent.get(name)][j];
		        }
			return sum;
		    }
	 
		
		}