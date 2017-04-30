import java.util.*;

public class SimulatedAnnealing{
	
	private static double temperature = 10000; //temperature is basically a counter, but counts down -- setting an intial temperature
	private static double distance = 0;
	private static House[] route;
	private static int number = 0;
	private static int number2 = 0;
	private static double greatestDistance;
	private static int greatestNumber;
	private static double distanceHolder = 0;
	private static double distancePreHolder = 0;
	
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
		return Math.exp((distOriginal - distNext)/346 / temperature);
	}
	
	
	/*
	this is for the beginning, where the route is made 
	when implemented, original is the original array of inputs, and numOfHouses is the number of houses
	changed this method to use araylists, now works
	*/
	public static void constructRoute(House[] original) {
		
		route = new House[original.length+2]; 
		route[0] = new House(125,22);
		ArrayList<Integer> randomChecker = new ArrayList<Integer>();
		for(int i = 0; i<original.length; i++) {
			int x = (int)(Math.random()*(original.length));
			if(randomChecker.contains(x)) {
				i--;
				continue;
			}
				
			randomChecker.add(x);
			route[i+1] = original[x];
		}

		route[route.length-1] = new House(125,22);
		
	}
	
	
		
	public static void setOriginalDistance() {
		distance = 0;
		greatestDistance = 0;
		for(int i = 0; i<route.length-2; i++) {
			distance+=route[i].distance(route[i+1]);
			if(route[i].distance(route[i+1])>greatestDistance) {
				greatestDistance = route[i].distance(route[i+1]);
				greatestNumber = i;
			}
		}
	}
	//returns the total distance of the current array
	public static void setRouteDistance() {
		
		distance-=distancePreHolder;
		distance+=distanceHolder;
		double a = route[number-1].distance(route[number]);
		double b = route[number].distance(route[number+1]);
		double c = route[number2 -1].distance(route[number2]);
		double d = route[number2].distance(route[number2 +1]);
		if(a>greatestDistance) {
			greatestDistance = a;
			greatestNumber = number-1;
		}
		if(b>greatestDistance) {
			greatestDistance = b;
			greatestNumber = number;
		}
		if(c>greatestDistance) {
			greatestDistance = c;
			greatestNumber = number2 -1;
		}
		if(d>greatestDistance) {
			greatestDistance = d;
			greatestNumber = number2;
		}
		
		
	}
	
	public static void randomChangeRoute() {
		
		number = 0; 
		while(number==0 || number==(route.length-1)) {
			number = (int)(Math.random()*(route.length-1));
		}
		
		number2 = 0;
		while(number2==0 || number2==(route.length-1)) {
			number2 = (int)(Math.random()*(route.length-1));
		}
		
		distancePreHolder = route[number-1].distance(route[number])+route[number].distance(route[number+1]);
		distancePreHolder += route[number2-1].distance(route[number2])+route[number2].distance(route[number2+1]);
		
		House holder;
		holder = route[number];
		route[number]=route[number2];
		route[number2] = holder;
		distanceHolder = route[number-1].distance(route[number])+route[number].distance(route[number+1]);
		distanceHolder += route[number2-1].distance(route[number2])+route[number2].distance(route[number2+1]);

				
	}
	public static void flipGreat() {
		number = 0;
		while(number==0 || number==(route.length-1)) {
			number = (int)(Math.random()*(route.length-1));
		}
		number2 = greatestNumber;

		distancePreHolder = route[number-1].distance(route[number])+route[number].distance(route[number+1]);
		distancePreHolder += route[greatestNumber-1].distance(route[greatestNumber])+route[greatestNumber+1].distance(route[greatestNumber+1]);
		
		House holder = route[greatestNumber];
		route[greatestNumber] = route[number];
		route[number] = holder;
		
		distanceHolder = route[greatestNumber-1].distance(route[greatestNumber])+route[greatestNumber].distance(route[greatestNumber+1]);
		distanceHolder += route[number-1].distance(route[number])+route[number].distance(route[number+1]);
	}
	

	
}
