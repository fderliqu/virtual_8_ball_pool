package view;

import libs.SimplePoint;

import java.awt.*;

import static libs.Constants.PX_PER_CM;

public class AimLineView implements ViewInterface {
    private final SimplePoint cursor, origin;
    private boolean active = false;

    public AimLineView (SimplePoint cursor, SimplePoint origin) {
        this.origin = origin;
        this.cursor = cursor;
    }
    @Override
    public void render(Graphics g) {
        if (!active) return;

        g.setColor(Color.WHITE);
        g.drawLine(
                (int) (origin.getX()*PX_PER_CM),
                (int) (origin.getY()*PX_PER_CM),
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
