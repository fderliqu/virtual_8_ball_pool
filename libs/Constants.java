package libs;

import java.awt.Toolkit;

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

    //Others

    public static final double VERTICAL_OFFSET_CM = (SCREEN_HEIGHT/PX_PER_CM - POOL_TABLE_WIDTH)/2;
    public static final double HORIZONTAL_OFFSET_CM = (SCREEN_WIDTH/PX_PER_CM - POOL_TABLE_LENGTH)/2;  
}