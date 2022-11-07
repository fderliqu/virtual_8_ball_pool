import components.*;
import render.*;
import static libs.Constants.*;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BallTable tableJeu = new BallTable(player1,player2);

        Renderer panel = new Renderer(tableJeu.getBalls(), tableJeu.getHoles());

        Thread render = new Thread(){
            public void run(){
                    while(true){
                    long before = System.currentTimeMillis();
                    panel.drawUpdate();
                    long after = System.currentTimeMillis();
                    long time = after - before;
                    //System.out.println("Time :" + time );
                    if(time < FORCED_TIMEOUT_MS){
                        try{
                            Thread.sleep((long)(FORCED_TIMEOUT_MS - time));
                        }catch(Exception e){};
                    }
                }
            }
        };
        render.start();

        while(true){
        
            if(!tableJeu.checkBallsNoSpeed()) {
                tableJeu.update();
            }
        }
    }
}