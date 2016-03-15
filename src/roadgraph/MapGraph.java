/**
 * @author UCSD MOOC development team and YOU
 *
 * A class which represents a graph of geographic locations Nodes in the graph
 * are intersections between
 *
 */
package roadgraph;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import geography.RoadSegment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 *
 * A class which represents a graph of geographic locations Nodes in the graph
 * are intersections between
 *
 */
public class MapGraph {
    //TODO: Add your member variables here in WEEK 2

    /**
     * Create a new empty MapGraph
     */
    private HashMap<GeographicPoint, ArrayList<GeographicPoint>> GraphAdjList;
    private List<RoadSegment> Edges;
    private int numVertices;
    private int numEdges;

    //For Week3 Dijkstra, and for later.
    //I know! It looks clumsy. Maybe I'll refactor later
    private HashMap<GeographicPoint, ArrayList<MapEdge>> NodeEdgesFromDictionary;

    public MapGraph() {
        // TODO: Implement in this constructor in WEEK 2
        GraphAdjList = new HashMap<>();
        Edges = new ArrayList<>();
        numVertices = 0;
        numEdges = 0;

        NodeEdgesFromDictionary = new HashMap<>();
    }

    /**
     * Get the number of vertices (road intersections) in the graph
     *
     * @return The number of vertices in the graph.
     */
    public int getNumVertices() {
        //TODO: Implement this method in WEEK 2
        return numVertices;
    }

    /**
     * Return the intersections, which are the vertices in this graph.
     *
     * @return The vertices in this graph as GeographicPoints
     */
    public Set<GeographicPoint> getVertices() {
        //TODO: Implement this method in WEEK 2

        return new HashSet<>(GraphAdjList.keySet());
    }

    /**
     * Get the number of road segments in the graph
     *
     * @return The number of edges in the graph.
     */
    public int getNumEdges() {
        //TODO: Implement this method in WEEK 2
        return numEdges;
    }

    /**
     * Add a node corresponding to an intersection at a Geographic Point If the
     * location is already in the graph or null, this method does not change the
     * graph.
     *
     * @param location The location of the intersection
     * @return true if a node was added, false if it was not (the node was
     * already in the graph, or the parameter is null).
     */
    public boolean addVertex(GeographicPoint location) {
        // TODO: Implement this method in WEEK 2
        if (location == null) {
            return false;
        }
        if (GraphAdjList.containsKey(location)) {
            return false;
        }
        GraphAdjList.put(location, new ArrayList<>());
        numVertices++;
        return true;
    }

    /**
     * Adds a directed edge to the graph from pt1 to pt2. Precondition: Both
     * GeographicPoints have already been added to the graph
     *
     * @param from The starting point of the edge
     * @param to The ending point of the edge
     * @param roadName The name of the road
     * @param roadType The type of the road
     * @param length The length of the road, in km
     * @throws IllegalArgumentException If the points have not already been
     * added as nodes to the graph, if any of the arguments is null, or if the
     * length is less than 0.
     */
    public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
            String roadType, double length) throws IllegalArgumentException {
        //TODO: Implement this method in WEEK 2
        if (from == null || to == null || roadName == null || roadType == null) {
            throw new IllegalArgumentException("NULL arguments!");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length of edge cannot be less than zero!");
        }

        if (!GraphAdjList.containsKey(to) || !GraphAdjList.containsKey(from)) {
            throw new IllegalArgumentException("Points are not in graph!");
        }

        List<GeographicPoint> geometry = new ArrayList<GeographicPoint>() {
            {
                add(from);
                add(to);
            }
        };
        addNeighbor(from, to);
        RoadSegment rs = new RoadSegment(from, to, geometry, roadName, roadType, length);
        MapEdge mEdge = new MapEdge(from, to, roadName, roadType, length);
        // if from GeographicPoint doesn't already exit in NodeEdgesFromDictionary then create new mapEdgesList.
        //if it exits just get mapEdgesList
        ArrayList<MapEdge> mapEdgesList = NodeEdgesFromDictionary.get(from) == null ? new ArrayList<>() : NodeEdgesFromDictionary.get(from);
        mapEdgesList.add(mEdge);
        NodeEdgesFromDictionary.put(from, mapEdgesList);
        Edges.add(rs);
        numEdges++;
    }

    /**
     * Find the path from start to goal using breadth first search
     *
     * @param start The starting location
     * @param goal The goal location
     * @return The list of intersections that form the shortest (unweighted)
     * path from start to goal (including both start and goal).
     */
    public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return bfs(start, goal, temp);
    }

    /**
     * Find the path from start to goal using breadth first search
     *
     * @param start The starting location
     * @param goal The goal location
     * @param nodeSearched A hook for visualization. See assignment instructions
     * for how to use it.
     * @return The list of intersections that form the shortest (unweighted)
     * path from start to goal (including both start and goal).
     */
    public List<GeographicPoint> bfs(GeographicPoint start,
            GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 2

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());
        NodeQueue queue = new NodeQueue();
        Set<GeographicPoint> visited = new HashSet<>();
        HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<>();

        queue.enqueue(start);
        visited.add(start);
        parent.put(start, null);
        while (!queue.isEmpty()) {

            GeographicPoint curr = (GeographicPoint) queue.dequeue();
            nodeSearched.accept(curr);

            if (curr.getX() == goal.getX() && curr.getY() == goal.getY()) { //path found
                return pathList(curr, parent);
            }

            for (GeographicPoint n : getNeighbors(curr)) {
                if (!visited.contains(n)) {
                    queue.enqueue(n);
                    visited.add(n);
                    parent.put(n, curr);
                }
            }
        }
        return null;
    }

    /**
     * Find the path from start to goal using Dijkstra's algorithm
     *
     * @param start The starting location
     * @param goal The goal location
     * @return The list of intersections that form the shortest path from start
     * to goal (including both start and goal).
     */
    public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        // You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return dijkstra(start, goal, temp);
    }

    /**
     * Find the path from start to goal using Dijkstra's algorithm
     *
     * @param start The starting location
     * @param goal The goal location
     * @param nodeSearched A hook for visualization. See assignment instructions
     * for how to use it.
     * @return The list of intersections that form the shortest path from start
     * to goal (including both start and goal).
     */
    public List<GeographicPoint> dijkstra(GeographicPoint start,
            GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 3

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());
        HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<>();
        PriorityQueue<MapEdge> pq = new PriorityQueue<>(500);
        Set<GeographicPoint> visited = new HashSet<>();

        parent.put(start, null);

        if (start.equals(goal)) {
            System.out.println("This Works!!");
            return pathList(start, parent);
        }

        for (MapEdge e : NodeEdgesFromDictionary.get(start)) {
            pq.add(e);
            //System.out.println(e.toString());
        }
        visited.add(start);

        while (!pq.isEmpty()) {
            //Print Queue
            //PrintPriorityQueue(pq);
            //System.out.println("Before remove: " + pq.size());
            MapEdge currMapEdge = pq.remove();
            System.out.println(currMapEdge);
            //System.out.println("After remove: " + pq.size());
            //System.out.println(currMapEdge);
            double currMapEdgeLength = currMapEdge.getLength();
            GeographicPoint curr = currMapEdge.getEnd();
            visited.add(curr);
            nodeSearched.accept(curr);

            if (curr.equals(goal)) { //path found
                return pathList(curr, parent);
            }
            if (curr == null){
                System.out.println("Curr is NULL for some reason");
            }
            for (MapEdge e : NodeEdgesFromDictionary.get(curr)) {
               // System.out.println(e);
                e.setLength(e.getLength() + currMapEdgeLength);
                //don't add an edge if the end node has been visited
                if (!visited.contains(e.getEnd())) {
                    pq.add(e);
                    //System.out.println("MapEdge Enqueued: " + e);
                    parent.put(e.getEnd(), curr);
                }

            }
        }
        return null;
    }

    /**
     * Find the path from start to goal using A-Star search
     *
     * @param start The starting location
     * @param goal The goal location
     * @return The list of intersections that form the shortest path from start
     * to goal (including both start and goal).
     */
    public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return aStarSearch(start, goal, temp);
    }

    /**
     * Find the path from start to goal using A-Star search
     *
     * @param start The starting location
     * @param goal The goal location
     * @param nodeSearched A hook for visualization. See assignment instructions
     * for how to use it.
     * @return The list of intersections that form the shortest path from start
     * to goal (including both start and goal).
     */
    public List<GeographicPoint> aStarSearch(GeographicPoint start,
            GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 3

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());
        return null;
    }

    private List<GeographicPoint> getNeighbors(GeographicPoint v) {
        return new ArrayList<>(GraphAdjList.get(v));

    }

    private void addNeighbor(GeographicPoint from, GeographicPoint to) {
        ArrayList neighborsList = GraphAdjList.get(from);
        neighborsList.add(to);
        GraphAdjList.put(from, neighborsList);
    }

    private List<GeographicPoint> pathList(GeographicPoint goal, HashMap<GeographicPoint, GeographicPoint> parent) {
        List<GeographicPoint> path = new ArrayList<>();
        while (goal != null) {
            path.add(0, goal);
            goal = parent.get(goal);
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.print("Making a new map...");
        MapGraph theMap = new MapGraph();
        System.out.print("DONE. \nLoading the map...");
        GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
        System.out.println("DONE.");

        // You can use this method for testing.  
        // Use this code in Week 3 End of Week Quiz
        //MapGraph theMap = new MapGraph();
        /*
         System.out.print("DONE. \nLoading the map...");
         GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
         System.out.println("DONE.");
         */
        //GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
        //GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
        GeographicPoint start = new GeographicPoint(1.0, 1.0);
        GeographicPoint end = new GeographicPoint(8.0, -1.0);

        List<GeographicPoint> route = theMap.dijkstra(start, end);
        //List<GeographicPoint> route2 = theMap.aStarSearch(start, end);
//        System.out.println("---Starting BFS---");
//        List<GeographicPoint> route3 = theMap.bfs(start, end);
//        System.out.println(route3.size());
//        for (GeographicPoint pt : route3) {
//            System.out.println(pt.toString());
//        }

        System.out.println("---START DIJKSTRA---");
        List<GeographicPoint> foundpath = theMap.dijkstra(start, end);
        for (GeographicPoint pt : foundpath) {
            System.out.println(pt.toString());
        }

    }

    private void printParentMap(HashMap<GeographicPoint, GeographicPoint> parent) {
        for (GeographicPoint k : parent.keySet()) {
            System.out.println("Point: " + k + " Parent: " + parent.get(k));
        }
    }

    //Helper
    private void PrintPriorityQueue(PriorityQueue<MapEdge> pq) {
        int count = 0;
        for (MapEdge e : pq) {
            count++;
            System.out.println("++++++++++++++++++++++++++++++++");
            System.out.println("Element # " + count);
            System.out.println(e.toString());
        }

    }

}
