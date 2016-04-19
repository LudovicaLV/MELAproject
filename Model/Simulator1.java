package Model;

import java.util.ArrayList;
//import java.util.HashMap;

import Actions.Action;
import Actions.EnvAction;
import Actions.InfAction;
import Actions.NoInfAction;
import Actions.PassAction;
public class Simulator1 {

	public static void main(String[] args) {
		
		double time = 0;
		double timeTotal = 10;
		
		//HashMap<String, int[]> PopMap = new HashMap<>();		
		
		//int[][] locations = new int[][] {{2,3},{1,4},{1,4},{2,3}};
		int[][] populations = new int[][] {{1,2,1,1}, {1,0,0,0}};
		
		//S {"birthS", "deathS", "contact", "moveS"};
		
		Action a0 = new NoInfAction("birth", 0.4, "uparrow", "S");	
		
		Action a1 = new NoInfAction("death", 0.3, "downarrow", "S");	
		
		Action a2 = new PassAction("contact", 0.8, "dot", "S");	
		
		Action a3 = new NoInfAction("move", 0.2, "dot", "new");	
		
		//I {"birth", "death", "contact", "move"}; 
		
        Action a4 = new NoInfAction("birth", 0.4, "uparrow", "S");	
		
		Action a5 = new NoInfAction("death", 0.3, "downarrow", "S");	
		
		Action a6 = new InfAction("contact", 0.8, "l", "dot", "S");	
		
		Action a7 = new NoInfAction("move", 0.2, "dot", "new");	
		
		//then you add in the array list the set of actions
		
		ArrayList<Action> actionListS = new ArrayList<>();
		ArrayList<Action> actionListI = new ArrayList<>();
		
		addAction(a0, actionListS);
		addAction(a1, actionListS);
		addAction(a2, actionListS);
		addAction(a3, actionListS);
		
		addAction(a4, actionListI);
		addAction(a5, actionListI);
		addAction(a6, actionListI);
		addAction(a7, actionListI);
		
		ArrayList<Action> allActions = new ArrayList<>();
        allActions.addAll(actionListS);
        allActions.addAll(actionListS);
		
//		if (a5.getType() == Action.ACTION_TYPE_NoInf){
//			System.out.println("yes");
//		}
		
		//PropFunc array, depending on the type - then update
//		ArrayList<Double> PropFunc = new ArrayList<>();
//		ArrayList<Integer> actionTypeList = new ArrayList<>();
//		ArrayList<String> actionUpdateList = new ArrayList<>();
		
		while(time < timeTotal){
		ArrayList<Double> PropFunc = new ArrayList<>();
		ArrayList<Integer> actionTypeList = new ArrayList<>();
		ArrayList<String> actionUpdateList = new ArrayList<>();
		ArrayList<Integer> agentPositionArray = new ArrayList<>();
		
		for(int i=0; i< populations[0].length; i++){
			if (populations[0][i]==0){}else{
					for(Action action: actionListS) {
						if (action.getType() == Action.ACTION_TYPE_NoInf){
						   NoInfAction noinfaction = (NoInfAction) action;
					       PropFunc.add(noinfaction.getRate() * populations[0][i]);
					       actionTypeList.add(action.getType());
					       actionUpdateList.add(action.getUpdate());
					       agentPositionArray.add(i);
				        }						
//						if (action.getType() == Action.ACTION_TYPE_Inf){
//						   InfAction infaction = (InfAction) action;
//						      if (infaction.getInfSet() == "l"){
//							  PropFunc.add(infaction.getRate() * populations[0][i]);
//						      }
//						      if (infaction.getInfSet() == "all"){
//							  for(int k: populations[1]){
//							  PropFunc.add(infaction.getRate() * populations[0][i]);}
//						      }
//						}
						if (action.getType() == Action.ACTION_TYPE_Pass){
							agentPositionArray.add(i);
						}
						if (action.getType() == Action.ACTION_TYPE_Env){
							EnvAction envaction = (EnvAction) action;
							      //the infset for the env can also be a set of locations, no string, how to handle this information?
							      //if (envaction.getInfSet() == "l"){   
								  //PropFunc.add(infaction.getRate() * i * populations[1][i]);
							      //}
							      if (envaction.getInfSet() == "all"){
								  for(int k: populations[1]){
								  PropFunc.add(envaction.getRate() * populations[1][k]);}
								  actionTypeList.add(action.getType());
							      actionUpdateList.add(action.getUpdate());
							      agentPositionArray.add(i);
							      }						
						      }
					     }
				    }
		      } 
		
		
		for(int j=0; j< populations[1].length; j++){
			if (populations[1][j]==0){}else{
					for(Action action: actionListI) {
						if (action.getType() == Action.ACTION_TYPE_NoInf){
						   NoInfAction noinfaction = (NoInfAction) action;
					       PropFunc.add(noinfaction.getRate() * populations[1][j]);
					       actionTypeList.add(action.getType());
					       actionUpdateList.add(action.getUpdate());
					       agentPositionArray.add(agentPositionArray.get(agentPositionArray.size()-1) + 1 + j);
				        }						
						if (action.getType() == Action.ACTION_TYPE_Inf){
						   InfAction infaction = (InfAction) action;
						      if (infaction.getInfSet() == "l"){
						      if(populations[0][j]>0){
							  PropFunc.add(infaction.getRate() * populations[1][j]);
							  actionTypeList.add(action.getType());
						      actionUpdateList.add(action.getUpdate());
						      agentPositionArray.add(agentPositionArray.get(agentPositionArray.size()-1) + 1 + j);}}
						      if (infaction.getInfSet() == "all"){
							  for(int k: populations[1]){
								  if(populations[1][k]>0){
							  PropFunc.add(infaction.getRate() * populations[1][j]);
							  actionTypeList.add(action.getType());
						      actionUpdateList.add(action.getUpdate());
						      agentPositionArray.add(agentPositionArray.get(agentPositionArray.size()-1) + 1 + j);}}
						      }
						}
						if (action.getType() == Action.ACTION_TYPE_Pass){
							 agentPositionArray.add(agentPositionArray.get(agentPositionArray.size()- 1)+ 1 + j);;
						}
						if (action.getType() == Action.ACTION_TYPE_Env){
							EnvAction envaction = (EnvAction) action;
							      //the infset for the env can also be a set of locations, no string, how to handle this information?
							      //if (envaction.getInfSet() == "l"){   
								  //PropFunc.add(infaction.getRate() * i * populations[1][i]);
							      //}
							      if (envaction.getInfSet() == "all"){
								  for(int k: populations[1]){
								  PropFunc.add(envaction.getRate() * populations[1][k]);
								  actionTypeList.add(action.getType());
							      actionUpdateList.add(action.getUpdate());
							      agentPositionArray.add(agentPositionArray.get(agentPositionArray.size()-1) + j);}}						
						      }
					     }
				    }
		      } 
		
	
		
//		for(int i = 0; i < PropFunc.size(); i++) {   
//	    System.out.println(PropFunc.get(i));
//	} 
//				

	double sumPropFunc = sum(PropFunc);
	double[] Prob = new double[PropFunc.size()];
	
	double time_passed = Samples.getExp(sumPropFunc);

	for (int i = 0; i < PropFunc.size(); i++){
		Prob[i] = (PropFunc.get(i)/sumPropFunc);
    }
	
	int[] ActionRef = new int[PropFunc.size()];	
	for (int i = 0; i < (actionListS.size() + actionListI.size()); i++){
		ActionRef[i] = i;
	    }	
	
	int action_index = Samples.getDiscrete(ActionRef, Prob);

	//int type = actionTypeList.get(action_index);
	
	time = time + time_passed;
	System.out.println("time -> " + time + " - " + action_index);	
	for (int i = 0; i < agentPositionArray.size() ; i++) {
		System.out.println(agentPositionArray.get(i));
	}
	
	}}
	
	
	public static double sum (ArrayList<Double> list) {
	    double sum = 0;
	    for (int i = 0; i < list.size() ; i++) {
	        sum = sum + list.get(i);
	    }
	    return sum;
	}	
	
	
	
//		for(Action ac: state.getActionList()) {
//			if(ac.getType() == Action.ACTION_TYPE_SpBr) {
//				SpBrAction spBrAc = (SpBrAction) ac;
//				PropensityFunc pf = new PropensityFunc(stateID, ac, spBrAc.getRate());
//				propensityFuncs.add(pf);
//			}else if(ac.getType() == Action.ACTION_TYPE_SpNoMsg) {
//				SpNoMsgAction spNoMsgAc = (SpNoMsgAction) ac;
//				PropensityFunc pf = new PropensityFunc(stateID, ac, spNoMsgAc.getRate());
//				propensityFuncs.add(pf);
//			}else if(ac.getType() == Action.ACTION_TYPE_SpUn) {
//				SpUnAction spUnAc = (SpUnAction) ac;
//				PropensityFunc pf = new PropensityFunc(stateID, ac, spUnAc.getRate());
//				propensityFuncs.add(pf);
//			}else {
//				IndActions.add(ac);
		
		
		public static void addAction(Action a, ArrayList<Action> list){
			list.add(a);
		}
		
}	
		//Which is the order? For each agent list of actions, but how?
		//name, not enough - depending on the type of actions different input needed,
		//how to distinguish this?
				
	
	    //I {"birth", "death", "contact", "move"};
		
		//ArrayList<Integer> initpop = new ArrayList<>();
		
		//next step: for each population (S, I) we describe the possible action they can make.
		
		//no-infl
		//the actions require name, type, update function
		//propensity function: rate * count
		//demo+: count++
		//demo-: count--
		//state: count-- / update: count++
		//spatial: count-- / move function, then update: count++
		
		//influence: add influence set
		//then for each couple active-passive the propensity function is count_act * count_pass
		//and this has to be added in the propensity function vector
		
		//env action - as influence
		
//		int numPop = populations.length;
//		int numLoc = locations.length;	
//		
//		setPopulation(populations[0], popS, PopMap);
//		setPopulation(populations[1], popI, PopMap);
//	
//	    int[] popCount = getPopCount("S", PopMap);
//	    for (int i = 0; i < numLoc; i++){
//	    System.out.println(popCount[i]);
//	    }
//	    }		
//	
//        //System.out.println(getPopCount("S", PopMap)[1]);
//	
//	    public static void setPopulation(String name, int[] values, HashMap<String, int[]> PopMap ) {
//		PopMap.put(name, values);
//		}	
//				
//		public static int[] getPopCount(String name, HashMap<String, int[]> PopMap) {
//		String pop = name; 
//		int[] count = PopMap.get(pop);
//		return count;
//}		


