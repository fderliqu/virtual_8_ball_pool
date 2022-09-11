package components;

import java.lang.Math;

import static libs.Constants.*;

public class Ball {

    public static final int STRIPED = 1;
    public static final int PLAIN = 2;
    public static final int WHITE = 3;
    public static final int BLACK = 4;

    public enum BallTypeEnum {
        STRIPED,
        PLAIN,
        WHITE,
        BLACK
    }

    private double posX;
    private double posY;

    private BallTypeEnum ballType;
    private int ballNumber;
    private boolean isDropped;

    private double speedX;
    private double speedY;

    public Ball(double x, double y, double sx, double sy) {
        posX = x;
        posY = y;

        speedX = sx;
        speedY = sy;
    }

    public double distance(Ball b){
        return Math.sqrt((b.getPosX()-posX)*(b.getPosX()-posX) + (b.getPosY()-this.posY)*(b.getPosY()-this.posY));
    }

    public boolean collide(Ball b) {
        return this.distance(b) < BALL_SIZE;
    }

    public void wallCollide(){
        double ORIGIN_X = POOL_TABLE_LENGTH - GAME_SURFACE_LENGTH;
        double ORIGIN_Y = POOL_TABLE_WIDTH - GAME_SURFACE_WIDTH;
        double rayon = BALL_SIZE/2;
        //Bottom wall
        if(posX-rayon <= ORIGIN_X + RUBBER_BAND){
            this.setSpeedX(Math.abs(speedX));
        }
        if(posX+rayon >= ORIGIN_X + GAME_SURFACE_LENGTH - RUBBER_BAND){
            this.setSpeedX(-Math.abs(speedX));
        }
        if(posY-rayon <= ORIGIN_Y + RUBBER_BAND){
            this.setSpeedY(Math.abs(speedY));
        }
        if(posY+rayon >= ORIGIN_Y + GAME_SURFACE_WIDTH - RUBBER_BAND){
            this.setSpeedX(-Math.abs(speedY));
        }
    }

    public double getPosX() { return posX; }
    public void setPosX(double posX) { this.posX = posX; }

    public double getPosY() { return posY; }
    public void setPosY(double posY) { this.posY = posY; }

    public double getSpeedX() { return speedX; }
    public void setSpeedX(double speedX) { this.speedX = speedX; }

    public double getSpeedY() { return speedY; }
    public void setSpeedY(double speedY) { this.speedY = speedY; }

    public BallTypeEnum getBallType() { return ballType; }
    public void setBallType(BallTypeEnum ballType) { this.ballType = ballType; }

    public int getBallNumber() { return ballNumber; }
    public void setBallNumber(int ballNumber) { this.ballNumber = ballNumber; }

    public boolean getIsDropped() { return isDropped; }
    public void setIsDropped(boolean isDropped) { this.isDropped = isDropped; }
}