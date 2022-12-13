package components;

import libs.BallTypeEnum;

public class Player {
    private String ID;
    private BallTypeEnum typeBall = BallTypeEnum.NULL;

    //Constructors

    public Player(String ID){
        this.ID = ID;
    }

    /*
     * Methods
     */

    //Getters
    public String getID(){
        return ID;
    }

    public BallTypeEnum getTypeBall(){
        return typeBall;
    }

    //Setters

    public void setID(String ID){
        this.ID = ID;
    }

    public void setTypeBall(BallTypeEnum typeBall){
        this.typeBall = typeBall;
    }
}