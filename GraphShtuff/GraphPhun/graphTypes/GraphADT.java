package graphTypes;
/**
 * GraphADT.java -- parent class for simple graph-manipulation project
 * Adam Ferrara
 * CSC 230 Fall 2013
 * 10/14/13
 * 
 * 11/8/17:  A. Thall -- Refactored:  renamed GraphADT from older MyGraph
 * 11/9/17:  Added boolean isIsomorphic(GraphADT g); interface for isomorphism test (Molina request)
 */

import graphDraw.*;

import java.util.ArrayList;

/**
 * @author adam
 *
 */
public abstract class GraphADT implements SpatiallyMapped {

	/**
	 * What's a Graph?
	 */
	
	int nVerts;
	int nEdges;
	
	public ArrayList<Point> coords;//needed for spatiallymapped
	
	public GraphADT() {
		
		nVerts = 0;
		nEdges = 0;
		coords = new ArrayList<Point>();
		
	}
	
	public GraphADT(int nVerts) {
		this.nVerts = nVerts;
		this.nEdges = 0;
		coords = new ArrayList<Point>();
		for (int i = 0; i < nVerts; i++)
			coords.add(new Point());
	}
	
	public GraphADT(int nVerts, ArrayList<Point> coords) {
		this.coords = coords;
		this.nVerts = nVerts;
		this.nEdges = 0;
	}

	public int numEdges() { return nEdges; }
	public int numVerts() { return nVerts; }
	public Point loc(int i) { return coords.get(i); }
	
	public Iterable<Point> vertexIterable() {
		return coords;
	}
	
	public abstract Iterable<Segment> segmentIterable();
	
	public abstract int addVertex();
	public abstract void addEdge(int v0, int v1);//guarantee any child will have an addEdge
	//public abstract void removeEdge(int v0, int v1);//need to add this to every one
	public abstract void addRandomEdge();
	
	public abstract boolean hasEdge(int v0, int v1);
	public abstract boolean hasPath(int v0, int v1);
	public abstract boolean connected(); //can you reach every vertex from tha one?
	//public abstract ArrayList<Integer> unweightSSSP(int v);//implement list and matrix graph as well
	// public abstract boolean isIsomorphic(GraphADT g);
	
	public GraphADT computeBFST(int startVert) {
		
		boolean []visited = new boolean[numVerts()];
		
		ArrayList<Integer> nodesToVisitFrom = new ArrayList<Integer>();
		
		AdjMatrixGraph bfsT = new AdjMatrixGraph(numVerts(), coords);
		
		nodesToVisitFrom.add(startVert);
		visited[startVert] = true;
		// nodesToVisit contains FIFO queue of nodes to visit on BFS
		while (nodesToVisitFrom.size() > 0) {
			int node = nodesToVisitFrom.remove(0);
			for (int i = 0; i < numVerts(); i++) {
				if ((!visited[i]) && hasEdge(node, i)) {
					visited[i] = true;
					nodesToVisitFrom.add(i);
					bfsT.addEdge(node, i);
				}
			}
		}
		return bfsT;
	}

	AdjMatrixGraph dfsT;
	boolean []visited;
	public GraphADT computeDFST(int startVert) {
		
		visited = new boolean[numVerts()];
		dfsT = new AdjMatrixGraph(numVerts(), coords);

		visited[startVert] = true;
		dfVisitFrom(startVert, numVerts() - 1);
		return dfsT;
	}

	private void dfVisitFrom(int node, int vertsLeft) {
		if (vertsLeft == 0)
			return;
		
		for (int i = 0; i < numVerts(); i++) {
			if (!visited[i] && hasEdge(node, i)) {
				visited[i] = true;
				dfsT.addEdge(node, i);
				dfVisitFrom(i, vertsLeft - 1);
			}
		}
	}
	
	public static void main(String []args) {
		
		GraphADT thisG = null;
		//can't do GraphADT thisG = new GraphADT(); because it is an abstract class.
		//   You can define it and you can assign child classes to this pointer, but you can't 
		//   instantiate it
		
		System.out.println("This does absolutely nothing");
	}
	

}
