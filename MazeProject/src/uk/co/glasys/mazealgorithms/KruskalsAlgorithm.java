package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class KruskalsAlgorithm extends MazeAlgorithm
{
	private List<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
	private Logger logger = LogManager.getLogger(KruskalsAlgorithm.class);

	public void carve(Maze maze)
	{
		logger.info(String.format("%s starting to carve", getAlgorithmName()));
		setMaze(maze);
		generateCellList();
		generateInitialSets();
		
		while(cells.size() > 1)
		{
			Collections.shuffle(cells, random);			
			List<Cell> neighbours = pathNeighbours(cells.get(0));			
			Cell chosen = neighbours.get(random.nextInt(neighbours.size()));			
			List<Cell> selectedPath = new ArrayList<Cell>();
			
			for(List<Cell> path : cells)
			{
				if(!cells.get(0).equals(path) && path.contains(chosen))
				{
					selectedPath = path;
					break;
				}
			}
			connectPaths(cells.get(0), selectedPath);
		}
		logger.info(String.format("%s finished carving", getAlgorithmName()));
	}
	
	private void connectPaths(List<Cell> path, List<Cell> connect)
	{
		done:
		{
			for(Cell cell : path)
			{
				for(Cell connecting : connect)
				{
					if(canCellsConnect(cell, connecting))
					{
						cell.setState(CellState.IN);
						connecting.setState(CellState.IN);
						getEdges().add(new Edge(cell, connecting));
						break done;
					}
				}
			}
		}
		path.addAll(connect);
		cells.remove(connect);
	}
	
	private List<Cell> pathNeighbours(List<Cell> path)
	{
		List<Cell> neighbours = new ArrayList<Cell>();
		for(Cell cell : path)
		{
			List<Cell> tmp = getNeighbours(cell);
			for(Cell possible : tmp)
			{
				if(!path.contains(possible) && !neighbours.contains(possible))
				{
					neighbours.add(possible);
				}
			}
		}
		return neighbours;
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
	
	private void generateInitialSets()
	{
		for(Cell cell : getCells())
		{
			ArrayList<Cell> tmp = new ArrayList<Cell>();
			tmp.add(cell);
			cells.add(tmp);
		}
	}		
}
