package uk.co.glasys.util;

import java.util.List;

import uk.co.glasys.Cell;
import uk.co.glasys.Maze;

public class ConsoleDraw implements MazeDrawer
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
	
	
	@Override
	public void drawMaze()
	{
		StringBuilder row = new StringBuilder();
		for(int i = 0; i < maze.getWidth(); ++i)
		{
			row.append(String.format(" %s", horizontalLine));
		}
		System.out.println(row);
		row.setLength(0);
		
		for (int i = 0; i < maze.getHeight(); ++i)
		{
			for (int j = 0; j < maze.getWidth(); ++j)
			{
				if (j == 0)
				{
					row.append(verticalLine);
				}
				
				int currentIndex = i * maze.getHeight() + j;				
				List<Cell> cells = maze.getConnectedCells(maze.getCellList().get(currentIndex));
				
				if(currentIndex + maze.getWidth() < maze.size() &&
						cells.contains(maze.getCellList().get(currentIndex + maze.getWidth())))
				{
					row.append(invisibleHorizontalLine);
				}
				else
				{
					row.append(horizontalLine);
					
				}

				if( currentIndex + 1 < maze.size() &&
						cells.contains(maze.getCellList().get(currentIndex + 1)))
				{
					row.append(invisibleVerticalLine);
				}
				else
				{
					row.append(verticalLine);
					
				}
			} 
			System.out.println(row);
			row.setLength(0);
		}		
		System.out.println();
		System.out.println(maze.toString());
	}
}
