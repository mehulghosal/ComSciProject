import java.util.*;
import java.io.*;

public class Main{
	

	static public void main(String[] args){
		
		double moneySpent = 0;
		double time = 0;//in seconds
		
		//this is for the input from the file
		List<String> records = new ArrayList<String>();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("cycle.txt"));
			String line;
			while ((line = reader.readLine()) != null){
				records.add(line);
			}
			reader.close();
		}
		catch (Exception e){
			System.err.format("Exception occurred trying to read '%s'.", "cycle.txt");
			e.printStackTrace();
		}
		
		int cycleNumber = Integer.parseInt(records.get(0)); //this is the first input -- the cycle number
		
		int numberOfInputs = Integer.parseInt(records.get(1).replaceAll("[^0-9]", "")) + 2; //this is the total number of inputs
		
		
		//this is all to set up the array of houses that need to be visited
		House[] inputs = new House[numberOfInputs];
		String[] holderArray;
		int holderStreet;
		int holderAve;
		double houseAddress = 0;
		
		
		
		for(int i = 0; i < records.size() - 6; i++){
			
			holderArray = records.get(i+2).split(",");//splits every line up by commas
			holderStreet = Integer.parseInt(holderArray[0].replaceAll("[^0-9]", ""));
			holderAve = Integer.parseInt(holderArray[1].replaceAll("[^0-9]", ""));
			if(holderArray[2].equals("A") || holderArray[2].equals("AA")) {
				houseAddress = 0;
			}
			else if(holderArray[2].equals("B") || holderArray[2].equals("BB")) {
				houseAddress = .1;
			}
			else if(holderArray[2].equals("C") || holderArray[2].equals("CC")) {
				houseAddress = .2;
			}
			else if(holderArray[2].equals("D") || holderArray[2].equals("DD")) {
				houseAddress = .3;
			}
			else if(holderArray[2].equals("E") || holderArray[2].equals("EE")) {
				houseAddress = .4;
			}
			else if(holderArray[2].equals("F") || holderArray[2].equals("FF")) {
				houseAddress = .5;
			}
			else if(holderArray[2].equals("G") || holderArray[2].equals("GG")) {
				houseAddress = .6;
			}
			else if(holderArray[2].equals("H") || holderArray[2].equals("HH")) {
				houseAddress = .7;
			}
			else if(holderArray[2].equals("I") || holderArray[2].equals("II")) {
				houseAddress = .8;
			}
			else if(holderArray[2].equals("J") || holderArray[2].equals("JJ")) {
				houseAddress = .9;
			}			
						
			inputs[i] = new House(holderStreet, holderAve + houseAddress);
			
		}
		
		//Bart and lisa
		inputs[inputs.length - 2] = new House(2, 3);//bart
		inputs[inputs.length -1] = new House(149, 33);//lisa
		
		int bartInputs = Integer.parseInt(records.get(records.size() - 3));
		int lisaInputs = Integer.parseInt(records.get(records.size() - 1));
		
		int amtTrucks = 0;
		double maxTime = 100000;
		double totalCost = 0;
		//while(maxTime/3600>24) {
		for(int x = 0; x<10; x++) {
			amtTrucks++;
			maxTime = 0;
			totalCost = 0;
			int[] aveHolder = new int[50];
			int sum = 0;
			double interval = 50.0/amtTrucks;
			double holder = interval;
			int times = 0;
			while(sum<50) {
				while(sum<holder) {
					aveHolder[sum] = times;
					sum++;
				}
				holder+=interval;
				times++;
			}
			Truck[] trucks = new Truck[amtTrucks];
			for(int i = 0; i<amtTrucks; i++) {
				trucks[i] = new Truck(bartInputs, lisaInputs, false);	
			}
			
			for(int i = 0; i<inputs.length; i++) {
				int toAve = (int)(((inputs[i].getY())/1000)-1);
				int toTruck = aveHolder[toAve];
				trucks[toTruck].addHouse(inputs[i]);
			}
			for(int i = 0; i<amtTrucks; i++) {
				trucks[i].calcRoute();
				if(trucks[i].calcTime()>maxTime) {
					maxTime = trucks[i].calcTime();	
				}
				totalCost+=trucks[i].calcCost();
			}
			System.out.println(maxTime/3600);
		}
		
		System.out.println("Cost is " + totalCost);
		System.out.println("Time in hours is " + maxTime/3600);
		System.out.println("Needed " + amtTrucks + " trucks.");

		
		/*
		ClosestHouse.inputRoute(inputs);
		ClosestHouse.calcRoute();
		House[] optimalRoute = ClosestHouse.getRoute();
		double finalfinal = ClosestHouse.calcDistance();
		Truck firstTruck = new Truck(optimalRoute, finalfinal, numberOfInputs, bartInputs, lisaInputs, true, true, true);		
*/
		
		/*this is for splitting the array
		we're going to spilt the array into quadrants by x and y 
		first put everything into an array list
		and then transfer into an array for all of the computations
		*/
		
		
		/*
		List<House> firstList = null;		House[] firstArr = null;	boolean firstBart = false; 		boolean firstLisa = false;
		List<House> secondList = null;		House[] secondArr = null;	boolean secondBart = false; 	boolean secondLisa = false;
		List<House> thirdList = null;		House[] thirdArr = null;	boolean thirdBart = false; 		boolean thirdLisa = false;
		List<House> fourthList = null;		House[] fourthArr = null;	boolean fourthBart = false; 	boolean fourthLisa = false;
		List<House> fifthList = null;		House[] fifthArr = null;	boolean fifthBart = false; 		boolean fifthLisa = false;
		List<House> sixthList = null;		House[]	sixthArr = null;	boolean sixthBart = false; 		boolean sixthLisa = false;
		List<House> seventhList = null;		House[] seventhArr = null;	boolean seventhBart = false; 	boolean seventhLisa = false;
		List<House> eightList = null;		House[] eightArr = null;	boolean eighthBart = false; 	boolean eighthLisa = false;
		
		double totalTime = 0; //sec
		int amtTrucks = 1;
		while(totalTime/3600 > 24) {
			Truck[] trucks = new Truck[amtTrucks];
			int houseNum = numberOfInputs+2;
			for(int i = 0; i<amtTrucks; i++) {
				if(amtTrucks == 1) {
					firstList.add(optimalRoute[i]);
				}
				if(amtTrucks == 2) {
					if(i<=houseNum/2) {
						firstList.add(optimalRoute[i]);
					}
					else {
						secondList.add(optimalRoute[i]);
					}
				}
				if(amtTrucks == 3) {
					
				}
				if(amtTrucks == 4) {
					
				}
				if(amtTrucks == 5) {
					
				}
				if(amtTrucks == 6) {
					
				}
				if(amtTrucks == 7) {
					
				}
				if(amtTrucks == 8) {
					
				}
				
				
			}
			
			
			
		}*/
		
		
		
		
		
		
		
		/*
		System.out.println(finalfinal);
		System.out.println("Time is " + firstTruck.calcTime() + " in seconds, " + (firstTruck.calcTime()/3600) + " in hours.");
		System.out.println("Cost is " + firstTruck.calcCost());
		*/

		//public Truck(House[] route, int numHouses, int bartIn, int lisaIn, boolean b, boolean l, boolean r) {
		

		
	}

}
