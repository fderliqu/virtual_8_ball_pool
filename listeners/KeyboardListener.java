package listeners;

import components.BallTable;
import components.Player;
import components.Rules;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardListener extends KeyAdapter {
    private final BallTable table;
    private final Rules rules;
    private final Player player1,player2;
    public KeyboardListener(BallTable table, Rules rules, Player player1, Player player2){
        this.table = table;
        this.rules = rules;
        this.player1 = player1;
        this.player2 = player2;
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_R){
            rules.setPlayer(player2, player1);
            rules.resetFlags();
            table.resetTable();
        }
    }
}
