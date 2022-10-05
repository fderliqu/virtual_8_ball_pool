package components;

import render.Renderer;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import components.Ball.BallTypeEnum;

public class BallTable {
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    private Player player1,player2;

    private int cursorX,cursorY,pressedX,pressedY,draggedX,draggedY;

    private boolean cursorOnDrag = false;

    protected Renderer panel = new Renderer();
    
    public BallTable(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        balls.add(new Ball(10, 10, 0, 0, BallTypeEnum.WHITE, 0));
        balls.add(new Ball(30, 10, 0, 0, BallTypeEnum.PLAIN, 1));
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
                if(!checkBallsSpeed()){
                    pressedX = event.getX();
                    pressedY = event.getY();
                }
            }
            public void mouseReleased(MouseEvent event){
                if(!checkBallsSpeed()){
                draggedX = event.getX();
                draggedY = event.getY();
                }
            }
        });
    }

    public boolean checkBallsSpeed(){
        for(Ball b : this.balls){
            if(b.getSpeedX() + b.getSpeedY() > 0)return false;
        }
        return true;
    }
}