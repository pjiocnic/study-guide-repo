
package com.example.model;
import java.util.List;

public class Location {
    private String type;
    private String zip;
    private List<String> coords;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public List<String> getCoords() { return coords; }
    public void setCoords(List<String> coords) { this.coords = coords; }
}
