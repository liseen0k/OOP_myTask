package vsu.oop.service.figure;

import vsu.oop.model.Cell;
import vsu.oop.model.Figure;
import vsu.oop.model.Game;
import vsu.oop.model.Step;

import java.util.List;

public interface IFigureService {

    List<Cell> getVariants(Game game, Figure figure);

    Step moveFigure(Game game, Figure figure, Cell target);
}
