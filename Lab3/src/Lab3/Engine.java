package Lab3;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Engine {
	
	public Vector<Clause> clauseVec = new Vector<Clause>();
	
	Engine(String[] stringClauses)
	{
		for(int i = 0; i < stringClauses.length; i ++)
		{
			Clause c = new Clause(stringClauses[i]);
			
			if(!c.isEmpty())
			{
				clauseVec.addElement(c);
				c.display();
			}
		}
		
	}
	
	public void Solver()
	{
		Clause c;
		Vector<Clause> KB = new Vector<Clause>();
		
		do {
			c = new Clause();
			
			for(int i = 0; i < clauseVec.size() -1; i++)
			{
				for(int j = i + 1; j < clauseVec.size(); j++)
				{
					c = resolution(clauseVec.get(i), clauseVec.get(j));
					
					if(c != null) {
						KB.addElement(c);
					}
				}
			}
			
			KB = Incorporate(KB);
	
		}while(c != null && !c.isEmpty());
		
		
		System.out.println("solved");
		for(int i = 0; i < KB.size(); i++) {
			if(KB.get(i)!=null && !KB.get(i).isEmpty())
			{
				KB.get(i).display();
			}
		}
		
	}
	
	public Vector<Clause> Incorporate(Vector<Clause> KB)
	{
		for(int i = 0; i < clauseVec.size(); i++)
		{
			KB = IncorporateClause(clauseVec.get(i),KB);
		}
		
		return KB;
	}
	
	public Vector<Clause> IncorporateClause(Clause A, Vector<Clause> KB)
	{
		for(int i = 0; i < KB.size(); i++) {
			if(KB.get(i).isSubset(A))
			{
				return KB;
			}
		}
		for(int i = 0; i < KB.size(); i ++)
		{
			if(A.isSubset(KB.get(i)))
			{
				KB.remove(KB.get(i));
			}
		}
		
		KB.add(A);
		
		return KB;
	}
	
	
	public Clause resolution(Clause A, Clause B)
	{
		String literal = null;
		Clause newClause = new Clause();
		
		for (int i = 0; i < A.posVec.size(); i++)
		{
			if(B.negVec.contains(A.posVec.get(i)))
				literal = A.posVec.get(i);
		}
		
		for (int i = 0; literal == null && i < B.posVec.size(); i++)
		{
			if(A.negVec.contains(B.posVec.get(i)))
			{
				literal = B.posVec.get(i);
			}
		}
		
		if(literal != null)
		{
			for (int i = 0; i < A.posVec.size(); i++) {
				if (!A.posVec.get(i).equals(literal) && !newClause.posVec.contains(A.posVec.get(i)))
					newClause.posVec.add(A.posVec.get(i));
			}

			for (int i = 0; i < A.negVec.size(); i++) {
				if (!A.negVec.get(i).equals(literal) && !newClause.negVec.contains(A.negVec.get(i)))
					newClause.negVec.add(A.negVec.get(i));
			}

			for (int i = 0; i < B.posVec.size(); i++) {
				if (!B.posVec.get(i).equals(literal) && !newClause.posVec.contains(B.posVec.get(i)))
					newClause.posVec.add(B.posVec.get(i));
			}

			for (int i = 0; i < B.negVec.size(); i++) {
				if (!B.negVec.get(i).equals(literal) && !newClause.negVec.contains(B.negVec.get(i)))
					newClause.negVec.add(B.negVec.get(i));
			}
		}else {
			return null;
		}
		
		if(literal != null && newClause.isContradictory())
		{
			return null;
		}
		
		return newClause;
		
	}
	
}
