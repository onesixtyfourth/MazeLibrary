package uk.co.glasys.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;

public class EdgeTests
{

	@Test
	public void testConstructor()
	{
		Cell leftCell = new Cell(5, 5);
		Cell rightCell = new Cell(5, 6);
		Edge edge = new Edge(leftCell, rightCell);
		
		assertTrue("Constructor did not set up correctly", 
					edge.getFrom().equals(leftCell) && edge.getTo().equals(rightCell));
	}
	
	@Test
	public void testDeepEquality()
	{
		Edge firstEdge = new Edge(new Cell(5, 5), new Cell(5, 6));
		Edge secondEdge = new Edge(new Cell(5, 5), new Cell(5, 6));
		
		assertTrue("Equality test failed", firstEdge.equals(secondEdge));
	}
}
