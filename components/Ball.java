package components;

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