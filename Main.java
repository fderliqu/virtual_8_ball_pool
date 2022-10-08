import components.Ball;
import components.BallTable;
import components.Player;
import components.Ball.BallTypeEnum;
import render.Renderer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        ArrayList<Ball> balls = new ArrayList<>();

        balls.add(new Ball(20, 100, 0, 0, BallTypeEnum.WHITE, 0));
        balls.add(new Ball(30, 100, 0, 0, BallTypeEnum.PLAIN, 1));
        balls.add(new Ball(40, 100, 0, 0, BallTypeEnum.PLAIN, 2));
        balls.add(new Ball(50, 100, 0, 0, BallTypeEnum.PLAIN, 3));
        balls.add(new Ball(60, 100, 0, 0, BallTypeEnum.PLAIN, 4));
        balls.add(new Ball(70, 100, 0, 0, BallTypeEnum.PLAIN, 5));
        balls.add(new Ball(80, 100, 0, 0, BallTypeEnum.PLAIN, 6));
        balls.add(new Ball(90, 100, 0, 0, BallTypeEnum.PLAIN, 7));
        balls.add(new Ball(100, 100, 0, 0, BallTypeEnum.BLACK, 8));
        balls.add(new Ball(110, 100, 0, 0, BallTypeEnum.STRIPED, 9));
        balls.add(new Ball(120, 100, 0, 0, BallTypeEnum.STRIPED, 10));
        balls.add(new Ball(130, 100, 0, 0, BallTypeEnum.STRIPED, 11));
        balls.add(new Ball(140, 100, 0, 0, BallTypeEnum.STRIPED, 12));
        balls.add(new Ball(150, 100, 0, 0, BallTypeEnum.STRIPED, 13));
        balls.add(new Ball(160, 100, 0, 0, BallTypeEnum.STRIPED, 14));
        balls.add(new Ball(170, 100, 0, 0, BallTypeEnum.STRIPED, 15));

        Renderer fenetre = new Renderer(balls);*/
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BallTable tableJeu = new BallTable(player1,player2);
        while(true){
            tableJeu.update();
        }
    }
}