package uk.co.glasys.mazealgorithms;

import java.util.List;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class RecursiveBacktrackerAlgorithm extends MazeAlgorithm
{
	@Override
	public void carve(Maze maze)
	{
		setMaze(maze);
		generateCellList();
		Cell current = getCells().get(random.nextInt(getMaze().size()));
		current.setState(CellState.IN);		
		Cell next = null;
		
		while(getCells()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT))
		{
			List<Cell> neighbours = getNeighbours(current);
			if(neighbours.size() > 0)
			{
				next = neighbours.get(random.nextInt(neighbours.size()));
				next.setState(CellState.IN);
				getEdges().add(new Edge(current, next));
				current = next;
			}
			else
			{
				current = backtrack(current);
			}
		}
	}

	private Cell backtrack(Cell current)
	{//TODO copied from PrimsAlgorithm so should be moved out for sharing/re-use
		Cell next = null;
		
		for(int i = getEdges().size() - 1; i >= 0; --i)
		{
			Edge edge = getEdges().get(i);

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
}
