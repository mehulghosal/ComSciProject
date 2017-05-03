import java.util.*;
import java.io.*;

public class ClosestHouse{
	
	private static House[] inputRoute;
	private static House[] outputRoute;
	private static int maxHolder;
	
	public static void inputRoute(House[] inputs) {
		inputRoute = inputs;	
	}
	public static House[] getRoute() {
		return outputRoute;
	}
	public static int getMaxHolder() {
		return maxHolder;
	}
	
	public static void calcRoute() {
		System.out.println(inputRoute.length);
		outputRoute = new House[inputRoute.length+2];
		outputRoute[0] = new House(125,22);
		ArrayList<House> unused = new ArrayList<House>();
		for(int i = 0; i<inputRoute.length; i++) {
			unused.add(inputRoute[i]);
		}
		for(int i = 0; i<inputRoute.length; i++) {
			//making route with houses that haven been used yet

			outputRoute[i+1] = outputRoute[i].nearestHouse(unused);
			unused.remove(outputRoute[i].returnShort());
		}
		
		outputRoute[outputRoute.length-1] = new House(125,22);		
	}
	

	public static double calcDistance() {
		double distance = 0;
		for(int i = 0; i<outputRoute.length-2; i++) {
			distance+=outputRoute[i].distance(outputRoute[i+1]);
			System.out.println(outputRoute[i].distance(outputRoute[i+1]));
		}

		return distance;
	}
	
	public static double getMaxDistance() {
		double maxDistance = 0;
		maxHolder = 0;
		for(int i = 0; i<outputRoute.length-2; i++) {
			if(outputRoute[i].distance(outputRoute[i+1])>maxDistance) {
				maxDistance = outputRoute[i].distance(outputRoute[i+1]);
				maxHolder = i;
			}
		}
		return maxDistance;
	}
	
	public static void flipGreat() {
		double distanceHolder = outputRoute[maxHolder].distance(outputRoute[maxHolder+1]);
		//House[] routeHolder = outputRoute[];
		for(int i = 0; i<outputRoute.length-2; i++) {
			House holder = outputRoute[maxHolder];
			outputRoute[maxHolder] = outputRoute[i];
			outputRoute[i] = holder;
			ClosestHouse.calcDistance();
		}
	}

	
	
	
	

		
		
		
		


}
