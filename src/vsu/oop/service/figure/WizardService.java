package vsu.oop.service.figure;

import vsu.oop.model.Cell;
import vsu.oop.model.Figure;
import vsu.oop.model.Game;
import vsu.oop.model.Step;

import java.util.List;

public class WizardService implements IFigureService{
    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        return null;
    }

    @Override
    public Step moveFigure(Game game, Figure figure, List<Cell> variants) {
        return null;
    }
}
