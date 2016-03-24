/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadgraph;

import geography.GeographicPoint;

/**
 *
 * @author HilaryB
 */
public class MapNode implements Comparable {

    private double distance;
    final private GeographicPoint point;

    public MapNode(GeographicPoint _point) {
        this.point = _point;
        this.distance = 0.0;
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

    public GeographicPoint getGeographicPoint() {
        return point;
    }
    
    public boolean equals(MapNode o){
        return this.getGeographicPoint().equals(o.getGeographicPoint());       
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
