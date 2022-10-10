package components;

import render.Renderer;

import java.util.ArrayList;

import java.awt.event.*;
import java.awt.*;

import static libs.Constants.*;

public class BallTable {
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    private Player player1,player2;

    private int cursorX,cursorY,pressedX,pressedY,draggedX,draggedY;

    private boolean cursorOnDrag = false;

    protected Renderer panel;

    double PxPerCm = POOL_TABLE_LENGTH / Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    private long LastTime;
    private long NewTime;
    
    
    public BallTable(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        //double originX = (Constants.POOL_TABLE_LENGTH - Constants.GAME_SURFACE_LENGTH)/2 + Constants.HORIZONTAL_OFFSET_CM;
        //double originY = (Constants.POOL_TABLE_WIDTH - Constants.GAME_SURFACE_WIDTH)/2 + Constants.VERTICAL_OFFSET_CM;
        balls.add(new Ball(WALL_THICKNESS + START_ZONE,WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2, 200, 0, BallTypeEnum.WHITE, 0));
        //System.out.println("verticalOffset:"+Constants.VERTICAL_OFFSET_CM+" honroOffset:"+Constants.HORIZONTAL_OFFSET_CM);
        balls.add(new Ball(30, 30, 0, 0, BallTypeEnum.PLAIN, 1));
        balls.add(new Ball(50, 30, 0, 0, BallTypeEnum.PLAIN, 2));
        balls.add(new Ball(70, 100, 0, 0, BallTypeEnum.PLAIN, 3));
        balls.add(new Ball(90, 110, 0, 0, BallTypeEnum.PLAIN, 4));
        balls.add(new Ball(110, 30, 0, 0, BallTypeEnum.PLAIN, 5));
        balls.add(new Ball(130, 50, 0, 0, BallTypeEnum.PLAIN, 6));
        balls.add(new Ball(150, 120, 0, 0, BallTypeEnum.PLAIN, 7));
        balls.add(new Ball(120, WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 +2, 0, 0, BallTypeEnum.BLACK, 8));
        balls.add(new Ball(190, 20, 0, 0, BallTypeEnum.STRIPED, 9));
        balls.add(new Ball(210, 23, 0, 0, BallTypeEnum.STRIPED, 10));
        balls.add(new Ball(190, 50, 0, 0, BallTypeEnum.STRIPED, 11));
        balls.add(new Ball(190, 80, 0, 0, BallTypeEnum.STRIPED, 12));
        balls.add(new Ball(220, 110, 0, 0, BallTypeEnum.STRIPED, 13));
        balls.add(new Ball(150, 30, 0, 0, BallTypeEnum.STRIPED, 14));
        balls.add(new Ball(70, 30, 0, 0, BallTypeEnum.STRIPED, 15));
     
        panel = new Renderer(balls);
        panel.addMouseMotionListener(new MouseMotionAdapter(){
            //Only for tracking display
            public void mouseMoved(MouseEvent event){
                if(!cursorOnDrag && !checkBallsNoSpeed()){
                    cursorX = event.getX();
                    cursorY = event.getY();
                }
            }
        });

        panel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                if(checkBallsNoSpeed()){
                    pressedX = event.getX();
                    pressedY = event.getY();
                }
                System.out.println("pressedX : "+pressedX+" draggedY : "+draggedY);
            }
            public void mouseReleased(MouseEvent event){
                if(checkBallsNoSpeed()){
                draggedX = event.getX();
                draggedY = event.getY();
                }
            }
        });
        NewTime = System.nanoTime();
    }

    /*
    * checks if any ball is moving
    * @return boolean if at least one ball is still moving
    * */
    public boolean checkBallsNoSpeed(){
        for(Ball b : this.balls){
            if((int)(b.getSpeedX()) != 0 || (int)(b.getSpeedY()) != 0 )return false;
        }
        return true;
    }

    /*
    * updates the positions of every ball on the table
    * */
    public void update(){
        double lastXpos,lastYpos;
        boolean flagDrawUpdate = false;
        LastTime = NewTime;
        NewTime = System.nanoTime();
        double delta = (NewTime-LastTime)/(1E9);
        for(Ball b : this.balls){
            //System.out.println("posX:" + b.getPosX() + " posY:" + b.getPosY()+" speedX:"+b.getSpeedX()+" speedY:"+b.getSpeedY());
            for(Ball b2 : this.balls){
                if(!b.equals(b2)){
                    if(b.isColliding(b2))b.transfert_energy(b2);
                }
            }
            lastXpos = b.getPosX();
            lastYpos = b.getPosY();
            b.wallCollide();
            b.update(delta);
            if((int)(PX_PER_CM*b.getPosX()) == (int)(lastXpos*PX_PER_CM) || (int)(PX_PER_CM*b.getPosY()) == (int)(lastYpos*PX_PER_CM)){
                flagDrawUpdate = true;
            }
            
        }
        if(flagDrawUpdate)panel.drawUpdate();
    }
}