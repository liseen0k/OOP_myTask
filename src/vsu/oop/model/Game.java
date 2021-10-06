package vsu.oop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Cell leftBottom;

    private Map<String, Cell> board;

    private final Map<Player, Direction> preferredDirection = new HashMap<>();

    private Map<Player, List<Figure>> playerListMap1 = new HashMap<>();
    private Map<Player, List<Figure>> playerListMap2 = new HashMap<>();

    private Map<Figure, Cell> m2;

    private Map<Cell, Figure> m3;

    private List<Step> steps;

    public Game(Map<String, Cell> board) {
        this.board = board;
    }

    public Cell getLeftBottom() {
        return leftBottom;
    }

    public Map<Player, List<Figure>> getPlayerListMap1() {
        return playerListMap1;
    }

    public void setPlayerListMap1(Map<Player, List<Figure>> playerListMap1) {
        this.playerListMap1 = playerListMap1;
    }

    public Map<Player, List<Figure>> getPlayerListMap2() {
        return playerListMap2;
    }

    public void setPlayerListMap2(Map<Player, List<Figure>> playerListMap2) {
        this.playerListMap2 = playerListMap2;
    }
}
