import components.*;
import listeners.AimLineListener;
import render.*;
import static libs.Constants.*;
import libs.SimplePoint;

import java.awt.event.*;

public class Main {
    static boolean isAiming = false;
    static SimplePoint mousePressed = new SimplePoint(0,0);
    static SimplePoint mouseReleased = new SimplePoint(0,0);
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BallTable tableJeu = new BallTable(player1, player2);

        SimplePoint cursor = new SimplePoint(0, 0);
        Renderer panel = new Renderer(tableJeu.getBalls(), tableJeu.getHoles(), cursor);
        AimLineListener ligneListener = new AimLineListener(panel, cursor);
        panel.addMouseListener(ligneListener);
        panel.addMouseMotionListener(ligneListener);

        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    isAiming = true;
                    mousePressed.setX(e.getX() / PX_PER_CM);
                    mousePressed.setY(e.getY() / PX_PER_CM);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    isAiming = false;
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && isAiming) {

                    if (!tableJeu.checkBallsNoSpeed()) {
                        isAiming = false;
                        return;
                    }

                    mouseReleased.setX(e.getX() / PX_PER_CM);
                    mouseReleased.setY(e.getY() / PX_PER_CM);

                    Ball white = tableJeu.getBalls().get(0);
                    double dist = white.getPos().distanceTo(mousePressed);

                    SimplePoint intensity = new SimplePoint((mouseReleased.getX() - white.getPosX()) / dist, (mouseReleased.getY() - white.getPosY()) / dist);
                    dist = Math.min(400, mousePressed.distanceTo(mouseReleased) * 2);

                    tableJeu.setNewTime(System.nanoTime());

                    white.setSpeedX(dist * intensity.getX());
                    white.setSpeedY(dist * intensity.getY());

                    isAiming = false;
                }
            }
        });

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

        while (true) {
            if (!tableJeu.checkBallsNoSpeed()) {
                tableJeu.update();
            }
        }
    }
}