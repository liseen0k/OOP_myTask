package vsu.oop.service.figure;

import vsu.oop.exception.ChessException;
import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class ChampionService implements IFigureService{

    private final List<Direction> CHAMPION_DIRECTIONS = List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.EAST,
            Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST);

    private Cell move(Direction direction, Cell thisCell) throws ChessException {
        if (!CHAMPION_DIRECTIONS.contains(direction)) {
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
        Cell target = game.getFigureCellMap().get(figure);
        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for (Direction d: CHAMPION_DIRECTIONS) {
            try {
                if (d == Direction.NORTH || d == Direction.EAST || d == Direction.SOUTH || d == Direction.WEST) {
                    for (int i = 0; i < 2; i++) {
                        target = move(d, target);
                        Figure f = game.getCellFigureMap().get(target);
                        if (f == null || !listOfFigure.contains(f)) variants.add(target);
                    }
                } else {
                    target = move(d, target);
                    target = move(d, target);
                    Figure f = game.getCellFigureMap().get(target);
                    if (f == null || !listOfFigure.contains(f)) variants.add(target);
                }
            } catch (ChessException e) {
                e.printStackTrace();
            }
            target = game.getFigureCellMap().get(figure);
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
        }
        else {
            game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().getLast()).remove(game.getCellFigureMap().get(step.getNewCell()));
            game.getCellFigureMap().put(step.getNewCell(), figure);
            game.getFigureCellMap().put(figure, step.getNewCell());
        }
        return step;
    }
}
