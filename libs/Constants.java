package libs;

import java.awt.Toolkit;
import java.awt.Color;

public class Constants {
    //all dimensions of the billard stuff in cm
    public static final double BALL_SIZE = 5.72;
    public static final double BALL_SIZE_e = 5.80;
    public static final double DECALE_X = Math.sqrt(BALL_SIZE_e*BALL_SIZE_e - (BALL_SIZE_e*BALL_SIZE_e)/4);

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
    public static final double TABLE_DEACCELERATION = 99;

    public static final long FORCED_FPS = 60;
    public static final double FORCED_TIMEOUT_MS = 1000/FORCED_FPS;

    //BallType
    public static final int NULL = 0;
    public static final int STRIPED = 1;
    public static final int PLAIN = 2;
    public static final int WHITE = 3;
    public static final int BLACK = 4;

    public enum BallTypeEnum {
        NULL,
        STRIPED,
        PLAIN,
        WHITE,
        BLACK
    }

    //RulesType
    public static final int NO_FOOL_BUT_CAN_REPLAY = -1;
    public static final int NO_FOOL = 0;
    public static final int WHITE_BALL_NO_HIT_FOOL = 1;
    public static final int WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL = 2;
    public static final int BALL_HITTED_BY_WHITE_DO_NOT_TOUCH_BAND_FOOL = 3;
    public static final int WHITE_BALL_POTTED_FOOL = 4;
    public static final int BLACK_BALL_POTTED_FOOL = 5;

    public enum RulesTypeEnum {
        NO_FOOL_BUT_CAN_REPLAY,
        NO_FOOL,
        WHITE_BALL_NO_HIT_FOOL,
        WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL,
        BALL_HITTED_BY_WHITE_DO_NOT_TOUCH_BAND_FOOL,
        WHITE_BALL_POTTED_FOOL,
        BLACK_BALL_POTTED_FOOL
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

    //offsets

    public static final double VERTICAL_OFFSET_CM = (SCREEN_HEIGHT/PX_PER_CM - POOL_TABLE_WIDTH)/2;
    public static final double HORIZONTAL_OFFSET_CM = (SCREEN_WIDTH/PX_PER_CM - POOL_TABLE_LENGTH)/2;  

    //Initial pos of balls and holes : 

    public static final double BALLS_INIT_POS_X[] =  {
        WALL_THICKNESS + START_ZONE,
        180,
        180 + DECALE_X,
        180 + DECALE_X,
        180 + DECALE_X*2,
        180 + DECALE_X*2,
        180 + DECALE_X*2,
        180 + DECALE_X*3,
        180 + DECALE_X*3,
        180 + DECALE_X*3,
        180 + DECALE_X*3,
        180 + DECALE_X*4,
        180 + DECALE_X*4,
        180 + DECALE_X*4,
        180 + DECALE_X*4,
        180 + DECALE_X*4
    };

    public static final double BALLS_INIT_POS_Y[] =  {
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE_e/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE_e/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE_e/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE_e/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE_e/2 + BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE_e/2 - BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 + 2*BALL_SIZE_e,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH/2 - 2*BALL_SIZE_e
    };

    public static final double HOLES_INIT_POS_X[] = {
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM,
        WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH/2,
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH,
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM,
        WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH/2,
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH
    };

    public static final double HOLES_INIT_POS_Y[] = {
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM,
        WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM,
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM,
        WALL_THICKNESS/2 + ANGLE_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH,
        WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH,
        WALL_THICKNESS/2 + MID_HOLE_DIAMETER/2 + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH

    };
}

    