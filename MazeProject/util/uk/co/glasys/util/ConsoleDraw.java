package uk.co.glasys.util;

import java.util.List;

import uk.co.glasys.Cell;
import uk.co.glasys.Maze;

public class ConsoleDraw implements IDrawMazes
{
	private Maze maze;
	
	private static final String horizontalLine = "__";
	private static final String verticalLine = "|";
	
	private static final String invisibleHorizontalLine = "  ";
	private static final String invisibleVerticalLine = " ";
	
	public ConsoleDraw(Maze maze)
	{
		this.maze = maze;
	}
	
	/* (non-Javadoc)
	 * @see uk.co.glasys.util.IDrawMazes#drawMaze()
	 */
	@Override
	public void drawMaze()
	{
		for(int i = 0; i < maze.getWidth(); ++i)
		{
			System.out.print(String.format(" %s", horizontalLine));
		}
		
		for (int i = 0; i < maze.getHeight(); ++i)
		{
			System.out.println();
			for (int j = 0; j < maze.getWidth(); ++j)
			{
				if (j == 0)
				{
					System.out.print(verticalLine);
				}
				
				int currentIndex = i * maze.getHeight() + j;				
				List<Cell> cells = maze.getConnectedCells(maze.getCellList().get(currentIndex));
				
				if(currentIndex + maze.getWidth() < maze.size() &&
						cells.contains(maze.getCellList().get(currentIndex + maze.getWidth())))
				{
					System.out.print(invisibleHorizontalLine);
				}
				else
				{
					System.out.print(horizontalLine);
					
				}

				if( currentIndex + 1 < maze.size() &&
						cells.contains(maze.getCellList().get(currentIndex + 1)))
				{
					System.out.print(invisibleVerticalLine);
				}
				else
				{
					System.out.print(verticalLine);
					
				}
				
			} 
		}		
	}
}
