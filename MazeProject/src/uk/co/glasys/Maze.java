package uk.co.glasys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uk.co.glasys.Cell.CellState;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;

public class Maze
{
	private static final int DEFAULT_WIDTH = 10;	
	public static int getDefaultWidth(){return DEFAULT_WIDTH;}

	private int width;
	public int getWidth() {return width;}
	public void setWidth(int width)	{this.width = width;}	

	private int height;
	public int getHeight(){return height;}
	public void setHeight(int height){this.height = height;}	
	
	private MazeAlgorithm algorithm;	
	public MazeAlgorithm getAlgorithm(){return algorithm;}
	public void setAlgorithm(MazeAlgorithm algorithm){this.algorithm = algorithm;}	
	
	protected List<Cell> cellList;	
	public List<Cell> getCellList(){return cellList;}
	public void setCellList(List<Cell> cellList){this.cellList = cellList;}
	
	private List<Edge> edgeList;
	public List<Edge> getEdgeList(){return edgeList;}
	public void setEdgeList(List<Edge> edgeList){this.edgeList = edgeList;}

	
	public Maze()
	{
		this(getDefaultWidth());
	}
	
	public Maze(int size)
	{
		this(size, size);
	}
	
	public Maze(int width, int height)
	{
		setWidth(width);
		setHeight(height);
		setCellList(new ArrayList<Cell>());
		generateCellList();
		setEdgeList(new ArrayList<Edge>());
	}	
	
	public int size()
	{
		return getWidth() * getHeight();
	}
	
	public void generateCellList()
	{
		getCellList().clear();		
		for(int i = 0; i < size(); ++i)
		{
			int x = i % getWidth();
			int y = i / getHeight();
			getCellList().add(new Cell(x, y));
		}
	}
	
	public void generateEdgeList()
	{
		setEdgeList(getAlgorithm().carve(this));
	}
	
	public void resetMaze()
	{
		getEdgeList().clear();
		getCellList().forEach(l -> l.setState(CellState.OUT));
	}
	
	public List<Cell> getConnectedCells(Cell cell)
	{
		List<Cell> cellList = new ArrayList<Cell>();
		
		getEdgeList().forEach(item ->
		{
			if(item.getLeft().equals(cell))
			{
				cellList.add(item.getRight());
			}
			else if(item.getRight().equals(cell))
			{
				cellList.add(item.getLeft());
			}
		});
		return cellList;		
	}
		
	@Override
	public String toString()
	{
		return String.format("Width : %d, Height : %d, Algorithm Name : %s", 
						getWidth(), getHeight(), getAlgorithm().getAlgorithmName());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		
		if(Objects.nonNull(obj) && this == obj)
		{
			equal = true;
		}
		else if(obj instanceof Maze)
		{
			Maze maze = (Maze)obj;
			equal = getHeight() == maze.getHeight() && getWidth() == maze.getWidth();
		}
		return equal;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(getWidth(), getHeight(), algorithm.hashCode(),
						getCellList().hashCode(), getEdgeList().hashCode());
	}
}


