package Model;


public class Try {

	public static void main(String[] args) {
		Location.createAllLocThreeD(4,4,5);
		
        for (int i = 0; i < LocationManager.AllLoc.size(); i++){
        	for (int j = 0; j < LocationManager.AllLoc.get(i).size(); j++){
		System.out.println(LocationManager.AllLoc.get(i).get(j));
        }
	}

}}
