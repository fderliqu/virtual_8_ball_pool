package components;

public class Hole {
    private SimplePoint position;
    private double diameter;

    public Hole(double posX, double posY, double diameter){
        this.position = new SimplePoint(posX, posY);
        this.diameter = diameter;
    }

    public SimplePoint getPosition(){
        return this.position;
    }

    public double getDiameter(){
        return this.diameter;
    }

    public void setPosition(SimplePoint position){
        this.position = position;
    }

    public void setDiameter(double diameter){
        this.diameter = diameter;
    }
}
