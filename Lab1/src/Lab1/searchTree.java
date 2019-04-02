package Lab1;

public class searchTree {
	
	private Board board;
	private int totalCost; 
	private int firstMoveCost;
	private int secondMoveCost;
	private searchTree parent; 
	
	public searchTree(int[] boardSetup)
	{
		board = new Board(boardSetup);
		totalCost = 0; 
		firstMoveCost = 0; 
		secondMoveCost = 0; 
		parent = null; 
	}
	
	public searchTree(Board b)
	{
		board = b;
		totalCost = 0; 
		firstMoveCost = 0; 
		secondMoveCost = 0; 
		parent = null; 
	}
	
	public searchTree(searchTree prev, Board b, int firstMove, int secondMove)
	{
		parent = prev; 
		board = b;
		firstMoveCost = firstMove; 
		secondMoveCost = secondMove;
		totalCost = firstMove + secondMove; 
	}
	
	public int getTotalCost()
	{
		return totalCost;
	}
	
	public int getFirstMoveCost()
	{
		return firstMoveCost;
	}
	
	public int getSecondMoveCost()
	{
		return secondMoveCost;
	}
	
	public Board getBoard() 
	{
		return board; 
	}
	
	public searchTree getParent()
	{
		return parent; 
	}
}
