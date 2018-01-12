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
}
