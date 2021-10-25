package vsu.oop.service.figure;

import vsu.oop.model.Cell;
import vsu.oop.model.Figure;
import vsu.oop.model.Game;
import vsu.oop.model.Step;

import java.util.ArrayList;
import java.util.List;

public class KnightService implements IFigureService{
    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        if (cellVar.getNorth().getNorth_east() == null) variants.add(cellVar.getNorth().getNorth_east()); // фигуру можно еще и бить, а не только пустая клетка, добавить это
        else if (cellVar.getNorth().getNorth_west() == null) variants.add(cellVar.getNorth().getNorth_west());
        return variants;
    }

    @Override
    public Step moveFigure(Game game, Figure figure, Cell target) {
        return null;
    }
}
