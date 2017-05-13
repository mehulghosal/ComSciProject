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
		ClosestHouse.calcDistance();
		House[] holderRoute = outputRoute.clone();
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
	
	public static void flipGreat() {
		ClosestHouse.calcDistance();
		House[] holderRoute = outputRoute.clone();
		double optDistance = routeDistance;
		int number = greatestHolder;
		
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
	
	public static void twoOpt() {
		ClosestHouse.calcDistance();
		for(int i = 0; i<outputRoute.length-1; i++) {
			for(int j = i+1; j<outputRoute.length-2-i; j++) {
				House[] holderRoute = outputRoute.clone();
				
				double distanceHolder = 0;
				
				if(i!=0) {
					distanceHolder+= outputRoute[i-1].distance(outputRoute[i]);
				}
				
				if(i!=outputRoute.length-1) {
					distanceHolder+=outputRoute[i].distance(outputRoute[i+1]);
				}
				
				if(j!=0) {
					distanceHolder+= outputRoute[j-1].distance(outputRoute[j]);
				}
				
				if(j!=outputRoute.length-1) {
					distanceHolder+=outputRoute[j].distance(outputRoute[j+1]);
				}
				
				House holder = outputRoute[i];
				outputRoute[i] = outputRoute[j];
				outputRoute[j] = holder;
				
				double newDistance = 0;
				
				if(i!= 0) {
					newDistance+=outputRoute[i-1].distance(outputRoute[i]);
				}
				if(i!=outputRoute.length-1) {
					newDistance+=outputRoute[i].distance(outputRoute[i+1]);
				}
				if(j!=0){
					newDistance+= outputRoute[j-1].distance(outputRoute[j]);
				}
				if(j!=outputRoute.length-1) {
					newDistance+=outputRoute[j].distance(outputRoute[j+1]);
				}
				
				if(distanceHolder>newDistance) {
					ClosestHouse.setRoute(holderRoute);
				}
			
			}
			System.out.println(i);
		}
		System.out.println("ok");
		ClosestHouse.calcDistance();
	}
	


	
	
	
	

		
		
		
		


}
