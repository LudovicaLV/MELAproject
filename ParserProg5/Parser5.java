package ParserProg5;

public class Parser5 {

	public static void main(String[] args) throws Exception {
		MELAprog5 Parser= new MELAprog5();
		Parser.parseFromFile("/Users/ludovicaluisavissat/GitHub/MELAproject/try.mela");
        System.out.println("Good!");
	}
}