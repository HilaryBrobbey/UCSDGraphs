/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadgraph;

import geography.GeographicPoint;

/**
 *
 * @author Hilary Brobbey
 */
public class MapEdge
        implements Comparable {

    private double length;
    private GeographicPoint start;
    private GeographicPoint end;
    private String roadName;
    private String roadType;

    public MapEdge(GeographicPoint from, GeographicPoint to, String _roadName, String _roadType, double _length) {
        length = _length;
        roadType = _roadType;
        roadName = _roadName;
        start = from;
        end = to;
    }

    public double getLength() {
        return this.length;
    }

    public GeographicPoint getStart() {
        return start;
    }

    public GeographicPoint getEnd() {
        return end;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getRoadType() {
        return roadType;
    }
    
    public void setLength(double l){
        this.length = l;
    }

    @Override
    public int compareTo(Object o) {
        MapEdge m = (MapEdge) o;
        return ((Double) this.getLength()).compareTo((Double) m.getLength());
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Start(").append(start.toString()).append(") -> End(").append(end.toString()).append(")").append("\n");
        sb.append("RoadName: ").append(roadName).append(" RoadType: ").append(roadType).append(" Length: ").append(Double.toString(length));
        sb.append("\n");
        return sb.toString();       
    }

}
