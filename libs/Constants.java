package libs;

import java.awt.Toolkit;
import java.awt.Color;

public class Constants {
    //all dimensions of the billard stuff in cm
    public static final double BALL_SIZE = 5.72;

    public static final double GAME_SURFACE_WIDTH = 127;
    public static final double GAME_SURFACE_LENGTH = 254;


    public static final double POOL_TABLE_WIDTH = 153;
    public static final double POOL_TABLE_LENGTH = 280;
    public static final double WALL_THICKNESS = (POOL_TABLE_WIDTH - GAME_SURFACE_WIDTH)/2;

    public static final double ANGLE_HOLE_DIAMETER = 13; // 12.5-14
    public static final double MID_HOLE_DIAMETER = 11;   // 10.5-12

    public static final double MARK_DIAMETER = 2;
    public static final double START_ZONE = GAME_SURFACE_LENGTH/5.0;

    public static final double RUBBER_BAND = 3.6;

    //screen dimension
    public static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //Px cm conversion
    public static final double PX_PER_CM = SCREEN_WIDTH/POOL_TABLE_LENGTH;

    public static final double CANCEL_CERCLE_RADIUS = 50;
    public static final double TABLE_DEACCELERATION = 90;

    //BallType
    public static final int STRIPED = 1;
    public static final int PLAIN = 2;
    public static final int WHITE = 3;
    public static final int BLACK = 4;

    public enum BallTypeEnum {
        STRIPED,
        PLAIN,
        WHITE,
        BLACK
    }

    //ColorType
    
    public static final Color YELLOW = new Color(254,209,1);
    public static final Color BLUE = new Color(1,0,228);
    public static final Color RED = new Color(235,0,1);
    public static final Color PURPLE = new Color(132,39,145);
    public static final Color ORANGE = new Color(245,117,30);
    public static final Color GREEN = new Color(0,124,62);
    public static final Color BROWN = new Color(119,1,1);  

    public enum ColorTypeEnum{
        YELLOW,
        BLUE,
        RED,
        PURPLE,
        ORANGE,
        GREEN,
        BROWN,
    }
    //Others

    public static final double VERTICAL_OFFSET_CM = (SCREEN_HEIGHT/PX_PER_CM - POOL_TABLE_WIDTH)/2;
    public static final double HORIZONTAL_OFFSET_CM = (SCREEN_WIDTH/PX_PER_CM - POOL_TABLE_LENGTH)/2;  
}