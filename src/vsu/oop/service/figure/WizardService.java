package vsu.oop.service.figure;

import vsu.oop.exception.ChessException;
import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class WizardService implements IFigureService{

    private final List<Direction> WIZARD_DIRECTIONS = List.of(Direction.NORTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    private Cell move(Direction direction, Cell thisCell) throws ChessException {
        if (!WIZARD_DIRECTIONS.contains(direction)) {
            throw new ChessException("Incorrect direction");
        }
        return thisCell.getDirections().get(direction);
    }

    private int rnd(int size) {
        return (int) Math.random() * ++size;
    }

    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        Cell cellTarget1 = cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_EAST);
        Cell cellTarget2 = cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_WEST);
        Cell cellTarget3 = cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_EAST);
        Cell cellTarget4 = cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_WEST);
        Cell cellTarget5 = cellVar.getDirections().get(Direction.WEST).getDirections().get(Direction.WEST).getDirections().get(Direction.NORTH_WEST);
        Cell cellTarget6 = cellVar.getDirections().get(Direction.WEST).getDirections().get(Direction.WEST).getDirections().get(Direction.SOUTH_WEST);
        Cell cellTarget7 = cellVar.getDirections().get(Direction.EAST).getDirections().get(Direction.EAST).getDirections().get(Direction.NORTH_EAST);
        Cell cellTarget8 = cellVar.getDirections().get(Direction.EAST).getDirections().get(Direction.EAST).getDirections().get(Direction.SOUTH_EAST);

        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for (Figure f: listOfFigure) {
            if (cellTarget1 == null ||
                    !game.getCellFigureMap().get(cellTarget1).equals(f)) variants.add(cellTarget1);
            else if (cellTarget2 == null ||
                    !game.getCellFigureMap().get(cellTarget2).equals(f)) variants.add(cellTarget2);
            else if (cellTarget3 == null ||
                    !game.getCellFigureMap().get(cellTarget3).equals(f)) variants.add(cellTarget3);
            else if (cellTarget4 == null ||
                    !game.getCellFigureMap().get(cellTarget4).equals(f)) variants.add(cellTarget4);
            else if (cellTarget5 == null ||
                    !game.getCellFigureMap().get(cellTarget5).equals(f)) variants.add(cellTarget5);
            else if (cellTarget6 == null ||
                    !game.getCellFigureMap().get(cellTarget6).equals(f)) variants.add(cellTarget6);
            else if (cellTarget7 == null ||
                    !game.getCellFigureMap().get(cellTarget7).equals(f)) variants.add(cellTarget7);
            else if (cellTarget8 == null ||
                    !game.getCellFigureMap().get(cellTarget8).equals(f)) variants.add(cellTarget8);
            else for (Direction d: WIZARD_DIRECTIONS) {
                    try {
                        cellVar = move(d, cellVar);
                        if (f == null || !listOfFigure.contains(f)) variants.add(cellVar);
                    } catch (ChessException e) {
                        e.printStackTrace();
                    }
                    cellVar = game.getFigureCellMap().get(figure);
                }
        }
        return variants;
    }

    @Override
    public Step moveFigure(Game game, Figure figure, List<Cell> variants) {
        Step step = new Step();
        int index = rnd(variants.size());
        Cell target = variants.get(index);
        step.setOldCell(game.getFigureCellMap().get(figure));
        step.setNewCell(target);
        step.setFigure(figure);
        if (step.getNewCell() == null) {
            game.getCellFigureMap().put(step.getNewCell(), figure);
            game.getFigureCellMap().put(figure, step.getNewCell());
        } else {
            game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().getLast()).remove(game.getCellFigureMap().get(step.getNewCell()));
            game.getCellFigureMap().put(step.getNewCell(), figure);
            game.getFigureCellMap().put(figure, step.getNewCell());
        }
        return step;
    }
}
