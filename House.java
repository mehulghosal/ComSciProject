import java.util.*;

class House {
	
    private double x;//x is the street
    private double y;//y is the avenue plus the house 

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

}