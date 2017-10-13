package uk.co.glasys;

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
	}		
		
	@Override
	public String toString()
	{
		return String.format("Width = %d, Height = %d", getWidth(), getHeight());
	}
}


