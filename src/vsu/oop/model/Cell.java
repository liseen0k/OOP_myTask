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
}
