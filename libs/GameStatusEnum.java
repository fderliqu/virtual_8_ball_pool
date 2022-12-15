package libs;

public enum GameStatusEnum {
    GOOD_BALL_POTTED("Ball potted, last player can replay", false),
    NO_FOOL ("Player turn", false),
    NO_WHITE_COLLISION("White Ball didn't hit a ball", true),
    WRONG_WHITE_COLLISION("White ball hit opposite player's or black ball first", true),
    WHITE_BALL_POTTED("White ball has been potted", true),
    VICTORY("Black ball has been potted", false),
    DEFEAT("Black ball has been potted", false),

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
