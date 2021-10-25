package vsu.oop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Cell leftBottom;

    private Map<String, Cell> board;

    private final Map<Player, Direction> preferredDirection = new HashMap<>();

    private Map<Player, List<Figure>> playerListOfFiguresMap = new HashMap<>();


    public Map<Figure, Cell> getFigureCellMap() {
        return figureCellMap;
    }

    private Map<Figure, Cell> figureCellMap;

    public Map<String, Cell> getBoard() {
        return board;
    }

    private Map<Cell, Figure> cellFigureMap;

    private List<Step> steps;

    public Game(Map<String, Cell> board) {
        this.board = board;
    }

    public Cell getLeftBottom() {
        return leftBottom;
    }

    public Map<Player, List<Figure>> getPlayerListOfFiguresMap() {
        return playerListOfFiguresMap;
    }

    public void setPlayerListOfFiguresMap(Map<Player, List<Figure>> playerListOfFiguresMap) {
        this.playerListOfFiguresMap = playerListOfFiguresMap;
    }

    public Map<Cell, Figure> getCellFigureMap() {
        return cellFigureMap;
    }
}
