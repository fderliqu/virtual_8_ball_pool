package components;

import components.Holes.Hole;
import components.Holes.RoundHole;

import java.util.ArrayList;


import static libs.Constants.*;

public class BallTable {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<Hole> holes = new ArrayList<>();

    private Rules rules;
    private long LastTime;
    private long NewTime;
    
    
    public BallTable(Rules rules){
        this.rules = rules;
        balls.add(new Ball(WALL_THICKNESS + START_ZONE,WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2, 0, 0, BallTypeEnum.WHITE, 0));
        balls.add(new Ball(180, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2, 0, 0, BallTypeEnum.PLAIN, 1));
        balls.add(new Ball(180 + BALL_SIZE, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE/2, 0, 0, BallTypeEnum.PLAIN, 2));
        balls.add(new Ball(180 + BALL_SIZE, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE/2, 0, 0, BallTypeEnum.PLAIN, 3));
        balls.add(new Ball(180 + BALL_SIZE*2, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2, 0, 0, BallTypeEnum.PLAIN, 4));
        balls.add(new Ball(180 + BALL_SIZE*2, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE, 0, 0, BallTypeEnum.PLAIN, 5));
        balls.add(new Ball(180 + BALL_SIZE*2, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE, 0, 0, BallTypeEnum.PLAIN, 6));
        balls.add(new Ball(180 + BALL_SIZE*3, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE/2, 0, 0, BallTypeEnum.PLAIN, 7));
        balls.add(new Ball(180 + BALL_SIZE*3, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE/2, 0, 0, BallTypeEnum.BLACK, 8));
        balls.add(new Ball(180 + BALL_SIZE*3, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE/2 + BALL_SIZE, 0, 0, BallTypeEnum.STRIPED, 9));
        balls.add(new Ball(180 + BALL_SIZE*3, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE/2 - BALL_SIZE, 0, 0, BallTypeEnum.STRIPED, 10));
        balls.add(new Ball(180 + BALL_SIZE*4, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2, 0, 0, BallTypeEnum.STRIPED, 11));
        balls.add(new Ball(180 + BALL_SIZE*4, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE, 0, 0, BallTypeEnum.STRIPED, 12));
        balls.add(new Ball(180 + BALL_SIZE*4, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE, 0, 0, BallTypeEnum.STRIPED, 13));
        balls.add(new Ball(180 + BALL_SIZE*4, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + 2*BALL_SIZE, 0, 0, BallTypeEnum.STRIPED, 14));
        balls.add(new Ball(180 + BALL_SIZE*4, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - 2*BALL_SIZE, 0, 0, BallTypeEnum.STRIPED, 15));

        holes.add(new RoundHole( WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM,
                            WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM, 
                            ANGLE_HOLE_DIAMETER));
        holes.add(new RoundHole( WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH/2,
                            WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM, 
                            MID_HOLE_DIAMETER));
        holes.add(new RoundHole( WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH,
                            WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM, 
                            ANGLE_HOLE_DIAMETER));
        holes.add(new RoundHole( WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM,
                            WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH, 
                            ANGLE_HOLE_DIAMETER));
        holes.add(new RoundHole( WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH/2,
                            WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH, 
                            MID_HOLE_DIAMETER));
        holes.add(new RoundHole( WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH,
                            WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH, 
                            ANGLE_HOLE_DIAMETER));

        NewTime = System.nanoTime();
    }

    /*
    * checks if any ball is moving
    * @return boolean if at least one ball is still moving
    * */
    public boolean checkBallsNoSpeed(){
        for(Ball b : balls){
            if(b.hasSpeed()) {
                return false;
            }
        }
        return true;
    }

    /*
    * updates the positions of every ball on the table
    * */
    public void update(){
        LastTime = NewTime;
        NewTime = System.nanoTime();
        double delta = (NewTime-LastTime)/(1E9);
        //System.out.println(delta);
        for(Ball b : this.balls){
            if(b.getIsDropped())continue;
            //System.out.println("posX:" + b.getPosX() + " posY:" + b.getPosY()+" speedX:"+b.getSpeedX()+" speedY:"+b.getSpeedY());
            for(Ball b2 : this.balls){
                if(!b.equals(b2) && !b2.getIsDropped()){
                    if(b.isColliding(b2) && (b.hasSpeed() || b2.hasSpeed()) && !b.getChecked()[b2.getBallNumber()]){
                        //System.out.print("c");
                        //System.out.println(b.getBallNumber() + " speedX:" + b.getSpeedX() + " speedY:" + b.getSpeedY() + " " + b2.getBallNumber()); 
                        b.transfert_energy(b2);
                        b2.setChecked(true, b.getBallNumber());
                        if(b.getBallType() == BallTypeEnum.WHITE){
                            rules.incWhiteBallCollisions();
                            if(rules.getFirstBallTouch() == BallTypeEnum.NULL)rules.setFirstBallTouch(b2.getBallType()); 
                        }
                        else if(b2.getBallType() == BallTypeEnum.WHITE){
                            rules.incWhiteBallCollisions();
                            if(rules.getFirstBallTouch() == BallTypeEnum.NULL)rules.setFirstBallTouch(b.getBallType()); 
                        }
                        //System.out.println(b.getBallNumber() + " speedX:" + b.getSpeedX() + " speedY:" + b.getSpeedY() + " aftertranfert");
                    }
                }
            }
            for(Hole h : holes){
                if(b.ballInHole(h)){
                    b.setIsDropped(true);
                    b.setSpeedX(0);
                    b.setSpeedY(0);
                    if(b.getBallType()!=BallTypeEnum.WHITE){
                        if(rules.getPlayer(true).getTypeBall() == b.getBallType()){
                            rules.getPlayer(true).incrementBallPotted();
                        }   
                        else rules.getPlayer(false).incrementBallPotted();
                    }
                    if(b.getBallType()==BallTypeEnum.STRIPED){
                        if(rules.getPlayer(true).noBallPotted()){
                            rules.getPlayer(true).setTypeBall(BallTypeEnum.STRIPED);
                            rules.getPlayer(false).setTypeBall(BallTypeEnum.PLAIN);
                        }
                        rules.setStripedPotted(true);
                    }
                    else if(b.getBallType()==BallTypeEnum.PLAIN){
                        if(rules.getPlayer(true).noBallPotted()){
                            rules.getPlayer(true).setTypeBall(BallTypeEnum.PLAIN);
                            rules.getPlayer(false).setTypeBall(BallTypeEnum.STRIPED);
                        }
                        rules.setPlainPotted(true);
                    }
                    else if(b.getBallType()==BallTypeEnum.BLACK){
                        if(rules.getPlayer(true).allowedToPutBlackBall())rules.getPlayer(true).setHeWin(true);
                        else rules.setBlackPotted(true);
                    }
                    else {
                        rules.setWhitePotted(true);
                    }
                }
            }
            if(b.wallCollide())rules.incWallCollisions();
            b.update(delta);
            for(int i=0;i<b.getChecked().length;i++){
                b.setChecked(false, i);
            }
        }
    }

    public ArrayList<Ball> getBalls(){
        return balls;
    }
    
    public ArrayList<Hole> getHoles(){
        return holes;
    }

    public void setNewTime(long NewTime){
        this.NewTime = NewTime;
    }
}


