package uk.co.glasys.util;

import java.util.List;
import java.util.stream.Collectors;

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
		
		for (int i = 0; i < maze.getWidth(); ++i)
		{
			final int rowIndex = i;
			List<Cell> cells = maze.getCellList()
					.stream()
					.filter(c -> c.getY() == rowIndex)
					.collect(Collectors.toList());

			System.out.println(drawRow(cells));
			row.setLength(0);
		}		
		System.out.println();
		System.out.println(maze.toString());
	}
	
	private String drawRow(List<Cell> cells)
	{
		StringBuilder row = new StringBuilder();
		
		for(Cell cell : cells)
		{
			if(cell.getX() == 0)
			{
				row.append(verticalLine);
			}
			
			int cellindex = cell.getY() *  maze.getWidth() + cell.getX();
			List<Cell> neigbours = maze.getConnectedCells(cell);
			if(cellindex + maze.getWidth() < maze.size() &&
					neigbours.contains(maze.getCellList().get(cellindex + maze.getWidth())))
			{
				row.append(invisibleHorizontalLine);
			}
			else
			{
				row.append(horizontalLine);
				
			}

			if( cellindex + 1 < maze.size() &&
					neigbours.contains(maze.getCellList().get(cellindex + 1)))
			{
				row.append(invisibleVerticalLine);
			}
			else
			{
				row.append(verticalLine);
				
			}
			
		}
		return row.toString();
	}
}
