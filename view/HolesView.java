package view;

import components.Holes.Hole;
import components.Holes.RoundHole;
import components.Holes.StadiumHole;
import libs.CustomDraw;

import java.awt.*;
import java.util.ArrayList;

public class HolesView implements View{
    private final ArrayList<Hole> holes;
    public HolesView (ArrayList<Hole> holes) {
        this.holes = holes;
    }


    @Override
    public void render(Graphics g, double PxPerCm, double verticalOffset) {
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
}
