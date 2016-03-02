package Actions;

public class InfAction implements Action {
			private String name;
			private double rate;
			private String infset;
			private String symbol;
			private String update;
			
			public InfAction(String name, double rate, String infset, String symbol, String update){
				this.name = name;
				this.rate = rate;
				this.infset = infset;
				this.symbol = symbol;
				this.update = update;			
			}
					
@Override
public int getType() {
	return Action.ACTION_TYPE_Inf;
}

public String getName() {
	return name;
}

public Double getRate() {
	return rate;
}

public String getInfSet() {
	return infset;
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
	String ret = "->{" + infset + "}(" + name + "," + rate + ")" + symbol + update;
	return ret;
}


}





