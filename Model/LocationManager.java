package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationManager {
	public static ArrayList<ArrayList<Integer>> AllLoc = new ArrayList<ArrayList<Integer>>();	   
    public static HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighGraph = new HashMap<>();		    		
    public static HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighOneD = new HashMap<>();		    	    		  
    public static HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighTwoD = new HashMap<>();		    	    		  
    public static HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighThreeD = new HashMap<>();		    	    		    
    

    public static HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> globalMap  = new HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>>();       
	public static int BracketsCounter;
			    
	public static ArrayList<ArrayList<Integer>> getAllLoc(){
    	return AllLoc;
    }  
    
    //Hashmap name - Matrix position

	public static HashMap<ArrayList<Integer>, Integer> MatrixLoc = new HashMap<>();    
	public void MatrixLocation(ArrayList<ArrayList<Integer>> AllLoc) {
           for(int i=0; i< AllLoc.size(); i++){
        	   MatrixLoc.put(AllLoc.get(i),i);
        	   }
           }

	//Hashmap name - neigh (given differently if graph or grid)	
	
	public static ArrayList<ArrayList<Integer>> getNeigh(ArrayList<Integer> LocName){
	    if(Init.SpatialSt == "Graph") {
	   		return LocationManager.NeighGraph.get(LocName);	    		
	   	}else{if (Init.SpatialSt == "OneD"){
	   		return LocationManager.NeighOneD.get(LocName);		    	    		  
	   	}else{if (Init.SpatialSt == "TwoD"){
    		return NeighTwoD.get(LocName);	
	    }else{
	   		return NeighThreeD.get(LocName);			    	    		  
	    }}}}
	    		

	   public static void createNeighbourhood(ArrayList<Integer> LocName){
		   if (Init.SpatialSt == "Graph"){
			   //create previously (while parsing)
		   }else{
			   if (Init.SpatialSt == "OneD"){
				   ArrayList<ArrayList<Integer>> NeighOneD = new ArrayList<ArrayList<Integer>> ();		
				ArrayList<Integer> Neigh1 = new ArrayList<Integer>(); 	   			
				//careful with boundaries conditions - to add
				Neigh1.add(LocName.get(0) + 1);  			
				Neigh1.add(LocName.get(0) - 1); 			
				LocationManager.NeighOneD.put(LocName, NeighOneD); 			
			}
			if (Init.SpatialSt == "TwoD"){
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
				LocationManager.NeighTwoD.put(LocName, NeighTwoD); 			
			}
			if (Init.SpatialSt == "ThreeD"){
				
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
							
				LocationManager.NeighThreeD.put(LocName, NeighThreeD); 			
			}
		}
	}
	   
	   
	   //method to use for the parser 
	   public static HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> prepareMap(ArrayList<Integer> NodeName){	   	   
		   ArrayList<ArrayList<Integer>> NeighNodes = new ArrayList<ArrayList<Integer>>();
		   globalMap.put(NodeName, NeighNodes);
		   return globalMap; 
		  } 
	   
	   public static HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> addNeighNode(ArrayList<Integer> NodeNeigh, int BracketsCounter){
		   globalMap.get(LocationManager.AllLoc.get(BracketsCounter-2)).add(NodeNeigh);
		   return globalMap;		   	 
		  } 
	    	  
	   
	   
	   
	   
	   
}