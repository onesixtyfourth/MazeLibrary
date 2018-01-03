import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.EllersAlgorithm;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.util.AlgorithmFactory;
import uk.co.glasys.util.ConsoleDraw;

public class MazeMain
{
	private static MazeAlgorithm algorithm;
	private static Maze maze;
	private static ConsoleDraw draw;
	
	public static void main(String [] args)
	{
		maze = new Maze();		
//		for(int i = 0; i < 10; ++i)
//		{
//			algorithm = AlgorithmFactory.getAlgorithmFactory().generateAlgorithm();		
//			carveAndDraw();
//		}
		
		algorithm = new EllersAlgorithm();
		carveAndDraw();
	}
	
	private static void carveAndDraw()
	{
		algorithm.carve(maze);
		draw = new ConsoleDraw(algorithm);
		draw.drawMaze();
	}
}
