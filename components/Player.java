package components;

import static libs.Constants.*;

public class Player {
    private boolean hisTurn,doFoul,heWin,allBallPotted;
    private String ID;
    private BallTypeEnum typeBall;
    private int ballPotted;

    //Constructors

    public Player(){
        this.ID = "anonymous player";
        hisTurn = false;
        doFoul = false;
        heWin = false;
        allBallPotted = false;
        typeBall = null;
        ballPotted = 0;
    }

    public Player(String ID){
        this.ID = ID;
        hisTurn = false;
        doFoul = false;
        heWin = false;
        allBallPotted = false;
        typeBall = BallTypeEnum.NULL;
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

    public boolean getHisTurn(){
        return hisTurn;
    }

    public boolean getDoFoul(){
        return doFoul;
    }

    public boolean getHeWin(){
        return heWin;
    }

    public boolean getAllBallPotted(){
        return allBallPotted;
    }

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

    public void setHisTurn(boolean hisTurn){
        this.hisTurn = hisTurn;
    }

    public void setDoFoul(boolean doFoul){
        this.doFoul = doFoul;
    }

    public void setHeWin(boolean heWin){
        this.heWin = heWin;
    }

    public void setAllBallPotted(boolean allBallPotted){
        this.allBallPotted = allBallPotted;
    }

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