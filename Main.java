import components.*;
import listeners.AimLineListener;
import listeners.AimListener;
import render.*;
import static libs.Constants.*;
import libs.SimplePoint;

public class Main {
    
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BallTable tableJeu = new BallTable(player1, player2);

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

        while (true) {
            if (!tableJeu.checkBallsNoSpeed()) {
                panel.ballHasSpeed();
                tableJeu.update();
            }
            else panel.ballHasNoSpeed();
        }
    }
}