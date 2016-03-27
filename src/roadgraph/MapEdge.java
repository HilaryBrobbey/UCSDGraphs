/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadgraph;

import geography.GeographicPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hilary Brobbey
 */
public class MapEdge {

    MapNode start;
    MapNode end;

    GeographicPoint firstPoint;
    GeographicPoint secondPoint;

    public MapEdge(MapNode _start, MapNode _end) {
        this.start = _start;
        this.end = _end;

        AssignGeoPoints();
    }

    private void AssignGeoPoints() {
       GeographicPoint globalOrigin = new GeographicPoint(0.0, 0.0);
       
       if (globalOrigin.distance(start.getGeographicPoint()) < globalOrigin.distance(end.getGeographicPoint())){
           this.firstPoint = this.start.getGeographicPoint();
           this.secondPoint = this.end.getGeographicPoint();
       }
       else{
           this.firstPoint = this.end.getGeographicPoint();
           this.secondPoint = this.start.getGeographicPoint();
       }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.firstPoint);
        hash = 89 * hash + Objects.hashCode(this.secondPoint);
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
        final MapEdge other = (MapEdge) obj;
        if (!Objects.equals(this.firstPoint, other.firstPoint)) {
            return false;
        }
        if (!Objects.equals(this.secondPoint, other.secondPoint)) {
            return false;
        }
        return true;
    }

    public MapNode getStartNode() {
        return this.start;
    }

    public MapNode getEndNode() {
        return this.end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GeographicPoint from = start.getGeographicPoint();
        GeographicPoint to = end.getGeographicPoint();
        sb.append(from.toString()).append("\t").append("-->").append("\t").append(to.toString());
        return sb.toString();
    }

}
