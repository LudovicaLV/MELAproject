package Model;

import java.util.ArrayList;

import Actions.Action;
import Actions.EnvAction;
import Actions.InfAction;
import Actions.NoInfAction;
import Actions.PassAction;
import Utility.StdRandom;
public class Simulator {

	public static void main(String[] args) {

		//from parsing:
		
		//define agent types (names) - AgentNames {S,I}
		
		//define locations - AllLoc
        
        //define position in the Locations array - MatrixAgent, MatrixLoc
        
        //define neighbourhood relations - graph, while parsing / grid - automatically
        		
        //define initial populations - while parsing, entry in GlobalMatrix (by default 0)
        
        //population matrix: GlobalMatrix
		
		Agent S = new Agent("S");
		Agent I = new Agent("I");
		Agent R = new Agent("R");
				
        //actions of agent S
		Action a0 = new NoInfAction("birth", 0.4, "uparrow", "S");			
		Action a1 = new NoInfAction("death", 0.3, "downarrow", "S");	
		Action a2 = new PassAction("contact", 0.9, "dot", "I");			
		Action a3 = new NoInfAction("move", 0.2, "dot", "new");	
		
		S.addAction(a0);
		S.addAction(a1);
		S.addAction(a2);
		S.addAction(a3);
		 	
		//actions of agent I	
		Action a5 = new NoInfAction("deathI", 0.4, "downarrow", "I");			
		Action a6 = new InfAction("contact", 0.8, "l", "dot", "I");			
		Action a7 = new NoInfAction("moveI", 0.2, "dot", "new");	
		Action a8 = new NoInfAction("recover", 0.4, "dot", "R");	
		
		I.addAction(a5);
		I.addAction(a6);
		I.addAction(a7);
		I.addAction(a8);
		
		//actions of agent R		
		Action a9 = new NoInfAction("deathR", 0.3, "downarrow", "R");	
		
		R.addAction(a9);
				
	
		//arrayList of the actions arrayLists
		ArrayList<ArrayList<Action>> allActions = new ArrayList<>();
		
		for (Agent a : AgentManager.Agents){
        allActions.add(a.getActionList());
		}
              
        //setting time for the simulation
		double time = 0;
		double timeTotal = 10;
				
		while(time < timeTotal){
		ArrayList<Double> PropFunc = new ArrayList<>();
		ArrayList<Integer> agentArrayList = new ArrayList<>();			
		ArrayList<ArrayList<Integer>> agentPositionArray = new ArrayList<>();	
		ArrayList<String> nameActions = new ArrayList<>();
		ArrayList<ArrayList<Integer>> passiveAgentPositionArray = new ArrayList<>(); //name of the location where the passive agent is
		ArrayList<Double> passiveProbArray = new ArrayList<>();
		ArrayList<Integer> Type = new ArrayList<>();		
		
		ArrayList<Integer> NoPassive = new ArrayList<Integer>();
		NoPassive.add(-1);
		
		
		for(int i=0; i < AgentManager.Agents.size(); i++){
	        	for(int j=0; j < LocationManager.AllLoc.size(); j++){
	        		if (AgentManager.GlobalMatrix[i][j]==0){}else{
	        			//this has to be changed, for each agent we want to get the list, or as before, with AgentAction
	        			for(Action action: AgentManager.Agents.get(i).getActionList()){
	        				//no-inf action - mass action
	        				if (action.getType() == Action.ACTION_TYPE_NoInf){
	 						   NoInfAction noinfaction = (NoInfAction) action;
	 					       PropFunc.add(noinfaction.getRate() * AgentManager.GlobalMatrix[i][j]);	 					       
	 					       agentArrayList.add(i);
	 					       agentPositionArray.add(LocationManager.AllLoc.get(j));
	 					       nameActions.add(action.getName());
	 					       passiveAgentPositionArray.add(NoPassive);//there is no passive agent
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
	 						    		    	if(AgentManager.GlobalMatrix[k][j] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * AgentManager.GlobalMatrix[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(LocationManager.AllLoc.get(j));
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(LocationManager.AllLoc.get(j));
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
	 						    		    	for(ArrayList<Integer> t : LocationManager.getNeigh(LocationManager.AllLoc.get(j))){
	 						    		    	if(AgentManager.GlobalMatrix[k][LocationManager.MatrixLoc.get(t)] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * AgentManager.GlobalMatrix[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(LocationManager.AllLoc.get(j));
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
	 						    		    	for(ArrayList<Integer> s : LocationManager.AllLoc){
	 						    		    	if(AgentManager.GlobalMatrix[k][LocationManager.MatrixLoc.get(s)] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * AgentManager.GlobalMatrix[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(LocationManager.AllLoc.get(j));
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(s);
	 					 					    PassAction passAction = (PassAction) actionToCheck;	
	 					 					    passiveProbArray.add(passAction.getInfProb());
	 					 					    Type.add(action.getType());}}}}}}}}
	 						    
	 					   if (action.getType() == Action.ACTION_TYPE_Pass){
	 						    PropFunc.add(0.0);
					    		agentArrayList.add(i);
				 			    agentPositionArray.add(LocationManager.AllLoc.get(j));
				 			    nameActions.add(action.getName());
							    passiveAgentPositionArray.add(LocationManager.AllLoc.get(j));
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
		 						     	    	for(ArrayList<Integer> s : LocationManager.AllLoc){
		 						    		    	if(AgentManager.GlobalMatrix[k][LocationManager.MatrixLoc.get(s)] > 0){
		 						    		    	PropFunc.add(envaction.getRate());
		 						    		    	agentArrayList.add(i);
		 					 					    agentPositionArray.add(NoPassive);//env action no location
		 					 					    nameActions.add(action.getName());
		 					 					    passiveAgentPositionArray.add(s);
		 					 					    PassAction passAction = (PassAction) actionToCheck;	
		 					 					    passiveProbArray.add(passAction.getInfProb());
		 					 					    Type.add(action.getType());}}}}}}}
		 						  //the case of just one location (TO DO: more than one)
		 						  else{ArrayList<Integer> locName = new ArrayList<Integer>();
		 							  for (int r = 0; r < AgentManager.GlobalMatrix.length; r++){
		 								  	if(AgentManager.GlobalMatrix[r][LocationManager.MatrixLoc.get(locName)] > 0){
		 								  	for(Action actionToCheck: allActions.get(r)){
		 								  		if(action.getName() == actionToCheck.getName() && actionToCheck.getType() == Action.ACTION_TYPE_Pass){
		 								  			//now: passive agent same loc and >0
		 								  			PropFunc.add(envaction.getRate());
		 								  			agentArrayList.add(r);
		 								  			agentPositionArray.add(locName);
		 								  			nameActions.add(action.getName());
		 					 					    PassAction passAction = (PassAction) actionToCheck;	
		 					 					    passiveProbArray.add(passAction.getInfProb());
		 								  			passiveAgentPositionArray.add(locName);}}}}}}}}}}
		 						    		       				
	 		        				
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
			for(int i=0; i < AgentManager.AgentNames.size(); i++){
		        for(Action action: AgentManager.Agents.get(i).getActionList()){
		        	if(action.getName() == nameToCheck){
		        		NoInfAction chosenAction = (NoInfAction) action;		        	
		        		String symbol = chosenAction.getSymbol();
		//demo+
		if (symbol == "uparrow"){
			AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]++;	
		System.out.println("Demographic+ action");
		}
		//demo-
		if (symbol == "downarrow"){
			AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]--;					
		System.out.println("Demographic- action");
		}
		if (symbol == "dot"){
			if (chosenAction.getUpdate().matches("(?i).*new*")){				
				//so far just random movement
				ArrayList<Integer> actualposition = agentPositionArray.get(action_index);
				ArrayList<ArrayList<Integer>> neighbourhood = LocationManager.getNeigh(actualposition);
				double[] ProbLoc = new double[neighbourhood.size()];
				for (int j=0; j < neighbourhood.size(); j++){				
						ProbLoc[j] = (1.0/neighbourhood.size());
				}
				int[] neighbourhoodEntries = new int[neighbourhood.size()];
				for (int j=0; j < neighbourhood.size(); j++){				
					neighbourhoodEntries[j] = j;
			    }
				int locNewEntry = Samples.getDiscrete(neighbourhoodEntries, ProbLoc);
				ArrayList<Integer> locNewName = neighbourhood.get(locNewEntry);
				AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(actualposition)]--;					
				AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(locNewName)]++;
     			System.out.println("Spatial action");}else{
					
					
                //change of state
				String newState = action.getUpdate();				
				int newStatePos = 0;				
				for(int k=0; k < AgentManager.AgentNames.size(); k++){
					if(newState == AgentManager.AgentNames.get(k)){
						newStatePos = k;	
					}
				}
				AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]--;					
				AgentManager.GlobalMatrix[newStatePos][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]++;					
				System.out.println("State action");}}}}}}
		
		
		
		if(Type.get(action_index) == Action.ACTION_TYPE_Inf){
			double probEffect = passiveProbArray.get(action_index);
				if(StdRandom.bernoulli(probEffect)){
					String nameToCheck = nameActions.get(action_index);
						for(int i=0; i < AgentManager.AgentNames.size(); i++){
					        for(Action action: AgentManager.Agents.get(i).getActionList()){
					        	if(action.getName() == nameToCheck && action.getType() == Action.ACTION_TYPE_Inf){
					        		InfAction chosenAction = (InfAction) action;		        	
					        		String symbolInf = chosenAction.getSymbol();
				    //update for the influence agent
					//demo+
					if (symbolInf == "uparrow"){
						AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]++;	
					System.out.println("Demographic+ action - Inf");
					}
					//demo-
					if (symbolInf == "downarrow"){
						AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]--;					
					System.out.println("Demographic- action - Inf");
					}
					
					
					
					if (symbolInf == "dot"){
						if (chosenAction.getUpdate().matches("(?i).*new*")){				
							//so far just random movement
							ArrayList<Integer> actualposition = agentPositionArray.get(action_index);
							ArrayList<ArrayList<Integer>> neighbourhood = LocationManager.getNeigh(actualposition);
							double[] ProbLoc = new double[neighbourhood.size()];
							for (int j=0; j < neighbourhood.size(); j++){				
									ProbLoc[j] = (1.0/neighbourhood.size());
							}
							int[] neighbourhoodEntries = new int[neighbourhood.size()];
							for (int j=0; j < neighbourhood.size(); j++){				
								neighbourhoodEntries[j] = j;
						    }
							int locNewEntry = Samples.getDiscrete(neighbourhoodEntries, ProbLoc);
							ArrayList<Integer> locNewName = neighbourhood.get(locNewEntry);
							AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(actualposition)]--;					
							AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(locNewName)]++;
			     			System.out.println("Spatial action - Inf");}else{
			                //change of state
							String newState = action.getUpdate();
							
							int newStatePos = 0;				
							for(int k=0; k < AgentManager.AgentNames.size(); k++){
								if(newState == AgentManager.AgentNames.get(k)){
									newStatePos = k;	
								}
							}
							AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]--;					
							AgentManager.GlobalMatrix[newStatePos][LocationManager.MatrixLoc.get(agentPositionArray.get(action_index))]++;					
							System.out.println("State action - Inf");}}
										
					//update of passive agents
					for (int k=0; k < AgentManager.AgentNames.size(); k++){					
					for(Action actionToCheckPass: AgentManager.Agents.get(k).getActionList()){
					        	if(actionToCheckPass.getName() == nameToCheck && actionToCheckPass.getType() == Action.ACTION_TYPE_Pass){
					        		PassAction chosenActionPass = (PassAction) actionToCheckPass;		        	
					        		String symbolPass = chosenActionPass.getSymbol();
					        		int PassAgentName = k;
					        		ArrayList<Integer> PassAgentPosition = passiveAgentPositionArray.get(action_index);
					    //demo+
					    if (symbolPass == "uparrow"){
					    	AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]++;	
						System.out.println("Demographic+ action - Pass");
						}
						//demo-
						if (symbolPass == "downarrow"){
							AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]--;					
						System.out.println("Demographic- action - Pass");
						}
						if (symbolPass == "dot"){
								if (chosenAction.getUpdate().matches("(?i).*new*")){				
									//so far just random movement
									ArrayList<ArrayList<Integer>> neighbourhood = LocationManager.getNeigh(PassAgentPosition);
									double[] ProbLoc = new double[neighbourhood.size()];
										for (int j=0; j < neighbourhood.size(); j++){				
											ProbLoc[j] = (1.0/neighbourhood.size());
										}
									int[] neighbourhoodEntries = new int[neighbourhood.size()];
									for (int j=0; j < neighbourhood.size(); j++){				
											neighbourhoodEntries[j] = j;
									    }
									int locNewEntry = Samples.getDiscrete(neighbourhoodEntries, ProbLoc);
									ArrayList<Integer> locNewName = neighbourhood.get(locNewEntry);
									AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(PassAgentPosition)]--;															AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(locNewName)]++;
							     	System.out.println("Spatial action - Pass");}else{				
									
									
				                //change of state
								String newState = action.getUpdate();
								int newStatePos = 0;				
								for(int q=0; q < AgentManager.AgentNames.size(); q++){
									if(newState == AgentManager.AgentNames.get(q)){
										newStatePos = q;	
									}
								}
								AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]--;					
								AgentManager.GlobalMatrix[newStatePos][LocationManager.MatrixLoc.get(PassAgentPosition)]++;					
								System.out.println("State action - Pass");}}}}}
					
					        			
		
		if(Type.get(action_index) == Action.ACTION_TYPE_Env){			
			//update of passive agents (after an Env action)
			for (int k=0; k < AgentManager.AgentNames.size(); k++){					
			for(Action actionToCheckPass: AgentManager.Agents.get(k).getActionList()){
			        	if(actionToCheckPass.getName() == nameToCheck && actionToCheckPass.getType() == Action.ACTION_TYPE_Pass){
			        		PassAction chosenActionPass = (PassAction) actionToCheckPass;		        	
			        		String symbolPass = chosenActionPass.getSymbol();
			        		int PassAgentName = k;
			        		ArrayList<Integer> PassAgentPosition = passiveAgentPositionArray.get(action_index);
			    //demo+
			    if (symbolPass == "uparrow"){
			    	AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]++;	
				System.out.println("Demographic+ action - Pass");
				}
				//demo-
				if (symbolPass == "downarrow"){
					AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]--;					
				System.out.println("Demographic- action - Pass");
				}
	     		if (symbolPass == "dot"){
						if (chosenAction.getUpdate().matches("(?i).*new*")){				
							//so far just random movement
								ArrayList<ArrayList<Integer>> neighbourhood = LocationManager.getNeigh(PassAgentPosition);
								double[] ProbLoc = new double[neighbourhood.size()];
									for (int j=0; j < neighbourhood.size(); j++){				
										ProbLoc[j] = (1.0/neighbourhood.size());
										}
								int[] neighbourhoodEntries = new int[neighbourhood.size()];
								for (int j=0; j < neighbourhood.size(); j++){				
										neighbourhoodEntries[j] = j;
								    }
								int locNewEntry = Samples.getDiscrete(neighbourhoodEntries, ProbLoc);
								ArrayList<Integer> locNewName = neighbourhood.get(locNewEntry);
								AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]--;															AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(locNewName)]++;
								AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(locNewName)]++;															AgentManager.GlobalMatrix[agentArrayList.get(action_index)][LocationManager.MatrixLoc.get(locNewName)]++;
								System.out.println("Spatial action - Pass");}else{				
	                //change of state
						String newState = action.getUpdate();
						int newStatePos = 0;				
						for(int q=0; q < AgentManager.AgentNames.size(); q++){
							if(newState == AgentManager.AgentNames.get(q)){
								newStatePos = q;	
							}
						}
						AgentManager.GlobalMatrix[PassAgentName][LocationManager.MatrixLoc.get(PassAgentPosition)]--;					
						AgentManager.GlobalMatrix[newStatePos][LocationManager.MatrixLoc.get(PassAgentPosition)]++;					
						System.out.println("State action - Pass");}}}}}
			
			
		}}}}}}
			
		
		
		time = time + time_passed;
		for(int i=0; i < AgentManager.AgentNames.size(); i++){
	        	for(int j=0; j < LocationManager.AllLoc.size(); j++){
	        		System.out.println(AgentManager.AgentNames.get(i) + "(" + LocationManager.AllLoc.get(j) + ")=" + AgentManager.GlobalMatrix[i][j]);
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
				

