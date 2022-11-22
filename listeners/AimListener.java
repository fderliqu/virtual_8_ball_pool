package listeners;

import libs.SimplePoint;
import components.BallTable;
import components.Ball;
import static libs.Constants.PX_PER_CM;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AimListener extends MouseAdapter {
    private final BallTable table;
    static boolean isAiming = false;
    static SimplePoint mousePressed = new SimplePoint(0,0);
    static SimplePoint mouseReleased = new SimplePoint(0,0);

    public AimListener(BallTable table) {
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            isAiming = true;
            mousePressed.setX(e.getX() / PX_PER_CM);
            mousePressed.setY(e.getY() / PX_PER_CM);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            isAiming = false;
        }
    }

    @Override   
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && isAiming) {

            if (!table.checkBallsNoSpeed()) {
                isAiming = false;
                return;
            }

            mouseReleased.setX(e.getX() / PX_PER_CM);
            mouseReleased.setY(e.getY() / PX_PER_CM);

            Ball white = table.getBalls().get(0);
            SimplePoint speed = mouseReleased.getNewSpeedfromListener(white.getPos());
            table.setNewTime(System.nanoTime());
            white.setSpeedX(speed.getX());
            white.setSpeedY(speed.getY());

            isAiming = false;
        }
    }
}