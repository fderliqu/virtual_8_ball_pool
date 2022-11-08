package render;

import components.*;
import components.Holes.Hole;
import components.Holes.RoundHole;
import components.Holes.StadiumHole;
import libs.CustomDraw;
import libs.SimplePoint;

import static libs.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private final ArrayList<Ball> balls;
    private final ArrayList<Hole> holes;
    private final JFrame window = new JFrame();
    private final int height;
    private final double PxPerCm;
    private Ball whiteBall=null;

    private final SimplePoint cursor;
    private boolean isAiming;

    public Renderer (ArrayList<Ball> balls, ArrayList<Hole> holes, SimplePoint cursor) {
        super();
        this.balls  = balls;
        this.holes  = holes;
        this.cursor = cursor;

        for (Ball b : balls) {
            if (b.getBallType() == BallTypeEnum.WHITE) {
                whiteBall = b;
                break;
            }
        }

        if (whiteBall == null) {
            System.out.println("there are no white ball");
            System.exit(2);
        }

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        window.setUndecorated(true);
        window.setVisible(true);

        if (d.isFullScreenSupported()) {
            d.setFullScreenWindow(window);
        } else {
            System.out.println("Fullscreen is not supported for some reason");
        }


        window.add(this);
        int width = window.getWidth() - this.getWidth();
        height = window.getHeight();

        PxPerCm = width /POOL_TABLE_LENGTH;
        window.revalidate();
        window.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(new Color(107, 62, 46));
        int verticalOffset = (int) (height - (PxPerCm * POOL_TABLE_WIDTH))/2;
        g.fillRoundRect(0, verticalOffset, (int) (PxPerCm * POOL_TABLE_LENGTH), (int) (PxPerCm * POOL_TABLE_WIDTH), 30, 30);

        /*
        *
        * Drawing brown outer frame
        *
        */
        g.setColor(new Color(0, 184, 148));
        g.fillRoundRect(
                (int) (PxPerCm * WALL_THICKNESS),
                (int) (verticalOffset+ PxPerCm * WALL_THICKNESS),
                (int) (PxPerCm * GAME_SURFACE_LENGTH),
                (int) (PxPerCm * GAME_SURFACE_WIDTH),
        30, 30);
        
        /*
        *
        * Drawing the holes
        *
        */
        g.setColor(new Color(0, 0, 0));
        for(Hole h : holes){
            if (h instanceof RoundHole) {
                g.fillOval((int) ((((RoundHole) h).getPosition().getX() - ((RoundHole) h).getDiameter() / 2) * PxPerCm),
                        (int) ((((RoundHole) h).getPosition().getY() - ((RoundHole) h).getDiameter() / 2) * PxPerCm),
                        (int) (((RoundHole) h).getDiameter() * PxPerCm),
                        (int) (((RoundHole) h).getDiameter() * PxPerCm)
                );
            } else if (h instanceof StadiumHole) {
                CustomDraw.fillStadium(g, ((StadiumHole) h).getC1(), ((StadiumHole) h).getC2(), (int) ((StadiumHole) h).getThickness(), Color.BLACK);
            }
        }


        /*
        *
        * Drawing the start zone vertical line
        *
        */
        g.setColor(new Color(255,255,255));
        //System.out.println("start zone : "+START_ZONE);
        g.drawLine(
                (int) (PxPerCm * (WALL_THICKNESS + START_ZONE)),
                (int) ((PxPerCm * WALL_THICKNESS) + verticalOffset),
                (int) (PxPerCm * (WALL_THICKNESS + START_ZONE)),
                (int) (((PxPerCm * (WALL_THICKNESS + GAME_SURFACE_WIDTH)) + verticalOffset))
                );

        g.setColor(new Color(85, 239, 196));


        /*
        *
        *  Drawing balls
        *
        */
        for (Ball b : balls) {
            if(!b.getIsDropped()){
                switch (b.getBallNumber()){
                    case 0  -> g.setColor(Color.WHITE);
                    case 1, 9 -> g.setColor(YELLOW);
                    case 2, 10 -> g.setColor(BLUE);
                    case 3, 11 -> g.setColor(RED);
                    case 4, 12 -> g.setColor(PURPLE);
                    case 5, 13 -> g.setColor(ORANGE);
                    case 6, 14 -> g.setColor(GREEN);
                    case 7, 15 -> g.setColor(BROWN);
                    case 8  -> g.setColor(Color.BLACK);
                }

                g.fillOval((int) (PxPerCm * (b.getPosX() - BALL_SIZE/2)),
                            (int) (PxPerCm * (b.getPosY() - BALL_SIZE/2)),
                            (int) (PxPerCm * BALL_SIZE),
                            (int) (PxPerCm * BALL_SIZE));
            

                if (b.getBallType() == BallTypeEnum.STRIPED) {
                    g.setColor(Color.WHITE);
                        g.drawLine(
                            (int) (PxPerCm * b.getPosX()),
                            (int) (PxPerCm * (b.getPosY() - (BALL_SIZE / 2))),
                            (int) (PxPerCm * b.getPosX()),
                            (int) (PxPerCm * (b.getPosY() + (BALL_SIZE / 2)))
                        );
                }
            }
        }

        /*
        *
        * Drawing the aiming line
        *
        */
        if (isAiming) {
            g.setColor(Color.WHITE);
            g.drawLine((int) (PxPerCm * whiteBall.getPosX()), (int) (PxPerCm * whiteBall.getPosY()), (int) cursor.getX(), (int) cursor.getY());
        }
    }

    public void startAiming () {
        isAiming = true;
    }

    public void stopAiming() {
        isAiming = false;
    }

    public void drawUpdate(){
        window.repaint();
    }
}