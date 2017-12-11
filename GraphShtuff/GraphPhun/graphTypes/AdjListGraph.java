package graphTypes;
import java.util.ArrayList; 
import java.util.Iterator;
import java.util.Random;

import graphDraw.*;

/**
 * 
 */

/**
 * @author adam
 *
 */
public class AdjListGraph extends GraphADT {

	//public static ArrayList[] lg = new ArrayList[nVerts];
	
	ArrayList[] lg = new ArrayList[nVerts];
	//ArrayList<ArrayList<Double> > lg;

	/**
	 * 
	 */
	public AdjListGraph() {
		// TODO Auto-generated constructor stub
		super();//call the constructor from the class above it to initialize the object

	}

	public AdjListGraph (int nVerts) {
		super(nVerts);
		
		//lg = new ArrayList<ArrayList<Double> >();
		for (int i = 0; i < nVerts; i++) {
			lg [i] = new ArrayList<Double>();
			ArrayList<Double> rowi = new ArrayList<Double>();
			lg [i] = rowi;
		}
	}
	
	/**
	 * @param nVerts
	 */
	public AdjListGraph(int nVerts, ArrayList<Point> coords) {//needed for heap
		super(nVerts, coords); //so parent class knows how many vertices there are
		//mat = new ArrayList<ArrayList<Double> >();
		for (int i = 0; i < nVerts; i++) {
			lg [i] = new ArrayList<Double>();
			ArrayList<Double> rowi = new ArrayList<Double>();
			lg [i] = rowi;
		}

		// TODO Auto-generated constructor stub
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
	
	public String toString() {//doing this so you can print out the matrix that's storing the values
		if (nVerts == 0) {
			return "Empty graph: no vertices";
		}
		else {
			String retval = "Graph with " + nVerts + " vertices\n"
							+ "and " + nEdges + " edges\n";
			for (int row = 0; row < lg.length; row++) {
				for (int col = 0; col < lg[row].size(); col++) {
					//int value = lg[row].get(col);//how to I get the value of the arraylist?
					//System.out.print(value);
					System.out.print(lg[row].get(col));
				}
			}
			
			
			
			/*for (int row = 0; row < nVerts; row++) {
				for (int col = 0; col < nVerts; col++) {
					retval += lg.get(row).get(col);
					retval += " ";
				}
				retval += "\n";
			}*/
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
			//mat.get(v0).set(v1, 1.0);//?
			//mat.get(v1).set(v0, 1.0);//?
			for (int i = 0; i < nVerts; i++)
				if (i == v0)
					//how do I just add something onto an arraylist?
			
			
			nEdges += 1;
		}

	}

	/* (non-Javadoc)
	 * @see GraphADT#addRandomEdge()
	 */
	@Override
	public void addRandomEdge() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see GraphADT#hasEdge(int, int)
	 */
	@Override
	public boolean hasEdge(int v0, int v1) {
		
		return false;
		//return mat.get(v0).get(v1) == 1;//will return a boolean if there's a 1 in that row and column
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphADT g = new AdjListGraph(20);
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
		
		//lg.toString();
		System.out.println(g.toString());

	}

}