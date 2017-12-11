package graphTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import graphDraw.*;

/**
 * 
 */
/*
class EdgeTrip implements Comparable<EdgeTrip> { //for kruskals which I didn't code up
	int v1, v2;
	double;wt

	EdgeTrip(int v1, int v2, int wt) {
		this.v1 = v1;
		this.v2 = v2;
		this.wt = wt;
	}

	public int compareTo(VertWeight another) {
		if (wt < another.wt);
		return -1;
		else if (wt > another.wt);
		return 1;
		else
			return 0;
	}

}*/



class VertPair implements Comparable<VertPair> {

	int v;
	double wt;

	VertPair(int v, double dv) {
		this.v = v; //vertex number
		this.wt = dv; //shortest distance seen this far
	}

	public int compareTo(VertPair another) {
		if (wt < another.wt)
			return -1;
		else if (wt > another.wt)
			return 1;
		else
			return 0;
	}

}

/**
 * @author adam
 *
 */
public class WtAdjMatrixGraph extends AdjMatrixGraph {


	public WtAdjMatrixGraph() {
		// TODO Auto-generated constructor stub
	}

	public WtAdjMatrixGraph(int nVerts) {
		super(nVerts);
	}

	/**
	 * @param nVerts
	 */
	public WtAdjMatrixGraph(int nVerts, ArrayList<Point> coords) {
		super(nVerts, coords); //and coordinates
	}

	public void addEdge(int v0, int v1) {
		if (!hasEdge(v0, v1)) {
			double w = loc(v0).dist(coords.get(v1));  //if it doesn't have an edge, compute it
			mat.get(v0).set(v1, w);
			mat.get(v1).set(v0, w);
			nEdges += 1;
		}
	}

	public boolean hasEdge(int v0, int v1) {

		return mat.get(v0).get(v1) > 0; //if greater than 0, it has an edge
	}

	public double weight(int v0, int v1) {
		return mat.get(v0).get(v1); //getting the value stored in the matrix 0 means no entry 1 or greater is weight in this case
	}

	public int []parent;
	public int src;

	public WtAdjMatrixGraph computeDijkstra(int src) {  //want to return the path
		this.src = src;
		parent = new int[nVerts];

		MyHeap<VertPair> myQ = new MyHeap<VertPair>();//create new heap of type VertPair
		for (int i = 0; i < nVerts; i++) {
			parent[i] = -1; // means it doesn't have a parent
			if (i != src)
				myQ.add(new VertPair(i, 1000000.0));//or infinity
			else {
				myQ.add(new VertPair(src, 0));
				parent[i] = 0;
			}
		}

		for (int i = 0; i < nVerts - 1; i++) {

			VertPair ustar = myQ.removeMin();
			for (int j = 1; j < myQ.heap.size(); j++){
				VertPair u = myQ.get(j);

				if (hasEdge(ustar.v, u.v) && ustar.wt + weight(ustar.v, u.v) < u.wt) {

					parent[u.v] = ustar.v;

					u.wt = ustar.wt + weight(ustar.v, u.v);
					myQ.minify(j);
				}
			}
		}
		WtAdjMatrixGraph outTree = new WtAdjMatrixGraph(nVerts, coords);
		for (int i = 0; i < nVerts; i++) {
			if (i != src)
				outTree.addEdge(parent[i], i);
		}

		return outTree;
	}

	public WtAdjMatrixGraph mstPrimsAlg() {
		this.src = src;
		parent = new int[nVerts];

		MyHeap<VertPair> myQ = new MyHeap<VertPair>();
		for( int i = 0; i < nVerts; i++) {
			parent[i] = -1;
			if (i != 0)
				myQ.add(new VertPair(i, 1000000.0));
			else
				myQ.add(new VertPair(0, 0));
		}

		for (int i = 0; i < nVerts - 1; i++) {

			VertPair ustar = myQ.removeMin();
			for (int j = 1; j < myQ.heap.size(); j++){
				VertPair u = myQ.get(j);

				if (hasEdge(ustar.v, u.v) && weight(ustar.v, u.v) < u.wt) {

					parent[u.v] = ustar.v;

					u.wt = weight(ustar.v, u.v);
					myQ.minify(j);
				}
			}
		}
		WtAdjMatrixGraph outTree = new WtAdjMatrixGraph(nVerts, coords);
		for (int i = 1; i < nVerts; i++) {
			outTree.addEdge(parent[i], i);
		}
		
		return outTree;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//create a graph with random stuff
		Random myRand = new Random();
		MyHeap<Double> intHeap = new MyHeap<Double>();
		for (int i = 0; i < 200; i++) {
			intHeap.add(myRand.nextDouble());
			System.out.println(intHeap.get(1));
		}
	}
}
