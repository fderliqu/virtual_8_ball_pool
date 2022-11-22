import components.*;
import listeners.AimLineListener;
import listeners.AimListener;
import render.*;
import static libs.Constants.*;
import libs.SimplePoint;

public class Main {
    
    public static void main(String[] args) {

        Player player1 = new Player("1");
        Player player2 = new Player("2");
        Rules rules = new Rules();
        BallTable tableJeu = new BallTable(rules);

        SimplePoint cursor = new SimplePoint(0, 0);
        Renderer panel = new Renderer(tableJeu.getBalls(), tableJeu.getHoles(), cursor);
        AimLineListener ligneListener = new AimLineListener(panel, cursor);
        AimListener aimListener = new AimListener(tableJeu);
        panel.addMouseListener(ligneListener);
        panel.addMouseMotionListener(ligneListener);
        panel.addMouseListener(aimListener);

        /*
         * Thread for renderer
         */
        Thread render = new Thread(() -> {
            while (true) {
                long before = System.currentTimeMillis();
                panel.drawUpdate();
                long after = System.currentTimeMillis();
                long time = after - before;

                if (time >= FORCED_TIMEOUT_MS) continue;

                try {
                    Thread.sleep((long) (FORCED_TIMEOUT_MS - time));
                } catch (Exception ignored) {}
            }
        });
        render.start();

        /*
         * main loop
         */

        boolean alreadyCheckedRules = true;
        rules.setPlayer(player1,player2);

        while (true) {
            /*WHILE BALL HAS SPEED */
            if (!tableJeu.checkBallsNoSpeed()) {
                alreadyCheckedRules = false;
                panel.ballHasSpeed();
                tableJeu.update();
            }
            /*WHILE PLAYER IS AIMING OR AFTER BALLS MOVEMENT */
            else {
                panel.ballHasNoSpeed();
                /*RULES CHECKER */
                if(!alreadyCheckedRules){
                    rules.printflag();
                    switch (rules.checkRules()){
                        case NO_FOOL -> {
                            System.out.println("NO_FOOL");
                            if(rules.getPlayer(true) == player1){
                                rules.setPlayer(player2,player1);
                            }
                            else{
                                rules.setPlayer(player1,player2);
                            }
                        }
                        case WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL,WHITE_BALL_NO_HIT_FOOL,WHITE_BALL_POTTED_FOOL,BALL_HITTED_BY_WHITE_DO_NOT_TOUCH_BAND_FOOL -> {
                            /*Set whitelitsner active */
                            System.out.println("FOOL");
                            if(rules.getPlayer(true) == player1){
                                rules.setPlayer(player2,player1);
                            }
                            else{
                                rules.setPlayer(player1,player2);
                            }
                        }
                        case BLACK_BALL_POTTED_FOOL -> {
                            if(rules.getPlayer(true) == player1){
                                player2.setHeWin(true);
                            }
                            else{
                                player1.setHeWin(true);
                            }
                        }
                        case NO_FOOL_BUT_CAN_REPLAY ->{System.out.println("NO_FOOL_CAN_REPLAY");}
                    }
                    alreadyCheckedRules = true;
                    rules.resetFlags();
                }
                /*WIN CHECKER */
                if(player1.getHeWin()){
                    System.out.println("player 1 win !");
                    /*reset table */
                }
                else if(player2.getHeWin()){
                    System.out.println("player 2 win !");
                    /*reset table */
                }
            }
        }
    }   
}