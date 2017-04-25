import java.util.*;

public class SimulatedAnnealing{
	
	private static double temperature = 10000; //temperature is basically a counter, but counts down -- setting an intial temperature
	private static double distance;
	private static House[] route;
	
	public static double getDistance(){
		return distance;
	}
	
	public static House[] getRoute(){
		return route;
	}
	
	public static double getTemp(){
		return temperature;
	}
	
	public static void setTemperature(double temp){
		temperature = temp;
	}
	
	public static void setRoute(House[] holderRoute){
		route = holderRoute;
	}
	
	/*
	this is for the probablitity that the new distance is accpeted
	sim annealing allows for a probability for the new distance to be accepted
	as the temperature decreases, the accpetance prob increases (because you don't want to accept stuff too early and "commit"
	 */
	public static double acceptanceProbability(double distOriginal, double distNext) {
		// If the new solution is better, accept it
		if (distNext < distOriginal) {
			return 1.0;
		}
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((distOriginal - distNext)/347.16 / temperature);
	}
	
	
	/*
	this is for the beginning, where the route is made 
	when implemented, original is the original array of inputs, and numOfHouses is the number of houses
	changed this method to use araylists, now works
	*/
	public static void constructRoute(House[] original) {
		route = new House[original.length+2]; 
		route[0] = new House(125,22); //start at distribution center
		for(int i=0; i<route.length-2; i++){
			route[i+1] = original[i];	
		}
		route[route.length-1] = new House(125,22);
		
	}
	
	//returns the total distance of the current array
	public static void setRouteDistance() {
		
		distance = 0;
		for(int i = 0; i<route.length-2; i++) {
			distance+=(route[i].distance(route[i+1]));
		}
	}
	
	public static void randomChangeRoute() {
		int number = 0; 
		while(number==0 && number==(route.length-1)) {
			number = (int)(Math.random()*(route.length-1));
		}
		int number2 = 0;
		while(number2==0 && number2==(route.length-1)) {
			number2 = (int)(Math.random()*(route.length-1));
		}
		House holder;
		holder = route[number];
		route[number]=route[number2];
		route[number2] = holder;
				
	}
	

	
}
