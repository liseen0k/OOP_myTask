package vsu.oop.service.figure;

import vsu.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class KnightService implements IFigureService{ // не понятно как работает множесво сервисов, передается enum фигура?
    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        Cell cellTarget1 = cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_EAST);
        Cell cellTarget2 = cellVar.getDirections().get(Direction.NORTH).getDirections().get(Direction.NORTH_WEST);
        Cell cellTarget3 = cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_EAST);
        Cell cellTarget4 = cellVar.getDirections().get(Direction.SOUTH).getDirections().get(Direction.SOUTH_WEST);
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
