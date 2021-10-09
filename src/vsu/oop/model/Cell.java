package vsu.oop.model;

import java.util.HashMap;
import java.util.Map;

public class Cell {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Map<Direction, Cell> directions = new HashMap<>();

//    private Cell north;
//    private Cell south;
//    private Cell ost;
//    private Cell west;
//    private Cell north_ost;
//    private Cell north_west;
//    private Cell south_ost;
//    private Cell south_west;
//
//    public Cell getNorth() {
//        return north;
//    }
//
//    public void setNorth(Cell north) {
//        this.north = north;
//    }
//
//    public Cell getSouth() {
//        return south;
//    }
//
//    public void setSouth(Cell south) {
//        this.south = south;
//    }
//
//    public Cell getOst() {
//        return ost;
//    }
//
//    public void setOst(Cell ost) {
//        this.ost = ost;
//    }
//
//    public Cell getWest() {
//        return west;
//    }
//
//    public void setWest(Cell west) {
//        this.west = west;
//    }
//
//    public Cell getNorth_ost() {
//        return north_ost;
//    }
//
//    public void setNorth_ost(Cell north_ost) {
//        this.north_ost = north_ost;
//    }
//
//    public Cell getNorth_west() {
//        return north_west;
//    }
//
//    public void setNorth_west(Cell north_west) {
//        this.north_west = north_west;
//    }
//
//    public Cell getSouth_ost() {
//        return south_ost;
//    }
//
//    public void setSouth_ost(Cell south_ost) {
//        this.south_ost = south_ost;
//    }
//
//    public Cell getSouth_west() {
//        return south_west;
//    }
//
//    public void setSouth_west(Cell south_west) {
//        this.south_west = south_west;
//    }

    public void setNorth(Cell cell) {
        this.directions.put(Direction.NORTH, cell);
    }
    public Cell getNorth() {
        return this.directions.get(Direction.NORTH);
    }

    public void setSouth(Cell cell) {
        this.directions.put(Direction.SOUTH, cell);
    }
    public Cell getSouth() {
        return this.directions.get(Direction.SOUTH);
    }

    public void setWest(Cell cell) {
        this.directions.put(Direction.WEST, cell);
    }
    public Cell getWest() {
        return this.directions.get(Direction.WEST);
    }

    public void setEast(Cell cell) {
        this.directions.put(Direction.EAST, cell);
    }
    public Cell getEast() {
        return this.directions.get(Direction.EAST);
    }

    public void setNorth_east(Cell cell) {
        this.directions.put(Direction.NORTH_EAST, cell);
    }
    public Cell getNorth_east() {
        return this.directions.get(Direction.NORTH_EAST);
    }

    public void setNorth_west(Cell cell) {
        this.directions.put(Direction.NORTH_WEST, cell);
    }
    public Cell getNorth_west() {
        return this.directions.get(Direction.NORTH_WEST);
    }

    public void setSouth_east(Cell cell) {
        this.directions.put(Direction.SOUTH_EAST, cell);
    }
    public Cell getSouth_east() {
        return this.directions.get(Direction.SOUTH_EAST);
    }

    public void setSouth_west(Cell cell) {
        this.directions.put(Direction.SOUTH_WEST, cell);
    }
    public Cell getSouth_west() {
        return this.directions.get(Direction.SOUTH_WEST);
    }
}
