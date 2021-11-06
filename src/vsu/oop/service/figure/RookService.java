package vsu.oop.service.figure;

import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class RookService implements IFigureService{

    private Cell move(Direction direction, Cell thisCell) {
        if (direction == Direction.EAST) thisCell = thisCell.getEast();
        if (direction == Direction.NORTH) thisCell = thisCell.getNorth();
        if (direction == Direction.WEST) thisCell = thisCell.getWest();
        if (direction == Direction.SOUTH) thisCell = thisCell.getSouth();
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
            do {
                target = move(Direction.EAST, target);
                variants.add(target);
            }
            while (game.getCellFigureMap().get(target) == null ||
                    !game.getCellFigureMap().get(target).equals(f));
            target = thisCell;
            do {
                target = move(Direction.WEST, target);
                variants.add(target);
            }
            while (game.getCellFigureMap().get(target) == null ||
                    !game.getCellFigureMap().get(target).equals(f));
            target = thisCell;
            do {
                target = move(Direction.NORTH, target);
                variants.add(target);
            }
            while (game.getCellFigureMap().get(target) == null ||
                    !game.getCellFigureMap().get(target).equals(f));
            target = thisCell;
            do {
                target = move(Direction.SOUTH, target);
                variants.add(target);
            }
            while (game.getCellFigureMap().get(target) == null ||
                    !game.getCellFigureMap().get(target).equals(f));
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
