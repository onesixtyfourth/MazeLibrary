package uk.co.glasys;

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
}
