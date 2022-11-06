import components.*;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BallTable tableJeu = new BallTable(player1,player2);
        while(true){
            if(!tableJeu.checkBallsNoSpeed()) {
                tableJeu.update();
            }
        }
    }
}