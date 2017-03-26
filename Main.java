import java.util.*;
import java.io.*;

public class Main{

	static public void main(String[] args){
		
		//this is for the input from the file
		List<String> records = new ArrayList<String>();
		
		try
		{
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
		//System.out.println(cycleNumber + " " + records.get(1));
		
		//this is giving us an error because it is not a striaght number, it has a comma(not 2350, it is 2,350)
		int numberOfInputs = Integer.parseInt(records.get(1).replaceAll("[^0-9]", "")); //this is the total number of inputs
		
		//this is all to set up the array of houses that need to be visited
		House[] inputs = new House[numberOfInputs];
		String[] holderArray;
		int holderStreet;
		int holderAve;
		double houseAddress = 0;
		
		for(int i = 0; i < numberOfInputs; i++){ // starts at 2 bc the first(0 and 1) are not inputs
			
			holderArray = records.get(i+2).split(",");//splits every line up by commas
			//how do i split up the numbers and characters -- ascii values? And the numbers can be 1-250
			
			holderStreet = Integer.parseInt(holderArray[0].replaceAll("[^0-9]", ""));
			holderAve = Integer.parseInt(holderArray[1].replaceAll("[^0-9]", ""));
			
			if(holderArray[2].equals("A") || holderArray[2].equals("AA")){
				houseAddress = 0;
			}
			else if(holderArray[2].equals("B") || holderArray[2].equals("BB")){
				houseAddress = .1;
			}
			else if(holderArray[2].equals("C") || holderArray[2].equals("CC")){
				houseAddress = .2;
			}
			else if(holderArray[2].equals("D") || holderArray[2].equals("DD")){
				houseAddress = .2;
			}
			else if(holderArray[2].equals("E") || holderArray[2].equals("EE")){
				houseAddress = .4;
			}
			else if(holderArray[2].equals("F") || holderArray[2].equals("FF")){
				houseAddress = .5;
			}
			else if(holderArray[2].equals("G") || holderArray[2].equals("GG")){
				houseAddress = .6;
			}
			else if(holderArray[2].equals("H") || holderArray[2].equals("HH")){
				houseAddress = .7;
			}
			else if(holderArray[2].equals("I") || holderArray[2].equals("II")){
				houseAddress = .8;
			}
			else if(holderArray[2].equals("JJ") || holderArray[2].equals("JJ")){
				houseAddress = .9;
			}
			
			inputs[i] = new House(holderStreet, holderAve + houseAddress);
		}
		
	}

}