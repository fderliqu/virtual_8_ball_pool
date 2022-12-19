package render;

import view.ViewInterface;

import static libs.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private final ArrayList<ViewInterface> views;
    private final JFrame window = new JFrame();
    private final int height;

    public Renderer (ArrayList<ViewInterface> views) {
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

        if(DEBUG) {
            System.out.println("const Pxpercm = "+PX_PER_CM);
            System.out.println("constant width, height = "+SCREEN_WIDTH+","+SCREEN_HEIGHT);
            System.out.println("calculated width, height = "+width+","+height);
            System.out.println("rounded pxpercm = " + Math.round(PX_PER_CM));
            System.out.println("recomposed width, height = "+Math.round(POOL_TABLE_LENGTH * PX_PER_CM) + ", "+Math.round(POOL_TABLE_WIDTH*PX_PER_CM));
            System.out.println("horizontal, vertical offsets = "+HORIZONTAL_OFFSET_CM+", "+VERTICAL_OFFSET_CM);
        }

        window.revalidate();
        window.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        for (ViewInterface v : views) {
            v.render(g);
        }
    }

    public void drawUpdate(){
        window.repaint();
    }

    public JFrame getWindow() {
        return window;
    }
}