import java.util.*;
import java.io.*;

public class ClosestHouse{
	
	private static House[] inputRoute;
	private static House[] outputRoute;
	
	public static void inputRoute(House[] inputs) {
		inputRoute = inputs;	
	}
	public static House[] getRoute() {
		return outputRoute;
	}
	
	public static void calcRoute() {
		outputRoute = new House[inputRoute.length+2];
		outputRoute[0] = new House(125,22);
		ArrayList<House> unused = new ArrayList<House>();
		for(int i = 0; i<inputRoute.length-1; i++) {
			unused.add(inputRoute[i]);
		}
		for(int i = 0; i<inputRoute.length-2; i++) {
			//making route with houses that haven been used yet

			outputRoute[i+1] = outputRoute[i].closestHouse(unused);
			unused.remove(outputRoute[i].returnShort());
		}
		
		outputRoute[outputRoute.length-1] = new House(125,22);		
		}
	}

	
	
	
	

		
		
		
		


}
