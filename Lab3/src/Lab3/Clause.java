package Lab3;
import java.util.Vector;

public class Clause {
	public Vector<String> negVec = new Vector<String>();
	public Vector<String> posVec = new Vector<String>();
	
	Clause(){}
	
	Clause(String theString){
		theString = theString.replaceAll(" ", "");
		theString = theString.replaceAll("V", " ");
		
		String[] splittedString = theString.split(" ");
		
		for (int i = 0; i < splittedString.length; i++)
		{
			if(splittedString[i].contains("-"))
			{
				splittedString[i] = splittedString[i].replace("-", "");
				negVec.addElement(splittedString[i]);
			}else {
				posVec.addElement(splittedString[i]);
			}
		}
	}
	
	
	public void simplify() {
		int i=0;
		int j=0;
		
		//Compare negVec with posVec
		while(i<negVec.size())
		{
			while(j<posVec.size()) {
				if(negVec.get(i).equals(posVec.get(j)))
				{
					negVec.removeElementAt(i);
                    posVec.removeElementAt(j);
				}
			j++;
			}
		i++;	
		}
		
		
		i=0;
    	j=0;
    	
    	// Compare posVec with negVec
    	while(i<posVec.size()){
    		while(j<negVec.size()){
    			
    			if(posVec.get(i).equals(negVec.get(j))){
					//Remove from both negVec and posVec
                    posVec.removeElementAt(i);
                    negVec.removeElementAt(j);
                    
				}
    			j++;
    		}
    		i++;
    	}
	}
	
	public boolean isSubset(Clause c)
	{
		for (int i = 0; i < posVec.size(); i++)
		{
			if(!c.posVec.contains(posVec.get(i)))
			return false;
		}
		
		for (int i = 0; i < negVec.size(); i++)
		{
			if(!c.negVec.contains(negVec.get(i)))
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((negVec == null) ? 0 : negVec.hashCode());
		result = prime * result + ((posVec == null) ? 0 : posVec.hashCode());
		return result;
	}
	
	public void display(){

		System.out.print("[");

		if(negVec.size() > 0){
			for(int i=0; i<negVec.size(); i++){
				
				System.out.print("-"+negVec.elementAt(i));
				if(i != negVec.size()-1)
					System.out.print(" V ");
			}
		}
		
		if(posVec.size() > 0 && negVec.size() > 0)
			System.out.print(" V ");
		
		if(posVec.size() > 0){
			for(int i=0; i<posVec.size(); i++){
				System.out.print(posVec.elementAt(i));
				if(i != posVec.size()-1)
					System.out.print(" V ");
			}
		}
		System.out.print("] \n");
	}
	
	
	public boolean isEmpty() {
	    return (negVec.size() + posVec.size()) == 0;
    }
	
	public boolean isContradictory(){
		
		for(int i=0; i<posVec.size(); i++){
			for(int j=0; j<negVec.size(); j++){
				
				if(posVec.get(i).equals(negVec.get(j)))
					return true;
				
			}
		}
		
		return false;
	}
	
	
	
	
	
	
}
