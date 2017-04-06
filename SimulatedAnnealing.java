import java.util.*;

public class SimulatedAnnealing{
	
	private double temperature = 10000; //temperature is basically a counter, but counts down -- setting an intial temperature
	private double coolingRate = 0.003; //this is the cooling rate -- after every run through, multiply temperature by (1-coolingrate)
	private static double distance = 0;
	private static House[] route;
	
	
	//this is for the probablitity that the new distance is accpeted
	//sim annealing allows for a probability for the new distance to be accepted
	//as the temperature decreases, the accpetance prob increases (because you don't want to accept stuff too early and "commit"
	public static double acceptanceProbability(int currentDistance, int newDistance, double temperature) {
		// If the new solution is better, accept it
		if (newDistance < currentDistance) {
			return 1.0;
		}
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((currentDistance - newDistance) / temperature);
	}
	
	//this is for the beginning, where the route is made randomly
	//when implemented, original is the original array of inputs, and numOfHouses is the number of houses
	public static void constructRoute(int numOfHouses, House[] original) {
		route = new House[numOfHouses]; 
		int[] randomChecker = new int[numOfHouses];
		
		for(int i=0; i<numOfHouses; i++){
			int random = (int) (Math.random()*(numOfHouses));
			randomChecker[i] = random;
			for(int j=0; j<numOfHouses; j++){
				if (random == randomChecker[j]){
					i--;
					continue;
				}
			}
			
			route[i] = original[random];
		}
		
	}
	
	//returns the total distance of the current array
	public static void setRouteDistance( int numHouses) {
		
		distance = 0;
		for(int i = 0; i<numHouses-1; i++) {
			distance+=(route[i].distance(route[i+1]));
		}
	}
	
	public static void changeRoute() {
		
	}
	
	
	
}