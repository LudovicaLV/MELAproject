package Model;

import java.util.ArrayList;

public class Init{

	public static String SpatialSt = new String();
	
    double[][] Populations = new double[AgentManager.AgentNames.size()][LocationManager.AllLoc.size()];	
    //by default the initial values in the matrix are 0.0
    
    public void InitAgent(String name, ArrayList<Integer> Loc, int initNumber) {	
         Populations[AgentManager.MatrixAgent.get(name)][LocationManager.MatrixLoc.get(Loc)] = initNumber;
         
     }
        
}
    
