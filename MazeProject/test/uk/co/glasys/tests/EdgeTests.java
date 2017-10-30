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
		
		assertTrue(edge.getLeft().equals(leftCell) && edge.getRight().equals(rightCell));
	}
	
	@Test
	public void testDeepEquality()
	{
		Edge firstEdge = new Edge(new Cell(5, 5), new Cell(5, 6));
		Edge secondEdge = new Edge(new Cell(5, 5), new Cell(5, 6));
		
		assertTrue(firstEdge.equals(secondEdge));
	}
}
