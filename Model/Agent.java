package Model;

import java.util.ArrayList;
import Actions.Action;


public class Agent {

	String name;
    Location AgentLoc;
	ArrayList<Action> actionList = new ArrayList<Action>();

	
	public Agent(String name, ArrayList<Integer> LocName) {
		this.name = name;
		this.AgentLoc = new Location(LocName);
	}
	
	public String getName(){
		return name;
	}
	
	public void addAction(Action a){
		actionList.add(a);
	}
	
	public ArrayList<Action> getActionList() {
		return this.actionList;
	}
	
}