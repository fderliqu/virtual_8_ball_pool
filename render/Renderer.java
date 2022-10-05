package render;

import components.Ball;
import libs.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private ArrayList<Ball> balls;
    private JPanel canva = new JPanel();
    private JFrame window = new JFrame();
    private int width;
    private int height;

    private double PxPerCm;

    public Renderer (ArrayList<Ball> balls) {
        super();
        this.balls = balls;

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);
        window.setVisible(true);

        window.add(this);
        this.lazyRender();
        window.revalidate();
        window.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(107, 62, 46));
        //g2d.setColor(new Color(107, 62, 46));
        int verticalOffset = (int) (height - (PxPerCm * Constants.POOL_TABLE_WIDTH))/2;
        g.fillRoundRect(0, verticalOffset, (int) (PxPerCm * Constants.POOL_TABLE_LENGTH), (int) (PxPerCm * Constants.POOL_TABLE_WIDTH), 30, 30);
        //g.drawRoundRect(0, 0, 200, 200, 20, 20);

        g.setColor(new Color(0, 184, 148));
        g.fillRoundRect(
                (int) (PxPerCm * Constants.WALL_THICKNESS),
                (int) (verticalOffset+ PxPerCm * Constants.WALL_THICKNESS),
                (int) (PxPerCm * Constants.GAME_SURFACE_LENGTH),
                (int) (PxPerCm * Constants.GAME_SURFACE_WIDTH),
        30, 30);

        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < 6; ++i){
            g.fillOval(
                (int) ((PxPerCm * Constants.WALL_THICKNESS/2) + (PxPerCm * (Constants.GAME_SURFACE_LENGTH/2)*(i%3))),
                (int) (((PxPerCm * Constants.WALL_THICKNESS/2) + verticalOffset) + (i>2?PxPerCm * Constants.GAME_SURFACE_WIDTH:0)),
                (int) (i%3 == 1?(PxPerCm * Constants.MID_HOLE_DIAMETER):(PxPerCm * Constants.ANGLE_HOLE_DIAMETER)),
                (int) (i%3 == 1?(PxPerCm * Constants.MID_HOLE_DIAMETER):(PxPerCm * Constants.ANGLE_HOLE_DIAMETER))
            );
        }

        g.setColor(new Color(255,255,255));
        System.out.println("start zone : "+Constants.START_ZONE);
        g.drawLine(
                (int) (PxPerCm * (Constants.WALL_THICKNESS + Constants.START_ZONE)),
                (int) ((PxPerCm * Constants.WALL_THICKNESS) + verticalOffset),
                (int) (PxPerCm * (Constants.WALL_THICKNESS + Constants.START_ZONE)),
                (int) (((PxPerCm * (Constants.WALL_THICKNESS + Constants.GAME_SURFACE_WIDTH)) + verticalOffset))
                );

        g.setColor(new Color(85, 239, 196));

        //g.fillPolygon();

        System.out.println("width, height = "+width+" "+height);
        System.out.println("pxpercm, pool_width = "+PxPerCm+" "+(PxPerCm * Constants.POOL_TABLE_WIDTH));
        System.out.println("verticalOffset = "+verticalOffset);

        for (Ball b : balls) {
            switch (b.getBallType()) {
                case WHITE -> g.setColor(new Color(255, 255, 255));
                case BLACK -> g.setColor(new Color(0, 0, 0));
                default -> g.setColor(new Color(150, 25, 55));
            }

            g.fillOval((int) (PxPerCm * b.getPosX()),
                            (int) (PxPerCm * b.getPosY()),
                            (int) (PxPerCm * Constants.BALL_SIZE),
                            (int) (PxPerCm * Constants.BALL_SIZE));

            if (b.getBallType() == Ball.BallTypeEnum.STRIPED) {
                g.setColor(new Color(255, 255, 255));
                    g.drawLine(
                        (int) (PxPerCm * (b.getPosX() + (Constants.BALL_SIZE / 2))),
                        (int) (PxPerCm * (b.getPosY())),
                        (int) (PxPerCm * (b.getPosX() + (Constants.BALL_SIZE / 2))),
                        (int) (PxPerCm * (b.getPosY() + (Constants.BALL_SIZE)))
                        );
            }
        }
    }

    public void render() {

    }

    public void lazyRender() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        PxPerCm = width/Constants.POOL_TABLE_LENGTH;

        window.setSize(width, height);
    }
}