package listeners;

import libs.SimplePoint;
import components.Ball;

import static libs.Constants.BALL_SIZE;
import static libs.Constants.PX_PER_CM;

import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WhiteListener extends MouseAdapter{
    private SimplePoint cursor = new SimplePoint(0, 0);
    private ArrayList<Ball> balls;
    private boolean canUpdate;
    private boolean active;
    public WhiteListener(ArrayList<Ball> balls) {
        this.balls = balls;
        canUpdate = false;
        active = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        cursor.setX(e.getX() / PX_PER_CM);
        cursor.setY(e.getY() / PX_PER_CM);
        if(active && balls.get(0).getPos().distanceTo(cursor) < BALL_SIZE){
            canUpdate = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        canUpdate = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        boolean flag = true;
        super.mouseDragged(e);
        if(canUpdate){
            cursor.setX(e.getX() / PX_PER_CM);
            cursor.setY(e.getY() / PX_PER_CM);
            for(Ball b : balls){
                if(b.getBallNumber() != 0 && cursor.distanceTo(b.getPos()) < BALL_SIZE)flag = false;
            }
            if(!cursor.outOfBounds() && flag)balls.get(0).setPos(cursor.getX(),cursor.getY());
        }
    }

    public void on(){
        active = true;
        //System.out.println("set whitelistner to on");
    }

    public void off(){
        active = false;
    }
}
