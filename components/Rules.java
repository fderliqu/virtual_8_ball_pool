package components;

import libs.Constants.BallTypeEnum;
import libs.GameStatusEnum;

public class Rules {
    private final Player players[] = new Player[2];
    private Player currentPlayer;
    private BallTypeEnum firstBallTouch;
    private boolean hasWhiteCollided;
    private int wallCollisions;
    private boolean whitePotted;
    private boolean blackPotted;
    private boolean stripedPotted;
    private boolean plainPotted;
    private Player winner = null;

    public Rules (Player p1, Player p2) {
        if (p1 == null || p2 == null) {
            return;
        }

        players[0] = p1;
        players[1] = p2;

        currentPlayer = p1;
    }

    //Method

    private boolean ballsArePotted(){
        return stripedPotted || plainPotted;
    }

    //public GameStatusEnum getStatus();
    public GameStatusEnum checkRules(){
        if(whitePotted){
            return GameStatusEnum.WHITE_BALL_POTTED_FOOL;
        }
        else if(blackPotted){
            return GameStatusEnum.BLACK_BALL_POTTED_FOOL;
        }
        else if(!hasWhiteCollided){
            return GameStatusEnum.WHITE_BALL_NO_HIT_FOOL;
        }
        else if(    currentPlayer.getTypeBall() != BallTypeEnum.NULL &&
                    !(firstBallTouch==BallTypeEnum.BLACK && currentPlayer.allowedToPutBlackBall()) &&
                    firstBallTouch != currentPlayer.getTypeBall()){
            return GameStatusEnum.WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL;
        }
        else if(!ballsArePotted() && wallCollisions == 0){
            return GameStatusEnum.BALL_HITTED_BY_WHITE_DO_NOT_TOUCH_BAND_FOOL;
        }
        else if(    currentPlayer.getTypeBall() != BallTypeEnum.NULL &&
                    ((currentPlayer.getTypeBall() == BallTypeEnum.STRIPED && stripedPotted) || (currentPlayer.getTypeBall() == BallTypeEnum.PLAIN && plainPotted))
                ){
            return GameStatusEnum.NO_FOOL_BUT_CAN_REPLAY;
        }
        else return GameStatusEnum.NO_FOOL;
    }

    public void resetFlags(){
        firstBallTouch = BallTypeEnum.NULL;
        hasWhiteCollided = false;
        wallCollisions = 0;
        whitePotted = false;
        blackPotted = false;
        plainPotted = false;
        stripedPotted = false;
    }

    public void resetGame() {
        resetFlags();
        this.winner = null;
    }
    
    public void printflag(){
        System.out.println("player : " + currentPlayer.getID() + " balls : "+currentPlayer.getTypeBall()+" potted : "+currentPlayer.getBallPotted());
        System.out.println("--firstballtouch = "+firstBallTouch);
        System.out.println("--whiteballcollision = "+ hasWhiteCollided);
        System.out.println("--wallcollision = "+wallCollisions);
        System.out.println("--white = "+whitePotted);
        System.out.println("--black = "+blackPotted);
        System.out.println("--plain = "+plainPotted);
        System.out.println("--striped = "+stripedPotted);
    }

    //Setters

    public void setPlayer(Player currentPlayer, Player playerNoHisTurn){
        this.currentPlayer = currentPlayer;
    }

    public void setFirstBallTouch(BallTypeEnum firstBallTouch){
        this.firstBallTouch = firstBallTouch;
    }

    public void whiteBallCollide(){
        this.hasWhiteCollided = true;
    }

    public void incWallCollisions(){
        this.wallCollisions++;
    }

    public void setWhitePotted(boolean whitePotted){
        this.whitePotted = whitePotted;
    }

    public void setBlackPotted(boolean blackPotted){
        this.blackPotted = blackPotted;
    }

    public void setPlainPotted(boolean plainPotted){
        this.plainPotted = plainPotted;
    }
    public void setStripedPotted(boolean stripedPotted){
        this.stripedPotted = stripedPotted;
    }

    //getters

    public BallTypeEnum getFirstBallTouch(){
        return firstBallTouch;
    }

    public Player getPlayer(boolean histurn){
        if(histurn) return currentPlayer;
        else {
            for (Player p : players) {
                if (p != currentPlayer) return p;
            }
            return null;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(int nb) {
        this.winner = players[nb%2];
    }

    public void setWinner(Player player) {
        this.winner = player;
    }
    
}
