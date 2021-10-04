package vsu.oop.service.figure;

import vsu.oop.model.Cell;
import vsu.oop.model.Figure;
import vsu.oop.model.Game;
import vsu.oop.model.Step;

import java.util.ArrayList;
import java.util.List;

public class PawnService implements IFigureService {

    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> targetList = new ArrayList<>();
        return targetList;
    }

    @Override
    public Step moveFigure(Game game, Figure figure, Cell target) {
        Step step = new Step();

        return step;
    }
}
