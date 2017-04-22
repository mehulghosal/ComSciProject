
public class Truck {
	
	private double time = 0; //cumulative -- add on to total and reset it when restarting
	private double x = 125 * 200; //for moving the truck, this is the street its on, starts at distribution center
	private double y = 22 * 1000; //for moving the truck, this is the ave its on, starts at distribution center
	
	public void setTime(double time){
		this.time = time;
	}
	
	public double getTime(){
		return time;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	
}
