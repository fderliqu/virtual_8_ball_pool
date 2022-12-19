package libs;

import java.awt.Toolkit;
import java.awt.Color;

public class Constants {
    /*
    * Constant for debugging, should be turned to false
    * while in production
    * */
    public static final boolean DEBUG = false;

    /*
    * Constants representing the real dimensions of the billard
    * All of these are in cm for the modelisation.
    * They are then converted to pixels for the display
    * */
    //diameter of the ball
    public static final double BALL_SIZE = 5.72;
    //virtual size of the ball (from which we detect collisions)
    public static final double BALL_SIZE_e = 5.80;
    //size between the balls when they are positioned at the beginning
    public static final double SPACE_X = Math.sqrt(BALL_SIZE_e*BALL_SIZE_e - (BALL_SIZE_e*BALL_SIZE_e)/4);
    //funny name tho
    //dimensions of the whole table
    public static final double POOL_TABLE_WIDTH = 153;
    public static final double POOL_TABLE_LENGTH = 280;
    //dimensions of the inner part of the table (the green part)
    public static final double GAME_SURFACE_WIDTH = 127;
    public static final double GAME_SURFACE_LENGTH = 254;
    //the thickness of the walls (pretty self-explanatory)
    public static final double WALL_THICKNESS = (POOL_TABLE_WIDTH - GAME_SURFACE_WIDTH)/2;
    //diameter of the holes (middle ones and corner ones)
    public static final double CORNER_HOLE_DIAMETER = 13; // 12.5-14
    public static final double MID_HOLE_DIAMETER = 11;   // 10.5-12
    //the distance between the side and the white line representing the start zone
    public static final double START_ZONE = GAME_SURFACE_LENGTH/5.0;
    //how much the table slows down the balls (in cm/s/s)
    public static final double TABLE_DEACCELERATION = 99;

    //screen dimension
    public static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //size of a cm in pixels
    public static final double PX_PER_CM = SCREEN_WIDTH/POOL_TABLE_LENGTH;

    //how many frames per second we should try to display
    public static final long FORCED_FPS = 60;
    public static final double FORCED_TIMEOUT_MS = 1000.0/FORCED_FPS;

    //Custom Ball Colors
    public static final Color YELLOW = new Color(254,209,1);
    public static final Color BLUE = new Color(1,0,228);
    public static final Color RED = new Color(235,0,1);
    public static final Color PURPLE = new Color(132,39,145);
    public static final Color ORANGE = new Color(245,117,30);
    public static final Color GREEN = new Color(0,124,62);
    public static final Color BROWN = new Color(119,1,1);

    //size of the status display
    public static final int FONT_SIZE = 12;

    //free space between the table and the edge of the screen
    public static final double VERTICAL_OFFSET_CM = (SCREEN_HEIGHT/PX_PER_CM - POOL_TABLE_WIDTH)/2;
    public static final double HORIZONTAL_OFFSET_CM = (SCREEN_WIDTH/PX_PER_CM - POOL_TABLE_LENGTH)/2;

    //coordinates of the top left spot of the game surface
    public static final double ORIGIN_X = (POOL_TABLE_LENGTH - GAME_SURFACE_LENGTH)/2 + HORIZONTAL_OFFSET_CM;
    public static final double ORIGIN_Y = (POOL_TABLE_WIDTH - GAME_SURFACE_WIDTH)/2 + VERTICAL_OFFSET_CM;

    //Initial pos of balls and holes :
    public static final double[] BALLS_INIT_POS_X =  {
        WALL_THICKNESS + START_ZONE,
        180,
        180 + SPACE_X,
        180 + SPACE_X,
        180 + SPACE_X *2,
        180 + SPACE_X *2,
        180 + SPACE_X *2,
        180 + SPACE_X *3,
        180 + SPACE_X *3,
        180 + SPACE_X *3,
        180 + SPACE_X *3,
        180 + SPACE_X *4,
        180 + SPACE_X *4,
        180 + SPACE_X *4,
        180 + SPACE_X *4,
        180 + SPACE_X *4
    };

    public static final double[] BALLS_INIT_POS_Y =  {
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

    public static final double[] HOLES_INIT_POS_X = {
        WALL_THICKNESS + HORIZONTAL_OFFSET_CM,
        WALL_THICKNESS + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH/2,
        WALL_THICKNESS + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH,
        WALL_THICKNESS + HORIZONTAL_OFFSET_CM,
        WALL_THICKNESS + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH/2,
        WALL_THICKNESS + HORIZONTAL_OFFSET_CM + GAME_SURFACE_LENGTH
    };

    public static final double[] HOLES_INIT_POS_Y = {
        WALL_THICKNESS + VERTICAL_OFFSET_CM,
        WALL_THICKNESS + VERTICAL_OFFSET_CM,
        WALL_THICKNESS + VERTICAL_OFFSET_CM,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH,
        WALL_THICKNESS + VERTICAL_OFFSET_CM + GAME_SURFACE_WIDTH

    };
}

    