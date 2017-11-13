package uk.co.glasys;

import java.util.Objects;

public class Edge
{
	private Cell from;
	public Cell getFrom(){return from;}
	public void setFrom(Cell from){this.from = from;}
	
	private Cell to;
	public Cell getTo(){return to;}
	public void setTo(Cell to){this.to = to;}	
	

	public Edge(Cell from, Cell to)
	{
		setFrom(from);
		setTo(to);
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
