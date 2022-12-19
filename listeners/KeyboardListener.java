package listeners;

import rules.Rule;
import libs.Constants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardListener extends KeyAdapter {
    private final Rule rules;
    public KeyboardListener(Rule rules){
        this.rules = rules;
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_R){
            if  (Constants.DEBUG) System.out.println("Trying to reset");
            rules.resetGame();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            if  (Constants.DEBUG) System.out.println("Leaving the game");
            rules.stopGame();
        }
    }
}
