package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import uk.co.glasys.Edge;
import uk.co.glasys.Maze;
import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;

public class RecursiveBacktrackerAlgorithm extends MazeAlgorithm
{
//	Random random = new Random(System.currentTimeMillis());			

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

//	private List<Cell> getNeighbours(Cell current)
//	{//TODO copied from PrimsAlgorithm so should be moved out for sharing/re-use
//		List<Cell> neighbours = new ArrayList<Cell>();
//
//		for(int i = 0; i < current.getNumberOfSides(); ++i)
//		{//TODO need to accommodate more than four sides
//			int index = getCells().indexOf(current);
//			Cell toCheck = null;
//			
//			switch (i)
//			{
//				case 0:
//				if(index - getMaze().getWidth() >= 0 && index - getMaze().getWidth() < getMaze().size())
//				{
//					toCheck = getCells().get(index - getMaze().getWidth());
//					break;
//				}
//					
//				case 1:
//				if (index + 1 >= 0 && index + 1 < getMaze().size() &&
//						getCells().get(index + 1).getY() ==  getCells().get(index).getY())
//				{
//					toCheck = getCells().get(index + 1);
//					break;
//				}
//				case 2:
//				if (index + getMaze().getWidth() >= 0 && index + getMaze().getWidth() < getMaze().size())
//				{
//					toCheck = getCells().get(index + getMaze().getWidth());
//					break;
//				}
//				case 3:
//				if (index - 1 >= 0 && index - 1 < getMaze().size() &&
//						getCells().get(index - 1).getY() ==  getCells().get(index).getY())
//				{
//					toCheck = getCells().get(index - 1);
//					break;
//				}
//			}
//			
//			if(Objects.nonNull(toCheck) && toCheck.getState() != CellState.IN )
//			{
//				neighbours.add(toCheck);
//				toCheck.setState(CellState.FRONTIER);
//			}
//		}
//		return neighbours;
//	}

}
