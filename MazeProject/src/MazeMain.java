import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.HuntAndKillAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;
import uk.co.glasys.util.ConsoleDraw;

public class MazeMain
{
	public static void main(String [] args)
	{
		Maze maze = new Maze(20);
		maze.setAlgorithm(new HuntAndKillAlgorithm());	
//		maze.setAlgorithm(new PrimsAlgorithm());		
		maze.generateEdgeList();
		
//		System.out.println(maze.toString() );
//		System.out.println(maze.getCellList());
//		System.out.println(maze.getEdgeList());
		
		new ConsoleDraw(maze).drawMaze();
		
	}
}
