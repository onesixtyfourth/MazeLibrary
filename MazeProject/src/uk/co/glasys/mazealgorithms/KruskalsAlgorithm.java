package uk.co.glasys.mazealgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import uk.co.glasys.Cell;
import uk.co.glasys.Edge;
import uk.co.glasys.Maze;

public class KruskalsAlgorithm implements MazeAlgorithm
{

	private Maze maze;
	private List<ArrayList<Edge>> edgeSets;
	private Random random = new Random(System.currentTimeMillis());	

	@Override
	public List<Edge> carve(Maze maze)
	{
		this.maze = maze;
		generateInitialEdges();
	
		while(edgeSets.size() > 1)
		{					
			Collections.shuffle(edgeSets, random);
			List<Edge> next = edgeSets.get(0);
			connectPath(next);			
		}
		return edgeSets.get(0);
	}
	
	private void connectPath(List<Edge> edgeSet)
	{
		Collections.shuffle(edgeSet, random);
		Edge next = edgeSet.get(0);

		List<Edge> potentials = potentialEdgesToConnect(next);		
		
		Collections.shuffle(potentials);
		Edge connect = potentials.get(0);
		
		
		
//		List<Edge> connectTo = null;		
		for(List<Edge> pathConnect : edgeSets)
		{
			if(pathConnect != edgeSet && pathConnect.contains(connect) )
			{
				edgeSet.add(connectEdges(next, connect));
				edgeSets.remove(pathConnect);
				edgeSet.addAll(pathConnect);
//				connectTo = pathConnect;
				break;
			}
		}
		
				
	}
	
	private Edge connectEdges(Edge first, Edge second)
	{
		Edge edge = null;
		
		if(canCellsConnect(first.getFrom(), second.getFrom()))
		{
			edge = new Edge(first.getFrom(), second.getFrom());
		}
		else if(canCellsConnect(first.getFrom(), second.getTo()))
		{
			edge = new Edge(first.getFrom(), second.getTo());
		}
		else if(canCellsConnect(first.getTo(), second.getFrom()))
		{
			edge = new Edge(first.getTo(), second.getFrom());
		}
		else if(canCellsConnect(first.getTo(), second.getTo()))
		{
			edge = new Edge(first.getTo(), second.getTo());
		}
		
		return edge;
	}
	
	private List<Edge> potentialEdgesToConnect(Edge edge)
	{
		List<Edge> edges = new ArrayList<Edge>();
		
		for(ArrayList<Edge> edgeList : edgeSets)
		{
			for(Edge e : edgeList)
			{
				if(canCellsConnect(edge.getFrom(), e.getFrom()) ||
						canCellsConnect(edge.getFrom(), e.getTo()) ||
						canCellsConnect(edge.getTo(), e.getFrom()) ||
						canCellsConnect(edge.getTo(), e.getTo()))
				{
					edges.add(e);
				}
			}		
		}
		return edges;
	}
	
	private boolean canCellsConnect(Cell next, Cell potential)
	{
		boolean result = false;
		
		int dx = next.getX() - potential.getX();
		int dy = next.getY() - potential.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		
		if(distance == 1)
		{
			if(next.getX() == potential.getX() || next.getY() == potential.getY())
			{
				result = true;
			}
		}		
		return result;
	}
	
	private void generateInitialEdges()
	{
		edgeSets = new ArrayList<ArrayList<Edge>>();
		for(int i = 0; i < maze.getHeight(); ++i)
		{
			final int rowIndex = i;			
			List<Cell> cells = maze.getCellList()
					.stream()
					.filter(c -> c.getY() == rowIndex)
					.collect(Collectors.toList());
			
			for(Cell cell : cells)
			{
				if(cell.getX() < maze.getWidth() - 1)
				{
					Cell right = maze.getCellList().get(maze.getCellList().indexOf(cell) + 1);
					createEdge(cell, right);
				}
				

				if(cell.getY() < maze.getHeight() - 1)
				{
					Cell down = maze.getCellList().get(maze.getCellList().indexOf(cell) + maze.getWidth());
					createEdge(cell, down);
				}				
			}
			System.out.println(String.format("Finished row %d\n", rowIndex));
		}
	}	
	
	private void createEdge(Cell left, Cell right)
	{
		ArrayList<Edge> tmpSet = new ArrayList<Edge>();
		tmpSet.add(new Edge(left, right));
		edgeSets.add(tmpSet);
		System.out.println(String.format("Adding %s|-|%s", left, right));
	}	
}
