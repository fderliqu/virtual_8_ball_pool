package libs;

import components.SimplePoint;

import javax.swing.*;
import java.awt.*;

public class CustomDraw {

    public static void fillStadium(Graphics g, SimplePoint p1, SimplePoint p2, int thickness, Color c) {
        g.setColor(c);
        g.fillOval((int) p1.getX(), (int) p1.getY(), thickness, thickness);
        g.fillOval((int) p2.getX(), (int) p2.getY(), thickness, thickness);

        double dist = p1.distanceTo(p2);
        int halfThickness = thickness/2;
        double ratio = halfThickness/dist;

        if (p1.getY() == p2.getY()) {
            g.fillRect((int) p1.getX()+halfThickness, (int) p1.getY()+thickness, Math.abs((int) (p2.getX()-p1.getX())), -thickness);
            return;
        }
        /*
        * Here, we are trying to figure out the type of stadium we are trying to draw
        * First we are putting in a variable the point that is the most on the left
        * than we are calculating if we are in the scenario where the point most on the left
        * has a bigger y than the most on the right we than have a trajectory that is going on the
        * right and then upward
        * */
        SimplePoint leftMost = (p1.getX() < p2.getX())?p1:p2;
        SimplePoint rightMost = (p1.getX() > p2.getX())?p1:p2;

        boolean upRight = leftMost.getY() <= rightMost.getY();

        int widthDiff = (int) ((leftMost.getY()-rightMost.getY()) * ratio);
        int heightDiff = (int) ((rightMost.getX()-leftMost.getX()) * ratio);

        int[] x;
        int[] y;

        if (upRight) {
            x = new int[]{(int) leftMost.getX()+halfThickness-widthDiff,
                          (int) rightMost.getX()+halfThickness-widthDiff,
                          (int) rightMost.getX()+halfThickness+widthDiff,
                          (int) leftMost.getX()+halfThickness+widthDiff};

            y = new int[]{(int) leftMost.getY()+halfThickness-heightDiff,
                          (int) rightMost.getY()+halfThickness-heightDiff,
                          (int) rightMost.getY()+halfThickness+heightDiff,
                          (int) leftMost.getY()+halfThickness+heightDiff};
        } else {
            x = new int[]{(int) leftMost.getX()+halfThickness+widthDiff,
                          (int) rightMost.getX()+halfThickness+widthDiff,
                          (int) rightMost.getX()+halfThickness-widthDiff,
                          (int) leftMost.getX()+halfThickness-widthDiff};

            y = new int[]{(int) leftMost.getY()+halfThickness+heightDiff,
                          (int) rightMost.getY()+halfThickness+heightDiff,
                          (int) rightMost.getY()+halfThickness-heightDiff,
                          (int) leftMost.getY()+halfThickness-heightDiff};
        }

        g.fillPolygon(x, y, 4);
    }

    private static class TestClass extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            fillStadium(g, new SimplePoint(50,200), new SimplePoint(200, 50), 150, Color.BLACK);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        TestClass board = new TestClass();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(500,500));
        window.setVisible(true);
        board.repaint();
        window.add(board);
        window.revalidate();
        window.repaint();
    }
}
