import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.HuntAndKillAlgorithm;
import uk.co.glasys.mazealgorithms.KruskalsAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;
import uk.co.glasys.util.ConsoleDraw;

public class MazeMain
{
	public static void main(String [] args)
	{
		Maze maze = new Maze(15, 15);
		
//		System.out.println(maze.toString() );
//		System.out.println(maze.getCellList());
//		System.out.println(maze.getEdgeList());
		
		maze.setAlgorithm(new PrimsAlgorithm());		
		maze.generateEdgeList();		
		ConsoleDraw draw = new ConsoleDraw(maze);
		draw.drawMaze();
		
		maze.setAlgorithm(new HuntAndKillAlgorithm());
		maze.resetMaze();
		maze.generateEdgeList();
		draw = new ConsoleDraw(maze);
		draw.drawMaze();
		
		maze.setAlgorithm(new KruskalsAlgorithm());	
		maze.resetMaze();
		maze.generateEdgeList();
		draw = new ConsoleDraw(maze);
		draw.drawMaze();
	}
}
