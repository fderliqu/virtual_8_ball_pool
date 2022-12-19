package view;

import java.awt.*;

import static libs.Constants.*;

public class StartZoneView implements ViewInterface {
    public StartZoneView(){}
    @Override
    public void render(Graphics g) {
        g.setColor(new Color(255,255,255));

        g.drawLine(
                (int) Math.floor(PX_PER_CM * (WALL_THICKNESS + START_ZONE)),
                (int) Math.floor(PX_PER_CM * (WALL_THICKNESS + VERTICAL_OFFSET_CM)),

                (int) Math.floor(PX_PER_CM * (WALL_THICKNESS + START_ZONE)),
                (int) Math.floor(PX_PER_CM * (WALL_THICKNESS + GAME_SURFACE_WIDTH + VERTICAL_OFFSET_CM))-1//no comment
                );
    }
}
