package RandomFarms;

import java.util.ArrayList;
import java.util.Random;

public class RandomF {

	public static void main(String[] args) {
		
		int numberOfFarms = 100;
		int gridX = 10;
	    int gridY = 10;
	    
	    int initFarmX = 5;
	    int initFarmY = 5;
	    ArrayList<Integer> initFarm = new ArrayList<>();
	    initFarm.add(initFarmX);
	    initFarm.add(initFarmY);
	    
		ArrayList<ArrayList<Integer>> initialLocs = new ArrayList<>();		
//		while (initialLocs.size() < numberOfFarms){	    
//		int valueX = randInt(0,gridX-1);
//		int valueY = randInt(0,gridY-1);
//		ArrayList<Integer> loc = new ArrayList<>();
//		loc.add(valueX);
//		loc.add(valueY);
//		if (!(initialLocs.contains(loc)) && !(loc.equals(initFarm))){
//			initialLocs.add(loc);
//		  }
//	    }
		
		for (int i=0; i< gridX; i++){
			for (int j=0; j < gridY; j++){
			ArrayList<Integer> loc = new ArrayList<>();
			loc.add(i);
			loc.add(j);
			initialLocs.add(loc);}}
		
		
		
		//order coordinates - future extension
		System.out.println("Initial conditions:");
		for (int j=0; j < initialLocs.size(); j++){
			System.out.print("S(" + initialLocs.get(j).get(0) + "," + initialLocs.get(j).get(1) + ")[1]||");
		}
		System.out.print("I(" + initFarmX + "," + initFarmY + ")[1]");
		System.out.println(" ");
		System.out.println("Therefore to analyse:");
		
		for (int j=0; j < initialLocs.size(); j++){
			System.out.print("I(" + initialLocs.get(j).get(0) + "," + initialLocs.get(j).get(1) + "), ");
		}
		System.out.print("I(" + initFarmX + "," + initFarmY + ")}");
		
		System.out.println(" ");
		System.out.println("For plotting:");
		System.out.print("XMC = [");
		for (int j=0; j < initialLocs.size(); j++){
			System.out.print(initialLocs.get(j).get(0) + ", ");
		}
		System.out.print(initFarmX + "]");
		System.out.println(" ");
		System.out.print("YMC = [");
		for (int j=0; j < initialLocs.size(); j++){
			System.out.print(initialLocs.get(j).get(1) + ", ");
		}
		System.out.print(initFarmY  + "]");
		System.out.println(" ");	
		
		System.out.print("X = [");
		for (int i=0; i < gridX; i++){
			for (int j=0; j < gridY; j++){
			System.out.print(i + ", ");
		}
		}
		System.out.print("]");
		System.out.println(" ");
		System.out.print("Y = [");
		for (int i=0; i < gridX; i++){
			for (int j=0; j < gridY; j++){
			System.out.print(j + ", ");
		}
		}
		System.out.print("]");
		
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
