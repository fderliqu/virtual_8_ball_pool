package view;

import rules.Rule;

import java.awt.*;

import static libs.Constants.SCREEN_HEIGHT;
import static libs.Constants.SCREEN_WIDTH;
import static libs.CustomDraw.computeTextWidth;
import static libs.GameStatusEnum.VICTORY;

public class WinnerView implements ViewInterface {
    private final Rule rules;
    private boolean status = false;

    public WinnerView (Rule r) {
        this.rules = r;
    }

    public void setStatus(boolean b) {
        status = b;
    }

    @Override
    public void render(Graphics g) {
        if (status) {
            g.setColor(Color.WHITE);
            g.fillRect(0,0, (int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Helvetica", Font.BOLD, 40));
            if (rules.getWinner() == null) {
                g.drawString("Game Over",(int) SCREEN_WIDTH/3, (int) SCREEN_HEIGHT/2);
            } else {
                String text;
                if (rules.getStatus() == VICTORY){
                    text = rules.getWinner()+" scored all their balls and the black one!";
                } else {
                    text = rules.getWinner()+" won because the opponent potted the black ball too soon!";
                }

                g.drawString(text, computeTextWidth(g, text, 40), (int) SCREEN_HEIGHT/2);
            }
            g.setFont(new Font("Helvetica", Font.PLAIN, 30));
            g.drawString("Press R to play again or Q to quit", (int) SCREEN_WIDTH/3, (int) (SCREEN_HEIGHT/2) + 50);
        }
    }
}
