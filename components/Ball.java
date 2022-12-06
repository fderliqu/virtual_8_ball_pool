package components;

import components.Holes.Hole;
import libs.SimplePoint;

import static libs.Constants.*;

public class Ball {

    private final SimplePoint position;
    private final SimplePoint speed;

    private final BallTypeEnum ballType;
    private final int ballNumber;
    private boolean isDropped;
    private boolean alreadyChecked[] = new boolean[16];

    public Ball(double posX, double posY,double speedX, double speedY, BallTypeEnum ballType, int ballNumber) {
        this.position = new SimplePoint(posX, posY);
        this.speed = new SimplePoint(speedX, speedY);
        this.ballNumber = ballNumber;
        this.ballType = ballType;
        this.isDropped = false;
    }

    /*
    * computes the distance between this ball and another ball
    * @param b is the ball to compute the distance to
    * */
    public double distanceTo(Ball b){
        return position.distanceTo(b.position);
    }

    /*
    * checks if this ball is colliding with a given ball
    * @param b is a boolean if the two balls are colliding
    * */
    public boolean isColliding(Ball b) {
        return this.distanceTo(b) < BALL_SIZE;
    }

    /*
    * computes a collision with a wall
    * */
    public boolean wallCollide(){
        double ORIGIN_X = (POOL_TABLE_LENGTH - GAME_SURFACE_LENGTH)/2 + HORIZONTAL_OFFSET_CM;
        double ORIGIN_Y = (POOL_TABLE_WIDTH - GAME_SURFACE_WIDTH)/2 + VERTICAL_OFFSET_CM;
        double rayon = BALL_SIZE/2;
        //Bottom wall
        if(position.getX()-rayon <= ORIGIN_X){
            this.setSpeedX(Math.abs(speed.getX()));
            return true;
        }
        else if(position.getX()+rayon >= ORIGIN_X + GAME_SURFACE_LENGTH){
            this.setSpeedX(-Math.abs(speed.getX()));
            return true;
        }
        else if(position.getY()-rayon <= ORIGIN_Y){
            this.setSpeedY(Math.abs(speed.getY()));
            return true;
        }
        else if(position.getY()+rayon >= ORIGIN_Y + GAME_SURFACE_WIDTH){
            this.setSpeedY(-Math.abs(speed.getY()));
            return true;
        }
        else return false;
    }


    /*
    * performs a speed transfer on another ball in case of a collision
    * @param b is the other ball colliding with this ball
    * */
    public void transfert_energy(Ball b){
        //Calcul des deux vitesses transfert par le choc entre cette boule et la boule en paramètre
        //(dx,dy) = (cos(angle entre deux axes),sin(angle entre deux axes)) 
        double dx = position.getX() - b.getPosX();
        double dy = position.getY() - b.getPosY();
        double size = Math.sqrt(dx*dx + dy*dy);
        dx /= size;
        dy /= size;
        //vect_vitesse = valeur scalaire vitesse de la boule 1 selon l'axe de collision
        double vect_vitesse = dx*speed.getX() + dy*speed.getY();
        
        //vitesse transmis à boule 2 (nv1x,nv1y) = (vect_vitesse*cos(angle entre deux axes),vect_vitesse*sin(angle entre deux axes))
        double nv1x = dx*vect_vitesse;
        double nv1y = dy*vect_vitesse;
        //vitesse gardée sur boule 1
        double nv2x = speed.getX() - nv1x;
        double nv2y = speed.getY() - nv1y;

        //Pareil à l'inverse
        dx = b.getPosX() - position.getX();
        dy = b.getPosY() - position.getY();
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
        setSpeedX(nv2x + nv1bx);
        setSpeedY(nv2y + nv1by);

    }

    /*
    * updates the position and speed of the ball
    * @param time is the amount of time since the last update
    * */
    public void update(double time){
        setPosX(position.getX() + time*speed.getX());
        setPosY(position.getY() + time*speed.getY());
        if(this.hasSpeed()){
            double scalar_speed = Math.sqrt(speed.getX()*speed.getX() + speed.getY()*speed.getY());
            double intensityX = speed.getX()/scalar_speed;
            double intensityY = speed.getY()/scalar_speed;

            scalar_speed = Math.max((double)0,scalar_speed - time*TABLE_DEACCELERATION);
            
            setSpeedX(scalar_speed*intensityX);
            setSpeedY(scalar_speed*intensityY);
        }
    }

    /*
     * Return true if the ball has speed
     */

    public boolean hasSpeed(){
        return Math.abs(getSpeedX()) > ((float) 0) || Math.abs(getSpeedY()) > ((float) 0);
    }

    /*
     * Return true if the ball would be in the hole
     * @param hole is the hole to be test
     */

    public boolean ballInHole(Hole hole){
        return hole.isColliding(position);
    }

    /*
     * Getters and Setters
     */

    public SimplePoint getPos() { return position; }
    public void setPos(double x, double y){
        setPosX(x);
        setPosY(y);
    }

    public double getPosX() { return position.getX(); }
    public void setPosX(double posX) { this.position.setX(posX); }

    public double getPosY() { return position.getY(); }
    public void setPosY(double posY) { position.setY(posY); }

    public SimplePoint getSpeed() { return speed; }
    public void setSpeed(double x, double y){
        setSpeedX(x);
        setSpeedY(y);
    }

    public double getSpeedX() { return speed.getX(); }
    public void setSpeedX(double speedX) { speed.setX(speedX); }

    public double getSpeedY() { return speed.getY(); }
    public void setSpeedY(double speedY) { speed.setY(speedY); }

    public BallTypeEnum getBallType() { return ballType; }

    public int getBallNumber() { return ballNumber; }

    public boolean getIsDropped() { return isDropped; }
    public void setIsDropped(boolean isDropped) { this.isDropped = isDropped; }

    public boolean[] getChecked() {return alreadyChecked;}
    public void setChecked(boolean status,int i){alreadyChecked[i] = status;}

}