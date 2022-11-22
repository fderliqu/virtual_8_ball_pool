import components.*;
import listeners.AimLineListener;
import listeners.AimListener;
import render.*;
import static libs.Constants.*;
import libs.SimplePoint;
import view.*;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player playerTurn = player1;
        Player playerNoTurn = player2;
        Rules rules = new Rules();
        BallTable tableJeu = new BallTable(playerTurn,playerNoTurn,rules);

        SimplePoint cursor = new SimplePoint(0, 0);

        //views creation
        ArrayList<View> views = new ArrayList<>();
        views.add(new TableView());
        views.add(new HolesView(tableJeu.getHoles()));
        views.add(new StartZoneView());
        views.add(new BallsView(tableJeu.getBalls()));

        Renderer panel = new Renderer(views);

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

        boolean alreadyCheckedRules = false;
        rules.setPlayers(player1);

        while (true) {
            if (!tableJeu.checkBallsNoSpeed()) {
                alreadyCheckedRules = false;
                panel.ballHasSpeed();
                tableJeu.update();
            }
            else {
                panel.ballHasNoSpeed();
                if(!alreadyCheckedRules){
                    System.out.println(""+rules.checkRules());
                    rules.printflag();
                    alreadyCheckedRules = true;
                    rules.resetFlags();
                }
            }
        }
    }   
}