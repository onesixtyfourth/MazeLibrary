package uk.co.glasys.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.Test;

import uk.co.glasys.mazealgorithms.MazeAlgorithm;
import uk.co.glasys.util.AlgorithmFactory;

public class AlgorithmFactoryTests
{
	@Test
	public void testSingleton()
	{
		AlgorithmFactory f1 = AlgorithmFactory.getAlgorithmFactory();
		AlgorithmFactory f2 = AlgorithmFactory.getAlgorithmFactory();
		
		assertEquals("AlgorithmFactory returned different instances", f1, f2);
	}

	@Test
	public void testPackageName()
	{
		AlgorithmFactory f1 = AlgorithmFactory.getAlgorithmFactory();
		
		assertEquals("Incorrect package name returned", "uk.co.glasys.mazealgorithms", f1.getPackageName());
	}
	
	@Test
	public void testPackagePath()
	{
		AlgorithmFactory f1 = AlgorithmFactory.getAlgorithmFactory();
		
		assertEquals("Incorrect package path returned", "uk/co/glasys/mazealgorithms", f1.getPackagePath());
	}
	
	@Test
	public void testBaseClass()
	{
		AlgorithmFactory f1 = AlgorithmFactory.getAlgorithmFactory();
		
		assertEquals("Base class incorrect", MazeAlgorithm.class, f1.getBaseClass());
	}
	
	@Test
	public void testClassList()
	{
		List<Class<MazeAlgorithm>> classList = AlgorithmFactory.getAlgorithmFactory().getAlgorithmList();
		
		assertTrue("ClassList is null", Objects.nonNull(classList));
		
		for(Class<MazeAlgorithm> algo : classList)
		{
			assertTrue(String.format("%s was not assignable", algo.getCanonicalName()), 
											MazeAlgorithm.class.isAssignableFrom(algo));
		}
	}
}
