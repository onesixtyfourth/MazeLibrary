package uk.co.glasys;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cell
{
	private static final int DEFAULT_SIDES = 4;
	public static int getDefaultSides(){return DEFAULT_SIDES;}
	
	private int numberOfSides;
	public int getNumberOfSides(){return numberOfSides;}
	public void setNumberOfSides(int numberOfSides){this.numberOfSides = numberOfSides;}	

	private int x;
	public int getX(){return x;}
	public void setX(int x){this.x = x;}

	private int y;	
	public int getY(){return y;}
	public void setY(int y){this.y = y;}
	
	private CellState state;	
	public void setState(CellState state){this.state = state;}
	public CellState getState()
	{
		return state;
	}
	
	public static enum CellState
	{//TODO find somewhere better to put this.
		IN, OUT, FRONTIER
	}	
	
	
	private Logger logger = LogManager.getLogger(Cell.class);
	
	public Cell(int x, int y)
	{
		this(x, y, getDefaultSides());
//		logger.info(String.format("Cell two arg constructor x: %d, y: %d", x, y));
	}
	
	public Cell(int x, int y, int sides)
	{	
		setX(x);
		setY(y);
		setNumberOfSides(sides);
		setState(CellState.OUT);
		logger.info(String.format("Cell three arg constructor x: %d, y: %d, sides: %d", x, y, sides));
	}	
	
	@Override
	public String toString()
	{
		return String.format("X: %d, Y: %d, Sides:  %d, State: %s", 
							getX(), getY(), getNumberOfSides(), getState());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		
		if(Objects.nonNull(obj) && this == obj)
		{
			equal = true;
		}
		else if(obj instanceof Cell)
		{
			Cell cell = (Cell)obj;
			if(getNumberOfSides() == cell.getNumberOfSides() 
					&&  getX() == cell.getX() 
					&& getY() == cell.getY()
					&& getState() == cell.getState())
			{
				equal = true;
			}
		}
		return equal;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(getNumberOfSides(), getX(), getY(), getState());
	}
}
