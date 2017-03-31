import java.util.*;

public class SimulatedAnnealing{
	
	private double temperature = 10000; //temperature is basically a counter, but counts down -- setting an intial temperature
	private double coolingRate = 0.003; //this is the cooling rate -- after every run through, multiply temperature by (1-coolingrate)
	
	//this is for the 
	public static double acceptanceProbability(int currentDistance, int newDistance, double temperature) {
		// If the new solution is better, accept it
		if (newDistance < currentDistance) {
			return 1.0;
		}
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((currentDistance - newDistance) / temperature);
	}
	
	
	
}