import components.*;

import listeners.*;
import render.*;
import static libs.Constants.*;
import view.*;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        BallTable tableJeu = new BallTable();
        Rules rules = new Rules(player1, player2, tableJeu);
        tableJeu.setRules(rules);

        //views creation
        ArrayList<View> views = new ArrayList<>();

        AimLineListener ligneListener = new AimLineListener(null);
        AimListener aimListener = new AimListener(tableJeu);
        WhiteListener whiteListener = new WhiteListener(tableJeu.getBalls());
        KeyboardListener keyListerner = new KeyboardListener(tableJeu, rules, player1, player2);

        AimLineView aimLine = new AimLineView(ligneListener.getCursor(), tableJeu.getBalls().get(0).getPos());

        views.add(new TableView());
        views.add(new HolesView(tableJeu.getHoles()));
        views.add(new StatusView(rules));
        views.add(new StartZoneView());
        views.add(new BallsView(tableJeu.getBalls()));
        views.add(aimLine);

        Renderer panel = new Renderer(views,keyListerner);
        ligneListener.setView(aimLine);

        panel.addMouseListener(ligneListener);
        panel.addMouseMotionListener(ligneListener);
        panel.addMouseListener(aimListener);
        panel.addMouseMotionListener(aimListener);
        panel.addMouseListener(whiteListener);
        panel.addMouseMotionListener(whiteListener);



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
        boolean alreadyCheckedWinner = true;
        rules.setPlayer(player1,player2);

        while (true) {
            /*WHILE BALL HAS SPEED */
            if (tableJeu.anyBallMoving()) {
                if(alreadyCheckedRules)whiteListener.off();
                alreadyCheckedRules = false;
                alreadyCheckedWinner = false;
                tableJeu.update();
            }
            /*WHILE PLAYER IS AIMING OR AFTER BALLS MOVEMENT */
            else {
                /*RULES CHECKER */
                if(!alreadyCheckedRules){
                    //rules.printflag();
                    switch (rules.getStatus()){
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
                            System.out.println("FOOL");
                            whiteListener.on();
                            tableJeu.getBalls().get(0).setIsPotted(false);
                            if(rules.getPlayer(true) == player1){
                                rules.setPlayer(player2,player1);
                            }
                            else{
                                rules.setPlayer(player1,player2);
                            }
                        }
                        case BLACK_BALL_POTTED_FOOL -> {
                            rules.setWinner(rules.getPlayer(true));
                        }
                        case NO_FOOL_BUT_CAN_REPLAY ->{System.out.println("NO_FOOL_CAN_REPLAY");}
                    }
                    alreadyCheckedRules = true;
                    rules.resetFlags();
                }
                /*WIN CHECKER */
                if(!alreadyCheckedWinner){
                    if(rules.getWinner() != null) {
                        System.out.println(rules.getWinner().getID() + " has won");
                    }
                    alreadyCheckedWinner = true;
                }    
            }
        }
    }   
}