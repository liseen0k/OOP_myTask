package vsu.oop.model;

public class Cell {

    private Cell north;
    private Cell south;
    private Cell ost;
    private Cell west;
    private Cell north_ost;
    private Cell north_west;
    private Cell south_ost;
    private Cell south_west;

    public Cell getNorth() {
        return north;
    }

    public void setNorth(Cell north) {
        this.north = north;
    }

    public Cell getSouth() {
        return south;
    }

    public void setSouth(Cell south) {
        this.south = south;
    }

    public Cell getOst() {
        return ost;
    }

    public void setOst(Cell ost) {
        this.ost = ost;
    }

    public Cell getWest() {
        return west;
    }

    public void setWest(Cell west) {
        this.west = west;
    }

    public Cell getNorth_ost() {
        return north_ost;
    }

    public void setNorth_ost(Cell north_ost) {
        this.north_ost = north_ost;
    }

    public Cell getNorth_west() {
        return north_west;
    }

    public void setNorth_west(Cell north_west) {
        this.north_west = north_west;
    }

    public Cell getSouth_ost() {
        return south_ost;
    }

    public void setSouth_ost(Cell south_ost) {
        this.south_ost = south_ost;
    }

    public Cell getSouth_west() {
        return south_west;
    }

    public void setSouth_west(Cell south_west) {
        this.south_west = south_west;
    }
}
