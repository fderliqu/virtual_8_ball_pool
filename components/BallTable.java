package components;

import render.Renderer;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import components.Ball.BallTypeEnum;
import libs.Constants;

public class BallTable {
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    private Player player1,player2;

    private int cursorX,cursorY,pressedX,pressedY,draggedX,draggedY;

    private boolean cursorOnDrag = false;

    protected Renderer panel;

    double PxPerCm = Constants.POOL_TABLE_LENGTH / Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    private long LastTime;
    private long NewTime;
    private long RefTime;
    
    public BallTable(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        //double originX = (Constants.POOL_TABLE_LENGTH - Constants.GAME_SURFACE_LENGTH)/2 + Constants.HORIZONTAL_OFFSET_CM;
        //double originY = (Constants.POOL_TABLE_WIDTH - Constants.GAME_SURFACE_WIDTH)/2 + Constants.VERTICAL_OFFSET_CM;
        balls.add(new Ball(Constants.WALL_THICKNESS + Constants.START_ZONE,Constants.WALL_THICKNESS + Constants.VERTICAL_OFFSET_CM + Constants.GAME_SURFACE_WIDTH/2, 300, 300, BallTypeEnum.WHITE, 0));
        //System.out.println("verticalOffset:"+Constants.VERTICAL_OFFSET_CM+" honroOffset:"+Constants.HORIZONTAL_OFFSET_CM);
        /*balls.add(new Ball(30, 10, 0, 0, BallTypeEnum.PLAIN, 1));
        balls.add(new Ball(50, 10, 0, 0, BallTypeEnum.PLAIN, 2));
        balls.add(new Ball(70, 10, 0, 0, BallTypeEnum.PLAIN, 3));
        balls.add(new Ball(90, 10, 0, 0, BallTypeEnum.PLAIN, 4));
        balls.add(new Ball(110, 10, 0, 0, BallTypeEnum.PLAIN, 5));
        balls.add(new Ball(130, 10, 0, 0, BallTypeEnum.PLAIN, 6));
        balls.add(new Ball(150, 10, 0, 0, BallTypeEnum.PLAIN, 7));
        balls.add(new Ball(170, 10, 0, 0, BallTypeEnum.BLACK, 8));
        balls.add(new Ball(190, 10, 0, 0, BallTypeEnum.STRIPED, 9));
        balls.add(new Ball(210, 10, 0, 0, BallTypeEnum.STRIPED, 10));
        balls.add(new Ball(230, 10, 0, 0, BallTypeEnum.STRIPED, 11));
        balls.add(new Ball(250, 10, 0, 0, BallTypeEnum.STRIPED, 12));
        balls.add(new Ball(270, 10, 0, 0, BallTypeEnum.STRIPED, 13));
        balls.add(new Ball(290, 10, 0, 0, BallTypeEnum.STRIPED, 14));
        balls.add(new Ball(310, 10, 0, 0, BallTypeEnum.STRIPED, 15));
*/      
        panel = new Renderer(balls);
        RefTime = System.currentTimeMillis();
        panel.addMouseMotionListener(new MouseMotionAdapter(){
            //Only for tracking display
            public void mouseMoved(MouseEvent event){
                if(!cursorOnDrag && !checkBallsSpeed()){
                    cursorX = event.getX();
                    cursorY = event.getY();
                }
            }
        });

        panel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                if(checkBallsSpeed()){
                    pressedX = event.getX();
                    pressedY = event.getY();
                }
                System.out.println("pressedX : "+pressedX+" draggedY : "+draggedY);
            }
            public void mouseReleased(MouseEvent event){
                if(checkBallsSpeed()){
                draggedX = event.getX();
                draggedY = event.getY();
                }
            }
        });
        NewTime = System.nanoTime();
    }

    public boolean checkBallsSpeed(){
        for(Ball b : this.balls){
            if(b.getSpeedX() + b.getSpeedY() > 0)return false;
        }
        return true;
    }

    public void update(){
        double lastXpos,lastYpos;
        boolean flagDrawUpdate = false;
        LastTime = NewTime;
        NewTime = System.nanoTime();
        double delta = (NewTime-LastTime)/(1E9);
        for(Ball b : this.balls){
            //System.out.println("posX:" + b.getPosX() + " posY:" + b.getPosY()+"speedX:"+b.getSpeedX());
            lastXpos = b.getPosX();
            lastYpos = b.getPosY();
            b.wallCollide();
            b.update(delta);
            if((int)(Constants.PX_PER_CM*b.getPosX()) == (int)(lastXpos*Constants.PX_PER_CM) || (int)(Constants.PX_PER_CM*b.getPosY()) == (int)(lastYpos*Constants.PX_PER_CM)){
                flagDrawUpdate = true;
            }
            
        }
        if(flagDrawUpdate)panel.drawUpdate();
    }
}