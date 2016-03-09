package Model;

import java.util.ArrayList;
import java.util.HashMap;


import Actions.Action;
import Actions.EnvAction;
import Actions.InfAction;
import Actions.NoInfAction;
import Actions.PassAction;
import Utility.StdRandom;
public class Simulator4 {

	public static void main(String[] args) {

		//define agent types (names)
		String[] Names = new String[]{"S", "I", "R"};
		ArrayList<String> AgentNames = new ArrayList<>();		
		for(int i=0; i< Names.length; i++){
		   addName(Names[i], AgentNames);
		}
		
		//define locations
		int[] loc = new int[] {1,2,3,4};
        ArrayList<Integer> Locations = new ArrayList<>();
        for(int i=0; i< loc.length; i++){
		addLoc(i, Locations);
        }
        
        //define position in the Locations array
        HashMap<Integer, Integer> position = new HashMap<>();	       
        position.put(1,0);
        position.put(2,1);
        position.put(3,2);
        position.put(4,3);
        
        //define neighbourhood relations
		int[][] neighLocations = new int[][] {{2,3},{1,4},{1,4},{2,3}};	
        
		
        //define initial populations (different type for each location)
        double[][] populations = new double[AgentNames.size()][Locations.size()];		
        int[][] init = new int[][] {{1,2,1,1}, {1,0,0,0}, {0,0,0,0}};
        
        //population matrix
        for(int i=0; i< AgentNames.size(); i++){
        	for(int j=0; j< Locations.size(); j++){
                populations[i][j] = init[i][j];
        	}
        }
		
        //actions of agent S
		Action a0 = new NoInfAction("birth", 0.4, "uparrow", "S");			
		Action a1 = new NoInfAction("death", 0.3, "downarrow", "S");	
		Action a2 = new PassAction("contact", 0.9, "dot", "I");			
		Action a3 = new NoInfAction("move", 0.2, "dot", "new");	
		
		ArrayList<Action> actionListS = new ArrayList<>();
		addAction(a0, actionListS);
		addAction(a1, actionListS);
		addAction(a2, actionListS);
		addAction(a3, actionListS);
		 	
		//actions of agent I
        //Action a4 = new NoInfAction("birthI", 0.4, "uparrow", "I");		
		Action a5 = new NoInfAction("deathI", 0.4, "downarrow", "I");			
		Action a6 = new InfAction("contact", 0.8, "l", "dot", "I");			
		Action a7 = new NoInfAction("moveI", 0.2, "dot", "new");	
		Action a8 = new NoInfAction("recover", 0.4, "dot", "R");	
		
		ArrayList<Action> actionListI = new ArrayList<>();
       // addAction(a4, actionListI);
		addAction(a5, actionListI);
		addAction(a6, actionListI);
		addAction(a7, actionListI);
		addAction(a8, actionListI);
		
		//actions of agent R		
		Action a9 = new NoInfAction("deathR", 0.3, "downarrow", "R");	
		
		ArrayList<Action> actionListR = new ArrayList<>();
		addAction(a9, actionListI);
				
	
		//arrayList of the actions arrayLists
		ArrayList<ArrayList<Action>> allActions = new ArrayList<>();
        allActions.add(actionListS);
        allActions.add(actionListI);
        allActions.add(actionListR);
        
        //Map Agent -> actions
        HashMap<String, ArrayList<Action>> AgentAction = new HashMap<>();	
        for(int i=0; i< AgentNames.size(); i++){
        	AgentAction.put(AgentNames.get(i), allActions.get(i));	
        }
        
        //setting time for the simulation
		double time = 0;
		double timeTotal = 10;
				
		while(time < timeTotal){
		ArrayList<Double> PropFunc = new ArrayList<>();
		ArrayList<Integer> agentArrayList = new ArrayList<>();
		ArrayList<Integer> agentPositionArray = new ArrayList<>();
		ArrayList<String> nameActions = new ArrayList<>();
		ArrayList<Integer> passiveAgentPositionArray = new ArrayList<>();
		ArrayList<Double> passiveProbArray = new ArrayList<>();
		ArrayList<Integer> Type = new ArrayList<>();
		
		
		for(int i=0; i < AgentNames.size(); i++){
	        	for(int j=0; j < Locations.size(); j++){
	        		if (populations[i][j]==0){}else{
	        			for(Action action: AgentAction.get(AgentNames.get(i))){
	        				//no-inf action - mass action
	        				if (action.getType() == Action.ACTION_TYPE_NoInf){
	 						   NoInfAction noinfaction = (NoInfAction) action;
	 					       PropFunc.add(noinfaction.getRate() * populations[i][j]);	 					       
	 					       agentArrayList.add(i);
	 					       agentPositionArray.add(j);
	 					       nameActions.add(action.getName());
	 					       passiveAgentPositionArray.add(-1);//there is no passive agent
	 					       passiveProbArray.add(0.0);
	 					       Type.add(action.getType());
	 				        }		        				
	        				//inf action - check all the couple act-pass (depending on influence set)
	 						if (action.getType() == Action.ACTION_TYPE_Inf){
	 						   InfAction infaction = (InfAction) action;	 						   
	 						      if (infaction.getInfSet() == "l"){	 						    	 
	 						    	 for(int k = 0; k < allActions.size(); k++){
	 						    		 if(k != i){
	 						    	     for(Action actionToCheck: allActions.get(k)){
	 						    		    if(action.getName() == actionToCheck.getName() && actionToCheck.getType() == Action.ACTION_TYPE_Pass){
	 						    		    	//now: passive agent same loc and >0
	 						    		    	if(populations[k][j] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * populations[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(j);
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(j);
	 					 					    PassAction passAction = (PassAction) actionToCheck;	
	 					 					    passiveProbArray.add(passAction.getInfProb());
	 					 					    Type.add(action.getType());
	 					 					    }}}}}}	 						      
	 						     if (infaction.getInfSet() == "N(l)"){	 						    	 
	 						    	 for(int k = 0; k < allActions.size(); k++){
	 						    		 if(k != i){
	 						    	     for(Action actionToCheck: allActions.get(k)){
	 						    		    if(action.getName() == actionToCheck.getName() && actionToCheck.getType() == Action.ACTION_TYPE_Pass){
	 						    		    	//now: passive agent same loc and >0
	 						    		    	for(int t : neighLocations[j]){
	 						    		    	if(populations[k][position.get(t)] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * populations[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(j);
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(t);
	 					 					    PassAction passAction = (PassAction) actionToCheck;	
	 					 					    passiveProbArray.add(passAction.getInfProb());
	 					 					    Type.add(action.getType());
	 					 					    }}}}}}}	 						      
	 						      if (infaction.getInfSet() == "all"){
	 						    	 for(int k = 0; k < allActions.size(); k++){
	 						    		 if(k != i){
	 						    	     for(Action actionToCheck: allActions.get(k)){
	 						    		    if(action.getName() == actionToCheck.getName() && actionToCheck.getType() == Action.ACTION_TYPE_Pass){
	 						    		    	//now: passive agent >0
	 						    		    	for(int s = 0; s < Locations.size(); s++){
	 						    		    	if(populations[k][s] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * populations[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(j);
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(s);
	 					 					    PassAction passAction = (PassAction) actionToCheck;	
	 					 					    passiveProbArray.add(passAction.getInfProb());
	 					 					    Type.add(action.getType());}}}}}}}}
	 						    
	 					   if (action.getType() == Action.ACTION_TYPE_Pass){
	 						    PropFunc.add(0.0);
					    		agentArrayList.add(i);
				 			    agentPositionArray.add(j);
				 			    nameActions.add(action.getName());
							    passiveAgentPositionArray.add(j);
							    passiveProbArray.add(0.0);
							    Type.add(action.getType());}
	 						      
	        	
	 				       if (action.getType() == Action.ACTION_TYPE_Env){
	 						    EnvAction envaction = (EnvAction) action;	 						      
		 						  if (envaction.getInfSet() == "all"){
		 						      for(int k = 0; k < allActions.size(); k++){
		 						          if(k != i){
		 						          for(Action actionToCheck: allActions.get(k)){
		 						     	     if(action.getName() == actionToCheck.getName() && actionToCheck.getType() == Action.ACTION_TYPE_Pass){
		 						    	    	//now: passive agent >0
		 						     	    	for(int s = 0; s < Locations.size(); s++){
		 						    		    	if(populations[k][s] > 0){
		 						    		    	PropFunc.add(envaction.getRate());
		 						    		    	agentArrayList.add(i);
		 					 					    agentPositionArray.add(-1);//env action no location
		 					 					    nameActions.add(action.getName());
		 					 					    passiveAgentPositionArray.add(s);
		 					 					    PassAction passAction = (PassAction) actionToCheck;	
		 					 					    passiveProbArray.add(passAction.getInfProb());
		 					 					    Type.add(action.getType());}}}}}}}
		 						  //the case of just one location (TO DO: more than one)
		 						  else{int locName = Integer.parseInt(envaction.getInfSet());
		 							  for (int r = 0; r < populations.length; r++){
		 								  	if(populations[r][position.get(locName)] > 0){
		 								  	for(Action actionToCheck: allActions.get(r)){
		 								  		if(action.getName() == actionToCheck.getName() && actionToCheck.getType() == Action.ACTION_TYPE_Pass){
		 								  			//now: passive agent same loc and >0
		 								  			PropFunc.add(envaction.getRate());
		 								  			agentArrayList.add(r);
		 								  			agentPositionArray.add(position.get(locName));
		 								  			nameActions.add(action.getName());
		 					 					    PassAction passAction = (PassAction) actionToCheck;	
		 					 					    passiveProbArray.add(passAction.getInfProb());
		 								  			passiveAgentPositionArray.add(position.get(locName));}}}}}}}}}}
		 						    		       				
	 		        				
//		for(int i=0; i < PropFunc.size(); i++){ 
//			System.out.println(PropFunc.get(i));
//			System.out.println(agentArrayList.get(i));
//			System.out.println(agentPositionArray.get(i));
//			System.out.println(nameActions.get(i));
//			System.out.println(passiveProbArray.get(i));
//			System.out.println(passiveAgentPositionArray.get(i));
//			System.out.println("end");
//		}
		
		
		double sumPropFunc = sum(PropFunc);
		
		if (sumPropFunc == 0){
			System.out.println("End - null population");
			break;
		}else{
		
		
		double[] Prob = new double[PropFunc.size()];

		double time_passed = Samples.getExp(sumPropFunc);

		for (int i = 0; i < PropFunc.size(); i++){
			Prob[i] = (PropFunc.get(i)/sumPropFunc);
		}

		int[] ActionRef = new int[PropFunc.size()];	
		
		for (int i = 0; i < PropFunc.size(); i++){
			ActionRef[i] = i;
		}	
		
		int action_index = Samples.getDiscrete(ActionRef, Prob);
		
		if(Type.get(action_index) == Action.ACTION_TYPE_NoInf){
			String nameToCheck = nameActions.get(action_index);
			for(int i=0; i < AgentNames.size(); i++){
		        for(Action action: AgentAction.get(AgentNames.get(i))){
		        	if(action.getName() == nameToCheck){
		        		NoInfAction chosenAction = (NoInfAction) action;		        	
		        		String symbol = chosenAction.getSymbol();
		//demo+
		if (symbol == "uparrow"){
		populations[agentArrayList.get(action_index)][agentPositionArray.get(action_index)]++;	
		System.out.println("Demographic+ action");
		}
		//demo-
		if (symbol == "downarrow"){
		populations[agentArrayList.get(action_index)][agentPositionArray.get(action_index)]--;					
		System.out.println("Demographic- action");
		}
		if (symbol == "dot"){
			if (chosenAction.getUpdate().matches("(?i).*new*")){				
				//so far just random movement
				int actualposition = agentPositionArray.get(action_index);			
				int[] neighbourhood = neighLocations[actualposition];
				double[] ProbLoc = new double[neighbourhood.length];
				for (int j=0; j < neighbourhood.length; j++){				
						ProbLoc[j] = (1.0/neighbourhood.length);
				}
				int new_l = Samples.getDiscrete(neighbourhood, ProbLoc);
				int positionnew_l = position.get(new_l);
				populations[agentArrayList.get(action_index)][actualposition]--;					
				populations[agentArrayList.get(action_index)][positionnew_l]++;
				System.out.println("Spatial action");}else{
                //change of state
				String newState = action.getUpdate();
				
				int newStatePos = 0;				
				for(int k=0; k < AgentNames.size(); k++){
					if(newState == AgentNames.get(k)){
						newStatePos = k;	
					}
				}
				populations[agentArrayList.get(action_index)][agentPositionArray.get(action_index)]--;					
				populations[newStatePos][agentPositionArray.get(action_index)]++;					
				System.out.println("State action");}}}}}}
		
		
		
		if(Type.get(action_index) == Action.ACTION_TYPE_Inf){
			double probEffect = passiveProbArray.get(action_index);
				if(StdRandom.bernoulli(probEffect)){
					String nameToCheck = nameActions.get(action_index);
						for(int i=0; i < AgentNames.size(); i++){
					        for(Action action: AgentAction.get(AgentNames.get(i))){
					        	if(action.getName() == nameToCheck && action.getType() == Action.ACTION_TYPE_Inf){
					        		InfAction chosenAction = (InfAction) action;		        	
					        		String symbolInf = chosenAction.getSymbol();
				    //update for the influence agent
					//demo+
					if (symbolInf == "uparrow"){
					populations[agentArrayList.get(action_index)][agentPositionArray.get(action_index)]++;	
					System.out.println("Demographic+ action - Inf");
					}
					//demo-
					if (symbolInf == "downarrow"){
					populations[agentArrayList.get(action_index)][agentPositionArray.get(action_index)]--;					
					System.out.println("Demographic- action - Inf");
					}
					if (symbolInf == "dot"){
						if (chosenAction.getUpdate().matches("(?i).*new*")){				
							//so far just random movement
							int actualposition = agentPositionArray.get(action_index);			
							int[] neighbourhood = neighLocations[actualposition];
							double[] ProbLoc = new double[neighbourhood.length];
							for (int j=0; j < neighbourhood.length; j++){				
									ProbLoc[j] = (1.0/neighbourhood.length);
							}
							int new_l = Samples.getDiscrete(neighbourhood, ProbLoc);
							int positionnew_l = position.get(new_l);
							populations[agentArrayList.get(action_index)][actualposition]--;					
							populations[agentArrayList.get(action_index)][positionnew_l]++;
							System.out.println("Spatial action - Inf");}else{
			                //change of state
							String newState = action.getUpdate();
							
							int newStatePos = 0;				
							for(int k=0; k < AgentNames.size(); k++){
								if(newState == AgentNames.get(k)){
									newStatePos = k;	
								}
							}
							populations[agentArrayList.get(action_index)][agentPositionArray.get(action_index)]--;					
							populations[newStatePos][agentPositionArray.get(action_index)]++;					
							System.out.println("State action - Inf");}}
										
					//update of passive agents
					for (int k=0; k < Names.length; k++){					
					for(Action actionToCheckPass: AgentAction.get(AgentNames.get(k))){
					        	if(actionToCheckPass.getName() == nameToCheck && actionToCheckPass.getType() == Action.ACTION_TYPE_Pass){
					        		PassAction chosenActionPass = (PassAction) actionToCheckPass;		        	
					        		String symbolPass = chosenActionPass.getSymbol();
					        		int PassAgentName = k;
					        		int PassAgentPosition = passiveAgentPositionArray.get(action_index);
					    //demo+
					    if (symbolPass == "uparrow"){
						populations[PassAgentName][PassAgentPosition]++;	
						System.out.println("Demographic+ action - Pass");
						}
						//demo-
						if (symbolPass == "downarrow"){
						populations[PassAgentName][PassAgentPosition]--;					
						System.out.println("Demographic- action - Pass");
						}
						if (symbolPass == "dot"){
							if (chosenActionPass.getUpdate().matches("(?i).*new*")){				
								//so far just random movement			
								int[] neighbourhood = neighLocations[PassAgentPosition];
								double[] ProbLoc = new double[neighbourhood.length];
								for (int j=0; j < neighbourhood.length; j++){				
										ProbLoc[j] = (1.0/neighbourhood.length);
								}
								int new_l = Samples.getDiscrete(neighbourhood, ProbLoc);
								int positionnew_l = position.get(new_l);
								populations[PassAgentName][PassAgentPosition]--;					
								populations[PassAgentName][positionnew_l]++;
								System.out.println("Spatial action - Pass");}else{
				                //change of state
								String newState = action.getUpdate();
								int newStatePos = 0;				
								for(int q=0; q < AgentNames.size(); q++){
									if(newState == AgentNames.get(q)){
										newStatePos = q;	
									}
								}
								populations[PassAgentName][PassAgentPosition]--;					
								populations[newStatePos][PassAgentPosition]++;					
								System.out.println("State action - Pass");}}}}}
					
					        			
		
		if(Type.get(action_index) == Action.ACTION_TYPE_Env){			
			//update of passive agents (after an Env action)
			for (int k=0; k < Names.length; k++){					
			for(Action actionToCheckPass: AgentAction.get(AgentNames.get(k))){
			        	if(actionToCheckPass.getName() == nameToCheck && actionToCheckPass.getType() == Action.ACTION_TYPE_Pass){
			        		PassAction chosenActionPass = (PassAction) actionToCheckPass;		        	
			        		String symbolPass = chosenActionPass.getSymbol();
			        		int PassAgentName = k;
			        		int PassAgentPosition = passiveAgentPositionArray.get(action_index);
			    //demo+
			    if (symbolPass == "uparrow"){
				populations[PassAgentName][PassAgentPosition]++;	
				System.out.println("Demographic+ action - Pass");
				}
				//demo-
				if (symbolPass == "downarrow"){
				populations[PassAgentName][PassAgentPosition]--;					
				System.out.println("Demographic- action - Pass");
				}
				if (symbolPass == "dot"){
					if (chosenActionPass.getUpdate().matches("(?i).*new*")){				
						//so far just random movement			
						int[] neighbourhood = neighLocations[PassAgentPosition];
						double[] ProbLoc = new double[neighbourhood.length];
						for (int j=0; j < neighbourhood.length; j++){				
								ProbLoc[j] = (1.0/neighbourhood.length);
						}
						int new_l = Samples.getDiscrete(neighbourhood, ProbLoc);
						int positionnew_l = position.get(new_l);
						populations[PassAgentName][PassAgentPosition]--;					
						populations[PassAgentName][positionnew_l]++;
						System.out.println("Spatial action - Pass");}else{
		                //change of state
						String newState = action.getUpdate();
						int newStatePos = 0;				
						for(int q=0; q < AgentNames.size(); q++){
							if(newState == AgentNames.get(q)){
								newStatePos = q;	
							}
						}
						populations[PassAgentName][PassAgentPosition]--;					
						populations[newStatePos][PassAgentPosition]++;					
						System.out.println("State action - Pass");}}}}}
			
			
		}}}}}}
			
		
		
		time = time + time_passed;
		for(int i=0; i < AgentNames.size(); i++){
	        	for(int j=0; j < Locations.size(); j++){
	        		System.out.println(AgentNames.get(i) + "(" + loc[j] + ")=" + populations[i][j]);
	        		}
		}
	        	System.out.println("Time: " + time);
	        	System.out.println("-----");
		}}}
		
		
	
	
	
       //methods	
	
	    public static double sum (ArrayList<Double> list) {
	       double sum = 0;
	          for (int i = 0; i < list.size() ; i++) {
	              sum = sum + list.get(i);
	          }
	       return sum;
	    }	
			
		public static void addAction(Action a, ArrayList<Action> list){
			list.add(a);
		}
		
		public static void addName(String name, ArrayList<String> list){
			list.add(name);
		}
		
		public static void addLoc(Integer loc, ArrayList<Integer> list){
			list.add(loc);
		}
		
		
	  
		
}	
				

