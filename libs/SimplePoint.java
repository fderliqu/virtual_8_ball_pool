package libs;

import static libs.Constants.*;
import static libs.Constants.GAME_SURFACE_LENGTH;
import static libs.Constants.GAME_SURFACE_WIDTH;
import static libs.Constants.BALL_SIZE;

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

    /*
    *
    * Checks if a point is within the game zone
    *
    * */
    public boolean outOfBounds(){
        return X - BALL_SIZE / 2 < ORIGIN_X ||
               X + BALL_SIZE / 2 > GAME_SURFACE_LENGTH + ORIGIN_X ||
               Y - BALL_SIZE / 2 < ORIGIN_Y ||
               Y + BALL_SIZE / 2 > GAME_SURFACE_WIDTH + ORIGIN_Y;
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
