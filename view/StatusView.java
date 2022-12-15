package view;

import components.Rules;
import libs.GameStatusEnum;

import java.awt.*;

import static libs.Constants.*;

public class StatusView implements View{
    private final Rules rule;

    public StatusView (Rules rule) {
        this.rule = rule;
    }

    @Override
    public void render(Graphics g, double PxPerCm, double verticalOffset) {
        GameStatusEnum status = rule.getStatus();

        g.setColor(Color.RED);
        g.setFont(new Font("Helvetica", Font.PLAIN, 12));
        g.drawString(status.toString(), (int) (ORIGIN_X),(int) (ORIGIN_Y + verticalOffset));

        g.drawString(rule.getCurrentPlayer() + " " + rule.getCurrentPlayer().getTypeBall(), (int) ORIGIN_X, (int) (ORIGIN_Y + verticalOffset + FONT_SIZE + 1));
    }
}
