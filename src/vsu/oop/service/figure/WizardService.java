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

    private List<Cell> createCellTarget(Game game, Figure figure) {
        List<Cell> cellsTarget = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        cellsTarget.add(cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_EAST));
        cellsTarget.add(cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_WEST));
        cellsTarget.add(cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_EAST));
        cellsTarget.add(cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_WEST));
        cellsTarget.add(cellVar.getDirections().get(Direction.WEST).getDirections().get(Direction.WEST).getDirections().get(Direction.NORTH_WEST));
        cellsTarget.add(cellVar.getDirections().get(Direction.WEST).getDirections().get(Direction.WEST).getDirections().get(Direction.SOUTH_WEST));
        cellsTarget.add(cellVar.getDirections().get(Direction.EAST).getDirections().get(Direction.EAST).getDirections().get(Direction.NORTH_EAST));
        cellsTarget.add(cellVar.getDirections().get(Direction.EAST).getDirections().get(Direction.EAST).getDirections().get(Direction.SOUTH_EAST));
        return cellsTarget;
    }

    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        List<Cell> targets = createCellTarget(game, figure);

        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for (Figure f: listOfFigure) {
            for (Cell c: targets) {
                if (c == null ||
                        !game.getCellFigureMap().get(c).equals(f)) variants.add(c);
            }
            for (Direction d: WIZARD_DIRECTIONS) {
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
