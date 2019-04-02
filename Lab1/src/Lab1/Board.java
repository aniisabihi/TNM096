package Lab1;
import java.util.ArrayList; 
import java.util.Arrays; 

public class Board {
	
	private int[] boardSetup;
	private int wrongPlace = 0; 
	private final int[] goalBoard = { 1, 2, 3, 4, 5, 6, 7, 8, 0};
	
	public Board(int[] currentSetup) {
		boardSetup = currentSetup; 
		setWrongPlace();
	}
	
	public void setWrongPlace()
	{
		for(int i=0; i<boardSetup.length; i++)
		{
			if(boardSetup[i] != goalBoard[i])
			{
				wrongPlace++;
			}
		}
	}
	
	public ArrayList<Board> moveTiles()
	{
		ArrayList<Board> moves = new ArrayList<Board>();
		
		int emptyPos = getEmptyPos(); 
		
		// create moves 
		
		return moves; 
	}
	
	public int getWrongPlace()
	{
		return wrongPlace; 
	}
	
	public int getEmptyPos() {
		int emptyPos = -1;
		for(int i = 0; i<boardSetup.length; i++)
		{
			if(boardSetup[i] == 0) 
				emptyPos = i; 
		}
		
		return emptyPos;
	}
	
	public int[] getBoardSetup()
	{
		return boardSetup; 
	}

}
