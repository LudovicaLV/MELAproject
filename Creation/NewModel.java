package Creation;

import java.util.HashMap;

public class NewModel {
	
	public static void Create(int gridNumX, int gridNumY, String kernel, double threshold){	
		if (kernel == "K1"){
			HashMap<Integer, Double> rangeMap = Kernel.K1(threshold, gridNumX, gridNumY);		
			String AnyLocal = NewGridNoPrint.Evaluate(InitCond.initLoc, gridNumX, gridNumY);	
			AllModel.PrintAll(rangeMap, AnyLocal, gridNumX, "K1");		
			
		}else{
			if (kernel == "K2"){
				HashMap<Integer, Double> rangeMap = Kernel.K2(threshold, gridNumX, gridNumY);		
				String AnyLocal = NewGridNoPrint.Evaluate(InitCond.initLoc, gridNumX, gridNumY);					
				AllModel.PrintAll(rangeMap, AnyLocal, gridNumX, "K2");
			}else{
				if (kernel == "K3"){
					HashMap<Integer, Double> rangeMap = Kernel.K3(threshold, gridNumX, gridNumY);			
					String AnyLocal = NewGridNoPrint.Evaluate(InitCond.initLoc, gridNumX, gridNumY);	
					AllModel.PrintAll(rangeMap, AnyLocal, gridNumX, "K3");	
				}else{
					if (kernel == "K4"){
						HashMap<Integer, Double> rangeMap = Kernel.K4(threshold, gridNumX, gridNumY);					
						String AnyLocal = NewGridNoPrint.Evaluate(InitCond.initLoc, gridNumX, gridNumY);	
						AllModel.PrintAll(rangeMap, AnyLocal, gridNumX, "K4");	
					}}}}
	}

}
