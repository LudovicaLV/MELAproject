package Creation;

import java.util.ArrayList;
import java.util.HashMap;

public class Kernel{
	
	public static HashMap<Integer, Double> K1(double threshold, int gridNumX, int gridNumY){	
		HashMap<Integer, Double> rangeValue = new HashMap<Integer, Double>();
		double b0 = 0.00206;
		//true value 0.000206
		for (int i=1; i <= gridNumX; i++){
			double distance = i * (InitCond.Xkm/10.0) * (1.0/gridNumX);
			double toAdd = K1Value(distance) * b0;
			if (toAdd >= threshold){
				rangeValue.put(i, toAdd);
			}else{
				break;
			}
		}
//		System.out.println("For K1:");
//		for (Integer key : rangeValue.keySet()){
//			System.out.println("Range: " + key + " rate: " + rangeValue.get(key));
//		}
		
		//deal with sum of parameters
		HashMap<Integer, Double> rangeValueCorrect = new HashMap<Integer, Double>();

		ArrayList<Double> parameters = new ArrayList<Double>();
		for (Integer key : rangeValue.keySet()){
			parameters.add(rangeValue.get(key));
		}
		
		int range = parameters.size() - 1;
		for (int i=0; i < range; i++){
			int j = i+1;
			double newValue = parameters.get(i) - parameters.get(j);
			rangeValueCorrect.put(j, newValue);
		}
		int last = parameters.size();
		rangeValueCorrect.put(last, parameters.get(range));
		
//		for (Integer key : rangeValueCorrect.keySet()){
//			System.out.println("New Range1: " + key + " rate: " + rangeValueCorrect.get(key));
//		}			
		return rangeValueCorrect;
	}

	public static HashMap<Integer, Double> K2(double threshold, int gridNumX, int gridNumY){	
		HashMap<Integer, Double> rangeValue = new HashMap<Integer, Double>();
		double b0 = 0.00118;
		for (int i=1; i <= gridNumX; i++){
			double distance = i * InitCond.Xkm/(1000.0) * (100.0/gridNumX);
			double toAdd = K2Value(distance) * b0;
			if (toAdd > threshold){
				rangeValue.put(i, toAdd);
			}else{
				break;
			}
		}
//		System.out.println("For K2:");
//		for (Integer key : rangeValue.keySet()){
//			System.out.println("Range: " + key + " rate: " + rangeValue.get(key));
//		}
		
		//deal with sum of parameters
		HashMap<Integer, Double> rangeValueCorrect = new HashMap<Integer, Double>();

		ArrayList<Double> parameters = new ArrayList<Double>();
		for (Integer key : rangeValue.keySet()){
			parameters.add(rangeValue.get(key));
		}
		
		int range = parameters.size() - 1;
		for (int i=0; i < range; i++){
			int j = i+1;
			double newValue = parameters.get(i) - parameters.get(j);
			rangeValueCorrect.put(j, newValue);
		}
		int last = parameters.size();
		rangeValueCorrect.put(last, parameters.get(range));
		
//		for (Integer key : rangeValueCorrect.keySet()){
//			System.out.println("New Range: " + key + " rate: " + rangeValueCorrect.get(key));
//		}			
		return rangeValueCorrect;
	}
	
	public static HashMap<Integer, Double> K3(double threshold, int gridNumX, int gridNumY){	
		HashMap<Integer, Double> rangeValue = new HashMap<Integer, Double>();
		double b0 = 0.000907;
		for (int i=1; i <= gridNumX; i++){
			double distance = i * InitCond.Xkm/(1000.0) * (100.0/gridNumX);
			double toAdd = K3Value(distance) * b0;
			if (toAdd > threshold){
				rangeValue.put(i, toAdd);
			}else{
				break;
			}
		}
//		System.out.println("For K3:");
//		for (Integer key : rangeValue.keySet()){
//			System.out.println("Range: " + key + " rate: " + rangeValue.get(key));
//		}
		
		//deal with sum of parameters
		HashMap<Integer, Double> rangeValueCorrect = new HashMap<Integer, Double>();

		ArrayList<Double> parameters = new ArrayList<Double>();
		for (Integer key : rangeValue.keySet()){
			parameters.add(rangeValue.get(key));
		}
		
		int range = parameters.size() - 1;
		for (int i=0; i < range; i++){
			int j = i+1;
			double newValue = parameters.get(i) - parameters.get(j);
			rangeValueCorrect.put(j, newValue);
		}
		int last = parameters.size();
		rangeValueCorrect.put(last, parameters.get(range));
		
//		for (Integer key : rangeValueCorrect.keySet()){
//			System.out.println("New Range: " + key + " rate: " + rangeValueCorrect.get(key));
//		}
			
		return rangeValueCorrect;
	}
	
	public static HashMap<Integer, Double> K4(double threshold, int gridNumX, int gridNumY){	
		HashMap<Integer, Double> rangeValue = new HashMap<Integer, Double>();
		double b0 = 0.00134;
		for (int i=1; i <= gridNumX; i++){
			double distance = i * InitCond.Xkm/(1000.0) * (100.0/gridNumX);
			double toAdd = K4Value(distance) * b0;
			if (toAdd > threshold){
				rangeValue.put(i, toAdd);
			}else{
				break;
			}
		}
//		System.out.println("For K4:");
//		for (Integer key : rangeValue.keySet()){
//			System.out.println("Range: " + key + " rate: " + rangeValue.get(key));
//		}
		
		//deal with sum of parameters
		HashMap<Integer, Double> rangeValueCorrect = new HashMap<Integer, Double>();

		ArrayList<Double> parameters = new ArrayList<Double>();
		for (Integer key : rangeValue.keySet()){
			parameters.add(rangeValue.get(key));
		}
		
		int range = parameters.size() - 1;
		for (int i=0; i < range; i++){
			int j = i+1;
			double newValue = parameters.get(i) - parameters.get(j);
			rangeValueCorrect.put(j, newValue);
		}
		int last = parameters.size();
		rangeValueCorrect.put(last, parameters.get(range));
		
//		for (Integer key : rangeValueCorrect.keySet()){
//			System.out.println("New Range: " + key + " rate: " + rangeValueCorrect.get(key));
//		}
			
		return rangeValueCorrect;
	}
	
	public static double K1Value(double distance){	
		double value = 0.759 * distance;
		double result = Math.exp(-value) ;
		return result;		
	}
		
	
	public static double K2Value(double distance){	
		double result = 1/(1+Math.pow((distance/0.83),1.84));
		return result;		
	}
		
	
	public static double K3Value(double distance){	
		double result = 1/(1+(distance/0.354));
		return result;		
	}
		
	
	public static double K4Value(double distance){	
		double value = -Math.pow((distance/0.259), -1.61);
		double result = 1 - Math.exp(value) ;
		return result;		
	}
	
	public static double K1ValueComplete(double distance){	
		double value = 0.759 * distance;
		double result = Math.exp(-value) ;
		double b0 = 0.00206;
		//true value 0.000206
		double result2 = result * b0;
		return result2;		
	}
		
	
	public static double K2ValueComplete(double distance){	
		double result = 1/(1+Math.pow((distance/0.83),1.84));
		double b0 =  0.00118;
		double result2 = result * b0;
		return result2;			
	}
		
	
	public static double K3ValueComplete(double distance){	
		double result = 1/(1+(distance/0.354));
		double b0 =  0.000907;
		double result2 = result * b0;
		return result2;				
	}
		
	
	public static double K4ValueComplete(double distance){	
		double value = -Math.pow((distance/0.259), -1.61);
		double result = 1 - Math.exp(value) ;
		double b0 = 0.00134;
		double result2 = result * b0;
		return result2;			
	}
	
	public static double K1Percentage(int perc, int gridNumX){
		double b0 = 0.00206;
//		double distanceMin = (InitCond.Xkm/10.0) * (1.0/gridNumX);
//		double distanceMax = (InitCond.Xkm/10.0) * (1.0/gridNumX) * gridNumX;
//		double Max = K1Value(distanceMin) * b0;
//		double Min = K1Value(distanceMax) * b0;
//	    double value = (((Max)-(Min))/100)*perc + (Min);
		double distance = (InitCond.Xkm/10.0) * (1.0/gridNumX);
		double value = K1Value(distance) * b0;
		return value;	
	}
	
	//these other 3 have to be change!
	public static double K2Percentage(int perc){
		double value = (((0.0011791082393779121)-(2.5541768543151853E-4))/100)*perc + (2.5541768543151853E-4);
		return value;	
	}
	
	public static double K3Percentage(int perc){
		double value = (((8.661397356352847E-4)-(1.5863537549407115E-4))/100)*perc + (1.5863537549407115E-4);
		return value;	
	}
	
	public static double K4Percentage(int perc){
		double value = (((0.00134)-(6.504059062070917E-5))/100)*perc + (6.504059062070917E-5);
		return value;	
	}

}

