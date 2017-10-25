package uk.co.glasys.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import uk.co.glasys.Maze;
import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.mazealgorithms.PrimsAlgorithm;

public class AlgorithmTests
{

	@Test
	public void PrimsNameTest()
	{
		MazeAlgorithm algorithm = new PrimsAlgorithm(new Maze());
		assertEquals("Prims", algorithm.getAlgorithmName());
	}

}
