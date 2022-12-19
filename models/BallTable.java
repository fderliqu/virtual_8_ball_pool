package models;

import models.Holes.HoleInterface;
import models.Holes.RoundHole;
import libs.BallTypeEnum;
import render.Sound;
import rules.Rule;

import java.util.ArrayList;


import static libs.Constants.*;

public class BallTable {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<HoleInterface> holes = new ArrayList<>();

    private Rule rules;
    private long LastTime;
    private long NewTime;
    
    
    public BallTable(){
        balls.add(new Ball(BALLS_INIT_POS_X[0],BALLS_INIT_POS_Y[0], 0, 0, BallTypeEnum.WHITE, 0));
        balls.add(new Ball(BALLS_INIT_POS_X[1],BALLS_INIT_POS_Y[1], 0, 0, BallTypeEnum.PLAIN, 1));
        balls.add(new Ball(BALLS_INIT_POS_X[2],BALLS_INIT_POS_Y[2], 0, 0, BallTypeEnum.STRIPED, 9));
        balls.add(new Ball(BALLS_INIT_POS_X[3],BALLS_INIT_POS_Y[3], 0, 0, BallTypeEnum.PLAIN, 2));
        balls.add(new Ball(BALLS_INIT_POS_X[4],BALLS_INIT_POS_Y[4], 0, 0, BallTypeEnum.BLACK, 8));
        balls.add(new Ball(BALLS_INIT_POS_X[5],BALLS_INIT_POS_Y[5], 0, 0, BallTypeEnum.STRIPED, 10));
        balls.add(new Ball(BALLS_INIT_POS_X[6],BALLS_INIT_POS_Y[6], 0, 0, BallTypeEnum.PLAIN, 3));
        balls.add(new Ball(BALLS_INIT_POS_X[7],BALLS_INIT_POS_Y[7], 0, 0, BallTypeEnum.PLAIN, 7));
        balls.add(new Ball(BALLS_INIT_POS_X[8],BALLS_INIT_POS_Y[8], 0, 0, BallTypeEnum.STRIPED, 14));
        balls.add(new Ball(BALLS_INIT_POS_X[9],BALLS_INIT_POS_Y[9], 0, 0, BallTypeEnum.STRIPED, 11));
        balls.add(new Ball(BALLS_INIT_POS_X[10],BALLS_INIT_POS_Y[10], 0, 0, BallTypeEnum.PLAIN, 4));
        balls.add(new Ball(BALLS_INIT_POS_X[11],BALLS_INIT_POS_Y[11], 0, 0, BallTypeEnum.STRIPED, 15));
        balls.add(new Ball(BALLS_INIT_POS_X[12],BALLS_INIT_POS_Y[12], 0, 0, BallTypeEnum.STRIPED, 13));
        balls.add(new Ball(BALLS_INIT_POS_X[13],BALLS_INIT_POS_Y[13], 0, 0, BallTypeEnum.PLAIN, 6));
        balls.add(new Ball(BALLS_INIT_POS_X[14],BALLS_INIT_POS_Y[14], 0, 0, BallTypeEnum.PLAIN, 5));
        balls.add(new Ball(BALLS_INIT_POS_X[15],BALLS_INIT_POS_Y[15], 0, 0, BallTypeEnum.STRIPED, 12));

        holes.add(new RoundHole(HOLES_INIT_POS_X[0],HOLES_INIT_POS_Y[0], CORNER_HOLE_DIAMETER));
        holes.add(new RoundHole(HOLES_INIT_POS_X[1],HOLES_INIT_POS_Y[1],MID_HOLE_DIAMETER));
        holes.add(new RoundHole(HOLES_INIT_POS_X[2],HOLES_INIT_POS_Y[2], CORNER_HOLE_DIAMETER));
        holes.add(new RoundHole(HOLES_INIT_POS_X[3],HOLES_INIT_POS_Y[3], CORNER_HOLE_DIAMETER));
        holes.add(new RoundHole(HOLES_INIT_POS_X[4],HOLES_INIT_POS_Y[4],MID_HOLE_DIAMETER));
        holes.add(new RoundHole(HOLES_INIT_POS_X[5],HOLES_INIT_POS_Y[5], CORNER_HOLE_DIAMETER));

        NewTime = System.nanoTime();
    }

    public void resetTable(){
        int i=0;
        for(Ball b : balls){
            b.setPos(BALLS_INIT_POS_X[i],BALLS_INIT_POS_Y[i]);
            b.setSpeed(0, 0);
            for(int j=0;j<b.getChecked().length;j++)b.setChecked(false, j);
            b.setIsPotted(false);
            i++;
        }
        NewTime = System.nanoTime();
    }

    /*
    * checks if any ball is moving
    * @return boolean if at least one ball is still moving
    * */
    public boolean anyBallMoving(){
        for(Ball b : balls){
            if(b.hasSpeed()) {
                return true;
            }
        }
        return false;
    }

    /*
    * updates the positions of every ball on the table
    * */
    public void update(){
        /*Get time */
        LastTime = NewTime;
        NewTime = System.nanoTime();
        double delta = (NewTime-LastTime)/(1E9);
        for(Ball b : balls)b.update(delta);
        /*Ball checking collision loop */
        for(Ball b : this.balls){
            /*If ball is already dropped then continue */
            if(b.getIsPotted())continue;
            for(Ball b2 : this.balls){
                /*if same ball or ball2 is already dropped, skip this loop */
                if(!b.equals(b2) && !b2.getIsPotted()){

                    /*if collide and balls has speed and collision is not checked yet then : */
                    if(b.isColliding(b2) && (b.hasSpeed() ||
                       b2.hasSpeed()) && (!b.getChecked()[b2.getBallNumber()] ||
                       !b2.getChecked()[b.getBallNumber()])){
                        /*Play sound */
                        Sound.COLLIDE.playSound();
                        /*Compute new vectors */
                        b.transfert_energy(b2);
                        b.setChecked(true, b2.getBallNumber());
                        b2.setChecked(true, b.getBallNumber());

                        if(b.getBallType() == BallTypeEnum.WHITE){
                            rules.whiteCollide(b2);
                        }
                    }
                    /*if this collision already check and balls not collide then update flag */
                    else if((!b.isColliding(b2)  || (!b.hasSpeed() && !b2.hasSpeed())) && (b.getChecked()[b2.getBallNumber()] || b2.getChecked()[b.getBallNumber()])){
                        b.setChecked(false, b2.getBallNumber());
                        b2.setChecked(false, b.getBallNumber());
                    }

                }
            }
            for(HoleInterface h : holes){
                if(b.ballInHole(h)){
                    b.setIsPotted(true);
                    b.setSpeedX(0);
                    b.setSpeedY(0);
                    
                    rules.ballPotted(b);
                }
            }
            b.wallCollide();
        }
    }

    public ArrayList<Ball> getBalls(){
        return balls;
    }
    
    public ArrayList<HoleInterface> getHoles(){
        return holes;
    }

    public boolean ballsRemaining(BallTypeEnum type) {
        if (type == BallTypeEnum.NULL) return true;

        for (Ball b : balls) {
            if ((b.getBallType() == type) && (!b.getIsPotted())) return true;
        }
        return false;
    }

    public void setRules (Rule r) {
        rules = r;
    }

    public void setNewTime(long NewTime){
        this.NewTime = NewTime;
    }
}


