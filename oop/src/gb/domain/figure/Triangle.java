package gb.domain.figure;

import gb.begaviour.Figure;

public class Triangle implements Figure {

    private double firstSide;

    private double secondSide;

    private double thirdSide;

    public Triangle(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    @Override
    public double getPerimeter() {
        return firstSide + secondSide + thirdSide;
    }
}
