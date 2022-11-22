package view;

import java.awt.*;

import static libs.Constants.*;

public class TableView implements View{
    public TableView() {}

    @Override
    public void render(Graphics g, double PxPerCm, double verticalOffset) {

        g.setColor(new Color(107, 62, 46));
        g.fillRoundRect(0, (int) verticalOffset, (int) (PxPerCm * POOL_TABLE_LENGTH), (int) (PxPerCm * POOL_TABLE_WIDTH), 30, 30);

        g.setColor(new Color(0, 184, 148));
        g.fillRoundRect(
                (int) (PxPerCm * WALL_THICKNESS),
                (int) (verticalOffset+ PxPerCm * WALL_THICKNESS),
                (int) (PxPerCm * GAME_SURFACE_LENGTH),
                (int) (PxPerCm * GAME_SURFACE_WIDTH),
        30, 30);
    }
}
