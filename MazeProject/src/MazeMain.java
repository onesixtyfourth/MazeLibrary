import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;

public class MazeMain
{
	public static void main(String [] args)
	{
		Maze maze = new Maze();
		maze.setAlgorithm(new PrimsAlgorithm());		
		maze.generateEdgeList();
		
//		System.out.println(maze.toString() + System.lineSeparator() + maze.getAlgorithm().getAlgorithmName());
		System.out.println(maze.getEdgeList());
	}
}
