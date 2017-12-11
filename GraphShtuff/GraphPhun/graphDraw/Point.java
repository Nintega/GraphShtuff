package graphDraw;

/**
 * Point.java -- simple 2D point-class for java
 *
 * A. Thall
 * CSC 240 W12
 * 2/25/2012
 */

import java.util.Random;

/**
 * a class for creating and manipulating random points in a unit square
 * @author A. Thall
 */
public class Point
{
    public double x, y;
    
    static Random myRand = new Random();
    /**
     * default constructor creates random point (x, y) in [0,1)x[0,1)
     */
    public Point() {
        
        x = myRand.nextDouble();
        y = myRand.nextDouble();
    }
    
    public Point(double _x, double _y) {
        
        x = _x;
        y = _y;
    }
    
    public double dist(Point a) {
        
        double dx = x - a.x;
        double dy = y - a.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
