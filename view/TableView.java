package view;

import java.awt.*;

import static libs.Constants.*;

public class TableView implements View{


    @Override
    public void render(Graphics g, double PxPerCm) {
        g.setColor(new Color(0, 184, 148));
        g.fillRoundRect(
                (int) (PxPerCm * WALL_THICKNESS),
                (int) (verticalOffset+ PxPerCm * WALL_THICKNESS),
                (int) (PxPerCm * GAME_SURFACE_LENGTH),
                (int) (PxPerCm * GAME_SURFACE_WIDTH),
        30, 30);
    }
}
