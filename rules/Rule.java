package rules;

import libs.BallTypeEnum;
import libs.GameStatusEnum;
import models.Ball;
import models.BallTable;
import models.Player.PoolPlayer;
import view.WinnerView;

import static libs.Constants.DEBUG;

public class Rule {
    private PoolPlayer currentPlayer;
    private PoolPlayer nextPlayer;
    private BallTypeEnum firstBallTouch;
    private WinnerView winnerView;

    private PoolPlayer winner = null;

    private final BallTable table;

    private GameStatusEnum status = GameStatusEnum.NO_FOOL;

    public Rule(BallTable table) {
        currentPlayer = new PoolPlayer("Player 1");
        nextPlayer = new PoolPlayer("Player 2");

        this.table = table;
    }

    //Method

    public void whiteCollide(Ball b) {
        if  (DEBUG) System.out.println("white ball collision with " + b);

        //this function is only called when b is the white ball
        if (firstBallTouch == BallTypeEnum.NULL) {
            firstBallTouch = b.getBallType();

            if ((b.getBallType() != currentPlayer.getTypeBall()) && (currentPlayer.getTypeBall() != BallTypeEnum.NULL)) {
                if  (DEBUG) System.out.println("Fool : wrong ball hit");
                status = GameStatusEnum.WRONG_WHITE_COLLISION;
            }
        }
    }

    public void ballPotted(Ball b) {
        if  (DEBUG) System.out.println("Ball " + b + " potted");


        if ((b.getBallType() == currentPlayer.getTypeBall()) &&
                (status == GameStatusEnum.NO_FOOL)) {
                status = GameStatusEnum.GOOD_BALL_POTTED;
        } else if (currentPlayer.getTypeBall() == BallTypeEnum.NULL &&
                b.getBallType() != BallTypeEnum.WHITE &&
                b.getBallType() != BallTypeEnum.BLACK) {
                        currentPlayer.setTypeBall(b.getBallType());
                        nextPlayer.setTypeBall(BallTypeEnum.getOpposite(b.getBallType()));
                        status = GameStatusEnum.GOOD_BALL_POTTED;
        } else {
            switch (b.getBallType()) {
                case BLACK :
                    if (table.ballsRemaining(currentPlayer.getTypeBall())) {
                        status = GameStatusEnum.DEFEAT;
                        this.setWinner(nextPlayer);
                    } else {
                        status = GameStatusEnum.VICTORY;
                        this.setWinner(currentPlayer);
                    }
                    return;
                case WHITE:
                    status = GameStatusEnum.WHITE_BALL_POTTED;
            }
        }
    }

    public void endTurn() {
        if  (DEBUG) System.out.println("Turn ends");

        if (firstBallTouch == BallTypeEnum.NULL) {
            status = GameStatusEnum.NO_WHITE_COLLISION;
        } else if (firstBallTouch == BallTypeEnum.BLACK && table.ballsRemaining(currentPlayer.getTypeBall()) ||
                    firstBallTouch == BallTypeEnum.getOpposite(currentPlayer.getTypeBall())) {
            status = GameStatusEnum.WRONG_WHITE_COLLISION;
        }

        if  (status != GameStatusEnum.GOOD_BALL_POTTED) {
            PoolPlayer tmp = currentPlayer;
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
        this.winner = null;
        PoolPlayer tmp = currentPlayer;
        currentPlayer = nextPlayer;
        nextPlayer = tmp;
        currentPlayer.setTypeBall(BallTypeEnum.NULL);
        nextPlayer.setTypeBall(BallTypeEnum.NULL);
        winnerView.setStatus(false);
        table.resetTable();
    }

    //Setters
    public PoolPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public PoolPlayer getWinner() {
        return winner;
    }

    public GameStatusEnum getStatus() {
        return status;
    }

    public void stopGame() {
        status = GameStatusEnum.GAME_STOPPED;
    }

    private void setWinner(PoolPlayer p) {
        if (DEBUG) System.out.println("and the winner is :"+currentPlayer);
        winner = p;
        winnerView.setStatus(true);
    }

    public void setWinnerView(WinnerView w) {
        winnerView = w;
    }
    
}
