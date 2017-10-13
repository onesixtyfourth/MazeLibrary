package uk.co.glasys.tests;

import org.junit.Test;
import uk.co.glasys.Maze;
import static org.junit.Assert.*;
import uk.co.glasys.EdgeListGenerator;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;

public class EdgeListGeneratorTests
{

	@Test
	public void edgeListConstructorTest()
	{
		Maze maze = new Maze();
		MazeAlgorithm algorithm = new PrimsAlgorithm();
		EdgeListGenerator generator = new EdgeListGenerator(maze, algorithm);
		
		assertEquals(maze, generator.getMaze());
		assertEquals(algorithm, generator.getAlgorithm());
	}
}
