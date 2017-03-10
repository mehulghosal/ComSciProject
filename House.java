public class House{

	private String name;
	private double yPos; 
	private double avenue;
	
	public House(String name, double avenue){
		this.name = name;
		
		if(name.equals("A") || name.equals("AA")){
			yPos = avenue + .1;
		}
		else if(name.equals("B") || name.equals("BB")){
			yPos = avenue + .2;
		}
		else if(name.equals("C") || name.equals("CC")){
			yPos = avenue + .3;
		}
		else if(name.equals("D") || name.equals("DD")){
			yPos = avenue + .4;
		}
		else if(name.equals("E") || name.equals("EE")){
			yPos = avenue + .5;
		}
		else if(name.equals("F") || name.equals("FF")){
			yPos = avenue + .6;
		}
		else if(name.equals("G") || name.equals("GG")){
			yPos = avenue + .7;
		}
		else if(name.equals("H") || name.equals("HH")){
			yPos = avenue + .8;
		}
		else if(name.equals("I") || name.equals("II")){
			yPos = avenue + .9;
		}
		else if(name.equals("J") || name.equals("JJ")){
			yPos = avenue + 1.0;
		}
		
		this.avenue = avenue;
	}
	
	public double getAvenue(){
		return avenue;
	}
	
	public double getYPos(){
		return yPos;
	}
	
	public String getName(){
		return name;
	}


}