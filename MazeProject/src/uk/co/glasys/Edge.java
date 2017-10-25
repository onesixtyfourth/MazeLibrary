package uk.co.glasys;

import java.util.Objects;

public class Edge
{
	private Cell left;
	public Cell getLeft(){return left;}
	public void setLeft(Cell left){this.left = left;}
	
	private Cell right;
	public Cell getRight(){return right;}
	public void setRight(Cell right){this.right = right;}	
	

	public Edge(Cell left, Cell right)
	{
		setLeft(left);
		setRight(right);
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
			if(this.getLeft().equals(((Edge)obj).getLeft()) 
					&& this.getRight().equals(((Edge)obj).getRight()))
			{
				equal = true;
			}
		}		
		return equal;
	}	
	
	@Override
	public int hashCode()
	{		
		return Objects.hash(getLeft(), getRight());
	}
}
