package view;
// Java program that creates the Toast message
//(which is a selectively translucent JWindow)
import java.awt.*;
import javax.swing.*;

class Toast extends JPanel{
    // JWindow
	JWindow w;
    String s;

	public Toast(String s)
	{
        super();
        this.s = s;
		w = new JWindow();

		// make the background transparent
		w.setBackground(new Color(0, 0, 0, 0));

		// create a panel
		w.add(this);
		w.setLocation(0,0);
		w.setSize(300, 100);

		try {
			w.setOpacity(1);
			w.setVisible(true);

			// wait for some time
			Thread.sleep(2000);

			// make the message disappear slowly
			for (double d = 1.0; d > 0.2; d -= 0.1) {
				Thread.sleep(100);
				w.setOpacity((float)d);
			}

			// set the visibility to false
			w.setVisible(false);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

        w.dispose();
	}

    @Override
    public void paintComponent(Graphics g) {
        int wid = g.getFontMetrics().stringWidth(s);
        int hei = g.getFontMetrics().getHeight();
        // draw the boundary of the Toast and fill it
        g.setColor(Color.BLACK);
        g.fillRoundRect(10, 10, wid + 30, hei + 10, 10, 10);
        // set the color of text
        g.setColor(Color.WHITE);
        g.drawString(s, 25, 27);
        int t = 250;
        // draw the shadow of the Toast
        for (int i = 0; i < 4; i++) {
            t -= 60;
            g.setColor(new Color(0, 0, 0, t));
            g.drawRoundRect(10 - i, 10 - i, wid + 30 + i * 2,
                    hei + 10 + i * 2, 10, 10);
        }
    }

    public static void MakeToast(String s) {
        new Toast(s);
    }

    public static void main(String[] args) {
        Toast.MakeToast("Test 123 Test");
    }
}

