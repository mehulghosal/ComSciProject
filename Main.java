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
		
		
		
		//heyyyy tis working
		SimulatedAnnealing.constructRoute(inputs);
		SimulatedAnnealing.setOriginalDistance();
		double distOriginal = SimulatedAnnealing.getDistance();
		double distNext;
		double distFinal = distOriginal;
		double finalfinal = SimulatedAnnealing.getDistance();
		
		House[] holder = SimulatedAnnealing.getRoute();
		House[] bestRoute = SimulatedAnnealing.getRoute();
		
		while(SimulatedAnnealing.getTemp() > 0.001){
			
			SimulatedAnnealing.randomChangeRoute();
			SimulatedAnnealing.setRouteDistance();
			distNext = SimulatedAnnealing.getDistance();
			
			if(distNext<distFinal){
				distFinal = distNext;
				holder = SimulatedAnnealing.getRoute();
				if(distFinal<finalfinal) {
					finalfinal = distFinal;
					if(SimulatedAnnealing.getDistance()<finalfinal) {
						bestRoute = SimulatedAnnealing.getRoute();
					}
				}
			}
			
			else{
				if(SimulatedAnnealing.acceptanceProbability(distFinal, distNext) > Math.random()) {
					distFinal = distNext;
					holder = SimulatedAnnealing.getRoute();
				}
				else {
					SimulatedAnnealing.setRoute(holder);
				}
			}
			
			
			SimulatedAnnealing.setTemperature(SimulatedAnnealing.getTemp() * (1 - 0.0001));
			
			for(int i = 0; i<50; i++) {
				SimulatedAnnealing.flipGreat();
				SimulatedAnnealing.setRouteDistance();
				if(SimulatedAnnealing.getDistance()<distFinal) {
					holder = SimulatedAnnealing.getRoute();
					distFinal = SimulatedAnnealing.getDistance();
					SimulatedAnnealing.setOriginalDistance();
					if(distFinal<finalfinal) {
						finalfinal = distFinal;
					}
				}
			}
			
			System.out.println(distFinal);
			
			
			
		}
		SimulatedAnnealing.setOriginalDistance();
		System.out.println("Correct distance:  " + SimulatedAnnealing.getDistance());
		System.out.println("original distance: "+ distOriginal);
		System.out.println("final distance:" + distFinal);
		System.out.println("The best distance was " + finalfinal + " feet");
		
		Truck firstTruck = new Truck(bestRoute, finalfinal, numberOfInputs, bartInputs, lisaInputs, true, true, false);
		
		System.out.println("Time is " + firstTruck.calcTime() + " in seconds, " + (firstTruck.calcTime()/3600) + " in hours.");
		System.out.println("Cost is " + firstTruck.calcCost());
		//public Truck(House[] route, int numHouses, int bartIn, int lisaIn, boolean b, boolean l, boolean r) {
		

		
	}

}
