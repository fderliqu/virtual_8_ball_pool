import models.*;

import libs.GameStatusEnum;
import listeners.*;
import render.*;

import static libs.Constants.*;

import rules.Rule;
import view.*;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {

        BallTable tableJeu = new BallTable();
        Rule rules = new Rule(tableJeu);
        tableJeu.setRules(rules);

        //views creation
        ArrayList<ViewInterface> views = new ArrayList<>();

        AimLineListener ligneListener = new AimLineListener(null);
        AimListener aimListener = new AimListener(tableJeu, rules);
        WhiteListener whiteListener = new WhiteListener(tableJeu.getBalls());
        KeyboardListener keyListerner = new KeyboardListener(rules);

        AimLineView aimLine = new AimLineView(ligneListener.getCursor(), tableJeu.getBalls().get(0).getPos());
        WinnerView wview = new WinnerView(rules);

        views.add(new TableView());
        views.add(new HolesView(tableJeu.getHoles()));
        views.add(new StatusView(rules));
        views.add(new StartZoneView());
        views.add(new BallsView(tableJeu.getBalls()));
        views.add(aimLine);
        views.add(wview);

        Renderer panel = new Renderer(views);
        ligneListener.setView(aimLine);
        rules.setWinnerView(wview);

        panel.addMouseListener(ligneListener);
        panel.addMouseMotionListener(ligneListener);
        panel.addMouseListener(aimListener);
        panel.addMouseMotionListener(aimListener);
        panel.addMouseListener(whiteListener);
        panel.addMouseMotionListener(whiteListener);

        panel.getWindow().addKeyListener(keyListerner);

        Sound.COLLIDE.init();
        /*
         * Thread for renderer
         */
        Thread render = new Thread(() -> {
            while (rules.getStatus() != GameStatusEnum.GAME_STOPPED) {
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

        while (rules.getStatus() != GameStatusEnum.GAME_STOPPED) {
            /*WHILE BALL HAS SPEED */
            while (tableJeu.anyBallMoving()) {
                if(alreadyCheckedRules){
                    whiteListener.off();
                    Sound.CUE_HIGH.playSound();
                }
                alreadyCheckedRules = false;
                tableJeu.update();
            }
            /*WHILE PLAYER IS AIMING OR AFTER BALLS MOVEMENT */
            /*RULES CHECKER */
            if(!alreadyCheckedRules){
                rules.endTurn();
                if (rules.getStatus().getFool()) {
                    whiteListener.on();
                    tableJeu.getBalls().get(0).setIsPotted(false);
                } else {
                    whiteListener.off();
                }

                alreadyCheckedRules = true;
            }

            Thread.sleep(10);
        }

        System.exit(0);
    }   
}