import java.math.*;
import java.util.*;

class House {
    private double x;
    private double y;

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public House(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public House(){
        Random r = new Random();
        x = r.nextInt(1000);
        y = r.nextInt(650);
    }

    public double calculateDistanceToPoint(House p) {
        double dist = Math.sqrt(Math.pow(this.x-p.x, 2) + Math.pow(this.y-p.y, 2));
        return round(dist,2);
    }

    
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}