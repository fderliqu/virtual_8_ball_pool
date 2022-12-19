package view;

import models.Holes.HoleInterface;
import models.Holes.RoundHole;
import models.Holes.StadiumHole;
import libs.CustomDraw;

import java.awt.*;
import java.util.ArrayList;

import static libs.Constants.DEBUG;
import static libs.Constants.PX_PER_CM;

public class HolesView implements ViewInterface {
    private final ArrayList<HoleInterface> holes;
    public HolesView (ArrayList<HoleInterface> holes) {
        this.holes = holes;
    }


    @Override
    public void render(Graphics g) {
        /*
        *
        * Drawing the holes
        *
        */
        for(HoleInterface h : holes){
            if (h instanceof RoundHole) {
                RoundHole r = (RoundHole) h;
                double holex = r.getPosition().getX();
                double holey = r.getPosition().getY();
                double holediam = r.getDiameter();

                g.setColor(Color.BLACK);
                g.fillOval((int) Math.round((holex - (holediam / 2)) * PX_PER_CM),
                        (int) Math.round((holey - (holediam/2)) * PX_PER_CM),
                        (int) Math.round(holediam * PX_PER_CM),
                        (int) Math.round(holediam * PX_PER_CM)
                );

                if (DEBUG) {
                    g.setColor(Color.RED);
                    g.drawLine((int) Math.round(holex * PX_PER_CM),
                            (int) Math.round(holey * PX_PER_CM) + 100,
                            (int) Math.round(holex * PX_PER_CM),
                            (int) Math.round(holey * PX_PER_CM) - 100
                    );

                    g.drawLine((int) Math.round(holex * PX_PER_CM) + 100,
                            (int) Math.round(holey * PX_PER_CM),
                            (int) Math.round(holex * PX_PER_CM) - 100,
                            (int) Math.round(holey * PX_PER_CM)
                    );
                }

            } else if (h instanceof StadiumHole) {
                CustomDraw.fillStadium(g, ((StadiumHole) h).getC1(), ((StadiumHole) h).getC2(), (int) ((StadiumHole) h).getThickness(), Color.BLACK);
            }
        }
    }
}
