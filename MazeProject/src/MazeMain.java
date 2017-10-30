import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;
import uk.co.glasys.util.ConsoleDraw;
import uk.co.glasys.util.IDrawMazes;

public class MazeMain
{
	public static void main(String [] args)
	{
		Maze maze = new Maze(10);
		maze.setAlgorithm(new PrimsAlgorithm());		
		maze.generateEdgeList();
		
//		System.out.println(maze.toString() );
//		System.out.println(maze.getCellList());
//		System.out.println(maze.getEdgeList());
		
		IDrawMazes drawMaze = new ConsoleDraw(maze);
		drawMaze.drawMaze();
	}
}
