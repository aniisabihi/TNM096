package Lab1;

public class main {
	
	public static void main(String[] args) {
	
	int[] start = {6, 4, 7, 8, 5, 0, 3, 2, 1};
	
	long timeStart = System.currentTimeMillis(); 
	AStar.search(start);  
	long timeEnd = System.currentTimeMillis();
	
	long duration = (timeEnd - timeStart); 
	
	System.out.print("Runtime: " + duration + " milliseconds.");
	
	}
}
