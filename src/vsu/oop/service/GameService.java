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

        down.get(0).getDirections().put(Direction.SOUTH_WEST,cell4.get(0));
        cell4.get(0).getDirections().put(Direction.NORTH_EAST,down.get(0));

        down.get(9).getDirections().put(Direction.SOUTH_EAST,cell4.get(1));
        cell4.get(1).getDirections().put(Direction.NORTH_WEST,down.get(9));

       for (int i = 0; i < 9; i++) {
           List<Cell> up = createHorizontalLine(10);

           up.get(0).getDirections().put(Direction.SOUTH,down.get(0));
           down.get(0).getDirections().put(Direction.NORTH,up.get(0));

           up.get(0).getDirections().put(Direction.SOUTH_EAST,down.get(1));
           down.get(1).getDirections().put(Direction.NORTH_WEST,up.get(1));

           for (int j = 1; j < 9; j++) {
               up.get(j).getDirections().put(Direction.SOUTH_WEST,down.get(j-1));
               down.get(j-1).getDirections().put(Direction.NORTH_EAST,up.get(j));

               up.get(j).getDirections().put(Direction.SOUTH,down.get(j));
               down.get(j).getDirections().put(Direction.NORTH,up.get(j));

               up.get(j).getDirections().put(Direction.SOUTH_EAST,down.get(j+1));
               down.get(j+1).getDirections().put(Direction.NORTH_WEST,up.get(j));
           }

           up.get(9).getDirections().put(Direction.SOUTH,down.get(9));
           down.get(9).getDirections().put(Direction.NORTH,up.get(9));

           up.get(9).getDirections().put(Direction.SOUTH_WEST,down.get(8));
           down.get(8).getDirections().put(Direction.NORTH_EAST,up.get(9));

           board.add(up);
           down = up;

       }
        down.get(0).getDirections().put(Direction.NORTH_EAST,cell4.get(2));
        cell4.get(2).getDirections().put(Direction.SOUTH_WEST,down.get(0));

        down.get(9).getDirections().put(Direction.NORTH_WEST,cell4.get(3));
        cell4.get(3).getDirections().put(Direction.NORTH_EAST,down.get(9));

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
            cellList.get(i).getDirections().put(Direction.EAST,cellList.get(i+1));
            cellList.get(i).getDirections().put(Direction.WEST,cellList.get(i-1));
        }
        cellList.get(0).getDirections().put(Direction.EAST,cellList.get(1));
        cellList.get(cellList.size()-1).getDirections().put(Direction.WEST,cellList.get(cellList.size()-2));
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


    private List<Figure> giveToOnePlayer(Game game) {
        List<Figure> figureList = new ArrayList<>();
        for (Map.Entry entry: game.getCellFigureMap().entrySet()) {
            for (char i = 'a'; i <= 'j'; i++) {
                String d = i + "0";
                if (entry.getKey() == d) {
                    figureList.add((Figure) entry.getValue());
                   break;
                }
            }
            for (char i = 'a'; i <= 'j'; i++) {
                String d = i + "1";
                if (entry.getKey() == d) {
                    figureList.add((Figure) entry.getValue());
                    break;
                }
            }
            if (entry.getKey() == "w1" || entry.getKey() == "w2") {
                figureList.add((Figure) entry.getValue());
            }
        }
        return figureList;
    }
    private List<Figure> giveToAnotherPlayer(Game game) {
        List<Figure> figureList = new ArrayList<>();
        for (Map.Entry entry: game.getCellFigureMap().entrySet()) {
            for (char i = 'a'; i <= 'j'; i++) {
                String d = i + "9";
                if (entry.getKey() == d) {
                    figureList.add((Figure) entry.getValue());
                    break;
                }
            }
            for (char i = 'a'; i <= 'j'; i++) {
                String d = i + "8";
                if (entry.getKey() == d) {
                    figureList.add((Figure) entry.getValue());
                    break;
                }
            }
           if (entry.getKey() == "w3" || entry.getKey() == "w4") {
                figureList.add((Figure) entry.getValue());
            }
        }
        return figureList;
    }

    private void giveFigureToPlayers(Game game, List<Player> players) {
        List<Figure> player1 = giveToOnePlayer(game);
        List<Figure> player2 = giveToAnotherPlayer(game);

        game.getPlayerListOfFiguresMap().put(players.get(0), player1);
        game.getPlayerListOfFiguresMap().put(players.get(1), player2);
    }

    private void setFigureToCellMap(Game game) {
        for (Map.Entry entry: game.getCellFigureMap().entrySet()) {
            game.getFigureCellMap().put((Figure) entry.getValue(), (Cell) entry.getKey());
        }
    }

    private void figuresToBoard(Game game) {
        for (Map.Entry entry: game.getBoard().entrySet()) {
            if (entry.getKey() == "a1" || entry.getKey() == "b1" || entry.getKey() == "c1"
            || entry.getKey() == "d1" || entry.getKey() == "e1" || entry.getKey() == "f1"
            || entry.getKey() == "g1" || entry.getKey() == "h1" || entry.getKey() == "i1"
            || entry.getKey() == "j1" || entry.getKey() == "a8" || entry.getKey() == "b8"
            || entry.getKey() == "c8" || entry.getKey() == "d8" || entry.getKey() == "e8"
            || entry.getKey() == "f8" || entry.getKey() == "g8" || entry.getKey() == "h8"
            || entry.getKey() == "i8" || entry.getKey() == "j8") game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.PAWN));
            else if (entry.getKey() == "a0" || entry.getKey() == "a9" || entry.getKey() == "j0" || entry.getKey() == "j9") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.CHAMPION));
            }
            else if (entry.getKey() == "b0" || entry.getKey() == "b9" || entry.getKey() == "i0" || entry.getKey() == "i9") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.ROOK));
            }
            else if (entry.getKey() == "c0" || entry.getKey() == "c9" || entry.getKey() == "h0" || entry.getKey() == "h9") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.KNIGHT));
            }
            else if (entry.getKey() == "d0" || entry.getKey() == "d9" || entry.getKey() == "g0" || entry.getKey() == "g9") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.BISHOP));
            }
            else if (entry.getKey() == "e0" || entry.getKey() == "e9") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.QUEEN));
            }
            else if (entry.getKey() == "f0" || entry.getKey() == "f9") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.KING));
            }
            else if (entry.getKey() == "w1" || entry.getKey() == "w2" || entry.getKey() == "w3" || entry.getKey() == "w4") {
                game.getCellFigureMap().put((Cell) entry.getValue(), new Figure(FigureType.WIZARD));
            }
            else game.getCellFigureMap().put((Cell) entry.getValue(), null);
        }
    }

    public Game createNewGame(int playerCount) {
        Map<String, Cell> chessBoard = createChessBoard();
        Game game = new Game(chessBoard);
        List<Player> players = addPlayers(playerCount);
        figuresToBoard(game);
        setFigureToCellMap(game);
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
        List<Figure> figures = game.getPlayerListOfFiguresMap().get(p);
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
