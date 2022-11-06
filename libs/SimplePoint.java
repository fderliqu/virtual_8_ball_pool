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
