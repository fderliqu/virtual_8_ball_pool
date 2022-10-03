import components.Ball;
import components.Ball.BallTypeEnum;
import render.Renderer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ball> balls = new ArrayList<Ball>();

        Ball test = new Ball(10, 10, 0,0,BallTypeEnum.BLACK, 8);
        balls.add(test);

        Renderer fenetre = new Renderer(balls);
    }
}