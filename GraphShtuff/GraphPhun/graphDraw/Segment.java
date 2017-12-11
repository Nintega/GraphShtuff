package graphDraw;
/**
 * Segment.java -- simple line-segment class for java points
 *
 * A. Thall
 * CSC 240 W12
 * 2/25/2012
 */

/**
 * a container class for pairs of Point objects
 * @author A. Thall
 */
public class Segment
{
    public Point A, B;
    
    /**
     * default constructor leaves variables uninitialized for now
     */
    public Segment() {;}
    
    public Segment(Point a, Point b) {
        
        A = a;
        B = b;
    }
    
    public double length() {
        
        return A.dist(B);
    }
    
    public String toString() {
        
        return "[" + A.toString() + ", " + B.toString() + "]";
    }
}
