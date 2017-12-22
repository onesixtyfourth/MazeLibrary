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
		assertTrue(maze.getWidth() == Maze.getDefaultWidth() && maze.getHeight() == Maze.getDefaultWidth());
//		assertNotNull(maze.getCells());
	}

	@Test
	public void testSingleArgumentConstructor()
	{
		int size = 20;
		Maze maze = new Maze(size);
		assertTrue(maze.getWidth() == size && maze.getHeight() == size);
//		assertNotNull(maze.getCells());
	}

	@Test
	public void testTwoArgumentConstructor()
	{
		int width = 20; int height = 30;
		Maze maze = new Maze(width, height);
		assertTrue(maze.getWidth() == width && maze.getHeight() == height);
//		assertNotNull(maze.getCells());
	}	
	
//TODO reset is moving from Maze so refactor when/if it becomes relocated probably in MazeAlgorithm
//	@Test
//	public void testResetMaze()
//	{
//		Maze maze = new Maze();
//		MazeAlgorithm algorithm = new PrimsAlgorithm();
//		algorithm.carve(maze);
//		maze.resetMaze();
//		assertTrue(algorithm.getCells().stream().allMatch(c -> c.getState().equals(CellState.OUT)));
//	}
	
//	@Test
//	public void testCellGeneration()
//	{
//		Maze maze = new Maze();
//		assertTrue(maze.getCells().size() == maze.size());
//	}
	
	@Test
	public void testSize()
	{
		Maze maze = new Maze();
		assertTrue(maze.size() == 100);
	}
	
}
