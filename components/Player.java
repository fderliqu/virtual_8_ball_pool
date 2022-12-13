package components;

import static libs.Constants.*;

public class Player {
    private String ID = "anonymous player";
    private BallTypeEnum typeBall = BallTypeEnum.NULL;
    private int ballPotted = 0;

    //Constructors

    public Player(String ID){
        this.ID = ID;
    }

    /*
     * Methods
     */

    public boolean allowedToPutBlackBall(){
        return ballPotted == 7;
    }

    public boolean noBallPotted(){
        return ballPotted == 0;
    }

    //Getters
    public String getID(){
        return ID;
    }

    public BallTypeEnum getTypeBall(){
        return typeBall;
    }

    public int getBallPotted(){
        return ballPotted;
    }

    //Setters

    public void setID(String ID){
        this.ID = ID;
    }

    public void setTypeBall(BallTypeEnum typeBall){
        this.typeBall = typeBall;
    }

    public void incrementBallPotted(){
        ballPotted++;
    }

    
}