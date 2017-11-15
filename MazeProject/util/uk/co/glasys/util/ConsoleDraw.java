package uk.co.glasys.util;

import java.util.List;
import java.util.stream.Collectors;

import uk.co.glasys.Cell;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;

public class ConsoleDraw implements MazeDrawer
{
	private MazeAlgorithm algorithm;
	
	private static final String horizontalLine = "__";
	private static final String verticalLine = "|";
	
	private static final String invisibleHorizontalLine = "  ";
	private static final String invisibleVerticalLine = " ";
	
	public ConsoleDraw(MazeAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}
	
	@Override
	public void drawMaze()
	{
		StringBuilder row = new StringBuilder();
		for(int i = 0; i < algorithm.getMaze().getWidth(); ++i)
		{
			row.append(String.format(" %s", horizontalLine));
		}
		System.out.println(row);
		row.setLength(0);
		
		for (int i = 0; i < algorithm.getMaze().getHeight(); ++i)
		{
			final int rowIndex = i;
			List<Cell> cells = algorithm.getMaze().getCellList()
					.stream()
					.filter(c -> c.getY() == rowIndex)
					.collect(Collectors.toList());

			System.out.println(drawRow(cells));
			row.setLength(0);
		}		
		System.out.println();
		System.out.println(algorithm.getMaze().toString() + " " + algorithm.getAlgorithmName());
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
			
			int cellindex = cell.getY() *  algorithm.getMaze().getWidth() + cell.getX();
			List<Cell> neigbours = algorithm.getMaze().getConnectedCells(cell);//TODO getConnectedCells broken
			
			if(cellindex + algorithm.getMaze().getWidth() < algorithm.getMaze().size() &&
					neigbours.contains(algorithm.getMaze().getCellList().get(
											cellindex + algorithm.getMaze().getWidth())))
			{
				row.append(invisibleHorizontalLine);
			}
			else
			{
				row.append(horizontalLine);
			}

			if( cellindex + 1 < algorithm.getMaze().size() &&
					neigbours.contains(algorithm.getMaze().getCellList().get(cellindex + 1)) )
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
