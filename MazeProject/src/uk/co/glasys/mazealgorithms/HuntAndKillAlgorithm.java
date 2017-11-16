package uk.co.glasys.mazealgorithms;

import java.util.List;
import java.util.stream.Collectors;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class HuntAndKillAlgorithm extends MazeAlgorithm
{

	@Override
	public void carve(Maze maze)
	{
		setMaze( maze);
		generateCellList();	
		Cell currentCell = getCells().get(random.nextInt(getMaze().size()));		
		currentCell.setState(CellState.IN);
		Cell nextCell = null;
		
		while(getCells()
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
	
	private Cell huntForNext()
	{
		Cell next = null;
		
		search:
		{
			for (int i = 0; i < getMaze().getHeight(); ++i)
			{
				final int rowIndex = i;
							
				List<Cell> cells = getCells()
						.stream()
						.filter(c -> c.getY() == rowIndex)
						.collect(Collectors.toList());
				
				for (Cell cell : cells)
				{
					if(cell.getState().equals(CellState.IN) && getNeighbours(cell).stream()
																.anyMatch(c -> c.getState().equals(CellState.OUT) || 
																			c.getState().equals(CellState.FRONTIER)) )
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
