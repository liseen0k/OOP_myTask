package vsu.oop.service.figure;

import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class KingService implements IFigureService {

    private Cell move(Direction direction, Cell thisCell) {
        if (direction == Direction.NORTH_EAST) thisCell = thisCell.getNorth_east();
        if (direction == Direction.NORTH_WEST) thisCell = thisCell.getNorth_west();
        if (direction == Direction.SOUTH_EAST) thisCell = thisCell.getSouth_east();
        if (direction == Direction.SOUTH_WEST) thisCell = thisCell.getSouth_west();
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
        Cell target = game.getFigureCellMap().get(figure);
        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for (Figure f : listOfFigure) {
            if (game.getCellFigureMap().get(move(Direction.NORTH, target)) == null
                    || game.getCellFigureMap().get(move(Direction.NORTH, target)).equals(f))
                variants.add(move(Direction.NORTH, target));
            else if (game.getCellFigureMap().get(move(Direction.WEST, target)) == null
                    || game.getCellFigureMap().get(move(Direction.WEST, target)).equals(f))
                variants.add(move(Direction.WEST, target));
            else if (game.getCellFigureMap().get(move(Direction.SOUTH, target)) == null
                    || game.getCellFigureMap().get(move(Direction.SOUTH, target)).equals(f))
                variants.add(move(Direction.SOUTH, target));
            else if (game.getCellFigureMap().get(move(Direction.EAST, target)) == null
                    || game.getCellFigureMap().get(move(Direction.EAST, target)).equals(f))
                variants.add(move(Direction.EAST, target));
            else if (game.getCellFigureMap().get(move(Direction.NORTH_WEST, target)) == null
                    || game.getCellFigureMap().get(move(Direction.NORTH_WEST, target)).equals(f))
                variants.add(move(Direction.NORTH_WEST, target));
            else if (game.getCellFigureMap().get(move(Direction.NORTH_EAST, target)) == null
                    || game.getCellFigureMap().get(move(Direction.NORTH_EAST, target)).equals(f))
                variants.add(move(Direction.NORTH_EAST, target));
            else if (game.getCellFigureMap().get(move(Direction.SOUTH_EAST, target)) == null
                    || game.getCellFigureMap().get(move(Direction.SOUTH_EAST, target)).equals(f))
                variants.add(move(Direction.SOUTH_EAST, target));
            else if (game.getCellFigureMap().get(move(Direction.SOUTH_WEST, target)) == null
                    || game.getCellFigureMap().get(move(Direction.SOUTH_WEST, target)).equals(f))
                variants.add(move(Direction.SOUTH_WEST, target));
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
            List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().getLast());
            listOfFigure.remove(game.getCellFigureMap().get(step.getNewCell()));
            game.getCellFigureMap().put(step.getNewCell(), figure);
            game.getFigureCellMap().put(figure, step.getNewCell());
        }
        return step;
    }
}

