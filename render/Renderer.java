package render;

import components.Holes.Hole;
import components.Holes.RoundHole;
import components.Holes.StadiumHole;
import libs.CustomDraw;
import view.View;

import static libs.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private final ArrayList<Hole> holes;
    private final ArrayList<View> views;
    private final JFrame window = new JFrame();
    private final int height;
    private final double PxPerCm;
    private boolean ballSpeed=true;

    public Renderer (ArrayList<Hole> holes, ArrayList<View> views) {
        super();
        this.holes  = holes;
        this.views  = views;

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

        /*
        *
        * Drawing brown outer frame
        *
        */
        g.setColor(new Color(107, 62, 46));
        int verticalOffset = (int) (height - (PxPerCm * POOL_TABLE_WIDTH))/2;
        g.fillRoundRect(0, verticalOffset, (int) (PxPerCm * POOL_TABLE_LENGTH), (int) (PxPerCm * POOL_TABLE_WIDTH), 30, 30);

        /*
        *
        * Drwawing the green playboard
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
        * Drawing views (WIP)
        *
        */
        for (View v : views) {
            v.render(g, PxPerCm, verticalOffset);
        }


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
    }

    public void ballHasSpeed(){
        ballSpeed = true;
    }

    public void ballHasNoSpeed(){
        ballSpeed = false;
    }

    public void drawUpdate(){
        window.repaint();
    }
}