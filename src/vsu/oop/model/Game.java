package vsu.oop.model;

import java.util.*;

public class Game {


    private Cell leftBottom;

    private List<Step> stepList;

    private Map<String, Cell> board;

    private Map<Player, Figure> playerKing;

    private Deque playerQueue;

    private Map<Figure, Cell> figureCellMap;

    private Map<Cell, Figure> cellFigureMap;

    private final Map<Player, Direction> preferredDirection = new HashMap<>();

    private Map<Player, List<Figure>> playerListOfFiguresMap = new HashMap<>();

    public Game(Map<String, Cell> board) {
        this.board = board;
    }

    public Map<Player, Figure> getPlayerKing() {
        return playerKing;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public Map<Figure, Cell> getFigureCellMap() {
        return figureCellMap;
    }


    public Deque getPlayerQueue() {
        return playerQueue;
    }


    public Map<String, Cell> getBoard() {
        return board;
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
