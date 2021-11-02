package vsu.oop.service.figure;

import vsu.oop.model.Cell;
import vsu.oop.model.Figure;
import vsu.oop.model.Game;
import vsu.oop.model.Step;

import java.util.ArrayList;
import java.util.List;

public class KnightService implements IFigureService{ // не понятно как работает множесво сервисов, передается enum фигура?
    @Override
    public List<Cell> getVariants(Game game, Figure figure) {
        List<Cell> variants = new ArrayList<>();
        Cell cellVar = game.getFigureCellMap().get(figure);
        Cell cellTarget1 = cellVar.getNorth().getNorth_east();
        Cell cellTarget2 = cellVar.getNorth().getNorth_west();
        Cell cellTarget3 = cellVar.getSouth().getSouth_east();
        Cell cellTarget4 = cellVar.getSouth().getSouth_west();
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
            List<Figure> listOfFigure = game.getPlayerListOfFiguresMap().get(game.getPlayerQueue().getLast());
            listOfFigure.remove(game.getCellFigureMap().get(step.getNewCell()));
            game.getCellFigureMap().put(step.getNewCell(), figure);
            game.getFigureCellMap().put(figure, step.getNewCell());
        }
        return step;
    }
}
