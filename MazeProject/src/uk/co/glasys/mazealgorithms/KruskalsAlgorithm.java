package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class KruskalsAlgorithm implements MazeAlgorithm
{

	private Maze maze;
	private List<Edge> edges;

	@Override
	public List<Edge> carve(Maze maze)
	{
		this.maze = maze;
		generateInitialEdges();
//		Random random = new Random(System.currentTimeMillis());	
		
		
		
		return edges;
	}
	
	private void generateInitialEdges()
	{
		edges = new ArrayList<Edge>();
		for(int i = 0; i < maze.getHeight(); ++i)
		{
			final int rowIndex = i;
			
			List<Cell> cells = maze.getCellList()
					.stream()
					.filter(c -> c.getY() == rowIndex)
					.collect(Collectors.toList());
			
			for(Cell cell : cells)
			{//TODO create edges right and down for each cell in the row where appropriate
				
			}
		}
	}

}
