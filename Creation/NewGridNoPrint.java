package Creation;

import java.util.ArrayList;
import java.util.HashMap;

import ParserProg5Farms.ModelFarms;

public class NewGridNoPrint {
	
	public static String Evaluate(ArrayList<ArrayList<Integer>> LocList, int newX, int newY){
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		
		int valueX = InitCond.initialDimX/newX;
		int valueY = InitCond.initialDimY/newY;

		for (int i=0; i < LocList.size(); i++){
			int newValueX =  Math.floorDiv(LocList.get(i).get(0), valueX);
			int newValueY =  Math.floorDiv(LocList.get(i).get(1), valueY);		
			ArrayList<Integer> newLoc = new ArrayList<>();
			newLoc.add(newValueX);
			newLoc.add(newValueY);
			newList.add(newLoc);
			if (InitCond.ModelNumber != 0){
			ModelFarms.LocationHistory.get(i).add(newLoc);
		}else{
			ArrayList<ArrayList<Integer>> toAdd = new ArrayList<>();
			toAdd.add(newLoc);		
			ModelFarms.LocationHistory.add(i, toAdd);
		}}
		
//		System.out.println("--------------");
//		System.out.println("#Space: TwoD(" + newX + "," + newY + ")");
		
//		System.out.println("New points to consider:");	
//		for (int j=0; j < newList.size(); j++){
//			System.out.print("(" + newList.get(j).get(0) + "," + newList.get(j).get(1) + "), ");
//		}
//		System.out.print("(" + newFarmValueX + "," + newFarmValueY + ")");
		//counting in case of multiple farms
		HashMap<ArrayList<Integer>,Integer> Counting = new HashMap<>();
		
		Counting.put(newList.get(0), 1);
		
		for (int i=1; i < newList.size(); i++){
			if (Counting.keySet().contains(newList.get(i))){
				int newCount = Counting.get(newList.get(i)) + 1;
				Counting.replace(newList.get(i), newCount);
			}else{
				Counting.put(newList.get(i), 1);
			}
		}
			
	String Give = "";
	for (ArrayList<Integer> key : Counting.keySet()){
		if (Counting.get(key) != 1){	
			Give = "Yes";
			break;
		}else{
			Give = "No";
		}}
	return Give;
	

}
	
	
//	public static int getPosition(int modelnumber){
//		if (modelnumber == 100){
//			int value = 0;
//			return value;
//		}else{if (modelnumber == 50){
//			int value = 1;
//			return value;
//	    }else{if (modelnumber == 25){
//			int value = 2;
//			return value;
//	    }else{if (modelnumber == 20){
//			int value = 3;
//			return value;
//	    }else{if (modelnumber == 10){
//			int value = 4;
//			return value;
//	    }else{if (modelnumber == 5){
//			int value = 5;
//			return value;
//	    }else{if (modelnumber == 4){
//			int value = 6;
//			return value;
//	    }else{int value = 7;
//		return value;
//        }}}}}}}
//	
//}
}
