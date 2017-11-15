package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import uk.co.glasys.Edge;
import uk.co.glasys.Maze;
import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;

public class RecursiveBacktrackerAlgorithm implements MazeAlgorithm
{

	private Maze maze;
	private List<Edge> edges = new ArrayList<Edge>();
	Random random = new Random(System.currentTimeMillis());			

	@Override
	public List<Edge> carve(Maze maze)
	{
		this.maze = maze;
		
		Cell current = maze.getCellList().get(random.nextInt(maze.size()));
		current.setState(CellState.IN);
		
		Cell next = null;
		
		while(maze.getCellList()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT))
		{
			List<Cell> neighbours = getNeighbours(current);
			if(neighbours.size() > 0)
			{
				next = neighbours.get(random.nextInt(neighbours.size()));
				next.setState(CellState.IN);
				edges.add(new Edge(current, next));
				current = next;
			}
			else
			{
				current = backtrack(current);
			}
		}
		
		return edges;
	}

	private Cell backtrack(Cell current)
	{
		Cell next = null;
		
		for(int i = edges.size() - 1; i >= 0; --i)
		{
			Edge edge = edges.get(i);

			if(!edge.getTo().equals(current) && !getNeighbours(edge.getTo()).isEmpty())
			{
				next = edge.getTo();
				break;
			}
			else if(!edge.getFrom().equals(current) &&!getNeighbours(edge.getFrom()).isEmpty())
			{
				next = edge.getFrom();
				break;
			}
		}
		return next;
	}

	private List<Cell> getNeighbours(Cell current)
	{//TODO copied from PrimsAlgorithm so should be moved out for sharing/re-use
		List<Cell> neighbours = new ArrayList<Cell>();

		for(int i = 0; i < current.getNumberOfSides(); ++i)
		{//TODO need to accommodate more than four sides
			int index = maze.getCellList().indexOf(current);
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

}
