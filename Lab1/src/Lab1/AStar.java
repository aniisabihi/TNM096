package Lab1;

import java.util.*;

public class AStar {
	
	static public PriorityQueue<BoardNode> q = new PriorityQueue<BoardNode>(11,new Comparator<BoardNode>() 
	{
		@Override
		public int compare(BoardNode b, BoardNode b1)
		{
			return b.getTotCost() - b1.getTotCost();
		}
	});
	
	public static void search(int[] startBoard)
	{
		long startTime = System.currentTimeMillis();
		
		BoardNode root = new BoardNode(startBoard);
		System.out.print("Starting board: \n");
		root.getBoard().printBoard();
		System.out.print("---- \n");
		
		HashSet<String> usedBoards = new HashSet<String>();
		
		usedBoards.add(root.getBoard().getSetupBoard().toString());
		q.add(root);
		
		int searchIterations = 0;
		
		while(!q.isEmpty())
		{
			BoardNode firstNode = q.poll();
			
			if(!firstNode.getBoard().finished())
			{
				// All the moves that are possible to draw on the board
				ArrayList<Board> possibleMoves = firstNode.getBoard().moveTiles();
				ArrayList<BoardNode> nodeMoves = new ArrayList<BoardNode>();
				
				for(int i = 0; i < possibleMoves.size(); i++)
				{
					//Gets a parent and a cost to the node.
					BoardNode checkedNode = new BoardNode(firstNode,possibleMoves.get(i),firstNode.getTotCost(), possibleMoves.get(i).findCost());
					
					// Add only if the move hasn't been used before
					if(!usedBoards.contains(checkedNode.getBoard().boardToString())) 
					{
						nodeMoves.add(checkedNode);
					}
				}
				
				if(nodeMoves.isEmpty())
					continue;
				
				for(int i = 0; i < nodeMoves.size(); i++)
				{
					q.add(nodeMoves.get(i));
					usedBoards.add(nodeMoves.get(i).getBoard().boardToString());
				}
				
				searchIterations++;

			}else
			{
				Stack<BoardNode> solutionPath = new Stack<BoardNode>();
				solutionPath.push(firstNode);
				firstNode = firstNode.getParent();
				
				while(firstNode.getParent() != null)
				{
					solutionPath.push(firstNode);
					firstNode = firstNode.getParent();
				}
				
				solutionPath.push(firstNode);
				
				int nrOfMoves = solutionPath.size();
				
				for(int i = 0; i < nrOfMoves; i++)
				{
					firstNode = solutionPath.pop();
					firstNode.getBoard().printBoard();
				}
				System.out.println("The cost was: " + firstNode.getTotCost());
				System.out.println("The number of nodes examined: " + searchIterations);
				System.out.println("The number of moves: " + (nrOfMoves - 1));
				
				long endTime = System.currentTimeMillis();
				long duration = (endTime-startTime);
				
				System.out.print("Runtime for the application: " + duration + " milliseconds.");
                System.exit(1);
				
				
			}
			
		}
		System.out.print("No solution found.\n");

	}
	
}
