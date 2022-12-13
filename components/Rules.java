package components;

import libs.BallTypeEnum;
import libs.GameStatusEnum;

import java.util.ArrayList;

public class Rules {
    private Player currentPlayer;
    private Player nextPlayer;
    private BallTypeEnum firstBallTouch;

    private Player winner = null;

    private BallTable table;

    private GameStatusEnum status = GameStatusEnum.NO_FOOL;

    public Rules (Player p1, Player p2, BallTable table) {
        if (p1 == null || p2 == null) {
            return;
        }

        this.table = table;

        currentPlayer = p1;
        nextPlayer = p2;
    }

    //Method

    public void whiteCollide(Ball b1) {
        //this function is only called when b1 is the white ball
        if (firstBallTouch == BallTypeEnum.NULL) {
            firstBallTouch = b1.getBallType();

            if ((b1.getBallType() != currentPlayer.getTypeBall()) && (currentPlayer.getTypeBall() != BallTypeEnum.NULL)) {
                status = GameStatusEnum.WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL;
            }
        }
    }

    public void wallCollision(Ball b) {

    }

    public void ballPotted(Ball b) {
        if ((b.getBallType() == currentPlayer.getTypeBall()) &&
                (status == GameStatusEnum.NO_FOOL)) {
                status = GameStatusEnum.NO_FOOL_BUT_CAN_REPLAY;
        } else {
            switch (b.getBallType()) {
                case BLACK :
                    if (table.ballsRemaining(currentPlayer.getTypeBall())) {
                       status = GameStatusEnum.BLACK_BALL_POTTED_FOOL;
                    } else {
                        winner = currentPlayer;
                    }
                    break;
                case WHITE:
                    status = GameStatusEnum.WHITE_BALL_POTTED_FOOL;
            }
        }
    }

    public void endTurn() {
        if (firstBallTouch == BallTypeEnum.NULL) {
            status = GameStatusEnum.WHITE_BALL_NO_HIT_FOOL;
        }
    }

    public void resetFlags(){
        firstBallTouch = BallTypeEnum.NULL;
        status = GameStatusEnum.NO_FOOL;
    }

    public void resetGame() {
        resetFlags();
        this.winner = null;
    }

    //Setters
    public void setPlayer(Player currentPlayer, Player playerNoHisTurn){
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer(boolean histurn){
        if(histurn) return currentPlayer;
        else {
            return nextPlayer;
        }
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player player) {
        this.winner = player;
    }

    public GameStatusEnum getStatus() {
        return status;
    }
    
}
