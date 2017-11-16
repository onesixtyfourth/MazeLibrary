package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.List;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public abstract class MazeAlgorithm
{
	/**
	 * Carve a path through the maze
	 * 
	 */
	public abstract void carve(Maze maze);
	
	private Maze maze;	
	public Maze getMaze(){return maze;}
	public void setMaze(Maze maze){this.maze = maze;}
	
	private List<Edge> edges = new ArrayList<Edge>();
	public List<Edge> getEdges(){return edges;}
	public void setEdges(List<Edge> edges){this.edges = edges;}
	
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
	
	/**
	 * Returns a String object that represents the name of the algorithm
	 * created. This requires the class to be named so that the Class name consists
	 * of the algorithm name followed by the word Algorithm.
	 * 
	 * @return The name of the algorithm that this class represents.
	 */
	public String getAlgorithmName()
	{
		String className = this.getClass()
								.getSimpleName()
								.replace("Algorithm", "");
		return className;
	}	
}
