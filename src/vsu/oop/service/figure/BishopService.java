package vsu.oop.service.figure;

import vsu.oop.exception.ChessException;
import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class BishopService implements IFigureService {

    private final List<Direction> BISHOP_DIRECTIONS = List.of(Direction.NORTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    private Cell move(Direction direction, Cell thisCell) throws ChessException {
        if (!BISHOP_DIRECTIONS.contains(direction)) {
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
        Cell thisCell = game.getFigureCellMap().get(figure);
        Cell target = game.getFigureCellMap().get(figure);
        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for(Direction d: BISHOP_DIRECTIONS) {
            Figure f = game.getCellFigureMap().get(target);
            do {
                try {
                    target = move(d, target);
                    variants.add(target);
                    f = game.getCellFigureMap().get(target);
                } catch (ChessException e) {
                    e.printStackTrace();
                }
            }
            while (f == null || !listOfFigure.contains(f));
            target = thisCell;
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
