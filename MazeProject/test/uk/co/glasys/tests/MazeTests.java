package uk.co.glasys.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.glasys.Maze;

public class MazeTests
{

	@Test
	public void testDefaultConstructor()
	{
		Maze maze = new Maze();
		assertTrue("Constructor did not set up correctly",
				maze.getWidth() == Maze.getDefaultWidth() && maze.getHeight() == Maze.getDefaultWidth());
	}

	@Test
	public void testSingleArgumentConstructor()
	{
		int size = 20;
		Maze maze = new Maze(size);
		assertTrue("Constructor did not set up correctly",
				maze.getWidth() == size && maze.getHeight() == size);
	}

	@Test
	public void testTwoArgumentConstructor()
	{
		int width = 20; int height = 30;
		Maze maze = new Maze(width, height);
		assertTrue("Constructor did not set up correctly",
				maze.getWidth() == width && maze.getHeight() == height);
	}	

	@Test
	public void testSize()
	{
		Maze maze = new Maze();
		assertTrue("Maze size incorrect", maze.size() == 100);
	}
	
}
