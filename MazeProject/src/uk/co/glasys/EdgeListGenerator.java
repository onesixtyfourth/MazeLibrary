package uk.co.glasys;

import java.util.List;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;

public class EdgeListGenerator
{
	private Maze maze;
	public Maze getMaze(){return maze;}
	public void setMaze(Maze maze){this.maze = maze;}
	
	private MazeAlgorithm algorithm;
	public MazeAlgorithm getAlgorithm(){return algorithm;}
	public void setAlgorithm(MazeAlgorithm algorithm){this.algorithm = algorithm;}

	
	public EdgeListGenerator(Maze maze, MazeAlgorithm algorithm)
	{
		this.setMaze(maze);
		this.setAlgorithm(algorithm);
	}	
	
	public List<Edge> carvePath()
	{
		return algorithm.carveMaze(maze);
	}
}
