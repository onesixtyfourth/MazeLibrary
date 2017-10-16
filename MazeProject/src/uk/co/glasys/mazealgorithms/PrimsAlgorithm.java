package uk.co.glasys.mazealgorithms;

import java.util.List;
import java.util.Random;
import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;
import java.util.ArrayList;
import java.util.Arrays;


public class PrimsAlgorithm extends MazeAlgorithm
{
	private enum cellState
	{
		IN, OUT, FRONTIER
	}
	
	private List<Edge> edgeList = new ArrayList<Edge>();	
	private cellState []  cellStates = null;	
	private Random random = new Random(System.currentTimeMillis());
	
	private List<Cell> getNeighbours(Cell cell)
	{
		List<Cell> neighbours = new ArrayList<Cell>();
		
		
		
		
		return neighbours;
	}
	
	private Cell getNextFrontierCell(List<Cell> cells)
	{
		
		return null;
	}
	
	@Override
	public List<Edge> carveMaze(Maze maze)
	{
		cellList = generateCellList(maze);	
		Arrays.fill(cellStates, cellState.OUT);
		
		int nextCellIndex = random.nextInt(cellList.size());		

		List<Cell> neighbours = getNeighbours(cellList.get(nextCellIndex));
		
		
		return null;
	}	
}
