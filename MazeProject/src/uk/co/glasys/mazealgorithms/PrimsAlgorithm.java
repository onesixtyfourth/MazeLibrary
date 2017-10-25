package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;


public class PrimsAlgorithm extends MazeAlgorithm
{
	private enum cellState
	{
		IN, OUT, FRONTIER
	}
	
	
	private List<Edge> edgeList = new ArrayList<Edge>();	
	private List<cellState> cellStates;	
	private Random random = new Random(System.currentTimeMillis());
	
	
	public PrimsAlgorithm(Maze maze)
	{
		super(maze);
		cellList = generateCellList();
		cellStates = new ArrayList<cellState>(Collections.nCopies(maze.size(), cellState.OUT));
	}
	
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
	public List<Edge> carveMaze()
	{		
		int nextCellIndex = random.nextInt(cellList.size());		
		
		List<Cell> neighbours = getNeighbours(cellList.get(nextCellIndex));
		
		
		return null;
	}	
}
