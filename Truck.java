
public class Truck {
	
	private double time = 0;
	private double distance = 0;
	private double cost = 0;
	private House[] truckRoute;
	private int bartInputs;
	private int lisaInputs;
	private boolean bart;
	private boolean lisa;
	private boolean rental;
	private int houses;
	
	public Truck(House[] route, double dist, int numHouses, int bartIn, int lisaIn, boolean b, boolean l, boolean r) {
		
		truckRoute = route;
		bartInputs = bartIn;
		lisaInputs = lisaIn;
		bart = b;
		lisa = l;
		rental = r;
		distance = dist;
		
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
