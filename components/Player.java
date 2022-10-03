package components;

import java.util.ArrayList;

import components.Ball.BallTypeEnum;

public class Player {
    private boolean hisTurn,doFoul,heWin,allBallPotted;
    private String ID;
    private BallTypeEnum typeBall;
    private ArrayList<Ball> ballPotted;

    //Constructors

    public Player(){
        this.ID = "anonymous player";
        hisTurn = false;
        doFoul = false;
        heWin = false;
        allBallPotted = false;
        typeBall = null;
    }

    public Player(String ID){
        this.ID = ID;
        hisTurn = false;
        doFoul = false;
        heWin = false;
        allBallPotted = false;
        typeBall = null;
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

    public ArrayList<Ball> getBallPotted(){
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

    public void addPottedBall(Ball ball){
        ballPotted.add(ball);
    }

    
}