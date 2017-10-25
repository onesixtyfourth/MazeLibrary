import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;

public class MazeMain
{
	public static void main(String [] args)
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new PrimsAlgorithm(maze);			
		algorithm.carveMaze();
		
		System.out.println(maze.toString() + System.lineSeparator() + algorithm.getAlgorithmName());
	}
}
