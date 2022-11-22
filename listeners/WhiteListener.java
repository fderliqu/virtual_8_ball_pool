package listeners;

import libs.SimplePoint;
import components.Ball;

import static libs.Constants.BALL_SIZE;
import static libs.Constants.PX_PER_CM;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WhiteListener extends MouseAdapter{
    private SimplePoint cursor = new SimplePoint(0, 0);
    private Ball white;
    private boolean canUpdate;
    private boolean on;
    public WhiteListener(Ball white) {
        this.white = white;
        canUpdate = false;
        on = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        cursor.setX(e.getX() / PX_PER_CM);
        cursor.setY(e.getY() / PX_PER_CM);
        if(on && white.getPos().distanceTo(cursor) < BALL_SIZE){
            canUpdate = true;
            System.out.println(""+white.getPos().distanceTo(cursor));
        }
        System.out.println(""+white.getPos().distanceTo(cursor));
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        canUpdate = false;
        on = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(canUpdate){
            white.setPos(e.getX() / PX_PER_CM, e.getY() / PX_PER_CM);
        }
    }

    public void active(){
        on = true;
        //System.out.println("set whitelistner to on");
    }
}
