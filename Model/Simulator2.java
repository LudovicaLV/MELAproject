package Model;

import java.util.HashMap;
import java.util.List;

public class Simulator2 {

	public static void main(String[] args) {
		
		//map from set of locations to counts of different agents
		HashMap <int[], List<Integer>> PopMap = new HashMap<>();		

	}

}


//
//	public static void main(String[] args) {
//		
//		HashMap<String, int[]> PopMap = new HashMap<>();		
//		
//		String[] populations = new String[] {"S","I"};
//		int[] locations = new int[] {1,2,3,4};
//		int[] popS = new int[] {1,2,1,1};
//		int[] popI = new int[] {1,0,0,0};
//		//ArrayList<Integer> initpop = new ArrayList<>();
//		
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
//
//}
