package TotalFarms;

import Creation.InitCond;
import ParserProg5Farms.ModelFarms;

public class TotalFarms {
	
	public static int numberFarms = 1702;   
	//(1702, because the n.1703 is the infected one)
	public static int initialDimX = 50;   	
	public static int initialDimY = 50;
	public static int positionInitFarmX = 20;   	
	public static int positionInitFarmY = 40;
	
    public static int Xkm = 167;   	
	public static int Ykm = 167;
	
	//Erlang distribution for infection
	public static int shape = 36;
	public static double rate = 3.000102160645128;
	
	
	public static int numberOfRuns = 50;
	public static int simulationTime = 90;
	
	public static void main(String[] args) throws Exception {
	
     	InitCond.main(null);
		ModelFarms.main(null);

	}

}

