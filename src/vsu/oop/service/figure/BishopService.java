package vsu.oop.service.figure;

import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class BishopService implements IFigureService{

    private Cell move(Direction direction, Cell thisCell) {
        if (direction == Direction.NORTH_EAST) thisCell = thisCell.getNorth_east();
        if (direction == Direction.NORTH_WEST) thisCell = thisCell.getNorth_west();
        if (direction == Direction.SOUTH_EAST) thisCell = thisCell.getSouth_east();
        if (direction == Direction.SOUTH_WEST) thisCell = thisCell.getSouth_west();
        return thisCell;
    }

    private int rnd(int size) {
        return (int) Math.random() * ++size;
    }

    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        Cell thisCell = game.getFigureCellMap().get(figure);
        Cell target = game.getFigureCellMap().get(figure);
        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for (Figure f: listOfFigure) {
        while (game.getCellFigureMap().get(target) == null ||
                !game.getCellFigureMap().get(target).equals(f)) {
            target = move(Direction.NORTH_WEST, target);
            variants.add(target);
        } // исправить на do while
        target = thisCell;
        while (game.getCellFigureMap().get(target) == null ||
                !game.getCellFigureMap().get(target).equals(f)) {
            target = move(Direction.NORTH_EAST, target);
            variants.add(target);
        }
        target = thisCell;
        while (game.getCellFigureMap().get(target) == null ||
                !game.getCellFigureMap().get(target).equals(f)) {
            target = move(Direction.SOUTH_WEST, target);
            variants.add(target);
        }
        target = thisCell;
        while (game.getCellFigureMap().get(target) == null ||
                !game.getCellFigureMap().get(target).equals(f)) {
            target = move(Direction.SOUTH_EAST, target);
            variants.add(target);
            }
        }
        return variants; // сократить  код?
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
        }
        else {
            List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().getLast());
            listOfFigure.remove(game.getCellFigureMap().get(step.getNewCell()));
            game.getCellFigureMap().put(step.getNewCell(), figure);
            game.getFigureCellMap().put(figure, step.getNewCell());
        }
        return step;
    }
}
