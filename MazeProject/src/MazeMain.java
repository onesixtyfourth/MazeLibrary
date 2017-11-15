import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.HuntAndKillAlgorithm;
import uk.co.glasys.mazealgorithms.KruskalsAlgorithm;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;
import uk.co.glasys.mazealgorithms.RecursiveBacktrackerAlgorithm;
import uk.co.glasys.util.ConsoleDraw;

public class MazeMain
{
	private static MazeAlgorithm algorithm;
	private static Maze maze;
	private static ConsoleDraw draw;
	
	public static void main(String [] args)
	{
		maze = new Maze(15, 15);
		algorithm = new PrimsAlgorithm();
		carveAndDraw();
		
//		maze.resetMaze();		
//		algorithm = new HuntAndKillAlgorithm() ;
//		carveAndDraw();
//		
//		maze.resetMaze();
//		algorithm = new KruskalsAlgorithm();
//		carveAndDraw();
//		
//		maze.resetMaze();
//		algorithm = new RecursiveBacktrackerAlgorithm();
//		carveAndDraw();
	}
	
	private static void carveAndDraw()
	{
		algorithm.carve(maze);
		draw = new ConsoleDraw(algorithm);
		draw.drawMaze();
	}
}
