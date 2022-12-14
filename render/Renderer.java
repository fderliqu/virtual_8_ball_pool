package render;

import view.View;

import static libs.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private final ArrayList<View> views;
    private final JFrame window = new JFrame();
    private final int height;
    private final double PxPerCm;

    public Renderer (ArrayList<View> views) {
        super();
        this.views  = views;

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        window.setUndecorated(true);
        window.setVisible(true);
        window.setFocusable(true);
        if(window.requestFocusInWindow())System.out.println("request focus succes");

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
        //verticalOffset is the space available between the top of the table and the top of the screen
        int verticalOffset = (int) (height - (PxPerCm * POOL_TABLE_WIDTH))/2;

        for (View v : views) {
            v.render(g, PxPerCm, verticalOffset);
        }
    }

    public void drawUpdate(){
        window.repaint();
    }

    public JFrame getWindow() {
        return window;
    }
}