import java.util.*;
import java.io.*;

public class ClosestHouse{
	
	private static House[] inputRoute;
	private static House[] outputRoute;
	private static int greatestHolder;
	private static double greatestDistance;
	private static double routeDistance;
	
	public static void inputRoute(House[] inputs) {
		inputRoute = inputs;	
	}
	
	public static House[] getRoute() {
		return outputRoute;
	}
	public static void setRoute(House[] houses) {
		outputRoute = houses.clone();
	}
	
	public static void calcRoute() {
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
		greatestDistance = 0;
		greatestHolder = 0;
	
		for(int i = 0; i<outputRoute.length-2; i++) {
			double holdDist = outputRoute[i].distance(outputRoute[i+1]);
			distance+=holdDist;
			if(holdDist>greatestDistance) {
				greatestDistance = holdDist;
				greatestHolder = i;
			}
		}
		
		routeDistance = distance;
		return distance;
	}
	
	
	public static void reset() {
		inputRoute = null;
		outputRoute = null;
		greatestHolder = 0;
		greatestDistance = 0;
		routeDistance = 0;
	}
	
	public static void flipRandom() {
		ClosestHouse.calcDistance();
		System.out.println(routeDistance);
		House[] holderRoute = outputRoute;
		double optDistance = routeDistance;
		int number = 0;
		
		while(number==0 || number==outputRoute.length-1) {
			
			number = (int)(Math.random()*(outputRoute.length-1));
			
		}
		
		int number2 = 0;
		
		while(number2==0 || number2==outputRoute.length-1) {
		
			number2 = (int)(Math.random()*(outputRoute.length-1));
		
		}
		House holder = outputRoute[number];
		outputRoute[number] = outputRoute[number2];
		outputRoute[number2] = holder;
		ClosestHouse.calcDistance();
		
		if(routeDistance>optDistance) {
		
			ClosestHouse.setRoute(holderRoute);
			ClosestHouse.calcDistance();
		
		}
	}
	


	
	
	
	

		
		
		
		


}
