package graphDraw;

/**
 * Renderer.java -- a general purpose rendering class for rendering
 *   Points, Segments, and Graph vertices
 *
 * A. Thall
 * CSC 240 W12
 * 2/25/2012
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

/**
 * creates a drawing window to pass Point []  and PointPairs
 */
public class Renderer extends JPanel implements MouseListener
{
    // a Renderer knows its parent frame, so can communicate with code in TestGraph
    TestGraph testGraphFrame;
    
    public static final int HEIGHT = 512;
    public static final int WIDTH = 512;
    
    public static final int OFFSET = 10;
    public static final int XWIN = WIDTH - 2*OFFSET;
    public static final int YWIN = HEIGHT - 2*OFFSET;
    
    public static final int NODESIZE = 8;
    
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Color> pointColors = new ArrayList<Color>();
    
    ArrayList<Segment> edges = new ArrayList<Segment>();
    ArrayList<Color> edgeColors = new ArrayList<Color>();
    
    public Renderer(TestGraph frame) {  // constructor
        
        testGraphFrame = frame;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addMouseListener(this);       // listen to mouse events
    }
    
    public void addPoint(Point p) {
        
        points.add(p);
        pointColors.add(Color.WHITE);
        this.repaint();
    }
    
    public void addPoints(Point []p) {
        for (Point pt : p) {
            points.add(pt);
            pointColors.add(Color.WHITE);
        }
        this.repaint();
    }
    
    public void addPoints(Collection<Point> p) {
        for (Point pt : p) {
            points.add(pt);
            pointColors.add(Color.WHITE);
        }
        this.repaint();
    }
    
    public void addEdge(Point a, Point b) {
        edges.add(new Segment(a, b));
        edgeColors.add(Color.GREEN);
        this.repaint();
    }
    
    public void addEdge(Segment e) {
        
        edges.add(e);
        edgeColors.add(Color.GREEN);
        this.repaint();
    }
    
    public void addEdges(Segment []e) {
        
        for (Segment edge : e) {
            edges.add(edge);
            edgeColors.add(Color.GREEN);
        }
        this.repaint();
    }
    
    public void addEdges(Collection<Segment> e) {
        for (Segment edge : e) {
            edges.add(edge);
            edgeColors.add(Color.GREEN);
        }
        this.repaint();
    }
    
    public void addGraph(SpatiallyMapped graph) {
    	
    	for (Point p : graph.vertexIterable())
			addPoint(p);
    	
    	for (Segment s : graph.segmentIterable())
    		addEdge(s.A, s.B);
    }
    public void clearPoints() {
        
        points.clear();
        pointColors.clear();
        this.repaint();
    }
    
    public void clearEdges() {
        edges.clear();
        edgeColors.clear();
        this.repaint();
    }
    int numTimes = 0;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background, borders
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.GRAY);
        g.drawRect(OFFSET, OFFSET, XWIN, YWIN);
        
        for (int edgenum = 0; edgenum < edges.size(); edgenum++) {
            Segment e = edges.get(edgenum);
            g.setColor(edgeColors.get(edgenum));
            g.drawLine((int) Math.floor(e.A.x*XWIN) + OFFSET,
                       (int) Math.floor(e.A.y*YWIN) + OFFSET,
                       (int) Math.floor(e.B.x*XWIN) + OFFSET,
                       (int) Math.floor(e.B.y*YWIN) + OFFSET);
        }
        
        for (int ptnum = 0; ptnum < points.size(); ptnum++) {
            Point p = points.get(ptnum);
            g.setColor(pointColors.get(ptnum));
            g.fillOval(((int) Math.floor(p.x*XWIN)) + OFFSET - NODESIZE/2,
                       ((int) Math.floor(p.y*YWIN)) + OFFSET - NODESIZE/2,
                       NODESIZE, NODESIZE);
        }
    }
    
    /**
     * bunch of stuff for setting callbacks---mousePressed() only one active,
     *   tells containing TestGraph to create a new pointset, rerun and redisplay
     *   a test, etc.
     */
    // Need to define implementation methods or class will be abstract
    public void mouseClicked(MouseEvent e) {} // ignore
    public void mouseEntered(MouseEvent e) {}  // ignore
    public void mouseExited(MouseEvent e) {}  // ignore
    public void mouseReleased(MouseEvent e) {} // ignore
    
    public void mousePressed(MouseEvent e) {
        testGraphFrame.createAndShowPoints();
        repaint();
    } 
    
}
