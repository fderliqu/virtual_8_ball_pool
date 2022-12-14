package listeners;

import components.Rules;
import libs.Constants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardListener extends KeyAdapter {
    private final Rules rules;
    public KeyboardListener(Rules rules){
        this.rules = rules;
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_R){
            if  (Constants.DEBUG) System.out.println("Trying to reset");
            rules.resetGame();
        }
    }
}
