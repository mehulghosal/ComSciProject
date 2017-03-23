import java.math.*;
import java.util.*;

class House {
    private double x;
    private double y;

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
        return dist; //round(dist, 2);
        
    }
    
    private static double round(double value, int places) {
    	
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}