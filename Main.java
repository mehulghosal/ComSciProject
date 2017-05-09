import java.util.*;
import java.io.*;

public class Main{
	
	private static int cycleNumber;
	private static int trucks;
	private static double cost;
	private static double time;
	
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
			int[] aveHolder = new int[250];
			int sum = 0;
			double interval = 250.0/trucks;
			double holder = interval;
			int times = 0;
			while(sum<250) {
				while(sum<holder) {
					aveHolder[sum] = times;
					sum++;
				}
				holder+=interval;
				times++;
			}
			Truck[] trucksArr = new Truck[trucks];
			for(int i = 0; i<trucks; i++) {
				trucksArr[i] = new Truck(bartInputs, lisaInputs, true, 2);	
			}
			
			for(int i = 0; i<inputs.length; i++) {
				int toAve = (int)(((inputs[i].getX())/200)-1);
				int toTruck = aveHolder[toAve];
				trucksArr[toTruck].addHouse(inputs[i]);
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
		costArr[0] += 100000*avgTrucks;
		
		for(int i = 0; i<10; i++){
			
			truckArr[i] = truckArr[i] - avgTrucks;
			costArr[i] = costArr[i] - avgTrucks*15000;
			totalCost += costArr[i];
			totalTime += timeArr[i];
			
		}
		
		System.out.println("total cost is $" + totalCost);
		System.out.println("total time is " + totalTime);
		System.out.println("trucks bought is " + avgTrucks);
		
	}

}
