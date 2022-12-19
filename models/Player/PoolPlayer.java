package models.Player;

import libs.BallTypeEnum;

public class PoolPlayer extends PlayerAbstract{
    private BallTypeEnum typeBall = BallTypeEnum.NULL;

    public PoolPlayer(String ID){
        this.ID = ID;
    }

    public BallTypeEnum getTypeBall(){
        return typeBall;
    }

    //Setters

    public void setTypeBall(BallTypeEnum typeBall){
        this.typeBall = typeBall;
    }
}
