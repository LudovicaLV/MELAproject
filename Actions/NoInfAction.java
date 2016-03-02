package Actions;

public class NoInfAction implements Action {
	// public class SpBrAction implements Action {
		private String name;
		private double rate;
        private String symbol;
		private String update;
		
		//int DETERMINISTIC 
		
		public NoInfAction(String name, double rate, String symbol, String update){
			this.name = name;
			this.rate = rate;
			this.symbol = symbol;
			this.update = update;
		}
		
@Override
public int getType() {
	return Action.ACTION_TYPE_NoInf;
}

public String getName() {
	return name;
}

public Double getRate() {
	return rate;
}

public String getSymbol() {
	return symbol;
}

@Override
public String getUpdate() {
	return update;
}

@Override
public String printStr() {
	String ret = "(" + name + "," + rate + ")" + symbol + update;
	return ret;
}

}
