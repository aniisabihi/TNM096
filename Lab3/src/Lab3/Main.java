package Lab3;


public class Main {

    public static void main(String[] args) {
    	//String[] formulae = {"-a", "b", "-c V -b V -d", "-c V a V -d"};
    	String[] formulae = {"a V b V c", "-c V a", "-db" , "da V dc"};
    	Engine theEngine = new Engine(formulae);
    	theEngine.Solver();
    	
    	
    }
}
