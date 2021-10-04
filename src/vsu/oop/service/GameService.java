package vsu.oop.service;

import vsu.oop.model.*;
import vsu.oop.service.figure.IFigureService;
import vsu.oop.service.figure.PawnService;
import vsu.oop.service.figure.QueenService;

import java.util.*;

public class GameService {

    private final Map<FigureType, IFigureService> figureServiceMap = new HashMap<>();

    public GameService() {

        figureServiceMap.put(FigureType.PAWN, new PawnService());
        figureServiceMap.put(FigureType.QUEEN, new QueenService());
        //figureServiceMap.put(FigureType.PAWN, new PawnService());
    }



    private List<List<Cell>> createBoard() { // добавить еще 4 клетки
        List<Cell> down = createHorizontalLine(10);
        List<List<Cell>> board = new ArrayList<>();
        board.add(down);
       for (int i = 0; i < 9; i++) {
           List<Cell> up = createHorizontalLine(10);

           up.get(0).setSouth(down.get(0));
           down.get(0).setNorth(up.get(0));

           up.get(0).setSouth_ost(down.get(1));
           down.get(1).setNorth_west(up.get(1));

           for (int j = 1; j < 9; j++) {
               up.get(j).setSouth_west(down.get(j-1));
               down.get(j-1).setNorth_ost(up.get(j));

               up.get(j).setSouth(down.get(j));
               down.get(j).setNorth(up.get(j));

               up.get(j).setSouth_ost(down.get(j+1));
               down.get(j+1).setNorth_west(up.get(j));
           }

           up.get(9).setSouth(down.get(9));
           down.get(9).setNorth(up.get(9));

           up.get(9).setSouth_west(down.get(8));
           down.get(8).setNorth_ost(up.get(9));

           board.add(up);
           down = up;

       }
        return board;
    }

    private List<Integer> fillIntList(int count) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            integerList.add(i);
        }
        return integerList;
    }

    private List<Character> fillCharList() {
        List<Character> charList = new ArrayList<>();
        for (char i = 'a'; i <= 'j'; i++) {
            charList.add(i);
        }
        return charList;
    }

    private List<String> toStringList(List<Character> charList) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < charList.size(); i++) {
            stringList.add(charList.get(i).toString());
        }
        return stringList;
    }

    private List<String> toStringListNumber() {
        List<Character> charList = fillCharList();
        List<Integer> integerList = fillIntList(11);
        List<String> stringList = toStringList(charList);
        List<String> numberList =  new ArrayList<>();

        for (int ch = 0; ch < 10; ch++) {
            for (int i = 0; i < 10; i++) {
                String s = stringList.get(ch) + integerList.get(i);
                numberList.add(s);
            }
        }
        numberList.add("w1");
        numberList.add("w2");
        numberList.add("w3");
        numberList.add("w4");
        return numberList;
    }

    private Map<String, Cell> boardToMap(List<List<Cell>> board) {
        Map<String, Cell> newBoard = new HashMap<>();
        List<Character> charList = fillCharList();
        List<Integer> integerList = fillIntList(11);
        List<String> stringList = toStringList(charList);
        List<String> numberList =  new ArrayList<>();

        for (int ch = 0; ch < 11; ch++) {
            for (int i = 0; i < 10; i++) {
               String s = stringList.get(ch) + integerList.get(i);
               numberList.add(s);
            }
        }

        return newBoard;
    }

    private List<Cell> createHorizontalLine(int length) {
        List<Cell> cellList = fillCellList(length);
        connectCellsHorizontal(cellList);
        return cellList;
    }


    private List<Cell> fillCellList(int length) {
        List<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < length-1; i++) {
            cellList.add(new Cell());
        }
        return cellList;
    }


    private List<Cell> connectCellsHorizontal(List<Cell> cellList) {
        for (int i = 1; i < cellList.size()-1; i++) {
            cellList.get(i).setOst(cellList.get(i+1));
            cellList.get(i).setWest(cellList.get(i-1));
        }
        cellList.get(0).setOst(cellList.get(1));
        cellList.get(cellList.size()-1).setWest(cellList.get(cellList.size()-2));
        return cellList;
    }

    public Game createNewGame() {
        createBoard();
        return null;
    }

    public void play(Game game) {
        while (GameState.NORMAL.equals(getState())) {
            doStep(game);
            String gameAsString = printGame(game);
            System.out.println(gameAsString);
        }
    }

    public String printGame(Game game) {
        return "";
    }

    private void doStep(Game game) {
        Player p = null;
        List<Figure> figures = game.getPlayerListMap().get(p);
        for(Figure f: figures) {
            IFigureService service = figureServiceMap.get(f.getType());
            List<Cell> cellsToMove = service.getVariants(game, f);
            if (!cellsToMove.isEmpty()) {
                cellsToMove.get(0);
                //make move
                break;
            }
        }
    }

    private GameState getState() {
        return null;
    }
}
