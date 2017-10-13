package uk.co.glasys.mazealgorithms;

import java.util.List;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;


public class PrimsAlgorithm extends MazeAlgorithm
{
	@Override
	public List<Edge> carveMaze(Maze maze)
	{
		cellList = generateCellList(maze);	
		
		return null;
	}	
}
