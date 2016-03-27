/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadgraph;

import geography.GeographicPoint;
import java.util.Objects;

/**
 *
 * @author HilaryB
 */
public class MapNode implements Comparable {

    private double distance;
    final private GeographicPoint point;
    
    private double aStarDistance; // heuristic estimated cost from vertex to goal
    
    public MapNode parent = null;

    public MapNode(GeographicPoint _point) {
        this.point = _point;
        this.distance = 0.0;
        this.aStarDistance = 0.0;
 
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }
    
    public double calculateDistanceFromSource(MapNode source){
        double d = this.getGeographicPoint().distance(source.getGeographicPoint());
        return d;
    }
    
    public double calculateAstarDistance(MapNode source, MapNode goal){
        double h = this.getGeographicPoint().distance(goal.getGeographicPoint());
        return h + calculateDistanceFromSource(source);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.point);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapNode other = (MapNode) obj;
        if (!Objects.equals(this.point, other.point)) {
            return false;
        }
        return true;
    }

    public GeographicPoint getGeographicPoint() {
        return point;
    }


    @Override
    public int compareTo(Object o) {
        MapNode n = (MapNode) o;
        return ((Double) this.getDistance()).compareTo((Double) n.getDistance());

    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(point.toString()).append("\t");
        sb.append("Weight: ").append(Double.toString(distance));
        return sb.toString();
               
    }
}
