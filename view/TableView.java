package view;

import java.awt.*;

import static libs.Constants.*;

public class TableView implements ViewInterface {
    public TableView() {}

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(107, 62, 46));
        g.fillRoundRect(0, (int) Math.round(VERTICAL_OFFSET_CM * PX_PER_CM),
                (int) (PX_PER_CM * (POOL_TABLE_LENGTH)),
                (int) (PX_PER_CM * POOL_TABLE_WIDTH),
                30,
                30);

        g.setColor(new Color(62, 136, 189));
        g.fillRoundRect(
                (int) (PX_PER_CM * WALL_THICKNESS),
                (int) ((VERTICAL_OFFSET_CM + WALL_THICKNESS) * PX_PER_CM),
                (int) (PX_PER_CM * GAME_SURFACE_LENGTH),
                (int) (PX_PER_CM * GAME_SURFACE_WIDTH),
        30, 30);
    }
}
