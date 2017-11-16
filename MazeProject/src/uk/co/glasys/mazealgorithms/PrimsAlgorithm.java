package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;


public class PrimsAlgorithm extends MazeAlgorithm
{
//	private Maze maze;
//	@Override
//	public Maze getMaze(){return maze;}
//	public void setMaze(Maze maze){this.maze = maze;}
//
//	private List<Edge> edges = new ArrayList<Edge>();
//	@Override
//	public List<Edge> getEdges(){return edges;}
	
	@Override
	public void carve(Maze maze)
	{
		setMaze(maze);
//		edges = new ArrayList<Edge>();
		Random random = new Random(System.currentTimeMillis());			
		Cell currentCell = getMaze().getCellList().get(random.nextInt(getMaze().size()));		
		currentCell.setState(CellState.IN);
		Cell nextCell = null;		
		
		while(getMaze().getCellList()
					.stream()
					.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT))
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
				currentCell = backTrack(currentCell);
			}
		}
	}	
	
	//TODO check this is actually correctly backtracking. It should be as the reverse order of the edges
	//will reflect the paths creation so far. If it isn't I will have to trace the path backwards.
	private Cell backTrack(Cell cell)
	{
		Cell next = null;
		
		for(int i = getEdges().size() - 1; i >= 0; --i)
		{
			Edge edge = getEdges().get(i);

			if(!edge.getTo().equals(cell) && !getNeighbours(edge.getTo()).isEmpty())
			{
				next = edge.getTo();
				break;
			}
			else if(!edge.getFrom().equals(cell) &&!getNeighbours(edge.getFrom()).isEmpty())
			{
				next = edge.getFrom();
				break;
			}
		}
		return next;
	}
	
	private List<Cell> getNeighbours(Cell cell)
	{
		List<Cell> neighbours = new ArrayList<Cell>();

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
			
			if(Objects.nonNull(toCheck) && toCheck.getState() != CellState.IN )
			{
				neighbours.add(toCheck);
				toCheck.setState(CellState.FRONTIER);
			}
		}
		return neighbours;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		if(Objects.nonNull(obj) && this == obj)
		{
			equal = true;
		}
		return equal;
	}
	
	@Override
	public int hashCode()
	{//TODO seems a bit silly would be the same as the maze.
		return Objects.hash(getMaze().hashCode());
	}
}
