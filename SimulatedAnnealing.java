import java.util.*;

public class SimulatedAnnealing{
	
	private static double temperature = 10000; //temperature is basically a counter, but counts down -- setting an intial temperature
	private static double coolingRate = 0.003; //this is the cooling rate -- after every run through, multiply temperature by (1-coolingrate)
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
	
	public static double getCoolingRate(){
		return coolingRate;
	}
	
	public static void setTemperature(double temp){
		temperature = temp;
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
		return Math.exp((distOriginal - distNext) / temperature);
	}
	
	
	/*
	this is for the beginning, where the route is made randomly
	when implemented, original is the original array of inputs, and numOfHouses is the number of houses
	changed this method to use araylists, now works
	*/
	public static void constructRoute(House[] original) {
		route = new House[original.length]; 
		ArrayList<Integer> randomChecker = new ArrayList<Integer>(original.length);
		
		for(int i=0; i<original.length-1; i++){
			int random = (int) (Math.random()*(original.length));
			if(randomChecker.contains(random)){
				i--;
				continue;
			}	
			randomChecker.add(random);
			route[i] = original[random];
			
			
		}
		
	}
	
	//returns the total distance of the current array
	public static void setRouteDistance() {
		
		distance = 0;
		for(int i = 0; i<route.length-2; i++) {
			distance+=(route[i].distance(route[i+1]));
		}
	}
	
	public static void randomChangeRoute() {
		int number = (int)(Math.random()*(route.length-1));
		int number2 = (int)(Math.random()*(route.length-1));
		while(true) {
			if(number!=number2) {
				House holder;
				holder = route[number];
				route[number]=route[number2];
				route[number2] = holder;//what is this supposed to be?
				break;
			}
			else {
				number2 = (int)(Math.random()*(route.length-1));
			}
		}
				
	}
	
	
	
}