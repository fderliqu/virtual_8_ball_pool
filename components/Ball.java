public class Ball {
    private double posX;
    private double posY;

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
}