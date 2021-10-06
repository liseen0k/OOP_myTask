package vsu.oop.model;

public class Figure {

    private FigureType type;

    public Figure(FigureType type) {
        this.type = type;
    }

    public Figure() {}

    public FigureType getType() {
        return type;
    }

    public void setType(FigureType type) {
        this.type = type;
    }
}
