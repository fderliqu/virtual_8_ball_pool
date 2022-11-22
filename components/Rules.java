package components;

import libs.Constants.BallTypeEnum;
import libs.Constants.RulesTypeEnum;

public class Rules {
    private Player playerHisTurn;
    //private Player playerNoHisTurn;
    private BallTypeEnum firstBallTouch;
    private int whiteBallCollisions;
    private int wallCollisions;
    private boolean whitePotted;
    private boolean blackPotted;
    private boolean stripedPotted;
    private boolean plainPotted;

    //Method

    private boolean ballsArePotted(){
        return stripedPotted || plainPotted;
    }

    public RulesTypeEnum checkRules(){
        if(whitePotted){
            return RulesTypeEnum.WHITE_BALL_POTTED_FOOL;    
        }
        else if(blackPotted){
            return RulesTypeEnum.BLACK_BALL_POTTED_FOOL;
        }
        else if(whiteBallCollisions == 0 && firstBallTouch == BallTypeEnum.NULL){
            return RulesTypeEnum.WHITE_BALL_NO_HIT_FOOL;
        }
        else if(playerHisTurn.getTypeBall() != BallTypeEnum.NULL && firstBallTouch != playerHisTurn.getTypeBall()){
            return RulesTypeEnum.WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL;
        }
        else if(!ballsArePotted() && wallCollisions == 0){
            return RulesTypeEnum.BALL_HITTED_BY_WHITE_DO_NOT_TOUCH_BAND_FOOL;
        }
        else if(    playerHisTurn.getTypeBall() != BallTypeEnum.NULL && 
                    ((playerHisTurn.getTypeBall() == BallTypeEnum.STRIPED && stripedPotted) || (playerHisTurn.getTypeBall() == BallTypeEnum.PLAIN && plainPotted))
                ){
            return RulesTypeEnum.NO_FOOL_BUT_CAN_REPLAY;
        }
        else return RulesTypeEnum.NO_FOOL;
    }

    public void resetFlags(){
        firstBallTouch = BallTypeEnum.NULL;
        whiteBallCollisions = 0;
        wallCollisions = 0;
        whitePotted = false;
        blackPotted = false;
        plainPotted = false;
        stripedPotted = false;
    }
    
    public void printflag(){
        System.out.println("--firstballtouch = "+firstBallTouch);
        System.out.println("--whiteballcollision = "+whiteBallCollisions);
        System.out.println("--wallcollision = "+wallCollisions);
        System.out.println("--white = "+whitePotted);
        System.out.println("--black = "+blackPotted);
        System.out.println("--plain = "+plainPotted);
        System.out.println("--striped = "+stripedPotted);
    }
    
    //Setters

    public void setPlayers(Player playerHisTurn){
        this.playerHisTurn = playerHisTurn;
        //this.playerNoHisTurn = playerNoHisTurn;
    }

    public void setFirstBallTouch(BallTypeEnum firstBallTouch){
        this.firstBallTouch = firstBallTouch;
    }

    public void incWhiteBallCollisions(){
        this.whiteBallCollisions++;
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

    public BallTypeEnum getFirstBallTouch(){
        return firstBallTouch;
    }
    
}
