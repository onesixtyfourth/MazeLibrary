package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class EllersAlgorithm extends MazeAlgorithm
{
	private Logger logger = LogManager.getLogger(EllersAlgorithm.class);	
	
	@Override
	public void carve(Maze maze)
	{
		logger.info(String.format("%s starting to carve", getAlgorithmName()));
		setMaze(maze);
		generateCellList();	
		generateInitialSets();

		for(int i = 0; i < maze.getHeight(); ++i)
		{
			mergeRow(i);
			if(i == maze.getHeight() - 1)
			{
				break;
			}
			createPathsToNextRow(i);
		}
		joinFinalPaths();
		logger.info(String.format("%s finished carving", getAlgorithmName()));
	}
	
	private void joinFinalPaths()
	{
		while(getCellSets().size() > 1)
		{
			List<Cell> merge = getCellSets().get(random.nextInt(getCellSets().size()));
			List<Cell> neighbours = pathNeighbours(merge);
			
			Collections.shuffle(neighbours);
			for(Cell cell : neighbours)
			{
				if(!merge.contains(cell))
				{
					List<Cell> aSet = getCellSets().get(cellInSet(cell));
					connectPaths(merge, aSet);
				}
			}
		}
	}

	public void createPathsToNextRow(int rowIndex)
	{
		for(Cell selected : selectConnectors(rowIndex))
		{
			List<Cell> possibles = getNeighbours(selected).stream()
					.filter(c -> c.getY() - 1 == selected.getY())
					.collect(Collectors.toList());
			
			Collections.shuffle(possibles);
			Cell to = possibles.get(0);
			List<Cell> aSet = getCellSets().get(cellInSet(to));
			
			getEdges().add(new Edge(selected, to));
			getCellSets().remove(aSet);
			getCellSets().get(cellInSet(selected)).addAll(aSet);	
		}
	}
	
	public List<Cell> selectConnectors(int rowIndex)
	{
		List<Cell> connectors = new ArrayList<Cell>();
		
		List<Integer> indexes = new ArrayList<Integer>();
		for(Cell cell : getCells().stream().filter(c -> c.getY() == rowIndex).collect(Collectors.toList()))
		{
			if(!connectors.contains(cell) && !indexes.contains(cellInSet(cell)))
			{
				indexes.add(cellInSet(cell));
				connectors.add(cell);
			}
		}
		return connectors;
	}	
	
	public void mergeRow(int rowIndex)
	{
		List<Cell> currentRow = getCells().stream()
											.filter(c -> c.getY() == rowIndex)
											.collect(Collectors.toList());
		for(Cell cell : currentRow)
		{
			int cellIndex = currentRow.indexOf(cell);
			if(cellIndex < currentRow.size() - 1)
			{
				Cell next = currentRow.get(currentRow.indexOf(cell) + 1);
				if(Objects.nonNull(next) && (cellInSet(next) != cellInSet(cell)) && (cell.getY() == next.getY()))
				{
					if(random.nextBoolean())
					{
						List<Cell> aSet = getCellSets().get(cellInSet(next));
						getCellSets().remove(aSet);
						getEdges().add(new Edge(cell, next));
						getCellSets().get(cellInSet(cell)).addAll(aSet);
					}
				}
			}				
		}
	}
}
