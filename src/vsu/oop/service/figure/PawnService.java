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
    public Step moveFigure(Game game, Figure figure, List<Cell> variants) {
        Step step = new Step();
        // получать у фигуры клетку на которой она стоит и из нее высчитывать целевую клетку
        return step;
    }
}
