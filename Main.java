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
		
		int cycleNumber = Integer.parseInt(records.get(0));
		int numberOfInputs = Integer.parseInt(records.get(1));
		
		House[] inputs = new House[numberOfInputs];
		
		
	}

}