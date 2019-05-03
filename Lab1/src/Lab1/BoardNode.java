package Lab1;

public class BoardNode {
	private Board board;
    private int totCost;
    private int h1Cost;
    private int gCost;
    private BoardNode parent;
	

    public BoardNode(int[] boardSetup) 
    {
    	board = new Board(boardSetup);
    	totCost = 0;
    	h1Cost = 0;
    	gCost = 0;
    	parent = null;
    }
    
    public BoardNode(Board b)
    {
    	board = b;
    	totCost = 0;
    	h1Cost = 0;
    	gCost = 0;
    	parent = null;
    }
    
    public BoardNode(BoardNode prev, Board b, int h1, int g) 
    {
    	parent = prev;
    	board = b;
    	h1Cost = h1;
    	gCost = g;
    	totCost = h1 + g;
    }
    
    public int getTotCost() 
    {
    	return totCost;
    }
    
    public int geth1Cost()
    {
    	return h1Cost;
    }
    
    public Board getBoard()
    {
    	return board;
    }
    
    public BoardNode getParent()
    {
    	return parent;
    }
}
