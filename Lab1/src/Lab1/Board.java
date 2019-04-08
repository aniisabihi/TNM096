package Lab1;
import java.util.ArrayList; 
import java.util.Arrays; 

public class Board {
	
	private int[] setupBoard;
	private int wrongPlace = 0; 
	private final int[] goalBoard = { 1, 2, 3, 4, 5, 6, 7, 8, 0};
	
	public Board(int[] currentSetup) {
		setupBoard = currentSetup;
		setWrongPlace();
	}
	
	public void setWrongPlace()
	{
		for(int i = 0; i< setupBoard.length; i++)
		{
			if(setupBoard[i] != goalBoard[i])
			{
				wrongPlace++;
			}
		}
	}

	// Find our two children
	public ArrayList<Board> moveTiles()
	{
		ArrayList<Board> moves = new ArrayList<Board>();
		
		int emptyPos = getEmptyPos(); 

		// Move up
		if(emptyPos > 2)
		{
			saveMovedTiles(emptyPos, emptyPos-3, moves);
		}

		// Move down
		if(emptyPos < 6)
		{
			saveMovedTiles(emptyPos, emptyPos+3, moves);
		}

		// Move left
		if(emptyPos != 6 && emptyPos != 3 && emptyPos != 0)
		{
			saveMovedTiles(emptyPos, emptyPos-1, moves);
		}

		// Move right
		if(emptyPos != 8 && emptyPos != 5 && emptyPos != 2)
		{
			saveMovedTiles(emptyPos, emptyPos+2, moves);
		}
		
		return moves; 
	}

	private void saveMovedTiles (int emptySlot, int tile, ArrayList<Board> board)
	{
		int[] movedBoard = copyBoard(setupBoard);
		int temp = movedBoard[tile];
		movedBoard[tile] = setupBoard[emptySlot];
		movedBoard[emptySlot] = temp;
		board.add(new Board(movedBoard));
	}

	public int[] copyBoard(int[] board)
	{
		int[] copy = new int[9];
		for(int i=0; i<9; i++)
		{
			copy[i] = board[i];
		}
		return copy;
	}

	public void printBoard()
	{
		System.out.println(setupBoard[0] + " " + setupBoard[1] + " " + setupBoard[2]);
		System.out.println(setupBoard[3] + " " + setupBoard[4] + " " + setupBoard[5]);
		System.out.println(setupBoard[6] + " " + setupBoard[7] + " " + setupBoard[8]);
	}

	public int getWrongPlace()
	{
		return wrongPlace; 
	}
	
	public int getEmptyPos() {
		int emptyPos = -1;
		for(int i = 0; i< setupBoard.length; i++)
		{
			if(setupBoard[i] == 0)
				emptyPos = i; 
		}

		return emptyPos;
	}

	public boolean done()
	{
		if(Arrays.equals(setupBoard, goalBoard)){return true;}
		return false;
	}
	
	public int[] getSetupBoard()
	{
		return setupBoard;
	}
}
