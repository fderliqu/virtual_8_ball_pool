package listeners;

import libs.SimplePoint;
import view.AimLineView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AimLineListener extends MouseAdapter {
    private AimLineView view;
    private final SimplePoint cursor;

    public AimLineListener(AimLineView view) {

        this.view   = view;
        this.cursor = new SimplePoint(0,0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        if (e.getButton() == MouseEvent.BUTTON1) {
            cursor.setX(e.getX());
            cursor.setY(e.getY());
            view.activate();
            System.out.println("activating line");
        } else if (e.getButton() == MouseEvent.BUTTON2){
            view.deactivate();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        view.deactivate();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        cursor.setX(e.getX());
        cursor.setY(e.getY());
    }

    public SimplePoint getCursor() {
        return cursor;
    }

    public void setView (AimLineView view) {
        this.view = view;
    }
}
