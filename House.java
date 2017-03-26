import java.util.*;

class House {
    private double x;//x is the street
    private double y;//y is the avenue plus the house 

    public House(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    public House(){
        Random r = new Random();
        x = r.nextInt(1000);
        y = r.nextInt(1000);
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }

    public double calculateDistanceToHouse(House house) {
    	
        double dist = Math.abs(this.x - house.x) + Math.abs(this.y - house.y);
        return dist; 
       
    }

}