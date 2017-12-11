package graphDraw;
import graphTypes.WtAdjMatrixGraph;

import java.util.ArrayList;

/**
 * TestGraph.java -- class to execute tests and display generic points, segments, and graphs
 *    for data in a unit-square
 *
 * A. Thall
 * CSC 240 W12
 * 2/25/2012
 */


import java.util.Random;
import java.awt.*;

import javax.swing.*;

/**
 * @author A. Thall
 * 11/7/17:  added JFrame setLocationRelativeTo(null) to automatically center window
 */
public class TestGraph extends JFrame
{
    Renderer drawing;
    
    /**
     * instance constructor creates JFrame for rendering
     * -- called in main()
     */
    public TestGraph() {
        
        Container content = this.getContentPane();  // get content pane
        content.setLayout(new BorderLayout());      // set its layout
        drawing = new Renderer(this);  // Create a drawing window in the pain
        content.add(drawing, BorderLayout.CENTER);  // center expands
        
        this.setTitle("Swell Graph Renderer");
        this.pack();             // finalize window layout
    }
    
    int choice = 0;
    WtAdjMatrixGraph mat = null;
    Random rand = new Random();
    
    public void createAndShowPoints() {

    	drawing.clearPoints();
    	drawing.clearEdges();

    	if (choice == 0) {
    		// 0:  create complete WtAdjMatrixGraph
    		mat = new WtAdjMatrixGraph(20);
    		for (int i = 0; i < mat.numVerts() - 1; i++)
    			for (int j = i+1; j < mat.numVerts(); j += 1) {
    				if (rand.nextBoolean())
    					mat.addEdge(i, j);
    			}
    		drawing.addGraph(mat);
    		choice = 1;
    		this.setTitle("Swell Graph Renderer: the graph!");
    	}
    	else if (choice == 1) {

    		// 1: compute new single-source shortest path graph from vertex 0
    		//   using Dijkstra's Algorithm
    		SpatiallyMapped SSSPTree = mat.computeDijkstra(0);
    		drawing.addGraph(SSSPTree);
    		choice = 2;
    		this.setTitle("Swell Graph Renderer: Dijkstra's SSSP!");
    	}
    	else if (choice == 2) {

    		// 2: compute BFS tree
    		SpatiallyMapped SSSPTree = mat.computeBFST(0);
    		drawing.addGraph(SSSPTree);
    		choice = 3;
    		this.setTitle("Swell Graph Renderer: BFS Tree!");
    	}
    	else if (choice == 3) {

    		// 3: compute BFS tree
    		SpatiallyMapped SSSPTree = mat.computeDFST(0);
    		drawing.addGraph(SSSPTree);
    		choice = 4;
    		this.setTitle("Swell Graph Renderer: DFS Tree!");
    	}
    	else { 
    		// 4: compute minimum spanning tree using Prim's Algorithm
    		SpatiallyMapped MSTree = mat.mstPrimsAlg();
    		drawing.addGraph(MSTree);
    		choice = 0;
    		this.setTitle("Swell Graph Renderer: Prim's Alg Min. Spanning Tree");
    	}
    	/*
    	else {
    	    // 3:  (not implemented) Compute minimum spanning tree using Kruskal's Algorithm
    		SpatiallyMapped MSTree = mat.KruskalsAlg();
    		drawing.addGraph(MSTree);
    		choice = 0;
    		this.setTitle("Swell Graph Renderer: Kruskal's Alg Min. Spanning Tree");
    	}
    	*/
    }

    /**
     * driver method for min-point-distance finder
     * @param args -- optional number of points > 0
     */
    public static void main(String []args) {
        
        JFrame window = new TestGraph();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        
        ((TestGraph) window).createAndShowPoints();
    }
}