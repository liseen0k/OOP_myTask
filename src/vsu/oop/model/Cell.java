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

    public Map<Direction, Cell> getDirections() {
        return directions;
    }

//    public void setNorth(Cell cell) {
//        this.directions.put(Direction.NORTH, cell);
//    }
//    public Cell getNorth() {
//        return this.directions.get(Direction.NORTH);
//    }
//
//    public void setSouth(Cell cell) {
//        this.directions.put(Direction.SOUTH, cell);
//    }
//    public Cell getSouth() {
//        return this.directions.get(Direction.SOUTH);
//    }
//
//    public void setWest(Cell cell) {
//        this.directions.put(Direction.WEST, cell);
//    }
//    public Cell getWest() {
//        return this.directions.get(Direction.WEST);
//    }
//
//    public void setEast(Cell cell) {
//        this.directions.put(Direction.EAST, cell);
//    }
//    public Cell getEast() {
//        return this.directions.get(Direction.EAST);
//    }
//
//    public void setNorth_east(Cell cell) {
//        this.directions.put(Direction.NORTH_EAST, cell);
//    }
//    public Cell getNorth_east() {
//        return this.directions.get(Direction.NORTH_EAST);
//    }
//
//    public void setNorth_west(Cell cell) {
//        this.directions.put(Direction.NORTH_WEST, cell);
//    }
//    public Cell getNorth_west() {
//        return this.directions.get(Direction.NORTH_WEST);
//    }
//
//    public void setSouth_east(Cell cell) {
//        this.directions.put(Direction.SOUTH_EAST, cell);
//    }
//    public Cell getSouth_east() {
//        return this.directions.get(Direction.SOUTH_EAST);
//    }
//
//    public void setSouth_west(Cell cell) {
//        this.directions.put(Direction.SOUTH_WEST, cell);
//    }
//    public Cell getSouth_west() {
//        return this.directions.get(Direction.SOUTH_WEST);
//    }
}
