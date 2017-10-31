package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;
import uk.co.glasys.Cell.CellState;

public class HuntAndKillAlgorithm implements MazeAlgorithm
{

	private Maze maze;

	@Override
	public List<Edge> carve(Maze maze)
	{
		this.maze = maze;
		List<Edge> edges = new ArrayList<Edge>();
		Random random = new Random(System.currentTimeMillis());			
		Cell currentCell = maze.getCellList().get(random.nextInt(maze.size()));		
		currentCell.setState(CellState.IN);
		Cell nextCell = null;
		
		while(maze.getCellList()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT))
		{
			List<Cell> neighbours = getNeighbours(currentCell);	
			if(neighbours.size() > 0)
			{
				nextCell = neighbours.get(random.nextInt(neighbours.size()));
				edges.add(new Edge(currentCell, nextCell));
				
				nextCell.setState(CellState.IN);
				currentCell = nextCell;
			}
			else
			{
				currentCell = huntForNext();
			}
		}
		return edges;
	}	

	@Override
	public List<Cell> getNeighbours(Cell cell)
	{
		List<Cell> neighbours = new ArrayList<Cell>();

		for(int i = 0; i < cell.getNumberOfSides(); ++i)
		{//TODO need to accommodate more than four sides
			int index = maze.getCellList().indexOf(cell);
			Cell toCheck = null;
			
			switch (i)
			{
				case 0:
				if(index - maze.getWidth() >= 0 && index - maze.getWidth() < maze.size())
				{
					toCheck = maze.getCellList().get(index - maze.getWidth());
					break;
				}
					
				case 1:
				if (index + 1 >= 0 && index + 1 < maze.size() &&
						 maze.getCellList().get(index + 1).getY() ==  maze.getCellList().get(index).getY())
				{
					toCheck = maze.getCellList().get(index + 1);
					break;
				}
				case 2:
				if (index + maze.getWidth() >= 0 && index + maze.getWidth() < maze.size())
				{
					toCheck = maze.getCellList().get(index + maze.getWidth());
					break;
				}
				case 3:
				if (index - 1 >= 0 && index - 1 < maze.size() &&
						 maze.getCellList().get(index - 1).getY() ==  maze.getCellList().get(index).getY())
				{
					toCheck = maze.getCellList().get(index - 1);
					break;
				}
			}
			
			if(Objects.nonNull(toCheck) && toCheck.getState() != CellState.IN )
			{
				neighbours.add(toCheck);
				toCheck.setState(CellState.FRONTIER);
			}
		}
		return neighbours;
	}
	
	private Cell huntForNext()
	{
		Cell next = null;
		
		search:{
			for (int i = 0; i < maze.getHeight(); ++i)
			{
				final int rowIndex = i;
							
				List<Cell> cells = maze.getCellList()
						.stream()
						.filter(c -> c.getY() == rowIndex)
						.collect(Collectors.toList());
				
				for (Cell cell : cells)
				{
					if(!cell.getState().equals(CellState.IN) && 
							getNeighbours(cell).stream()
												.anyMatch(c -> c.getState()
												.equals(CellState.OUT) || c.getState().equals(CellState.FRONTIER)))
					{
						next = cell;
						break search;
					}
					
				}
			}
		}
		return next;
	}

}