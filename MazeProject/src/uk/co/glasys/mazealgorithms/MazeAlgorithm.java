package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public abstract class MazeAlgorithm
{
	private Maze maze;	
	public Maze getMaze(){return maze;}
	public void setMaze(Maze maze){this.maze = maze;}
	
	private List<Cell> cells = new ArrayList<Cell>();	
	public List<Cell> getCells(){return cells;}
	public void setCells(List<Cell> cellList){this.cells = cellList;}
	
	private List<Edge> edges = new ArrayList<Edge>();
	public List<Edge> getEdges(){return edges;}
	public void setEdges(List<Edge> edges){this.edges = edges;}
	
	protected Random random = new Random(System.currentTimeMillis());			
	
	/**
	 * Carve a path through the maze
	 * 
	 */
	public abstract void carve(Maze maze);

	public List<Cell> getConnectedCells(Cell cell)
	{
		List<Cell> cellList = new ArrayList<Cell>();
		
		getEdges().forEach(item ->
		{
			if(item.getFrom().equals(cell))
			{
				cellList.add(item.getTo());
			}
			else if(item.getTo().equals(cell))
			{
				cellList.add(item.getFrom());
			}
		});
		return cellList;		
	}
	
	public void reset()
	{
		getCells().forEach(l -> l.setState(CellState.OUT));
	}
	
	public List<Cell> getNeighbours(Cell cell)
	{
		List<Cell> neighbours = new ArrayList<Cell>();

		for(int i = 0; i < cell.getNumberOfSides(); ++i)
		{//TODO need to accommodate more than four sides
			int index = getCells().indexOf(cell);
			Cell toCheck = null;
			
			switch (i)
			{
				case 0:
				if(index - getMaze().getWidth() >= 0 && index - getMaze().getWidth() < getMaze().size())
				{
					toCheck = getCells().get(index - getMaze().getWidth());
					break;
				}
					
				case 1:
				if (index + 1 >= 0 && index + 1 < getMaze().size() &&
						getCells().get(index + 1).getY() ==  getCells().get(index).getY())
				{
					toCheck = getCells().get(index + 1);
					break;
				}
				case 2:
				if (index + getMaze().getWidth() >= 0 && index + getMaze().getWidth() < getMaze().size())
				{
					toCheck = getCells().get(index + getMaze().getWidth());
					break;
				}
				case 3:
				if (index - 1 >= 0 && index - 1 < getMaze().size() &&
						getCells().get(index - 1).getY() ==  getCells().get(index).getY())
				{
					toCheck = getCells().get(index - 1);
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
	
	public boolean canCellsConnect(Cell next, Cell potential)
	{
		boolean result = false;
		
		int dx = next.getX() - potential.getX();
		int dy = next.getY() - potential.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		
		if(distance == 1)
		{
			if(next.getX() == potential.getX() || next.getY() == potential.getY())
			{
				result = true;
			}
		}		
		return result;
	}
	
	public void generateCellList()
	{
		getCells().clear();		
		for(int i = 0; i < maze.size(); ++i)
		{
			int x = i % maze.getWidth();
			int y = i / maze.getWidth();
			getCells().add(new Cell(x, y));
		}
	}
	
	/**
	 * Returns a String object that represents the name of the algorithm
	 * created. This requires the class to be named so that the Class name consists
	 * of the algorithm name followed by the word Algorithm.
	 * 
	 * @return The name of the algorithm that this class represents.
	 */
	public String getAlgorithmName()
	{
		return this.getClass().getSimpleName().replace("Algorithm", "");
	}	
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return String.format("%s : Maze = %d, Width: %d, Height: %d", getAlgorithmName(), 
																		getMaze().size(), 
																		getMaze().getWidth(), 
																		getMaze().getHeight());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		if(Objects.nonNull(obj) && this == obj)
		{
			equal = true;
		}
		return equal;
	}
	
	//TODO consider overriding hashcode
}
