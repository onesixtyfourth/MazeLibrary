package uk.co.glasys.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.glasys.Cell;

public class CellTests
{
	@Test
	public void testTwoArgumentConstructor()
	{
		int x = 5;
		int y = 6;
		Cell cell = new Cell(x, y);
		assertTrue(cell.getX() == x && cell.getY() == y && cell.getNumberOfSides() == Cell.getDefaultSides());
	}
	
	@Test
	public void testThreeArgumentConstructor()
	{
		int x = 5;
		int y = 6;
		int sides = 6;
		Cell cell = new Cell(x, y, sides);
		assertTrue(cell.getX() == x && cell.getY() == y && cell.getNumberOfSides() == sides);
	}
}
