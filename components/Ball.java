package components;

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

    private double acceleration;

    public Ball(double posX, double posY,double speedX, double speedY, BallTypeEnum ballType, int ballNumber) {
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.acceleration = 0;
        this.ballNumber = ballNumber;
        this.ballType = ballType;
    }

    public double distance(Ball b){
        return Math.sqrt((b.getPosX()-posX)*(b.getPosX()-posX) + (b.getPosY()-this.posY)*(b.getPosY()-this.posY));
    }

    public boolean collide(Ball b) {
        return this.distance(b) < BALL_SIZE;
    }

    public void wallCollide(){
        double ORIGIN_X = (POOL_TABLE_LENGTH - GAME_SURFACE_LENGTH)/2 + HORIZONTAL_OFFSET_CM;
        double ORIGIN_Y = (POOL_TABLE_WIDTH - GAME_SURFACE_WIDTH)/2 + VERTICAL_OFFSET_CM;
        double rayon = BALL_SIZE/2;
        //Bottom wall
        if(posX-rayon <= ORIGIN_X){
            this.setSpeedX(Math.abs(speedX));
        }
        if(posX+rayon >= ORIGIN_X + GAME_SURFACE_LENGTH){
            this.setSpeedX(-Math.abs(speedX));
        }
        if(posY-rayon <= ORIGIN_Y){
            this.setSpeedY(Math.abs(speedY));
        }
        if(posY+rayon >= ORIGIN_Y + GAME_SURFACE_WIDTH){
            this.setSpeedY(-Math.abs(speedY));
        }
    }

    /* A TEST */

    public void transfert_energy(Ball b){
        //Calcul des deux vitesses transfert par le choc entre cette boule et la boule en paramètre
        //(dx,dy) = (cos(angle entre deux axes),sin(angle entre deux axes)) 
        double dx = this.posX - b.getPosX();
        double dy = this.posY - b.getPosY();
        double size = Math.sqrt(dx*dx + dy*dy);
        dx /= size;
        dy /= size;
        //vect_vitesse = valeur scalaire vitesse de la boule 1 selon l'axe de collision
        double vect_vitesse = dx*this.speedX + dy*this.speedY;
        
        //vitesse transmis à boule 2 (nv1x,nv1y) = (vect_vitesse*cos(angle entre deux axes),vect_vitesse*sin(angle entre deux axes))
        double nv1x = dx*vect_vitesse;
        double nv1y = dy*vect_vitesse;
        //vitesse gardée sur boule 1
        double nv2x = this.speedX - nv1x;
        double nv2y = this.speedY - nv1y;

        //Pareil à l'inverse
        dx = b.getPosX() - this.posX;
        dy = b.getPosY() - this.posY;
        size = Math.sqrt(dx*dx + dy*dy);
        dx /= size;
        dy /= size;
        vect_vitesse = dx*b.getSpeedX() + dy*b.getSpeedY();
        
        //vitesse transmis à boule 1
        double nv1bx = dx*vect_vitesse;
        double nv1by = dy*vect_vitesse;
        //vitesse gardée sur boule 2 
        double nv2bx = b.getSpeedX() - nv1bx;
        double nv2by = b.getSpeedY() - nv1by;

        //Additionner les vecteur ensembles 
        //vitesse boule 1 = vitesse transmis à boule 1 (nv1bx,nv1by) + vitesse gardée sur boule 1 (nv2x,nv2y)
        //vitesse boule 2 = vitesse transmis à boule 2 (nv1x,nv1y) + vitesse gardée sur boule 2 (nv2bx,nv2by)
        b.setSpeedX(nv1x + nv2bx);
        b.setSpeedY(nv1y + nv2by);
        this.setSpeedX(nv2x + nv1bx);
        this.setSpeedY(nv2y + nv1by);

    }

    public void update(double time){
        setPosX(posX + time*speedX);
        setPosY(posY + time*speedY);
        setSpeedX(speedX + time*acceleration);
        setSpeedY(speedY + time*acceleration);
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