package models.Holes;

import libs.SimplePoint;

public class RoundHole implements HoleInterface {
    private final SimplePoint position;
    private final double diameter;

    public RoundHole(double posX, double posY, double diameter){
        this.position = new SimplePoint(posX, posY);
        this.diameter = diameter;
    }

    public SimplePoint getPosition(){
        return this.position;
    }

    public double getDiameter(){
        return this.diameter;
    }

    @Override
    public boolean isColliding(SimplePoint s) {
        return (s.distanceTo(this.position) <= this.diameter/2);
    }
}
