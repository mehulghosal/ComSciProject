import java.util.*;

public class SimulatedAnnealing{
	
	private static double temperature = 10000; //temperature is basically a counter, but counts down -- setting an intial temperature
	private static double distance = 0;
	private static House[] route;
	private static int number = 0;
	private static int number2 = 0;
	private static double greatestDistance;
	private static int greatestNumber;
	
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
		return Math.exp((distOriginal - distNext)/10 / temperature);
	}
	
	
	/*
	this is for the beginning, where the route is made 
	when implemented, original is the original array of inputs, and numOfHouses is the number of houses
	changed this method to use araylists, now works
	*/
	public static void constructRoute(House[] original) {
		
		route = original;
		
	}
	
	
		
	public static void setRouteDistance() {
		distance = 0;
		greatestDistance = 0;
		greatestNumber = 0;
		for(int i = 0; i<route.length-2; i++) {
			distance+=route[i].distance(route[i+1]);
			if(route[i].distance(route[i+1])>greatestDistance && i!=0 && i!=route.length-1) {
				greatestDistance = route[i].distance(route[i+1]);
				greatestNumber = i;
			}
		}
	}
	//returns the total distance of the current array

	
	public static void randomChangeRoute() {
		
		number = 0; 
		while(number==0 || number==(route.length-1)) {
			number = (int)(Math.random()*(route.length-1));
		}
		
		number2 = 0;
		while(number2<=0 || number2>=(route.length-1)) {
			if(Math.random()>0.1) {
				if(Math.random()>0.5) {
					number2=number+((int)(Math.random()*100));
				}
				else {
					number2=number-((int)(Math.random()*100));
				}
			}
			else {
				number2 = (int)(Math.random()*(route.length-1));
			}
		}
		
		House holder;
		holder = route[number];
		route[number]=route[number2];
		route[number2] = holder;
				
	}
	public static void flipGreat() {
		number = 0;
		while(number<=0 || number>=(route.length-1)) {
			if(Math.random()>0.2) {
				if(Math.random()>0.5) {
					number=greatestNumber+((int)(Math.random()*100));
				}
				else {
					number=greatestNumber-((int)(Math.random()*100));
				}
			}
			else {
				number2 = (int)(Math.random()*(route.length-1));
			}
		}
		number2 = greatestNumber;
		
		House holder = route[greatestNumber];
		route[greatestNumber] = route[number];
		route[number] = holder;
		
	}
	
	
	
	/*double distOriginal = SimulatedAnnealing.getDistance();
	double distNext;
	double distFinal = distOriginal;
	double finalfinal = SimulatedAnnealing.getDistance();
	
	House[] holder = SimulatedAnnealing.getRoute();
	House[] bestRoute = SimulatedAnnealing.getRoute();
	
	while(SimulatedAnnealing.getTemp() > 0.001){
		
		SimulatedAnnealing.randomChangeRoute();
		SimulatedAnnealing.setRouteDistance();
		distNext = SimulatedAnnealing.getDistance();
		
		if(distNext<distFinal){
			distFinal = distNext;
			holder = SimulatedAnnealing.getRoute();
			if(distFinal<finalfinal) {
				finalfinal = distFinal;
				if(SimulatedAnnealing.getDistance()<finalfinal) {
					bestRoute = SimulatedAnnealing.getRoute();
				}
			}
		}
		
		else{
			if(SimulatedAnnealing.acceptanceProbability(distFinal, distNext) > Math.random()) {
				distFinal = distNext;
				holder = SimulatedAnnealing.getRoute();
			}
			else {
				SimulatedAnnealing.setRoute(holder);
			}
		}
		
		
		SimulatedAnnealing.setTemperature(SimulatedAnnealing.getTemp() * (1 - 0.001));
		
		for(int i = 0; i<10; i++) {
			SimulatedAnnealing.setRouteDistance();
			SimulatedAnnealing.flipGreat();
			SimulatedAnnealing.setRouteDistance();
			if(SimulatedAnnealing.getDistance()<distFinal) {
				holder = SimulatedAnnealing.getRoute();
				distFinal = SimulatedAnnealing.getDistance();
				if(distFinal<finalfinal) {
					finalfinal = distFinal;
				}
			}
			else {
				SimulatedAnnealing.setRoute(holder);
			}
			SimulatedAnnealing.setRouteDistance();
		//	System.out.println(SimulatedAnnealing.getDistance());
		}
		
		System.out.println(distFinal);
		
		
		
	}
	
	System.out.println("original distance: "+ distOriginal);
	System.out.println("final distance:" + distFinal);
	System.out.println("The best distance was " + finalfinal + " feet");
	*/
	

	
}
