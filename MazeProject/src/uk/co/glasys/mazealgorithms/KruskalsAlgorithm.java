package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.Cell;
import uk.co.glasys.Maze;

public class KruskalsAlgorithm extends MazeAlgorithm
{
	
	private Logger logger = LogManager.getLogger(KruskalsAlgorithm.class);

	public void carve(Maze maze)
	{
		logger.info(String.format("%s starting to carve", getAlgorithmName()));
		setMaze(maze);
		generateCellList();
		generateInitialSets();
		
		while(getCellSets().size() > 1)
		{
			Collections.shuffle(getCellSets());			
			List<Cell> neighbours = pathNeighbours(getCellSets().get(0));	
			Collections.shuffle(neighbours);
			Cell chosen = neighbours.get(0);			
			List<Cell> selectedPath = new ArrayList<Cell>();
			
			for(List<Cell> path : getCellSets())
			{
				if(!getCellSets().get(0).equals(path) && path.contains(chosen))
				{
					selectedPath = path;
					break;
				}
			}
			connectPaths(getCellSets().get(0), selectedPath);
		}
		logger.info(String.format("%s finished carving", getAlgorithmName()));
	}
}
