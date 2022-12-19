package listeners;

import rules.Rule;
import libs.SimplePoint;
import models.BallTable;
import models.Ball;
import static libs.Constants.PX_PER_CM;
import static libs.Constants.BALL_SIZE;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AimListener extends MouseAdapter {
    private final BallTable table;
    static boolean isAiming = false;
    static SimplePoint mousePressed = new SimplePoint(0,0);
    static SimplePoint mouseReleased = new SimplePoint(0,0);
    private final Rule rules;

    public AimListener(BallTable table, Rule r) {

        this.table = table;
        this.rules = r;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        if (e.getButton() == MouseEvent.BUTTON1) {
            isAiming = false;
            mousePressed.setX(e.getX() / PX_PER_CM);
            mousePressed.setY(e.getY() / PX_PER_CM);
            if(table.getBalls().get(0).getPos().distanceTo(mousePressed) >= BALL_SIZE)isAiming = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            isAiming = false;
        }
    }

    @Override   
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (e.getButton() == MouseEvent.BUTTON1 && isAiming) {

            if (table.anyBallMoving()) {
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
            rules.resetFlags();
        }
    }
}
