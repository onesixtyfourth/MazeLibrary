package uk.co.glasys.mazealgorithms;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public abstract class MazeAlgorithm
{
	protected List<Cell> cellList = new ArrayList<Cell>();
	
	private Maze maze;
	public Maze getMaze(){return maze;}
	public void setMaze(Maze maze){this.maze = maze;}
	
	public MazeAlgorithm(Maze maze)
	{
		setMaze(maze);
	}
	
	/**
	 * Carve a path through the maze
	 * 
	 * @return A list of edges that represent the path through the maze.
	 */
	public abstract List<Edge> carveMaze();
	
	/**
	 * Returns a String object that represents the name of the algorithm
	 * created. This requires the class to be named so that the Class name consists
	 * of the algorithm name followed by the word Algorithm.
	 * 
	 * @return The name of the algorithm that will be used to carve the maze.
	 */
	public String getAlgorithmName()
	{
		String className = this.getClass()
								.getSimpleName()
								.replace("Algorithm", "");
		return className;
	}	
	
	protected List<Cell> generateCellList()
	{
		if(Objects.nonNull(maze))
		{
			for(int i = 0; i < maze.size(); ++i)
			{
				int x = i % maze.getWidth();
				int y = i / maze.getHeight();
				cellList.add(new Cell(x, y));
			}
		}
		return cellList;
	}	
}
