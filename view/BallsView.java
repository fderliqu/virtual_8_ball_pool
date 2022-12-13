package view;

import components.Ball;

import java.awt.*;
import java.util.ArrayList;

import static libs.Constants.*;
import libs.BallTypeEnum;

public class BallsView implements View {
    private final ArrayList<Ball> balls;

    public BallsView(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void render(Graphics g, double PxPerCm, double verticalOffset) {
        for (Ball ball : balls) {
            if(!ball.getIsPotted()){
                switch (ball.getBallNumber()){
                    case 0  -> g.setColor(Color.WHITE);
                    case 1, 9 -> g.setColor(YELLOW);
                    case 2, 10 -> g.setColor(BLUE);
                    case 3, 11 -> g.setColor(RED);
                    case 4, 12 -> g.setColor(PURPLE);
                    case 5, 13 -> g.setColor(ORANGE);
                    case 6, 14 -> g.setColor(GREEN);
                    case 7, 15 -> g.setColor(BROWN);
                    case 8  -> g.setColor(Color.BLACK);
                }

                g.fillOval((int) (PxPerCm * (ball.getPosX() - BALL_SIZE/2)),
                        (int) (PxPerCm * (ball.getPosY() - BALL_SIZE/2)),
                        (int) (PxPerCm * BALL_SIZE),
                        (int) (PxPerCm * BALL_SIZE));
                if (ball.getBallType() == BallTypeEnum.STRIPED) {
                    g.setColor(Color.WHITE);
                    g.drawLine(
                            (int) (PxPerCm * ball.getPosX()),
                            (int) (PxPerCm * (ball.getPosY() - (BALL_SIZE / 2))),
                            (int) (PxPerCm * ball.getPosX()),
                            (int) (PxPerCm * (ball.getPosY() + (BALL_SIZE / 2)))
                    );
                }
            }
        }
    }
}
