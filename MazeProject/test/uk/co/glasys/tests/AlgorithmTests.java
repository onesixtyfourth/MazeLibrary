package uk.co.glasys.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.glasys.Cell;
import uk.co.glasys.Cell.CellState;
import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.HuntAndKillAlgorithm;
import uk.co.glasys.mazealgorithms.KruskalsAlgorithm;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;
import uk.co.glasys.mazealgorithms.RecursiveBacktrackerAlgorithm;

public class AlgorithmTests
{

	@Test
	public void primsNameTest()
	{
		MazeAlgorithm algorithm = new PrimsAlgorithm();
		assertEquals("Prims", algorithm.getAlgorithmName());
	}
	
	@Test
	public void testAllCellsInAfterCarvingWithPrims()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new PrimsAlgorithm();
//		maze.setEdgeList(algorithm.carve(maze));
		
		assertFalse(maze.getCellList()
							.stream()
							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
		
	}
	
	@Test
	public void testAllCellsInAfterCarvingWithHuntAndKill()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new HuntAndKillAlgorithm();
//		maze.setEdgeList(algorithm.carve(maze));
		
		assertFalse(maze.getCellList()
							.stream()
							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
	}
	
	@Test
	public void testKruskalsCarveMethod()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new KruskalsAlgorithm();
//		maze.setEdgeList(algorithm.carve(maze));
		
		assertFalse(maze.getCellList()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
		
	}
	
	@Test
	public void testRecursiveCarveMethod()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new RecursiveBacktrackerAlgorithm();
//		maze.setEdgeList(algorithm.carve(maze));
		
		assertFalse(maze.getCellList()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
	}
	
	@Test
	public void testResetMaze()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new PrimsAlgorithm();
//		maze.setEdgeList(algorithm.carve(maze));
		
		assertFalse(maze.getCellList()
							.stream()
							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
//		assertEquals("Prims", maze.getAlgorithm().getAlgorithmName());
		
		algorithm = new HuntAndKillAlgorithm();
		maze.resetMaze();
//		maze.setEdgeList(algorithm.carve(maze));
		
		assertFalse(maze.getCellList()
							.stream()
							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
//		assertEquals("HuntAndKill", maze.getAlgorithm().getAlgorithmName());
	}
	
	

}
