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

    private int PxPerCm;

    public Renderer (ArrayList<Ball> balls) {
        super();
        this.balls = balls;

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);
        window.setVisible(true);

        window.add(this);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(new Color(107, 62, 46));
    }

    public void render() {

    }

    public void lazyRender() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        PxPerCm = (int) (width/Constants.POOL_TABLE_LENGTH);

        window.setSize(width, height);
    }
}
