package render;

import components.*;
import static libs.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private final ArrayList<Ball> balls;
    private final ArrayList<Hole> holes;
    private JPanel board = new JPanel();
    private final JFrame window = new JFrame();
    private int width;
    private int height;
    private double PxPerCm;

    public Renderer (ArrayList<Ball> balls, ArrayList<Hole> holes) {
        super();
        this.balls = balls;
        this.holes = holes;

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);
        window.setVisible(true);

        window.add(this, BorderLayout.CENTER);
        window.add(board, BorderLayout.SOUTH);
        this.lazyRender();
        window.revalidate();
        window.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(107, 62, 46));
        //g2d.setColor(new Color(107, 62, 46));
        int verticalOffset = (int) (height - (PxPerCm * POOL_TABLE_WIDTH))/2;
        g.fillRoundRect(0, verticalOffset, (int) (PxPerCm * POOL_TABLE_LENGTH), (int) (PxPerCm * POOL_TABLE_WIDTH), 30, 30);
        //g.drawRoundRect(0, 0, 200, 200, 20, 20);

        g.setColor(new Color(0, 184, 148));
        g.fillRoundRect(
                (int) (PxPerCm * WALL_THICKNESS),
                (int) (verticalOffset+ PxPerCm * WALL_THICKNESS),
                (int) (PxPerCm * GAME_SURFACE_LENGTH),
                (int) (PxPerCm * GAME_SURFACE_WIDTH),
        30, 30);
        
        //Holes render

        g.setColor(new Color(0, 0, 0));
        for(Hole h : holes){
            g.fillOval( (int)((h.getPosition().getX() - h.getDiameter()/2)*PxPerCm), 
                        (int)((h.getPosition().getY() - h.getDiameter()/2)*PxPerCm), 
                        (int)(h.getDiameter()*PxPerCm), 
                        (int)(h.getDiameter()*PxPerCm)
            );
        }


        g.setColor(new Color(255,255,255));
        //System.out.println("start zone : "+START_ZONE);
        g.drawLine(
                (int) (PxPerCm * (WALL_THICKNESS + START_ZONE)),
                (int) ((PxPerCm * WALL_THICKNESS) + verticalOffset),
                (int) (PxPerCm * (WALL_THICKNESS + START_ZONE)),
                (int) (((PxPerCm * (WALL_THICKNESS + GAME_SURFACE_WIDTH)) + verticalOffset))
                );

        g.setColor(new Color(85, 239, 196));

        //g.fillPolygon();

        //System.out.println("width, height = "+width+" "+height);
        //System.out.println("pxpercm, pool_width = "+PxPerCm+" "+(PxPerCm * POOL_TABLE_WIDTH));
        //System.out.println("verticalOffset = "+verticalOffset);

        for (Ball b : balls) {
            /* 
            switch (b.getBallType()) {
                case WHITE -> g.setColor(new Color(255, 255, 255));
                case BLACK -> g.setColor(new Color(0, 0, 0));
                default -> g.setColor(new Color(150, 25, 55));
            }
            */
            if(!b.getIsDropped()){
                switch (b.getBallNumber()){
                    case 0 -> g.setColor(new Color(255, 255, 255));
                    case 1 -> g.setColor(YELLOW);
                    case 2 -> g.setColor(BLUE);
                    case 3 -> g.setColor(RED);
                    case 4 -> g.setColor(PURPLE);
                    case 5 -> g.setColor(ORANGE);
                    case 6 -> g.setColor(GREEN);
                    case 7 -> g.setColor(BROWN);
                    case 8 -> g.setColor(new Color(0, 0, 0));
                    case 9 -> g.setColor(YELLOW);
                    case 10 -> g.setColor(BLUE);
                    case 11 -> g.setColor(RED);
                    case 12 -> g.setColor(PURPLE);
                    case 13 -> g.setColor(ORANGE);
                    case 14 -> g.setColor(GREEN);
                    case 15 -> g.setColor(BROWN);
                }

                g.fillOval((int) (PxPerCm * (b.getPosX() - BALL_SIZE/2)),
                            (int) (PxPerCm * (b.getPosY() - BALL_SIZE/2)),
                            (int) (PxPerCm * BALL_SIZE),
                            (int) (PxPerCm * BALL_SIZE));
            

                if (b.getBallType() == BallTypeEnum.STRIPED) {
                    g.setColor(new Color(255, 255, 255));
                        g.drawLine(
                            (int) (PxPerCm * b.getPosX()),
                            (int) (PxPerCm * (b.getPosY() - (BALL_SIZE / 2))),
                            (int) (PxPerCm * b.getPosX()),
                            (int) (PxPerCm * (b.getPosY() + (BALL_SIZE / 2)))
                        );
                }
            }
        }
    }

    public void drawUpdate(){
        window.repaint();
    }

    public void lazyRender() {
        width = window.getWidth() - this.getWidth();
        height = window.getHeight();

        PxPerCm = width/POOL_TABLE_LENGTH;
    }
}