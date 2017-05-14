import java.util.*;
public class Truck {
	
	private double time = 0;
	private double distance = 0;
	private double cost = 0;
	private int bartInputs;
	private int lisaInputs;
	private boolean bart = false;
	private boolean lisa = false;
	private boolean rental;
	private int houses = 2;
	private House[] truckRoute;
	private ArrayList<House> initialRoute = new ArrayList<House>();
	private int numOfWorkers;
	
	
	public Truck(int bartIn, int lisaIn, boolean rent, int work) {
		bartInputs = bartIn;
		lisaInputs = lisaIn;
		rental = rent;
		numOfWorkers = work;
	}
	
	public House[] getRoute() {
		return truckRoute;
	}
	public void setRoute(House[] theRoute) {
		truckRoute = theRoute;
	}
	
	public void addHouse(House house) {
		initialRoute.add(house);	
		houses++;
		if(house.getX() == 400.0 && house.getY() == 3000.0) {
			bart = true;
		}
		if(house.getX() == 29800.0 && house.getY() == 33000.0) {
			lisa = true;
		}
	}
	
	public void calcRoute() {
		truckRoute = initialRoute.toArray(new House[initialRoute.size()]);
		ClosestHouse.inputRoute(truckRoute);
		ClosestHouse.calcRoute();
		/*for(int i = 0; i<1000; i++) {
			ClosestHouse.flipRandom();
		}
		for(int i = 0; i<10000; i++) {
			ClosestHouse.flipGreat();
		}*/
		ClosestHouse.twoOpt();
		truckRoute = ClosestHouse.getRoute();
		distance = ClosestHouse.calcDistance();
		ClosestHouse.reset();
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double calcTime() {
		
		time = 0;
		time += (distance)/(100.0/3);
		time += (houses - 2) * (60/numOfWorkers);
		if(bart) {
			time+=(bartInputs*(30/numOfWorkers));
		}
		if(lisa) {
			time+=(lisaInputs*(30/numOfWorkers));
		}
		return time;
		
	}
	
	public double calcCost() {
		
		
		cost += (distance/5000*5)+distance/500; //still have to add the cost if owned
		
		if(rental) {
			cost+=15000;
		}
		
		for(int i = 1; i<=(time/3600); i++) {
			if(i<=8) {
				cost+=30*numOfWorkers;
			}
			else {
				cost+=45*numOfWorkers;
			}
		}
		return cost;
		
	}
	
	
}
