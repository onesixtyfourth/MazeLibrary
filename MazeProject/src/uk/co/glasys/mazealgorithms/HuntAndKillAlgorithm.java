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

public class HuntAndKillAlgorithm extends MazeAlgorithm
{
//	private Maze maze;
//	@Override
//	public Maze getMaze()
//	{
//		return maze;
//	}
//
//	
//	private List<Edge> edges;
//	@Override
//	public List<Edge> getEdges()
//	{
//		return edges;
//	}

	@Override
	public void carve(Maze maze)
	{
		setMaze( maze);
//		getEdges() = new ArrayList<Edge>();
		Random random = new Random(System.currentTimeMillis());			
		Cell currentCell = maze.getCellList().get(random.nextInt(maze.size()));		
		currentCell.setState(CellState.IN);
		Cell nextCell = null;
		
		while(maze.getCellList()
				.stream()
				.anyMatch(s -> s.getState() != CellState.IN))
		{
			List<Cell> neighbours = getNeighbours(currentCell);	
			if(neighbours.size() > 0)
			{
				nextCell = neighbours.get(random.nextInt(neighbours.size()));
				getEdges().add(new Edge(currentCell, nextCell));
				
				nextCell.setState(CellState.IN);
				currentCell = nextCell;
			}
			else
			{
				currentCell = huntForNext();
			}
		}
	}	

	private List<Cell> getNeighbours(Cell cell)
	{
		List<Cell> neighbours = new ArrayList<Cell>();
		if(Objects.nonNull(cell))
		{
			for(int i = 0; i < cell.getNumberOfSides(); ++i)
			{//TODO need to accommodate more than four sides
				int index = getMaze().getCellList().indexOf(cell);
				Cell toCheck = null;
				
				switch (i)
				{
					case 0:
					if(index - getMaze().getWidth() >= 0 && index - getMaze().getWidth() < getMaze().size())
					{
						toCheck = getMaze().getCellList().get(index - getMaze().getWidth());
						break;
					}
						
					case 1:
					if (index + 1 >= 0 && index + 1 < getMaze().size() &&
							getMaze().getCellList().get(index + 1).getY() ==  getMaze().getCellList().get(index).getY())
					{
						toCheck = getMaze().getCellList().get(index + 1);
						break;
					}
					case 2:
					if (index + getMaze().getWidth() >= 0 && index + getMaze().getWidth() < getMaze().size())
					{
						toCheck = getMaze().getCellList().get(index + getMaze().getWidth());
						break;
					}
					case 3:
					if (index - 1 >= 0 && index - 1 < getMaze().size() &&
							getMaze().getCellList().get(index - 1).getY() ==  getMaze().getCellList().get(index).getY())
					{
						toCheck = getMaze().getCellList().get(index - 1);
						break;
					}
				}
				
				if(Objects.nonNull(toCheck) && toCheck.getState() != CellState.IN && !neighbours.contains(toCheck) )
				{
					neighbours.add(toCheck);
				}
			}
		}
		return neighbours;
	}
	
	private Cell huntForNext()
	{
		Cell next = null;
		
		search:
		{
			for (int i = 0; i < getMaze().getHeight(); ++i)
			{
				final int rowIndex = i;
							
				List<Cell> cells = getMaze().getCellList()
						.stream()
						.filter(c -> c.getY() == rowIndex)
						.collect(Collectors.toList());
				
				for (Cell cell : cells)
				{
					if(cell.getState().equals(CellState.IN) && getNeighbours(cell).stream()
																.anyMatch(c -> c.getState().equals(CellState.OUT)))
					{
						next = cell;
						break search;
					}
					
				}
			}
		}
			
		List<Cell> neighbours = getNeighbours(next).stream()
													.filter(c -> c.getState().equals(CellState.OUT))
													.collect(Collectors.toList());
		if(!neighbours.isEmpty())
		{
			Cell inPath = neighbours.get(0);
			getEdges().add(new Edge(next, inPath));
			next = inPath;
			next.setState(CellState.IN);
		}
		
		return next;
	}
}
