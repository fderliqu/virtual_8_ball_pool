package components;

import libs.BallTypeEnum;

public class Player {
    private final String ID;
    private BallTypeEnum typeBall = BallTypeEnum.NULL;

    //Constructors

    public Player(String ID){
        this.ID = ID;
    }

    /*
     * Methods
     */

    //Getters
    public String toString(){
        return ID;
    }

    public BallTypeEnum getTypeBall(){
        return typeBall;
    }

    //Setters

    public void setTypeBall(BallTypeEnum typeBall){
        this.typeBall = typeBall;
    }
}