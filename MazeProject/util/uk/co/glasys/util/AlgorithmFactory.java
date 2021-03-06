package uk.co.glasys.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.glasys.mazealgorithms.MazeAlgorithm;

public class AlgorithmFactory
{
	private Logger logger = LogManager.getLogger(AlgorithmFactory.class);
	
	private Class<MazeAlgorithm> baseClass = MazeAlgorithm.class;
	public Class<MazeAlgorithm> getBaseClass()
	{
		return baseClass;
	}
	
	private String packageName = "";
	public String getPackageName()
	{
		if(packageName.isEmpty())
		{
			packageName = baseClass.getPackage().toString();
			packageName = packageName.substring(packageName.lastIndexOf(' '), packageName.length()).trim();
		}	
		return packageName;
	}
	
	public String getPackagePath()
	{
		String tmp = getBaseClass().getPackage().toString().replace('.', '/');
		return tmp.substring(tmp.lastIndexOf(' '), tmp.length()).trim();
	}
	
	private List<Class<MazeAlgorithm>> classes;
	public List<Class<MazeAlgorithm>> getAlgorithmList()
	{
		if(Objects.isNull(classes))
		{
			setAlgorithmNames();
		}
		return classes;
	}
	
	private static AlgorithmFactory factoryInstance = null;
	public synchronized static AlgorithmFactory getAlgorithmFactory()
	{
		if(Objects.isNull(factoryInstance))
		{
			factoryInstance = new AlgorithmFactory();
		}
		return factoryInstance;
	}
	
	private AlgorithmFactory()
	{
		logger.info("AlgorithmFactory Created");
	}
	
	public MazeAlgorithm generateAlgorithm() 
	{
		MazeAlgorithm algorithm = null;	

		try
		{
			Collections.shuffle(getAlgorithmList());
			algorithm = (MazeAlgorithm) getAlgorithmList().get(0).newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		logger.info(String.format("Algorithm %s Generated", algorithm.getAlgorithmName()));
		return algorithm;
	}
	
	//TODO needs work if this is ever ran from a jar
	@SuppressWarnings("unchecked")
	private void setAlgorithmNames() 
	{
		classes = new ArrayList<Class<MazeAlgorithm>>();		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();		
		
		try
		{
			Enumeration<URL> res = loader.getResources(getPackagePath());			
			
			while(res.hasMoreElements())
			{
				URL resource = res.nextElement();	
				File dir = new File(resource.getFile());			
				
				for(File file : dir.listFiles() )
				{
					if(!file.getName().contains("MazeAlgorithm"))
					{
						String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
						classes.add( (Class<MazeAlgorithm>) loader.loadClass(getPackageName() + "." + className ));
					}					
				}
			}
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
