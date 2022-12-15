package libs;

public enum GameStatusEnum {
    NO_FOOL_BUT_CAN_REPLAY ("Ball potted, last player can replay", false),
    NO_FOOL ("Player turn", false),
    WHITE_BALL_NO_HIT_FOOL ("White Ball didn't hit a ball", true),
    WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL ("White ball hit opposite player's or black ball first", true),
    WHITE_BALL_POTTED_FOOL ("White ball has been potted", true),
    BLACK_BALL_POTTED_FOOL ("Game Over, Black ball has been potted", false),


    GAME_STOPPED ("Game Over", false);

    private final String statusMsg;
    private final boolean isFool;

    GameStatusEnum(String s, boolean b) {
        statusMsg = s;
        isFool = b;
    }

    public boolean getFool() {
        return this.isFool;
    }

    public String toString() {
       return (isFool?"Fool, ":"") + this.statusMsg;
    }
}
