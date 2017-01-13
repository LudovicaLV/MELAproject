package TotalFarms;

public class Calculate {

	public static void main(String[] args) {
		
    calc(50);
	calc(25);
	calc(10);
	calc(5);
	calc(2);
		
	}

	
	public static void calc(int valueModel){
	for (int i=1; i <= valueModel; i++){	
    double distance = i * (167/100.0) * (1.0/valueModel);
	double value = 0.759 * distance;
	double result = Math.exp(-value) ;
	double b0 = 0.000206;
	double result2 = result * b0;
	System.out.println(result2);
	}
	System.out.println("---");
	}
	
	
}
