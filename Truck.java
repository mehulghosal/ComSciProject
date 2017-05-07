
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
	
	
	public Truck(int bartIn, int lisaIn, boolean rent) {
		bartInputs = bartIn;
		lisaInputs = lisaIn;
		rental = rent;
	}
	
	public void addHouse(House house) {
		initialRoute.add(house);	
		houses++;
		if(house.getX() == 2000.0 && house.getY() == 600.0) {
			bart = true;
		}
		if(house.getX() == 149000.0 && house.getY() == 6600.0) {
			lisa = true;
		}
	}
	
	public void calcRoute() {
		ClosestHouse.inputRoute(initialRoute);
		ClosestHouse.calcRoute();
		truckRoute = ClosestHouse.getRoute();
		distance = ClosestHouse.calcDistance();
		ClosestHouse.reset();
	}
	
	public double calcTime() {
		
		time = 0;
		time += (distance)/(100.0/3);
		time += (houses - 2) * 60;
		if(bart) {
			time+=(bartInputs*30);
		}
		if(lisa) {
			time+=(lisaInputs*30);
		}
		return time;
		
	}
	
	public double calcCost() {
		
		
		cost += (distance/1000) + ((int)(distance/5000))*10;
		
		if(rental) {
			cost+=15000;
		}
		
		for(int i = 1; i<=(time/3600); i++) {
			if(i<=8) {
				cost+=30;
			}
			else {
				cost+=45;
			}
		}
		return cost;
		
	}
	
	
}
