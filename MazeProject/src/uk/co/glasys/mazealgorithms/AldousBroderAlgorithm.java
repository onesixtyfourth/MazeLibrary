package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.Cell;
import uk.co.glasys.Maze;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;

public class AldousBroderAlgorithm extends MazeAlgorithm
{

	private Logger logger = LogManager.getLogger(AldousBroderAlgorithm.class);
	
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
				Collections.shuffle(neighbours);
				nextCell = neighbours.get(0);
				if(nextCell.getState() != CellState.IN)
				{
					getEdges().add(new Edge(currentCell, nextCell));
					nextCell.setState(CellState.IN);
				}
				currentCell = nextCell;
			}			
		}		
	}
	
	@Override
	public List<Cell> getNeighbours(Cell cell)
	{
		List<Cell> neighbours = new ArrayList<Cell>();
		for(Cell nextCell : getCells())
		{
			if(canCellsConnect(cell, nextCell))
			{
				neighbours.add(nextCell);
			}
		}
		return neighbours;
	}
}
