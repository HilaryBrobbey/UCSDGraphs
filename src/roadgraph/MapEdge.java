/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadgraph;

import geography.GeographicPoint;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hilary Brobbey
 */
public class MapEdge {

    MapNode start;
    MapNode end;

    public MapEdge(MapNode _start, MapNode _end) {
        this.start = _start;
        this.end = _end;
    }

    public MapNode getStartNode() {
        return this.start;
    }

    public MapNode getEndNode() {
        return this.end;
    }

    private List<MapNode> getNodeList() {
        MapNode s = this.getStartNode();
        MapNode e = this.getEndNode();

        return new ArrayList<MapNode>() {
            {
                add(s);
                add(e);
            }
        };
    }
    
    
/*
    public boolean equals(MapEdge o) {
        
        MapNode m1_start = this.getStartNode();
        MapNode m1_end = this.getEndNode();
        MapNode m2_start = o.getStartNode();
        MapNode m2_end = o.getEndNode();
        
        boolean b;
        
        b =  m1_start.equals(m2_start) || m1_start.equals(m2_end);
        b = b && (m1_end.equals(m2_start) || m1_end.equals(m2_end));
        
        return b;

    }
    */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GeographicPoint from = start.getGeographicPoint();
        GeographicPoint to = end.getGeographicPoint();
        sb.append(from.toString()).append("\t").append("-->").append("\t").append(to.toString());
        return sb.toString();
    }

}
