package uk.co.glasys.mazealgorithms;

import java.util.List;

import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public interface MazeAlgorithm
{
	/**
	 * Carve a path through the maze
	 * 
	 * @return A list of edges that represent the path through the maze.
	 */
	List<Edge> carve(Maze maze);
	
	/**
	 * Returns a String object that represents the name of the algorithm
	 * created. This requires the class to be named so that the Class name consists
	 * of the algorithm name followed by the word Algorithm.
	 * 
	 * @return The name of the algorithm that this class represents.
	 */
	public default String getAlgorithmName()
	{
		String className = this.getClass()
								.getSimpleName()
								.replace("Algorithm", "");
		return className;
	}	
}
