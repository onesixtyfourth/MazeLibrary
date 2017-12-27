package uk.co.glasys;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private Logger logger = LogManager.getLogger(Maze.class);
	
	public Maze()
	{
		this(getDefaultWidth());
//		logger.info("Maze default constructor");
	}
	
	public Maze(int size)
	{
		this(size, size);
//		logger.info(String.format("Maze single argument constructor size: %d", size));
	}
	
	public Maze(int width, int height)
	{
		setWidth(width);
		setHeight(height);
		logger.info(String.format("Maze constructed with width: %d, height: %d", width, height));
	}	
	
	public int size()
	{
		return getWidth() * getHeight();
	}
	
	@Override
	public String toString()
	{		
		return String.format("Width: %d, Height: %d", getWidth(), getHeight());
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
		return Objects.hash(getWidth(), getHeight());
	}
}