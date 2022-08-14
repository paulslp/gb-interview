package gb.domain.figure;

import gb.begaviour.Figure;

public class Square implements Figure {

    private static final int SIDE_COUNT = 4;

    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getPerimeter() {
        return SIDE_COUNT * sideLength;
    }
}
