import java.util.*;
import java.io.*;

public class Main{

	static public void main(String[] args){
			
		//this is for the input from the file
		List<String> records = new ArrayList<String>();
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("testData.txt"));
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
		
		int numberOfInputs = Integer.parseInt(records.get(1).replaceAll("[^0-9]", "")); //this is the total number of inputs
		
		
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
		
		//heyyyy tis working
		SimulatedAnnealing.constructRoute(inputs);
		SimulatedAnnealing.setRouteDistance();
		double distOriginal = SimulatedAnnealing.getDistance();
		//System.out.println("original distance: " + distOriginal);
		double distNext;
		double distFinal = distOriginal;
		
		House[] holder = SimulatedAnnealing.getRoute();
		
		while(SimulatedAnnealing.getTemp() > 0.00000001){
			
			SimulatedAnnealing.randomChangeRoute();
			SimulatedAnnealing.setRouteDistance();
			distNext = SimulatedAnnealing.getDistance();
			
			if(distNext<distFinal){
				distFinal = distNext;
				holder = SimulatedAnnealing.getRoute();
			}
			else{
				SimulatedAnnealing.setRoute(holder);
			}
			
			/*
			if(SimulatedAnnealing.acceptanceProbability(distOriginal, distNext) > r){
				distFinal = distNext;
			}
			*/
			SimulatedAnnealing.setTemperature(SimulatedAnnealing.getTemp() * (1-0.000001));
			
		}
		System.out.println("original distance: "+ distOriginal);
		System.out.println("final distance:" + distFinal);
		
		// maybe go through it a few times to get an optimal answer
		
		/*
		distOriginal = distFinal;
		SimulatedAnnealing.setTemperature(10000);
		
		while(SimulatedAnnealing.getTemp() > 0.00001){
			
			SimulatedAnnealing.randomChangeRoute();
			SimulatedAnnealing.setRouteDistance();
			distNext = SimulatedAnnealing.getDistance();
			
			if(distNext<distFinal){
				distFinal = distNext;
				holder = SimulatedAnnealing.getRoute();
			}
			else{
				SimulatedAnnealing.setRoute(holder);
			}
			
			SimulatedAnnealing.setTemperature(SimulatedAnnealing.getTemp() * (1-0.00003));
			
		}
		System.out.println("final distance run 2:" + distFinal);
		*/
		
	}

}