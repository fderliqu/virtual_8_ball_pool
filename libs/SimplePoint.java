package libs;

public class SimplePoint {

    private double X;
    private double Y;

    public SimplePoint(double x, double y) {
        X = x;
        Y = y;
    }

    public SimplePoint(int x, int y) {
        X = x;
        Y = y;
    }

    public double distanceTo(SimplePoint p) {
        return Math.sqrt((p.getX()-getX())*(p.getX()-getX()) + (p.getY()-getY())*(p.getY()-getY()));
    }
    
    public SimplePoint getNewSpeedfromListener(SimplePoint p){
        SimplePoint speed = new SimplePoint(0, 0);
        double dist = p.distanceTo(this);

        SimplePoint intensity = new SimplePoint((X - p.getX()) / dist, (Y - p.getY()) / dist);
        dist = Math.min(400, dist*3);
        speed.setX(dist * intensity.getX());
        speed.setY(dist * intensity.getY());
        return speed;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
