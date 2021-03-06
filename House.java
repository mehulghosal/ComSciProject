import java.util.*;

class House {
	
    private double x;//x is the street
    private double y;//y is the avenue plus the house
    private int shortHolder = 0;

    public House(double x, double y) {
        this.x = x*200;
        this.y = y*1000;
    }
 
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double distance(House house) {
    	return Math.abs(this.x - house.x) + Math.abs(this.y - house.y);
    }
	
    public int returnShort() {
	    return shortHolder;
    }
	
    public House nearestHouse(ArrayList<House> route) {
	    double shortestDistance = 100000000;
	    int shortestNumber = 0;
	    for(int i = 0; i<route.size()-1; i++) {
		    double distance = this.distance(route.get(i));
		    if(distance<shortestDistance) {
			    shortestDistance = distance;
			    shortestNumber = i;
		    }
	    }
	    shortHolder = shortestNumber;
	    return route.get(shortestNumber);
    }

}
