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
		algorithm.carve(maze);
		
		assertFalse(algorithm.getCells()
							.stream()
							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
		
	}
	
	@Test
	public void testAllCellsInAfterCarvingWithHuntAndKill()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new HuntAndKillAlgorithm();
		algorithm.carve(maze);
		
		assertFalse(algorithm.getCells()
							.stream()
							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
	}
	
	@Test
	public void testKruskalsCarveMethod()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new KruskalsAlgorithm();
		algorithm.carve(maze);
		
		assertFalse(algorithm.getCells()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
		
	}
	
	@Test
	public void testRecursiveCarveMethod()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new RecursiveBacktrackerAlgorithm();
		algorithm.carve(maze);
		
		assertFalse(algorithm.getCells()
				.stream()
				.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
	}
	
//TODO needs refactoring as reset currently will not work from the maze object	
//	@Test
//	public void testResetMaze()
//	{
//		Maze maze = new Maze();
//		MazeAlgorithm algorithm = new PrimsAlgorithm();
//		algorithm.carve(maze);
//		
//		assertFalse(algorithm.getCells()
//							.stream()
//							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
////		assertEquals("Prims", maze.getAlgorithm().getAlgorithmName());
//		
//		algorithm = new HuntAndKillAlgorithm();
//		maze.resetMaze();
//		algorithm.carve(maze);
//		
//		assertFalse(algorithm.getCells()
//							.stream()
//							.anyMatch(s -> s.getState() == CellState.FRONTIER || s.getState() == CellState.OUT));
////		assertEquals("HuntAndKill", maze.getAlgorithm().getAlgorithmName());
//	}
	
	

}
