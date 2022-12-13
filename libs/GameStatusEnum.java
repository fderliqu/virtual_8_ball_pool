package libs;

public enum GameStatusEnum {
    NO_FOOL_BUT_CAN_REPLAY ("Ball potted, last player can replay"),
    NO_FOOL ("Player turn"),
    WHITE_BALL_NO_HIT_FOOL ("Fool, White Ball didn't hit a ball"),
    WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL ("Fool, White ball hit opposite player's ball first"),
    BALL_HITTED_BY_WHITE_DO_NOT_TOUCH_BAND_FOOL ("Fool, The ball hit by the white ball didn't touch a band"),
    WHITE_BALL_POTTED_FOOL ("Fool, White ball has been potted"),
    BLACK_BALL_POTTED_FOOL ("Game Over, Black ball has been potted");

    private final String statusMsg;

    GameStatusEnum(String s) {
        statusMsg = s;
    }

    public boolean equalsStatus(String otherStatus) {
        return statusMsg.equals(otherStatus);
    }

    public String toString() {
       return this.statusMsg;
    }
}
