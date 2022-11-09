package view;

import java.awt.*;

import static libs.Constants.*;

public class StartZoneView implements View {
    private final double verticalOffset;

    public StartZoneView(double verticalOffset) {
        this.verticalOffset = verticalOffset;
    }

    @Override
    public void render(Graphics g, double PxPerCm) {
        g.setColor(new Color(255,255,255));

        g.drawLine(
                (int) (PxPerCm * (WALL_THICKNESS + START_ZONE)),
                (int) ((PxPerCm * WALL_THICKNESS) + verticalOffset),
                (int) (PxPerCm * (WALL_THICKNESS + START_ZONE)),
                (int) (((PxPerCm * (WALL_THICKNESS + GAME_SURFACE_WIDTH)) + verticalOffset))
                );
    }
}
