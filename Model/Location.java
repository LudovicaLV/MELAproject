package Model;

import java.util.ArrayList;

public class Location {
    ArrayList<Integer> LocationName = new ArrayList<>();    
    
    public Location(ArrayList<Integer> LocName) {
    	this.LocationName = LocName;
    }
	
    public ArrayList<Integer> getLocName(){
		return LocationName;
	}
    
   //add LocName to AllLoc
   public static void addLocName(Location Loc){
	   LocationManager.AllLoc.add(Loc.getLocName());
   }
   
   public static ArrayList<Integer> createListName(int Name){
   ArrayList<Integer> NameAsList = new ArrayList<Integer>();
   NameAsList.add(Name);
   return NameAsList;
   }
   
   public static ArrayList<ArrayList<Integer>> createAllLocOneD(int x){
	   for (int i = 0; i < x; i++){
		   LocationManager.AllLoc.add(createListName(i));
	   }
	   return LocationManager.AllLoc;
   }
  
   public static ArrayList<ArrayList<Integer>> createAllLocTwoD(int x, int y){
	   for (int i = 0; i < x; i++){
		   for (int j = 0; j < y; j++){
			   ArrayList<Integer> newList = new ArrayList<Integer>();
			   newList.add(i);
			   newList.add(j);		   
			   LocationManager.AllLoc.add(newList);
		   }
	   }
	   return LocationManager.AllLoc;
   }
   
   public static ArrayList<ArrayList<Integer>> createAllLocThreeD(int x, int y, int z){
	   for (int i = 0; i < x; i++){
		   for (int j = 0; j < y; j++){
			   for (int k = 0; k < z; k++){
			   ArrayList<Integer> newList = new ArrayList<Integer>();
			   newList.add(i);
			   newList.add(j);		
			   newList.add(k);
			   LocationManager.AllLoc.add(newList);
		   }
	     }
	   }
	   return LocationManager.AllLoc;
   }
   
}
   
