package basicgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class that implements a directed graph. The graph may have self-loops,
 * parallel edges. Vertices are labeled by integers 0 .. n-1 and may also have
 * String labels. The edges of the graph are not labeled. Representation of
 * edges via an adjacency matrix.
 *
 * @author UCSD MOOC development team and YOU
 *
 */
public class GraphAdjMatrix extends Graph {

    private final int defaultNumVertices = 5;
    private int[][] adjMatrix;

    /**
     * Create a new empty Graph
     */
    public GraphAdjMatrix() {
        adjMatrix = new int[defaultNumVertices][defaultNumVertices];
    }

    /**
     * Implement the abstract method for adding a vertex. If need to increase
     * dimensions of matrix, double them to amortize cost.
     */
    public void implementAddVertex() {
        int v = getNumVertices();
        if (v >= adjMatrix.length) {
            int[][] newAdjMatrix = new int[v * 2][v * 2];
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    newAdjMatrix[i][j] = adjMatrix[i][j];
                }
            }
            adjMatrix = newAdjMatrix;
        }
        for (int i = 0; i < adjMatrix[v].length; i++) {
            adjMatrix[v][i] = 0;
        }
    }

    /**
     * Implement the abstract method for adding an edge. Allows for multiple
     * edges between two points: the entry at row v, column w stores the number
     * of such edges.
     *
     * @param v the index of the start point for the edge.
     * @param w the index of the end point for the edge.
     */
    public void implementAddEdge(int v, int w) {
        adjMatrix[v][w] += 1;
    }

    /**
     * Implement the abstract method for finding all out-neighbors of a vertex.
     * If there are multiple edges between the vertex and one of its
     * out-neighbors, this neighbor appears once in the list for each of these
     * edges.
     *
     * @param v the index of vertex.
     * @return List<Integer> a list of indices of vertices.
     */
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < getNumVertices(); i++) {
            for (int j = 0; j < adjMatrix[v][i]; j++) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    /**
     * Implement the abstract method for finding all in-neighbors of a vertex.
     * If there are multiple edges from another vertex to this one, the neighbor
     * appears once in the list for each of these edges.
     *
     * @param v the index of vertex.
     * @return List<Integer> a list of indices of vertices.
     */
    public List<Integer> getInNeighbors(int v) {
        List<Integer> inNeighbors = new ArrayList<Integer>();
        for (int i = 0; i < getNumVertices(); i++) {
            for (int j = 0; j < adjMatrix[i][v]; j++) {
                inNeighbors.add(i);
            }
        }
        return inNeighbors;
    }

    //For learners to implement
    /**
     * Implement the abstract method for finding all vertices reachable by two
     * hops from v. Use matrix multiplication to record length 2 paths.
     *
     * @param v the index of vertex.
     * @return List<Integer> a list of indices of vertices.
     */
    //Hilary Brobbey. Week 1. 02/17/2016
    /*
    public List<Integer> getDistance2(int v) {
        //Using matrix multiplication method.
        //Squaring matrix gives 2-Hop Neighbors
        //Matrices A X B of dimensions Am X An; Bm X Bn respectively.
        int mA = adjMatrix.length;
        int nA = adjMatrix[0].length;
        int mB = adjMatrix.length;
        int nB = adjMatrix[0].length;
        if (nA != mB) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        int[][] adjMatrix2Hop = new int[mA][nB];
        for (int r = 0; r < mA; r++) {
            for (int c = 0; c < nB; c++) {
                for (int k = 0; k < nA; k++) {
                    adjMatrix2Hop[r][c] += adjMatrix[r][k] * adjMatrix[k][c];
                }
            }
        }
        // Use getNeighbor code here
        List<Integer> neighbors2Hop = new ArrayList<Integer>();
        for (int i = 0; i < getNumVertices(); i++) {
            for (int j = 0; j < adjMatrix2Hop[v][i]; j++) {
                neighbors2Hop.add(i);
            }
        }
        return neighbors2Hop;

    }
*/
    //Gave up on matrix multiplication method for now.
     public List<Integer> getDistance2(int v) {
         ArrayList<Integer> Neighbor2Hop = new ArrayList<>();
         for (int i = 0; i < getNumVertices(); i++){
             if (adjMatrix[v][i] > 0){
                 Neighbor2Hop.addAll(getNeighbors(i));
             }
         }
         return Neighbor2Hop;
     }
    
    /**
     * Generate string representation of adjacency matrix
     *
     * @return the String
     */
    public String adjacencyString() {
        int dim = adjMatrix.length;
        String s = "Adjacency matrix";
        s += " (size " + dim + "x" + dim + " = " + dim * dim + " integers):";
        for (int i = 0; i < dim; i++) {
            s += "\n\t" + i + ": ";
            for (int j = 0; j < adjMatrix[i].length; j++) {
                s += adjMatrix[i][j] + ", ";
            }
        }
        return s;
    }

}
