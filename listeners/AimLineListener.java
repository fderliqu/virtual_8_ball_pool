package listeners;

import libs.SimplePoint;
import render.Renderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AimLineListener extends MouseAdapter {
    private final SimplePoint cursor;
    private final Renderer renderer;

    public AimLineListener(Renderer r, SimplePoint s) {
        this.cursor   = s;
        this.renderer = r;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        cursor.setX(e.getX());
        cursor.setY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        cursor.setX(e.getX());
        cursor.setY(e.getY());

        renderer.drawUpdate();
    }
}
