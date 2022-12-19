package view;

import rules.Rule;
import libs.GameStatusEnum;

import java.awt.*;

import static libs.Constants.*;

public class StatusView implements ViewInterface {
    private final Rule rule;

    public StatusView (Rule rule) {
        this.rule = rule;
    }

    @Override
    public void render(Graphics g) {

        //little offset for the text not to stick to the top of the wall
        int offset = FONT_SIZE;

        GameStatusEnum status = rule.getStatus();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 12));
        g.drawString("Status : " + status.toString(),
                (int) ((HORIZONTAL_OFFSET_CM + WALL_THICKNESS + CORNER_HOLE_DIAMETER) * PX_PER_CM),
                (int) (VERTICAL_OFFSET_CM * PX_PER_CM + FONT_SIZE + offset));

        g.drawString("Now playing : " + rule.getCurrentPlayer().toString(),
                (int) ((HORIZONTAL_OFFSET_CM + WALL_THICKNESS + CORNER_HOLE_DIAMETER) * PX_PER_CM),
                (int) (VERTICAL_OFFSET_CM * PX_PER_CM + 2*FONT_SIZE + offset));

        g.drawString("Ball type : " + rule.getCurrentPlayer().getTypeBall(),
                (int) ((HORIZONTAL_OFFSET_CM + WALL_THICKNESS + CORNER_HOLE_DIAMETER) * PX_PER_CM),
                (int) (VERTICAL_OFFSET_CM * PX_PER_CM + 3*FONT_SIZE + offset));
    }
}
