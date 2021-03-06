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
		if(emptyPos != 0 && emptyPos != 3 && emptyPos != 6)
		{
			saveMovedTiles(emptyPos, emptyPos-1, moves);
		}

		// Move right
		if(emptyPos != 8 && emptyPos != 5 && emptyPos != 2)
		{
			saveMovedTiles(emptyPos, emptyPos+1, moves);
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
		System.out.println("\n");
		System.out.println(setupBoard[0] + " " + setupBoard[1] + " " + setupBoard[2]);
		System.out.println(setupBoard[3] + " " + setupBoard[4] + " " + setupBoard[5]);
		System.out.println(setupBoard[6] + " " + setupBoard[7] + " " + setupBoard[8]);
	}

	public int findCost()
	{
		return wrongPlace; 
	}
	
	public boolean finished()
	{
		if(Arrays.equals(setupBoard, goalBoard))
		{
			return true;
		}else 
			return false;
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

	
	public int[] getSetupBoard()
	{
		return setupBoard;
	}
	
	public String boardToString() {
        return Arrays.toString(setupBoard);
    }
	

    public int setManDist() 
    {
        int index = -1;
        int manDist = 0;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                index++;
                int value = setupBoard[index];

                if (value != 0) {
                    int h = (value - 1) % 3; // expected x-coordinate
                    int v = (value -1 ) / 3; //expected y-coordinate
                    int dx = h - x; // x- distance
                    int dy = v - y; //y - distance

                    manDist += Math.abs(dy) + Math.abs(dx);
                }
            }
        }

        return manDist;
    }
}
