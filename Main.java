import components.*;
import render.*;
import static libs.Constants.*;
import libs.SimplePoint;

import java.awt.event.*;

public class Main {
    static boolean flag = false;
    static boolean flag2 = false;
    static boolean flag3 = false;
    static SimplePoint mousePressed = new SimplePoint(0,0);
    static SimplePoint mouseReleased = new SimplePoint(0,0);
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BallTable tableJeu = new BallTable(player1,player2);

        Renderer panel = new Renderer(tableJeu.getBalls(), tableJeu.getHoles());
/*
        panel.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e){
				tableJeu.setCursor(e.getX(), e.getY());
			}
		});
        */

        panel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1 && flag3){
                    flag = true;
                    mousePressed.setX(e.getX()/PX_PER_CM);
                    mousePressed.setY(e.getY()/PX_PER_CM);
                    System.out.println("x:"+mousePressed.getX()+" y:"+mousePressed.getY());
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    flag = false;
                    flag2 = false;
                }
			}
			public void mouseReleased(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1 && flag == true){
                    flag2 = true;
                    mouseReleased.setX(e.getX()/PX_PER_CM);
                    mouseReleased.setY(e.getY()/PX_PER_CM);
                    System.out.println("x:"+mouseReleased.getX()+" y:"+mouseReleased.getY());
                    if(flag){
                        Ball white = tableJeu.getBalls().get(0);
                        double dist = white.getPos().distanceTo(mousePressed);
                        System.out.println("dist : "+dist);
                        SimplePoint intensity = new SimplePoint((mousePressed.getX()-white.getPosX())/dist,(mousePressed.getY()-white.getPosY())/dist);
                        System.out.println("x:"+intensity.getX()+" y:"+intensity.getY());
                        dist = Math.min((double)400,mousePressed.distanceTo(mouseReleased)*4);
                        System.out.println("dist : "+dist);
                        tableJeu.setNewTime(System.nanoTime());
                        white.setSpeedX(dist*intensity.getX());
                        white.setSpeedY(dist*intensity.getY());
                    }
                    flag = false;
                }
			}
		});

        Thread render = new Thread(){
            public void run(){
                    while(true){
                    long before = System.currentTimeMillis();
                    panel.drawUpdate();
                    long after = System.currentTimeMillis();
                    long time = after - before;
                    //System.out.println("Time :" + time );
                    if(time < FORCED_TIMEOUT_MS){
                        try{
                            Thread.sleep((long)(FORCED_TIMEOUT_MS - time));
                        }catch(Exception e){};
                    }
                }
            }
        };
        render.start();

        while(true){
            flag3 = tableJeu.checkBallsNoSpeed();
            if(!flag3) {
                tableJeu.update();
            }
        }
    }
}