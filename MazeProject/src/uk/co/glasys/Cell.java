package uk.co.glasys;

import java.util.Objects;

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
	
	public Cell(int x, int y)
	{
		this(x, y, DEFAULT_SIDES);
	}
	
	public Cell(int x, int y, int sides)
	{	
		setX(x);
		setY(y);
		setNumberOfSides(sides);
	}
	
	@Override
	public String toString()
	{
		return String.format("X = %d, Y = %d, Sides = %d", getX(), getY(), getNumberOfSides());
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
					&& getY() == cell.getY())
			{
				equal = true;
			}
		}
		return equal;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(getNumberOfSides(), getX(), getY());
	}
}
