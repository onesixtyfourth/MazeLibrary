package uk.co.glasys.tests;

import static org.junit.Assert.*;

import java.util.Objects;

import org.junit.Test;

import uk.co.glasys.Maze;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;

public class AlgorithmTests
{

	@Test
	public void primsNameTest()
	{
		MazeAlgorithm algorithm = new PrimsAlgorithm();
		assertEquals("Prims", algorithm.getAlgorithmName());
	}
	
	@Test
	public void testAllCellsInAfterCarving()
	{
		Maze maze = new Maze();
		maze.setAlgorithm(new PrimsAlgorithm());
		maze.generateEdgeList();
		
		assertTrue(maze.getCellList().stream().anyMatch(c -> c.getState() != CellState.OUT || c.getState() != CellState.FRONTIER));
		
	}

}
