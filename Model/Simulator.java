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
			
		ArrayList<ArrayList<Action>> allActions = new ArrayList<ArrayList<Action>>();	
		for (Agent a : GlobalManager.getAgentManager().Agents){
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
		
		
		for(int i=0; i < GlobalManager.getAgentManager().Agents.size(); i++){
	        	for(int j=0; j < GlobalManager.getLocationManager().AllLoc.size(); j++){
	        		if (GlobalManager.getAgentManager().GlobalMatrix[i][j]==0){}else{
	        			//this has to be changed, for each agent we want to get the list, or as before, with AgentAction
	        			for(Action action: GlobalManager.getAgentManager().Agents.get(i).getActionList()){
	        				//no-inf action - mass action
	        				if (action.getType() == Action.ACTION_TYPE_NoInf){
	 						   NoInfAction noinfaction = (NoInfAction) action;
	 					       PropFunc.add(noinfaction.getRate() * GlobalManager.getAgentManager().GlobalMatrix[i][j]);	 					       
	 					       agentArrayList.add(i);
	 					       agentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
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
	 						    		    	if(GlobalManager.getAgentManager().GlobalMatrix[k][j] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * GlobalManager.getAgentManager().GlobalMatrix[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
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
	 						    		    	for(ArrayList<Integer> t : GlobalManager.getLocationManager().getNeigh(GlobalManager.getLocationManager().AllLoc.get(j))){
	 						    		    	if(GlobalManager.getAgentManager().GlobalMatrix[k][GlobalManager.getLocationManager().MatrixLoc.get(t)] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * GlobalManager.getAgentManager().GlobalMatrix[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
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
	 						    		    	for(ArrayList<Integer> s : GlobalManager.getLocationManager().AllLoc){
	 						    		    	if(GlobalManager.getAgentManager().GlobalMatrix[k][GlobalManager.getLocationManager().MatrixLoc.get(s)] > 0){
	 						    		    	PropFunc.add(infaction.getRate() * GlobalManager.getAgentManager().GlobalMatrix[i][j]);
	 						    		    	agentArrayList.add(i);
	 					 					    agentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
	 					 					    nameActions.add(action.getName());
	 					 					    passiveAgentPositionArray.add(s);
	 					 					    PassAction passAction = (PassAction) actionToCheck;	
	 					 					    passiveProbArray.add(passAction.getInfProb());
	 					 					    Type.add(action.getType());}}}}}}}}
	 						    
	 					   if (action.getType() == Action.ACTION_TYPE_Pass){
	 						    PropFunc.add(0.0);
					    		agentArrayList.add(i);
				 			    agentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
				 			    nameActions.add(action.getName());
							    passiveAgentPositionArray.add(GlobalManager.getLocationManager().AllLoc.get(j));
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
		 						     	    	for(ArrayList<Integer> s : GlobalManager.getLocationManager().AllLoc){
		 						    		    	if(GlobalManager.getAgentManager().GlobalMatrix[k][GlobalManager.getLocationManager().MatrixLoc.get(s)] > 0){
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
		 							  for (int r = 0; r < GlobalManager.getAgentManager().GlobalMatrix.length; r++){
		 								  	if(GlobalManager.getAgentManager().GlobalMatrix[r][GlobalManager.getLocationManager().MatrixLoc.get(locName)] > 0){
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
		
//		for (int i = 0; i < PropFunc.size(); i++){
//			System.out.println(PropFunc.get(i));
//			System.out.println(Prob[i]);
//			System.out.println(ActionRef[i]);
//		}
		
		int action_index = Samples.getDiscrete(ActionRef, Prob);
		
		if(Type.get(action_index) == Action.ACTION_TYPE_NoInf){
			String nameToCheck = nameActions.get(action_index);
			for(int i=0; i < GlobalManager.getAgentManager().AgentNames.size(); i++){
		        for(Action action: GlobalManager.getAgentManager().Agents.get(i).getActionList()){
		        	if(action.getName() == nameToCheck){
		        		NoInfAction chosenAction = (NoInfAction) action;		        	
		        		String symbol = chosenAction.getSymbol();

		//demo+
		if (symbol == ">>"){
			GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]++;	
		System.out.println("Demographic+ action");
		}
		//demo-
		if (symbol == "<<"){
			GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]--;					
		System.out.println("Demographic- action");
		}
		if (symbol == "."){
			if (chosenAction.getUpdate().matches("(.*)new(.*)")){				
				//so far just random movement
				ArrayList<Integer> actualposition = agentPositionArray.get(action_index);
				ArrayList<ArrayList<Integer>> neighbourhood = GlobalManager.getLocationManager().getNeigh(actualposition);
				double[] ProbLoc = new double[neighbourhood.size()];
				for (int j=0; j < neighbourhood.size(); j++){	
					for (int k = 0; k < neighbourhood.get(j).size(); k++ ){
						ProbLoc[j] = (1.0/neighbourhood.size());}
				}
				int[] neighbourhoodEntries = new int[neighbourhood.size()];
				for (int k=0; k < neighbourhood.size(); k++){				
					neighbourhoodEntries[k] = k;
			    }
				int locNewEntry = Samples.getDiscrete(neighbourhoodEntries, ProbLoc);
				ArrayList<Integer> locNewName = neighbourhood.get(locNewEntry);
				GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(actualposition)]--;	
				int s = agentArrayList.get(action_index);
				int t = GlobalManager.getLocationManager().MatrixLoc.get(locNewName);
     			GlobalManager.getAgentManager().GlobalMatrix[s][t]++;
				System.out.println("Spatial action");}else{
			
                //change of state
				String newState = action.getUpdate();	
				int newStatePos = 0;				
				for(int k=0; k < GlobalManager.getAgentManager().AgentNames.size(); k++){
					if(newState.equals(GlobalManager.getAgentManager().AgentNames.get(k) + "(l)")){
						newStatePos = k;
					}
				}
				GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]--;					
				GlobalManager.getAgentManager().GlobalMatrix[newStatePos][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]++;					
				System.out.println("State action");}}}}}}
		
		
		
		if(Type.get(action_index) == Action.ACTION_TYPE_Inf){
			double probEffect = passiveProbArray.get(action_index);
				if(StdRandom.bernoulli(probEffect)){
					String nameToCheck = nameActions.get(action_index);
						for(int i=0; i < GlobalManager.getAgentManager().AgentNames.size(); i++){
					        for(Action action: GlobalManager.getAgentManager().Agents.get(i).getActionList()){
					        	if(action.getName() == nameToCheck && action.getType() == Action.ACTION_TYPE_Inf){
					        		InfAction chosenAction = (InfAction) action;		        	
					        		String symbolInf = chosenAction.getSymbol();
				    //update for the influence agent
					//demo+
					if (symbolInf == "uparrow"){
						GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]++;	
					System.out.println("Demographic+ action - Inf");
					}
					//demo-
					if (symbolInf == "downarrow"){
						GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]--;					
					System.out.println("Demographic- action - Inf");
					}
					
					
					
					if (symbolInf == "dot"){
						if (chosenAction.getUpdate().matches("(?i).*new*")){				
							//so far just random movement
							ArrayList<Integer> actualposition = agentPositionArray.get(action_index);
							ArrayList<ArrayList<Integer>> neighbourhood = GlobalManager.getLocationManager().getNeigh(actualposition);
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
							GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(actualposition)]--;					
							GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(locNewName)]++;
			     			System.out.println("Spatial action - Inf");}else{
			                //change of state
							String newState = action.getUpdate();
							
							int newStatePos = 0;				
							for(int k=0; k < GlobalManager.getAgentManager().AgentNames.size(); k++){
								if(newState == GlobalManager.getAgentManager().AgentNames.get(k)){
									newStatePos = k;	
								}
							}
							GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]--;					
							GlobalManager.getAgentManager().GlobalMatrix[newStatePos][GlobalManager.getLocationManager().MatrixLoc.get(agentPositionArray.get(action_index))]++;					
							System.out.println("State action - Inf");}}
										
					//update of passive agents
					for (int k=0; k < GlobalManager.getAgentManager().AgentNames.size(); k++){					
					for(Action actionToCheckPass: GlobalManager.getAgentManager().Agents.get(k).getActionList()){
					        	if(actionToCheckPass.getName() == nameToCheck && actionToCheckPass.getType() == Action.ACTION_TYPE_Pass){
					        		PassAction chosenActionPass = (PassAction) actionToCheckPass;		        	
					        		String symbolPass = chosenActionPass.getSymbol();
					        		int PassAgentName = k;
					        		ArrayList<Integer> PassAgentPosition = passiveAgentPositionArray.get(action_index);
					    //demo+
					    if (symbolPass == "uparrow"){
					    	GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]++;	
						System.out.println("Demographic+ action - Pass");
						}
						//demo-
						if (symbolPass == "downarrow"){
							GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]--;					
						System.out.println("Demographic- action - Pass");
						}
						if (symbolPass == "dot"){
								if (chosenAction.getUpdate().matches("(?i).*new*")){				
									//so far just random movement
									ArrayList<ArrayList<Integer>> neighbourhood = GlobalManager.getLocationManager().getNeigh(PassAgentPosition);
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
									GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]--;															
									GlobalManager.getAgentManager().GlobalMatrix[agentArrayList.get(action_index)][GlobalManager.getLocationManager().MatrixLoc.get(locNewName)]++;
							     	System.out.println("Spatial action - Pass");}else{				
									
									
				                //change of state
								String newState = action.getUpdate();
								int newStatePos = 0;				
								for(int q=0; q < GlobalManager.getAgentManager().AgentNames.size(); q++){
									if(newState == GlobalManager.getAgentManager().AgentNames.get(q)){
										newStatePos = q;	
									}
								}
								GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]--;					
								GlobalManager.getAgentManager().GlobalMatrix[newStatePos][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]++;					
								System.out.println("State action - Pass");}}}}}
					
					        			
		
		if(Type.get(action_index) == Action.ACTION_TYPE_Env){			
			//update of passive agents (after an Env action)
			for (int k=0; k < GlobalManager.getAgentManager().AgentNames.size(); k++){					
			for(Action actionToCheckPass: GlobalManager.getAgentManager().Agents.get(k).getActionList()){
			        	if(actionToCheckPass.getName() == nameToCheck && actionToCheckPass.getType() == Action.ACTION_TYPE_Pass){
			        		PassAction chosenActionPass = (PassAction) actionToCheckPass;		        	
			        		String symbolPass = chosenActionPass.getSymbol();
			        		int PassAgentName = k;
			        		ArrayList<Integer> PassAgentPosition = passiveAgentPositionArray.get(action_index);
			    //demo+
			    if (symbolPass == "uparrow"){
			    	GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]++;	
				System.out.println("Demographic+ action - Pass");
				}
				//demo-
				if (symbolPass == "downarrow"){
					GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]--;					
				System.out.println("Demographic- action - Pass");
				}
	     		if (symbolPass == "dot"){
					if (chosenAction.getUpdate().matches("(?i).*new*")){				
							//so far just random movement
								ArrayList<ArrayList<Integer>> neighbourhood = GlobalManager.getLocationManager().getNeigh(PassAgentPosition);
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
								GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]--;			
								GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(locNewName)]++;					
								System.out.println("Spatial action - Pass");}else{				
	                //change of state
						String newState = action.getUpdate();
						int newStatePos = 0;				
						for(int q=0; q < GlobalManager.getAgentManager().AgentNames.size(); q++){
							if(newState == GlobalManager.getAgentManager().AgentNames.get(q)){
								newStatePos = q;	
							}
						}
						GlobalManager.getAgentManager().GlobalMatrix[PassAgentName][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]--;					
						GlobalManager.getAgentManager().GlobalMatrix[newStatePos][GlobalManager.getLocationManager().MatrixLoc.get(PassAgentPosition)]++;					
						System.out.println("State action - Pass");}}}}}
			
			
		}}}}}}
		
		time = time + time_passed;
	
		for(int i=0; i < GlobalManager.getAgentManager().AgentNames.size(); i++){
	        	for(int j=0; j < GlobalManager.getLocationManager().AllLoc.size(); j++){
	        		String formatedString = GlobalManager.getLocationManager().AllLoc.get(j).toString()
	        			    .replace("[", "")  //remove the right bracket
	        			    .replace("]", "")  //remove the left bracket
	        			    .trim(); 
	        		System.out.println(GlobalManager.getAgentManager().AgentNames.get(i) + "(" + formatedString + ")=" + GlobalManager.getAgentManager().GlobalMatrix[i][j]);
	        		}}}
	        	System.out.println("Time: " + time);
	        	System.out.println("-----");
		}}
		
	
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
				

