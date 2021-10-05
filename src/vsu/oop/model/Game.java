package vsu.oop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Cell leftBottom;

    private Map<String, Cell> board;

    private final Map<Player, Direction> preferredDirection = new HashMap<>();

    private Map<Player, List<Figure>> playerListMap = new HashMap<>();

    private Map<Figure, Cell> m2;

    private Map<Cell, Figure> m3;

    private List<Step> steps;

    public Game(Map<String, Cell> board) {
        this.board = board;
    }

    public Cell getLeftBottom() {
        return leftBottom;
    }

    public Map<Player, List<Figure>> getPlayerListMap() {
        return playerListMap;
    }
}
