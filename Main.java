import java.util.*;
import java.io.*;

public class Main{
	
	private static int cycleNumber;
	private static int trucks;
	private static double cost;
	private static double time;
	private static double distance;
	
	public static void algorithm(List<String> records){
		

		cycleNumber = Integer.parseInt(records.get(0)); //this is the first input -- the cycle number
		
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
		
		trucks = 0;
		double maxTime = 100000;
		double totalCost = 0;
		
		while(maxTime/3600>24) {
			trucks++;
			maxTime = 0;
			totalCost = 0;
			distance = 0;
			Truck[] trucksArr = new Truck[trucks];

			if(cycleNumber!=6) {	
				for(int i = 0; i<trucks; i++) {
					trucksArr[i] = new Truck(bartInputs, lisaInputs, true, 5);	
				}
			}
			else {
				for(int i = 0; i<trucks; i++) {
					trucksArr[i] = new Truck(bartInputs, lisaInputs, true, 7);	
				}
			}

			if(trucks==1) {
				for(int i = 0; i<inputs.length; i++) {
					trucksArr[0].addHouse(inputs[i]);
				}
			}
			if(trucks==2) {
				for(int i = 0; i<inputs.length; i++) {
					double toAve = ((inputs[i].getY()/1000)-1);
					if(toAve>25) {
						trucksArr[0].addHouse(inputs[i]);
					}
					else {
						trucksArr[1].addHouse(inputs[i]);
					}
				}
			}
			if(trucks==3) {
				for(int i = 0; i<inputs.length; i++) {
					double toAve = ((inputs[i].getY()/1000)-1);
					if(toAve<16) {
						trucksArr[0].addHouse(inputs[i]);
					}
					else if(toAve>=16 && toAve<33) {
						trucksArr[1].addHouse(inputs[i]);
					}
					else {
						trucksArr[2].addHouse(inputs[i]);
					}
				}
			}

			for(int i = 0; i< trucks ; i++) {
				trucksArr[i].calcRoute();
				if(trucksArr[i].calcTime()>maxTime) {
					maxTime = trucksArr[i].calcTime();	
				}
				totalCost+=trucksArr[i].calcCost();
			}
		}
		
		cost = totalCost;
		time = maxTime/3600;
		
		
	}
	

	static public void main(String[] args){
		
		int totalTrucks = 0;
		double totalTime = 0;
		double totalCost = 0;
		int[] truckArr = new int[10];
		double[] timeArr = new double[10];
		double[] costArr = new double[10];
		String cycle;
		
		
		for (int i = 0; i < 10; i++) {
			
			cycle = "cycle" + (i+1) + ".txt";
					
			//this is for the input from the file
			List<String> records = new ArrayList<String>();
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(cycle));
				String line;
				while ((line = reader.readLine()) != null) {
					records.add(line);
				}
				reader.close();
			} 
			
			catch (Exception e) {
				System.err.format("Exception occurred trying to read '%s'.", "cycle.txt");
				e.printStackTrace();
			}
			
			algorithm(records);
			totalTrucks += trucks;
			timeArr[i] = time;
			costArr[i] = cost;
			truckArr[i] = trucks;
			records.clear();			
		}
		
		int avgTrucks = (int) totalTrucks/10;
		
		for(int i = 0; i<10; i++){
			truckArr[i] = truckArr[i] - avgTrucks; //basically becomes rented per day
			costArr[i] = costArr[i] - avgTrucks*15000;
			totalCost += costArr[i];
			totalTime += timeArr[i];
			
		}
		
		totalCost+= 100000*avgTrucks;
		
		while(true){
			
			System.out.println("What would you like to view?");
			System.out.println("Time(1)"); System.out.println("Cost(2)"); System.out.println("Number of Trucks(3)"); System.out.println("Quit(0)");
			
			Scanner s = new Scanner(System.in);
			String in = s.nextLine();
			
			if(in.equals("1")){
				System.out.println("Total time: " + Math.round(totalTime*100)/100.0 + " hours");
				for(int i = 0; i<10; i++){
					System.out.println("Time is: " + Math.round(timeArr[i]*100)/100.0 + " in hours for cycle " + (i+1));
				}
				
			}
			else if(in.equals("2")){
				System.out.println("Total cost: $" + Math.round(totalCost*100)/100.0);
				System.out.println("Cost for buying trucks is $" + (avgTrucks*100000));
				for(int i = 0; i<10; i++){
					System.out.println("Cost is: $" + Math.round(costArr[i]*100)/100.0 + " for cycle " + (i+1));
				}
			}
			else if(in.equals("3")){
				System.out.println("Total number of trucks: " + totalTrucks + ". The number of trucks bought was " + avgTrucks);
				for(int i = 0; i<10; i++){
					System.out.println("Number of trucks rented is: " + truckArr[i] + " for cycle " + (i+1));
				}
			}
			else if(in.equals("0")){
				break;
			}
			
		}
		
	}

}
