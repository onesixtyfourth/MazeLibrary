package uk.co.glasys.mazealgorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class RecursiveBacktrackerAlgorithm extends MazeAlgorithm
{
	private Logger logger = LogManager.getLogger(RecursiveBacktrackerAlgorithm.class);
	
	@Override
	public void carve(Maze maze)
	{
		logger.info(String.format("%s starting to carve", getAlgorithmName()));
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
				current = backTrack(current);
			}
		}
		logger.info(String.format("%s finished carving", getAlgorithmName()));
	}
}
