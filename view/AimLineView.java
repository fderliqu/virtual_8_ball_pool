package view;

import libs.SimplePoint;

import java.awt.*;

public class AimLineView implements View{
    private final SimplePoint cursor, origin;
    private boolean active = false;

    public AimLineView (SimplePoint cursor, SimplePoint origin) {
        this.origin = origin;
        this.cursor = cursor;
    }
    @Override
    public void render(Graphics g, double PxPerCm, double verticalOffset) {
        if (!active) return;
        System.out.println("drawing..."+cursor.getY()+" "+cursor.getY());

        g.setColor(Color.WHITE);
        g.drawLine(
                (int) (origin.getX()*PxPerCm),
                (int) (origin.getY()*PxPerCm),
                (int) (cursor.getX()),
                (int) (cursor.getY())
        );
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
