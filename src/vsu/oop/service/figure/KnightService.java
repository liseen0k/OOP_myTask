package vsu.oop.service.figure;

import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class KnightService implements IFigureService{

    private List<Cell> createCellTarget(Game game, Figure figure) {
        List<Cell> cellsTarget = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        cellsTarget.add(cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_EAST));
        cellsTarget.add(cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_WEST));
        cellsTarget.add(cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_EAST));
        cellsTarget.add(cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_WEST));
        return cellsTarget;
    }

    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        List<Cell> target = createCellTarget(game, figure);
        List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().peek());
        for (Figure f: listOfFigure) {
            for (Cell c: target) {
                if (c == null ||
                        !game.getCellFigureMap().get(c).equals(f)) variants.add(c);
            }
        }
        return variants;
    }

    private int rnd(int size) {
       return (int) Math.random() * ++size;
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
