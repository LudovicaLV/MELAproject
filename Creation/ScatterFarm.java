package Creation;

import java.util.ArrayList;
import java.util.Random;

public class ScatterFarm {
	public static ArrayList<ArrayList<Integer>> InitFarm(int numberOfFarms, int gridX, int gridY, int initFarmX, int initFarmY) {

	    ArrayList<Integer> initFarm = new ArrayList<>();
	    initFarm.add(initFarmX);
	    initFarm.add(initFarmY);
	    
		ArrayList<ArrayList<Integer>> initialLocs = new ArrayList<>();		
		while (initialLocs.size() < numberOfFarms){	    
		int valueX = randInt(0,gridX-1);
		int valueY = randInt(0,gridY-1);
		ArrayList<Integer> loc = new ArrayList<>();
		loc.add(valueX);
		loc.add(valueY);
		if (!(initialLocs.contains(loc)) && !(loc.equals(initFarm))){
			initialLocs.add(loc);
		  }
	    }
		
		
//		for (int j=0; j < initialLocs.size(); j++){
//			ArrayList<ArrayList<Integer>> toAdd = new ArrayList<ArrayList<Integer>>();
//			toAdd.add(initialLocs.get(j));
//			InitCond.LocationHistoryInit.add(toAdd);
//	    }
				
		
//		for (int i=0; i< gridX; i++){
//			for (int j=0; j < gridY; j++){
//			ArrayList<Integer> loc = new ArrayList<>();
//			loc.add(i);
//			loc.add(j);
//			initialLocs.add(loc);}}
		
				
		//order coordinates - future extension
		
		//THIS PRINTS ALL THE INFO
		
//		System.out.println("#Space: TwoD(" + gridX + "," + gridY + ")");
//		
//		System.out.println("Points to consider:");	
//		for (int j=0; j < initialLocs.size(); j++){
//			System.out.print("(" + initialLocs.get(j).get(0) + "," + initialLocs.get(j).get(1) + "), ");
//		}
//		System.out.print("(" + initFarmX + "," + initFarmY + ")");
//		System.out.println(" ");
//		System.out.println("Initial conditions:");
//		for (int j=0; j < initialLocs.size(); j++){
//			System.out.print("S(" + initialLocs.get(j).get(0) + "," + initialLocs.get(j).get(1) + ")[1]||");
//		}
//		System.out.print("I(" + initFarmX + "," + initFarmY + ")[1]");
//		System.out.println(" ");
		
		
//		System.out.println("To analyse with stat MC:");
//		
//		for (int j=0; j < initialLocs.size(); j++){
//			System.out.print("I1(" + initialLocs.get(j).get(0) + "," + initialLocs.get(j).get(1) + "), ");
//		}
//		System.out.print("I1(" + initFarmX + "," + initFarmY + ")}");
		
//		System.out.println(" ");
//		System.out.println("For plotting:");
//		System.out.print("XMC = [");
//		for (int j=0; j < initialLocs.size(); j++){
//			System.out.print(initialLocs.get(j).get(0) + ", ");
//		}
//		System.out.print(initFarmX + "]");
//		System.out.println(" ");
//		System.out.print("YMC = [");
//		for (int j=0; j < initialLocs.size(); j++){
//			System.out.print(initialLocs.get(j).get(1) + ", ");
//		}
//		System.out.print(initFarmY  + "]");
//		System.out.println(" ");	
//		
//		System.out.print("X = [");
//		for (int i=0; i < gridX; i++){
//			for (int j=0; j < gridY; j++){
//			System.out.print(i + ", ");
//		}
//		}
//		System.out.print("]");
//		System.out.println(" ");
//		System.out.print("Y = [");
//		for (int i=0; i < gridX; i++){
//			for (int j=0; j < gridY; j++){
//			System.out.print(j + ", ");
//		}
//		}
//		System.out.print("]");
		
		return initialLocs;
		
	}
	
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
