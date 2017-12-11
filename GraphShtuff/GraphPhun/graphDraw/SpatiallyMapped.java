/**
 * SpatiallyMapped.java -- interface for being drawable by our Renderer class
 *
 * A. Thall
 * CSC 230 F 2013
 * 10/29/13
 * 
 * 11/7/17:  Refactored:  removed "abstract" from all methods, from older refactoring from abstract class
 *   to interface.
 *           New BRANCH:  added iterators for more straightforward entry into Renderer
 */
package graphDraw;

/**
 * @author mcs
 *
 */
public interface SpatiallyMapped {

	Point loc(int u);
	int numVerts();
	int numEdges();
	
	Iterable<Point> vertexIterable();
	Iterable<Segment> segmentIterable();
}
