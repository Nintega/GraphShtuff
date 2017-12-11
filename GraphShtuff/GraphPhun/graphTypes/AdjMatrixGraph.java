package graphTypes;
/**
 * MatrixGrpah.java -- a simple Adjacency Matric class extending GraphADT
 * Project; GrapphPhun
 * Adam 
 */
//
//arraylist of an arraylist of integers

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import graphDraw.*;


/**
 * @author adam
 *
 */
public class AdjMatrixGraph extends GraphADT {

	ArrayList<ArrayList<Double> > mat;

	/**
	 * 
	 */
	public AdjMatrixGraph() {
		// TODO Auto-generated constructor stub
		super();//call the constructor from the class above it to initialize the object

	}

	public AdjMatrixGraph (int nVerts) {
		super(nVerts);
		mat = new ArrayList<ArrayList<Double> >();
		for (int i = 0; i < nVerts; i++) {
			ArrayList<Double> rowi = new ArrayList<Double>();
			for (int j = 0; j < nVerts; j++)
				rowi.add(0.0);
			mat.add(rowi);
		}
	}
	
	/**
	 * @param nVerts
	 */
	public AdjMatrixGraph(int nVerts, ArrayList<Point> coords) {//needed for heap
		super(nVerts, coords); //so parent class knows how many vertices there are
		mat = new ArrayList<ArrayList<Double> >();
		for (int i = 0; i < nVerts; i++) {
			ArrayList<Double> rowi = new ArrayList<Double>();
			for (int j = 0; j < nVerts; j++)
				rowi.add(0.0);
			mat.add(rowi);
		}

		// TODO Auto-generated constructor stub
	}


	public String toString() {//doing this so you can print out the matrix that's storing the values
		if (nVerts == 0) {
			return "Empty graph: no vertices";
		}
		else {
			String retval = "Graph with " + nVerts + " vertices\n"
							+ "and " + nEdges + " edges\n";
			for (int row = 0; row < nVerts; row++) {
				for (int col = 0; col < nVerts; col++) {
					retval += mat.get(row).get(col);
					retval += " ";
				}
				retval += "\n";
			}
			return retval;
		}
	}


	/* (non-Javadoc)
	 * @see GraphADT#addVertex()
	 */
	@Override
	public int addVertex() {
		//has to add another arraylist at the end
		//mat.add(new ArrayList<Double>());
		//set all to 0
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see GraphADT#addEdge(int, int)
	 */
	@Override
	public void addEdge(int v0, int v1) { //va and vb will work too //change to include weight. undirected has (v0, v1) and (v1, v0)

		if (!hasEdge(v0, v1)) {//If not has Edge.  We don't want to make it a multigraph so we can't have duplicate edges.
			mat.get(v0).set(v1, 1.0);//?
			mat.get(v1).set(v0, 1.0);//?
			nEdges += 1;
		}

	}

	/* (non-Javadoc)
	 * @see GraphADT#addRandomEdge()
	 */
	@Override
	public void addRandomEdge() {
		// TODO Auto-generated method stub
		
		//Random randedge = new Random(nextInt());

	}

	/* (non-Javadoc)
	 * @see GraphADT#hasEdge(int, int)
	 */
	@Override
	public boolean hasEdge(int v0, int v1) {
		
		
		return mat.get(v0).get(v1) == 1;//will return a boolean if there's a 1 in that row and column
	}

	/* (non-Javadoc)
	 * @see GraphADT#hasPath(int, int)
	 */
	@Override
	public boolean hasPath(int v0, int v1) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see GraphADT#connected()
	 */
	@Override
	public boolean connected() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Segment> segmentIterable() {
		
		ArrayList<Segment> segments = new ArrayList<Segment>();
		
		for (int row = 0; row < nVerts - 1; row++) {
			for (int col = row + 1; col < nVerts; col++) {
				if (hasEdge(row, col))
					segments.add(new Segment(coords.get(row), coords.get(col)));
			}
		}
		return segments;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphADT g = new AdjMatrixGraph(20);
		System.out.println("Created new graph: " + g);
		System.out.println("Let's make a randon graph!!!");
		Random myRand = new Random();

		int numverts = g.numVerts();
		for (int newEdge = 0; newEdge < numverts*numverts/10; newEdge++) {
			int vi = myRand.nextInt(numverts);
			int vj = myRand.nextInt(numverts);
			//if we don't want self edges,
			while (vi == vj)
				vj = myRand.nextInt(numverts);
			g.addEdge(vi, vj);			
		}
		System.out.println("Random graph:" + g);

	}

}
