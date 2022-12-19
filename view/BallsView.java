package view;

import models.Ball;

import java.awt.*;
import java.util.ArrayList;

import static libs.Constants.*;
import libs.BallTypeEnum;

public class BallsView implements ViewInterface {
    private final ArrayList<Ball> balls;

    public BallsView(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void render(Graphics g) {
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

                g.fillOval((int) (PX_PER_CM * (ball.getPosX() - BALL_SIZE/2)),
                        (int) (PX_PER_CM * (ball.getPosY() - BALL_SIZE/2)),
                        (int) (PX_PER_CM * BALL_SIZE),
                        (int) (PX_PER_CM * BALL_SIZE));
                if (ball.getBallType() == BallTypeEnum.STRIPED) {
                    g.setColor(Color.WHITE);
                    g.drawLine(
                            (int) (PX_PER_CM * ball.getPosX()),
                            (int) (PX_PER_CM * (ball.getPosY() - (BALL_SIZE / 2))) + 1,
                            (int) (PX_PER_CM * ball.getPosX()),
                            (int) (PX_PER_CM * (ball.getPosY() + (BALL_SIZE / 2))) - 2
                    );
                }
            }
        }
    }
}
