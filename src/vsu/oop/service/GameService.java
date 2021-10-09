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


    private List<Cell> createCell4() {
        List<Cell> cell4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cell4.add(new Cell());
        }
        return cell4;
    }
    private List<List<Cell>> createBoardList() {
        List<Cell> down = createHorizontalLine(10);
        List<Cell> cell4 = createCell4();
        List<List<Cell>> board = new ArrayList<>();
        board.add(down);

        down.get(0).setSouth_west(cell4.get(0));
        cell4.get(0).setNorth_east(down.get(0));

        down.get(9).setSouth_east(cell4.get(1));
        cell4.get(1).setNorth_west(down.get(9));

       for (int i = 0; i < 9; i++) {
           List<Cell> up = createHorizontalLine(10);

           up.get(0).setSouth(down.get(0));
           down.get(0).setNorth(up.get(0));

           up.get(0).setSouth_east(down.get(1));
           down.get(1).setNorth_west(up.get(1));

           for (int j = 1; j < 9; j++) {
               up.get(j).setSouth_west(down.get(j-1));
               down.get(j-1).setNorth_east(up.get(j));

               up.get(j).setSouth(down.get(j));
               down.get(j).setNorth(up.get(j));

               up.get(j).setSouth_east(down.get(j+1));
               down.get(j+1).setNorth_west(up.get(j));
           }

           up.get(9).setSouth(down.get(9));
           down.get(9).setNorth(up.get(9));

           up.get(9).setSouth_west(down.get(8));
           down.get(8).setNorth_east(up.get(9));

           board.add(up);
           down = up;

       }
        down.get(0).setNorth_east(cell4.get(2));
        cell4.get(2).setSouth_west(down.get(0));

        down.get(9).setNorth_west(cell4.get(3));
        cell4.get(3).setSouth_east(down.get(9));

        board.add(cell4);
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

    private List<Cell> boarToCellList(List<List<Cell>> board) {
        List<Cell> boardAsList = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                boardAsList.add(board.get(i).get(j));
            }
        }
        return boardAsList;
    }

    private Map<String, Cell> boardToMap(List<List<Cell>> board) {
        Map<String, Cell> newBoard = new HashMap<>();
        List<String> numberCellList = toStringListNumber();
        List<Cell> boardAsList = boarToCellList(board);
        for (int i = 0; i < numberCellList.size(); i++) {
            newBoard.put(numberCellList.get(i), boardAsList.get(i));
            newBoard.get(numberCellList.get(i)).setName(numberCellList.get(i));
        }

        return newBoard;
    }

    public Map<String, Cell> createChessBoard() {
        List<List<Cell>> boardAsList = createBoardList();
        Map<String, Cell> boardAsMap = boardToMap(boardAsList);
        return boardAsMap;
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
            cellList.get(i).setEast(cellList.get(i+1));
            cellList.get(i).setWest(cellList.get(i-1));
        }
        cellList.get(0).setEast(cellList.get(1));
        cellList.get(cellList.size()-1).setWest(cellList.get(cellList.size()-2));
        return cellList;
    }

    private List<Player> addPlayers(int number) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Player player = new Player(i);
            playerList.add(player);
        }
        return playerList;
    }

    private List<Figure> setFigureList() {
        List<Figure> figureList = new ArrayList<>();
        figureList.add(new Figure(FigureType.CHAMPION));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.ROOK));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.KNIGHT));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.BISHOP));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.QUEEN));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.KING));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.BISHOP));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.KNIGHT));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.ROOK));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.CHAMPION));
        figureList.add(new Figure(FigureType.PAWN));
        figureList.add(new Figure(FigureType.WIZARD));
        figureList.add(new Figure(FigureType.WIZARD));
        return figureList;
    }

    private void giveFigureToPlayers(Game game, List<Player> players) {
        List<Figure> player1figureList = setFigureList();
        List<Figure> player2figureList = setFigureList();
        Map<Player, List<Figure>> map = new HashMap<>();
        map.put(players.get(0), player1figureList);
        Map<Player, List<Figure>> map1 = new HashMap<>();
        map.put(players.get(1), player2figureList);
        game.setPlayerListMap1(map);
        game.setPlayerListMap2(map1);
    }

    private Map<Figure, Cell> figureOnBoard(Map<Player, Figure> playerMap, Map<String, Cell> cellMap) {
        Map<Figure, Cell> figures = new HashMap<>();
        
        return figures;
    }

    public Game createNewGame(int playerCount) {
        Map<String, Cell> chessBoard = createChessBoard();
        Game game = new Game(chessBoard);
        List<Player> players = addPlayers(playerCount);
        giveFigureToPlayers(game, players);


        return game;
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
        List<Figure> figures = game.getPlayerListMap1().get(p);
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
