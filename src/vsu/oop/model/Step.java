package vsu.oop.model;

public class Step {
    private Cell oldCell;
    private Cell newCell;
    private Figure figure;

    public Cell getNewCell() {
        return newCell;
    }

    public void setNewCell(Cell newCell) {
        this.newCell = newCell;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Cell getOldCell() {
        return oldCell;
    }

    public void setOldCell(Cell oldCell) {
        this.oldCell = oldCell;
    }
}
