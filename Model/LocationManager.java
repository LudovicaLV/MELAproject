package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationManager {
	public ArrayList<ArrayList<Integer>> AllLoc = new ArrayList<ArrayList<Integer>>();	   
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighGraph = new HashMap<>();		    		
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighOneD = new HashMap<>();		    	    		  
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighTwoD = new HashMap<>();		    	    		  
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighThreeD = new HashMap<>();		    	    		       

    public HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> globalMap  = new HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>>();       
	public int BracketsCounter;	

	public String SpatialSt = new String();
			    
	public ArrayList<ArrayList<Integer>> getAllLoc(){
    	return AllLoc;
    }  

    //Hashmap name - Matrix position

	public HashMap<ArrayList<Integer>, Integer> MatrixLoc = new HashMap<>();    
	
	public void MatrixLocation(ArrayList<ArrayList<Integer>> AllLoc) {
           for(int i=0; i< AllLoc.size(); i++){
        	   MatrixLoc.put(AllLoc.get(i),i);
        	   }
           }

	//Hashmap name - neigh (given differently if graph or grid)	
	
	public ArrayList<ArrayList<Integer>> getNeigh(ArrayList<Integer> LocName){
	    if(SpatialSt == "Graph") {
	   		return GlobalManager.getLocationManager().globalMap.get(LocName);	    		
	   	}else{if (SpatialSt == "OneD"){
	   		return GlobalManager.getLocationManager().NeighOneD.get(LocName);		    	    		  
	   	}else{if (SpatialSt == "TwoD"){
    		return GlobalManager.getLocationManager().NeighTwoD.get(LocName);	
	    }else{
	   		return GlobalManager.getLocationManager().NeighThreeD.get(LocName);			    	    		  
	    }}}}
	    		

	   public void createNeighbourhood(ArrayList<Integer> LocName){
		   if (SpatialSt == "Graph"){
			   //create previously (while parsing)
		   }else{
			   if (SpatialSt == "OneD"){
				   ArrayList<ArrayList<Integer>> NeighOneD = new ArrayList<ArrayList<Integer>> ();		
				ArrayList<Integer> Neigh1 = new ArrayList<Integer>(); 	   			
				//careful with boundaries conditions - to add
				Neigh1.add(LocName.get(0) + 1);  			
				Neigh1.add(LocName.get(0) - 1); 			
				GlobalManager.getLocationManager().NeighOneD.put(LocName, NeighOneD); 			
			}
			if (SpatialSt == "TwoD"){
				ArrayList<ArrayList<Integer>> NeighTwoD = new ArrayList<ArrayList<Integer>>();	
				ArrayList<Integer> Neigh2_1 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh2_2 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh2_3 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh2_4 = new ArrayList<Integer>();	
				Neigh2_1.add(LocName.get(0) + 1);
				Neigh2_1.add(LocName.get(1));   			
				Neigh2_2.add(LocName.get(0) - 1);
				Neigh2_2.add(LocName.get(1));    			
				Neigh2_3.add(LocName.get(0) + 1); 
				Neigh2_3.add(LocName.get(1)); 
				Neigh2_4.add(LocName.get(0) - 1); 
				Neigh2_4.add(LocName.get(1)); 		
				NeighTwoD.add(Neigh2_1);  			
				NeighTwoD.add(Neigh2_2); 
				NeighTwoD.add(Neigh2_3);  			
				NeighTwoD.add(Neigh2_4); 
				GlobalManager.getLocationManager().NeighTwoD.put(LocName, NeighTwoD); 			
			}
			if (SpatialSt == "ThreeD"){
				
				ArrayList<ArrayList<Integer>> NeighThreeD = new ArrayList<ArrayList<Integer>>();	
				ArrayList<Integer> Neigh3_1 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh3_2 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh3_3 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh3_4 = new ArrayList<Integer>();	   			
				ArrayList<Integer> Neigh3_5 = new ArrayList<Integer>();	
				ArrayList<Integer> Neigh3_6 = new ArrayList<Integer>();	
				Neigh3_1.add(LocName.get(0) + 1);
				Neigh3_1.add(LocName.get(1));   		
				Neigh3_1.add(LocName.get(2));       			
				Neigh3_2.add(LocName.get(0) - 1);
				Neigh3_2.add(LocName.get(1));  
				Neigh3_2.add(LocName.get(2)); 
				
				Neigh3_3.add(LocName.get(0) + 1); 
				Neigh3_3.add(LocName.get(1)); 
				Neigh3_3.add(LocName.get(2));    			
				Neigh3_4.add(LocName.get(0) - 1); 
				Neigh3_4.add(LocName.get(1)); 	
				Neigh3_4.add(LocName.get(2)); 
				
				Neigh3_5.add(LocName.get(0)); 
				Neigh3_5.add(LocName.get(1)); 
				Neigh3_5.add(LocName.get(2) + 1);     			
				Neigh3_6.add(LocName.get(0)); 
				Neigh3_6.add(LocName.get(1)); 	
				Neigh3_6.add(LocName.get(2) - 1); 
							
				NeighThreeD.add(Neigh3_1);  
				NeighThreeD.add(Neigh3_2);
				NeighThreeD.add(Neigh3_3);
				NeighThreeD.add(Neigh3_4);
				NeighThreeD.add(Neigh3_5);
				NeighThreeD.add(Neigh3_6);
							
				GlobalManager.getLocationManager().NeighThreeD.put(LocName, NeighThreeD); 			
			}
		}
	}
	   
	   
	   //method to use for the parser 
	   public HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> prepareMap(ArrayList<Integer> NodeName){	   	   
		   ArrayList<ArrayList<Integer>> NeighNodes = new ArrayList<ArrayList<Integer>>();
		   globalMap.put(NodeName, NeighNodes);
		   return globalMap; 
		  } 
	   
	   public void addNeighNode(ArrayList<Integer> NodeNeigh, int BracketsCounter){
		   int positionMap = BracketsCounter-2;
		   ArrayList<ArrayList<Integer>> List = globalMap.get(GlobalManager.getLocationManager().AllLoc.get(positionMap));		
		   List.add(NodeNeigh);
		  } 
	   
	   
}