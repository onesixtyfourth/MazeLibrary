package uk.co.glasys.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import uk.co.glasys.mazealgorithms.MazeAlgorithm;

public class AlgorithmFactory
{
	private static List<Class> classes;
	
	public static MazeAlgorithm generateAlgorithm() 
	{
		MazeAlgorithm algorithm = null;	
		if(!Objects.nonNull(classes))
		{
			classes = getAllAlgorithmNames();	
		}			
		try
		{
			Collections.shuffle(classes);
			 algorithm = (MazeAlgorithm) classes.get(0).newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return algorithm;
	}
	
	private static List<Class> getAllAlgorithmNames() 
	{
		classes = new ArrayList<Class>();		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();		
		Class<?>c = MazeAlgorithm.class;		
		String tmp = c.getPackage().toString().replace('.', '/');
		String pkg = tmp.substring(tmp.lastIndexOf(' '), tmp.length()).trim();
		
		try
		{
			Enumeration<URL> res = loader.getResources(pkg);			
			
			while(res.hasMoreElements())
			{
				URL resource = res.nextElement();	
				File dir = new File(resource.getFile());			
				
				for(File file : dir.listFiles() )
				{
					if(!file.getName().contains("MazeAlgorithm"))
					{
						String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
						classes.add(loader.loadClass("uk.co.glasys.mazealgorithms." + className));
					}					
				}
			}
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return classes;
	}
}
