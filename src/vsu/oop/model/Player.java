package vsu.oop.model;

public class Player {
    private int name;
    private int stepCount;


    public Player( int name) {
        this.name = name;
        stepCount = 0;
    }



    public int getName() {
        return name;
    }

    public int getStepCount() {
        return stepCount;
    }
}
