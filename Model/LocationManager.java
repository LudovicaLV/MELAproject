package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationManager {
	public ArrayList<ArrayList<Integer>> AllLoc = new ArrayList<ArrayList<Integer>>();	
	
	//Graph, OneD, TwoD, ThreeD
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighGraph = new HashMap<>();		    		
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighOneD = new HashMap<>();		    	    		  
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighTwoD = new HashMap<>();		    	    		  
    public HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> NeighThreeD = new HashMap<>();		    	    		       
   
    
    //public HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> globalMap  = new HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>>();       
	
    public int BracketsCounter;	
	public String SpatialSt = new String();
	
	public int OneD;	
	
	public int TwoDx;	
	public int TwoDy;	
	
	public int ThreeDx;	
	public int ThreeDy;	
	public int ThreeDz;	
	 
			    
	public ArrayList<ArrayList<Integer>> getAllLoc(){
    	return AllLoc;
    }  

    //Hashmap name - Matrix position

	public HashMap<ArrayList<Integer>, Integer> MatrixLoc = new HashMap<ArrayList<Integer>, Integer>();    

    public HashMap<ArrayList<Integer>, Integer> MatrixLocationCreation() {
    	for(int i=0; i< AllLoc.size(); i++){
    	   ArrayList<Integer> name = AllLoc.get(i);
     	   MatrixLoc.put(name,i);   	  
    	   }
     	return MatrixLoc;
    }
    
	//Hashmap name - neigh (given differently if graph or grid)	
	
	public ArrayList<ArrayList<Integer>> getNeigh(ArrayList<Integer> LocName){
	    if(SpatialSt == "Graph") {
	   		return GlobalManager.getLocationManager().NeighGraph.get(LocName);	    		
	   	}else{if (SpatialSt == "OneD"){
          return GlobalManager.getLocationManager().getNeighOneD(LocName);		    	    		  
	   	}else{if (SpatialSt == "TwoD"){
    		return GlobalManager.getLocationManager().getNeighTwoD(LocName);	
	    }else{
	   		return GlobalManager.getLocationManager().getNeighThreeD(LocName);			    	    		  
	    }}}}
	

	public ArrayList<ArrayList<Integer>> getNeighOneD(ArrayList<Integer> LocName){
		ArrayList<ArrayList<Integer>> Neigh1 = new ArrayList<ArrayList<Integer>>(); 	
		int neigh1 = (LocName.get(0) + 1) % OneD;
		int neigh2 = (LocName.get(0) - 1 + OneD) % OneD;				
		ArrayList<Integer> neigh1_list = GlobalManager.createListName(neigh1);
		ArrayList<Integer> neigh2_list = GlobalManager.createListName(neigh2);			
		Neigh1.add(neigh1_list);  			
		Neigh1.add(neigh2_list); 
        return Neigh1;	
	   }		

	public ArrayList<ArrayList<Integer>> getNeighTwoD(ArrayList<Integer> LocName){
		ArrayList<ArrayList<Integer>> Neigh2 = new ArrayList<ArrayList<Integer>>(); 	
		ArrayList<Integer> Neigh2_1 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh2_2 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh2_3 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh2_4 = new ArrayList<Integer>();		
		Neigh2_1.add((LocName.get(0) + 1) % TwoDx);
		Neigh2_1.add((LocName.get(1)) % TwoDy);   			
		Neigh2_2.add((LocName.get(0) - 1 + TwoDx) % TwoDx);
		Neigh2_2.add((LocName.get(1)) % TwoDy);   			
		Neigh2_3.add(LocName.get(0)% TwoDx); 
		Neigh2_3.add((LocName.get(1) + 1) % TwoDy);
		Neigh2_4.add(LocName.get(0) % TwoDx); 
		Neigh2_4.add((LocName.get(1) - 1 + TwoDy) % TwoDy); 			
		Neigh2.add(Neigh2_1);  			
		Neigh2.add(Neigh2_2); 
		Neigh2.add(Neigh2_3);  			
		Neigh2.add(Neigh2_4); 
        return Neigh2;	
	   }
			
	public ArrayList<ArrayList<Integer>> getNeighThreeD(ArrayList<Integer> LocName){
		ArrayList<ArrayList<Integer>> Neigh3 = new ArrayList<ArrayList<Integer>>(); 	
		ArrayList<Integer> Neigh3_1 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh3_2 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh3_3 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh3_4 = new ArrayList<Integer>();	   			
		ArrayList<Integer> Neigh3_5 = new ArrayList<Integer>();	
		ArrayList<Integer> Neigh3_6 = new ArrayList<Integer>();	
		Neigh3_1.add((LocName.get(0) + 1) % ThreeDx);
		Neigh3_1.add(LocName.get(1) % ThreeDy);	
		Neigh3_1.add(LocName.get(2) % ThreeDz);	
		
		Neigh3_2.add((LocName.get(0) - 1 + ThreeDx) % ThreeDx);
		Neigh3_2.add(LocName.get(1) % ThreeDy);
		Neigh3_2.add(LocName.get(2) % ThreeDz);		
		
		Neigh3_3.add(LocName.get(0) % ThreeDx); 
		Neigh3_3.add((LocName.get(1) + 1) % ThreeDy);
		Neigh3_3.add(LocName.get(2) % ThreeDz);	   
		
		Neigh3_4.add(LocName.get(0) % ThreeDx);
		Neigh3_4.add((LocName.get(1) - 1 + ThreeDy) % ThreeDy);
		Neigh3_4.add(LocName.get(2) % ThreeDz);		
		
		Neigh3_5.add(LocName.get(0) % ThreeDx); 
		Neigh3_5.add(LocName.get(1) % ThreeDy); 
		Neigh3_5.add((LocName.get(2) + 1)  % ThreeDz);  
		
		Neigh3_6.add(LocName.get(0) % ThreeDx); 
		Neigh3_6.add(LocName.get(1) % ThreeDy); 	
		Neigh3_6.add((LocName.get(2) - 1 + ThreeDz)  % ThreeDz); 					
		Neigh3.add(Neigh3_1);  
		Neigh3.add(Neigh3_2);
		Neigh3.add(Neigh3_3);
		Neigh3.add(Neigh3_4);
		Neigh3.add(Neigh3_5);
		Neigh3.add(Neigh3_6);
        return Neigh3;	
	   }
		   
	   
	   //method to use for the parser 
	   public HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> prepareMap(ArrayList<Integer> NodeName){	   	   
		   ArrayList<ArrayList<Integer>> NeighNodes = new ArrayList<ArrayList<Integer>>();
		   NeighGraph.put(NodeName, NeighNodes);
		   return NeighGraph; 
		  } 
	   
	   public void addNeighNode(ArrayList<Integer> NodeNeigh, int BracketsCounter){
		   int positionMap = BracketsCounter-2;
		   ArrayList<ArrayList<Integer>> List = NeighGraph.get(GlobalManager.getLocationManager().AllLoc.get(positionMap));		
		   List.add(NodeNeigh);
		  } 
	   
	   

	   
	   
}