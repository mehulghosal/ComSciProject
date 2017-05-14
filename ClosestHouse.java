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
			if(holdDist>greatestDistance && i!=0 && i!=outputRoute.length-1) {
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
		int number = 0;
		
		while(number==0 || number==outputRoute.length-1) {
			
			number = (int)(Math.random()*(outputRoute.length-1));
			
		}
		int number2 = 0;
		
		while(number2==0 || number2==outputRoute.length-1 || number2==number+1 || number2==number-1) {
		
			number2 = (int)(Math.random()*(outputRoute.length-1));
		
		}
		
		double distanceA = 0;
		double distanceB = 0;
		
		distanceA+=outputRoute[number-1].distance(outputRoute[number]) + outputRoute[number].distance(outputRoute[number+1]);
		distanceA+=outputRoute[number2-1].distance(outputRoute[number2]) + outputRoute[number2].distance(outputRoute[number2+1]);
	
		distanceB+=outputRoute[number2-1].distance(outputRoute[number])+outputRoute[number].distance(outputRoute[number2+1]);
		distanceB+=outputRoute[number-1].distance(outputRoute[number2])+outputRoute[number2].distance(outputRoute[number+1]);
		
		if(distanceB<distanceA) {
			House holder = outputRoute[number];
			outputRoute[number] = outputRoute[number2];
			outputRoute[number2] = holder;
		}	
		
	}
	
	public static void flipGreat() {
		ClosestHouse.calcDistance();
		House[] holderRoute = outputRoute.clone();
		double optDistance = routeDistance;
		int number = greatestHolder;
		
		int number2 = 0;
		
		while(number2==0 || number2==outputRoute.length-1 || number2==number+1 || number2==number-1) {
		
			number2 = (int)(Math.random()*(outputRoute.length-1));
		
		}
		double distanceA = 0;
		double distanceB = 0;
		
		distanceA+=outputRoute[number-1].distance(outputRoute[number]) + outputRoute[number].distance(outputRoute[number+1]);
		distanceA+=outputRoute[number2-1].distance(outputRoute[number2]) + outputRoute[number2].distance(outputRoute[number2+1]);
	
		distanceB+=outputRoute[number2-1].distance(outputRoute[number])+outputRoute[number].distance(outputRoute[number2+1]);
		distanceB+=outputRoute[number-1].distance(outputRoute[number2])+outputRoute[number2].distance(outputRoute[number+1]);
		
		if(distanceB<distanceA) {
			House holder = outputRoute[number];
			outputRoute[number] = outputRoute[number2];
			outputRoute[number2] = holder;
		}	
	}
	
	public static void twoOpt() {
		ClosestHouse.calcDistance();
		boolean go = true;
		while(go) {
			go = false;
			for(int i = 1; i<outputRoute.length-1; i++) {
				for(int j = i+2; j<outputRoute.length-2-i; j++) {								
					
					double distanceA = 0;
					double distanceB = 0;
					
					distanceA+=outputRoute[i-1].distance(outputRoute[i]) + outputRoute[i].distance(outputRoute[i+1]);
					distanceA+=outputRoute[j-1].distance(outputRoute[j]) + outputRoute[j].distance(outputRoute[j+1]);
					
					distanceB+=outputRoute[i-1].distance(outputRoute[j])+outputRoute[j].distance(outputRoute[i+1]);
					distanceB+=outputRoute[j-1].distance(outputRoute[i])+outputRoute[i].distance(outputRoute[j+1]);
					
					if(distanceB<distanceA) {
						House holder = outputRoute[i];	
						outputRoute[i] = outputRoute[j];
						outputRoute[j] = holder;
						go = true;
					}
					
					
					
					
				}
				
			}
		}
		ClosestHouse.calcDistance();
	}
	


	
	
	
	

		
		
		
		


}
