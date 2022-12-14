package components;

import libs.BallTypeEnum;
import libs.Constants;
import libs.GameStatusEnum;

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

    public void whiteCollide(Ball b) {
        if  (Constants.DEBUG) System.out.println("white ball collision with " + b);

        //this function is only called when b is the white ball
        if (firstBallTouch == BallTypeEnum.NULL) {
            firstBallTouch = b.getBallType();

            if ((b.getBallType() != currentPlayer.getTypeBall()) && (currentPlayer.getTypeBall() != BallTypeEnum.NULL)) {
                if  (Constants.DEBUG) System.out.println("Fool : wrong ball hit");
                status = GameStatusEnum.WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL;
            }
        }
    }

    public void wallCollision(Ball b) {

    }

    public void ballPotted(Ball b) {
        if  (Constants.DEBUG) System.out.println("Ball " + b + " potted");


        if ((b.getBallType() == currentPlayer.getTypeBall()) &&
                (status == GameStatusEnum.NO_FOOL)) {
                status = GameStatusEnum.NO_FOOL_BUT_CAN_REPLAY;
        } else if (currentPlayer.getTypeBall() == BallTypeEnum.NULL) {
                        currentPlayer.setTypeBall(b.getBallType());
                        nextPlayer.setTypeBall(BallTypeEnum.getOpposite(b.getBallType()));
                        status = GameStatusEnum.NO_FOOL_BUT_CAN_REPLAY;
        } else {
            switch (b.getBallType()) {
                case BLACK :
                    if (table.ballsRemaining(currentPlayer.getTypeBall())) {
                        status = GameStatusEnum.BLACK_BALL_POTTED_FOOL;
                        winner = nextPlayer;
                    } else {
                        winner = currentPlayer;
                    }
                    return;
                case WHITE:
                    status = GameStatusEnum.WHITE_BALL_POTTED_FOOL;
            }
        }
    }

    public void endTurn() {
        if  (Constants.DEBUG) System.out.println("Turn ends");

        if (firstBallTouch == BallTypeEnum.NULL) {
            status = GameStatusEnum.WHITE_BALL_NO_HIT_FOOL;
        } else if (firstBallTouch == BallTypeEnum.BLACK && table.ballsRemaining(currentPlayer.getTypeBall()) ||
                    firstBallTouch == BallTypeEnum.getOpposite(currentPlayer.getTypeBall())) {
            status = GameStatusEnum.WHITE_BALL_HIT_NOT_ALLOWED_BALL_FOOL;
        }

        if  (status != GameStatusEnum.NO_FOOL_BUT_CAN_REPLAY) {
            Player tmp = currentPlayer;
            currentPlayer = nextPlayer;
            nextPlayer = tmp;
        }
    }

    public void resetFlags(){
        firstBallTouch = BallTypeEnum.NULL;
        status = GameStatusEnum.NO_FOOL;
    }

    public void resetGame() {
        resetFlags();
        currentPlayer = nextPlayer;
        this.winner = null;
        table.resetTable();
    }

    //Setters
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public GameStatusEnum getStatus() {
        return status;
    }
    
}
