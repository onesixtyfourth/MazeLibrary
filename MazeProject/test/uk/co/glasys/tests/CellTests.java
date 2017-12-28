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
		assertTrue("Constructor did not set up correctly", 
				cell.getX() == x && cell.getY() == y && cell.getNumberOfSides() == Cell.getDefaultSides());
	}
	
	@Test
	public void testThreeArgumentConstructor()
	{
		int x = 5;
		int y = 6;
		int sides = 6;
		Cell cell = new Cell(x, y, sides);
		assertTrue("Constructor did not set up correctly", 
				cell.getX() == x && cell.getY() == y && cell.getNumberOfSides() == sides);
	}
	
	@Test
	public void testEqualsMethodForSameObject()
	{
		Cell cell = new Cell(5, 6);
		assertTrue("Equality test failed", cell.equals(cell));
	}
	
	@Test
	public void testEqualsForDifferentEqualObjects()
	{
		Cell cellOne = new Cell(5, 6);
		Cell cellTwo = new Cell(5, 6);
		assertTrue("Equality test failed", cellOne.equals(cellTwo));
	}
	
	@Test
	public void testEqualsMethodFails()
	{
		Cell cellOne = new Cell(5, 6);
		Cell cellTwo = new Cell(6, 7);
		assertFalse("Equality test failed", cellOne.equals(cellTwo));
	}
}
