package uk.co.glasys;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Edge
{
	private Cell from;
	public Cell getFrom(){return from;}
	public void setFrom(Cell from){this.from = from;}
	
	private Cell to;
	public Cell getTo(){return to;}
	public void setTo(Cell to){this.to = to;}	
	
	private Logger logger = LogManager.getLogger(Edge.class);

	public Edge(Cell from, Cell to)
	{
		setFrom(from);
		setTo(to);
		logger.info(String.format("Edge constructor creating edge from: %s, to %s", from, to));
	}
	
	public boolean containsCell(Cell cell)
	{
		boolean result = false;
		
		if(from.equals(cell) || to.equals(cell))
		{
			result = true;
		}
		
		return result;
	}
	
	@Override
	public String toString()
	{
		return String.format("Left Cell: [%s], Right Cell: [%s]%n", getFrom(), getTo());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		
		if(Objects.nonNull(obj) && this == obj)
		{
			equal = true;
		}
		else if(obj instanceof Edge)
		{			
			if(this.getFrom().equals(((Edge)obj).getFrom()) 
					&& this.getTo().equals(((Edge)obj).getTo()))
			{
				equal = true;
			}
		}		
		return equal;
	}	
	
	@Override
	public int hashCode()
	{		
		return Objects.hash(getFrom(), getTo());
	}
}
