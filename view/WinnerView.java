package view;

import components.Rules;

import java.awt.*;

import static libs.Constants.SCREEN_HEIGHT;
import static libs.Constants.SCREEN_WIDTH;

public class WinnerView implements View{
    private final Rules rules;
    private boolean status = false;

    public WinnerView (Rules r) {
        this.rules = r;
    }

    public void setStatus(boolean b) {
        status = b;
    }

    @Override
    public void render(Graphics g, double PxPerCm, double verticalOffset) {
        if (status) {
            g.setColor(Color.WHITE);
            g.fillRect(0,0, (int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Helvetica", Font.BOLD, 50));
            if (rules.getWinner() == null) {
                g.drawString("Game Over",(int) SCREEN_WIDTH/2, (int) SCREEN_HEIGHT/2);
            } else {
                g.drawString(rules.getWinner()+" has won!",(int) SCREEN_WIDTH/2, (int) SCREEN_HEIGHT/2);
            }
        }
    }
}
