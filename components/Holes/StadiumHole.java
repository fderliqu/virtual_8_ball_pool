package components.Holes;

import libs.SimplePoint;

public class StadiumHole implements Hole {

    private final SimplePoint c1;
    private final SimplePoint c2;
    private final double thickness;

    public StadiumHole(SimplePoint c1, SimplePoint c2, double thickness) {
        this.c1 = c1;
        this.c2 = c2;
        this.thickness = thickness;
    }

    public SimplePoint getC1() {return c1;}
    public SimplePoint getC2() {return c2;}
    public double getThickness() {return thickness;}

    @Override
    public boolean isColliding (SimplePoint s){
        /*
        * Here is the function found on SO, it is supposed to compute the distance to
        * the main segment in the stadium.
        * */
        double numerator   = Math.abs(((c2.getX()-c1.getX())*(c1.getY()-s.getY()))-((c1.getX()-s.getX())*(c2.getY()-c1.getY())));
        double denominator = Math.sqrt((c2.getX()-c1.getX())*(c2.getX()-c1.getX())+(c2.getY()-c1.getY())*(c2.getY()-c1.getY()));

        double distToSegment = numerator/denominator;

        return  s.distanceTo(c1) < thickness ||
                s.distanceTo(c2) < thickness ||
                   distToSegment < thickness;
    }
}
