package Model;


public class GlobalManager {

	static ParamManager paramManager;

	
	public static void init(){	
			paramManager = new ParamManager();
			}

	public static ParamManager getParamManager() {
			return paramManager;
			}

}