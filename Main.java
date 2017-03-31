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
			switch (holderArray[2]) {
			case("A" || "AA") {
				houseAddress = 0;
			}
			case("B" || "BB") {
				houseAddress = .1;
			}
			case("C" || "CC") {
				houseAddress = .2;
			}
			case("D" || "DD") {
				houseAddress = .3;
			}
			case("E" || "EE") {
				houseAddress = .4;
			}
			case("F" || "FF") {
				houseAddress = .5;
			}
			case("G" || "GG") {
				houseAddress = .6;
			}
			case("H" || "HH") {
				houseAddress = .7;
			}
			case("I" || "II") {
				houseAddress = .8;
			}
			case("J" || "JJ") {
				houseAddress = .9;
			}
			}
						
			inputs[i] = new House(holderStreet, holderAve + houseAddress);
			
		}
		
		
		
		
	}

}