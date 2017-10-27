package uk.co.glasys.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.glasys.Maze;

public class MazeTests
{

	@Test
	public void testDefaultConstructor()
	{
		Maze maze = new Maze();
		assertTrue(maze.getWidth() == Maze.getDefaultWidth() && maze.getHeight() == Maze.getDefaultWidth());
		assertNotNull(maze.getCellList());
		assertNotNull(maze.getEdgeList());
	}

	@Test
	public void testSingleArgumentConstructor()
	{
		int size = 20;
		Maze maze = new Maze(size);
		assertTrue(maze.getWidth() == size && maze.getHeight() == size);
		assertNotNull(maze.getCellList());
		assertNotNull(maze.getEdgeList());
	}

	@Test
	public void testTwoArgumentConstructor()
	{
		int width = 20; int height = 30;
		Maze maze = new Maze(width, height);
		assertTrue(maze.getWidth() == width && maze.getHeight() == height);
		assertNotNull(maze.getCellList());
		assertNotNull(maze.getEdgeList());
	}	
		
	
}
