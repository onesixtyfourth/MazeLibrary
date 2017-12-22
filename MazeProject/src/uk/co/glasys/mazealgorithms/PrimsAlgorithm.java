package uk.co.glasys.mazealgorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class PrimsAlgorithm extends MazeAlgorithm
{	
	private Logger logger = LogManager.getLogger(PrimsAlgorithm.class);
	
	@Override
	public void carve(Maze maze)
	{
		logger.info(String.format("%s starting to carve", getAlgorithmName()));
		setMaze(maze);
		generateCellList();		
		Cell currentCell = getCells().get(random.nextInt(getMaze().size()));		
		currentCell.setState(CellState.IN);
		Cell nextCell = null;		
		
		while(getCells()
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
		logger.info(String.format("%s finished carving", getAlgorithmName()));
	}	
	
	//TODO check this is actually correctly backtracking. It should be as the reverse order of the edges
	//will reflect the paths creation so far. If it isn't I will have to trace the path backwards.
	private Cell backTrack(Cell cell)
	{
		logger.info(String.format("Backtrcking from %s", cell));
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
}
