import java.util.*;

public class Main{

	static public void main(String[] args){
		
		House hello = new House(19, 17.6);
		House hello1 = new House(14, 12.9);
		
		double a = hello.calculateDistanceToHouse(hello1);
		System.out.println(a);
		
	}

}