public class House{

	private String name;
	private double yPos; 
	private double street;
	
	public House(String name, double street){
		this.name = name;
		if(name.equals("A") || name.equals("AA")){
			yPos = .1;
		}
		else if(name.equals("B") || name.equals("BB")){
			yPos = .2;
		}
		else if(name.equals("C") || name.equals("CC")){
			yPos = .3;
		}
		else if(name.equals("D") || name.equals("DD")){
			yPos = .4;
		}
		else if(name.equals("E") || name.equals("EE")){
			yPos = .5;
		}
		else if(name.equals("F") || name.equals("FF")){
			yPos = .6;
		}
		else if(name.equals("G") || name.equals("GG")){
			yPos = .7;
		}
		else if(name.equals("H") || name.equals("HH")){
			yPos = .8;
		}
		else if(name.equals("I") || name.equals("II")){
			yPos = .9;
		}
		else if(name.equals("J") || name.equals("JJ")){
			yPos = 1.0;
		}
		
		this.street = street;
	}
	
	public double getStreet(){
		return street;
	}
	
	public double getYPos(){
		return yPos;
	}
	
	public String getName(){
		return name;
	}


}