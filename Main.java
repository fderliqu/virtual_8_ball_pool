import components.*;

import libs.GameStatusEnum;
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
        AimListener aimListener = new AimListener(tableJeu, rules);
        WhiteListener whiteListener = new WhiteListener(tableJeu.getBalls());
        KeyboardListener keyListerner = new KeyboardListener(rules);

        AimLineView aimLine = new AimLineView(ligneListener.getCursor(), tableJeu.getBalls().get(0).getPos());

        views.add(new TableView());
        views.add(new HolesView(tableJeu.getHoles()));
        views.add(new StatusView(rules));
        views.add(new StartZoneView());
        views.add(new BallsView(tableJeu.getBalls()));
        views.add(aimLine);
        views.add(new WinnerView(rules));

        Renderer panel = new Renderer(views);
        ligneListener.setView(aimLine);

        panel.addMouseListener(ligneListener);
        panel.addMouseMotionListener(ligneListener);
        panel.addMouseListener(aimListener);
        panel.addMouseMotionListener(aimListener);
        panel.addMouseListener(whiteListener);
        panel.addMouseMotionListener(whiteListener);

        panel.getWindow().addKeyListener(keyListerner);

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

        while (true) {
            /*WHILE BALL HAS SPEED */
            while (tableJeu.anyBallMoving()) {
                if(alreadyCheckedRules)whiteListener.off();
                alreadyCheckedRules = false;
                tableJeu.update();
            }
            /*WHILE PLAYER IS AIMING OR AFTER BALLS MOVEMENT */
            /*RULES CHECKER */
            if(!alreadyCheckedRules){
                if (rules.getStatus() != GameStatusEnum.NO_FOOL &&
                    rules.getStatus() != GameStatusEnum.NO_FOOL_BUT_CAN_REPLAY) {

                    whiteListener.on();
                    tableJeu.getBalls().get(0).setIsPotted(false);
                }
                alreadyCheckedRules = true;
                rules.endTurn();
            }

            //Thread.sleep(10);
        }
    }   
}