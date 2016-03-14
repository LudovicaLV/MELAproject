package Model;

import java.util.ArrayList;

public class Location {

    ArrayList<Integer> LocationName = new ArrayList<>();
    
    public Location(ArrayList<Integer> LocName) {
    	this.LocationName = LocName;
    	LocationManager.createNeighbourhood(LocName);
    }
 }
   
	
 
    
 
