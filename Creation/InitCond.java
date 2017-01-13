package Creation;

import java.util.ArrayList;

public class InitCond {
	public static int numberFarms = TotalFarms.TotalFarms.numberFarms;   
	//(1702, because the n.1703 is the infected one)
	public static int initialDimX = TotalFarms.TotalFarms.initialDimX;   	
	public static int initialDimY = TotalFarms.TotalFarms.initialDimY;
	public static int positionInitFarmX = TotalFarms.TotalFarms.positionInitFarmX;   	
	public static int positionInitFarmY = TotalFarms.TotalFarms.positionInitFarmY;
	
    public static int Xkm = TotalFarms.TotalFarms.Xkm;   	
	public static int Ykm = TotalFarms.TotalFarms.Ykm;
	
	//public static ArrayList<ArrayList<ArrayList<Integer>>> LocationHistoryInit = new ArrayList<ArrayList<ArrayList<Integer>>>();	
	public static int ModelNumber = 0;
	public static int KernelNumber = 1;
	
	//ArrayList of Integer is the KernelChoice + ModelNuber, List of Integer is the location name, list of doubles are result \pm confidence interval
	
	public static ArrayList<Integer> Choice = new ArrayList<>();	
	
	//entries: number  of farms, grid X, gridY, initFarmX, initFarmY 
	public static ArrayList<ArrayList<Integer>> initLoc = ScatterFarm.InitFarm(numberFarms, initialDimX, initialDimY, positionInitFarmX, positionInitFarmY);  	
	
    public static void main(String[] args){	   
    	
        double thresholdK1 = Kernel.K1Percentage(1, 2);
//      double thresholdK2 = Kernel.K2Percentage(1);
//      double thresholdK3 = Kernel.K3Percentage(1);
//      double thresholdK4 = Kernel.K4Percentage(1);
              
//    	Kernel.K1(thresholdK1, 100, 100);
//    	Kernel.K2(thresholdK2, 100, 100);
//    	Kernel.K3(thresholdK3, 100, 100);
//    	Kernel.K4(thresholdK4, 100, 100);
 
    	
//    	NewModel.Create(100, 100, "K1", thresholdK1);
//    	ModelNumber++;
    	NewModel.Create(50, 50, "K1", thresholdK1);  
    	ModelNumber++;
    	NewModel.Create(25, 25, "K1", thresholdK1);
    	ModelNumber++;
//    	NewModel.Create(20, 20, "K1", thresholdK1);
//    	ModelNumber++;
    	NewModel.Create(10, 10, "K1", thresholdK1);
    	ModelNumber++;
    	NewModel.Create(5, 5, "K1", thresholdK1);
    	ModelNumber++;
//    	NewModel.Create(4, 4, "K1", thresholdK1);
//    	ModelNumber++;
    	NewModel.Create(2, 2, "K1", thresholdK1);

    	
    	
//    	NewModel.Create(100, 100, "K2", thresholdK2);
//    	System.out.println("---------");
//    	NewModel.Create(50, 50, "K2", thresholdK2);  
//    	System.out.println("---------");
//    	NewModel.Create(25, 25, "K2", thresholdK2);
//    	System.out.println("---------");
//    	NewModel.Create(20, 20, "K2", thresholdK2);
//    	System.out.println("---------");
//    	NewModel.Create(10, 10, "K2", thresholdK2);
//    	System.out.println("---------");
//    	NewModel.Create(5, 5, "K2", thresholdK2);
//    	System.out.println("---------");
//    	NewModel.Create(4, 4, "K2", thresholdK2);
//    	System.out.println("---------");
//    	NewModel.Create(2, 2, "K2", thresholdK2);
//    	
//    	
//    	NewModel.Create(100, 100, "K3", thresholdK3);
//    	System.out.println("---------");
//    	NewModel.Create(50, 50, "K3", thresholdK3);  
//    	System.out.println("---------");
//    	NewModel.Create(25, 25, "K3", thresholdK3);
//    	System.out.println("---------");
//    	NewModel.Create(20, 20, "K3", thresholdK3);
//    	System.out.println("---------");
//    	NewModel.Create(10, 10, "K3", thresholdK3);
//    	System.out.println("---------");
//    	NewModel.Create(5, 5, "K3", thresholdK3);
//    	System.out.println("---------");
//    	NewModel.Create(4, 4, "K3", thresholdK3);
//    	System.out.println("---------");
//    	NewModel.Create(2, 2, "K3", thresholdK3);
//    	
//    	
//    	NewModel.Create(100, 100, "K4", thresholdK4);
//    	System.out.println("---------");
//    	NewModel.Create(50, 50, "K4", thresholdK4);  
//    	System.out.println("---------");
//    	NewModel.Create(25, 25, "K4", thresholdK4);
//    	System.out.println("---------");
//    	NewModel.Create(20, 20, "K4", thresholdK4);
//    	System.out.println("---------");
//    	NewModel.Create(10, 10, "K4", thresholdK4);
//    	System.out.println("---------");
//    	NewModel.Create(5, 5, "K4", thresholdK4);
//    	System.out.println("---------");
//    	NewModel.Create(4, 4, "K4", thresholdK4);
//    	System.out.println("---------");
//    	NewModel.Create(2, 2, "K4", thresholdK4);
    
    }}


