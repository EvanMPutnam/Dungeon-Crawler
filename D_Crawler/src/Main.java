import Backend.GameData;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TODO note that the following command is great... "find . -name '*.java' -print0 | xargs -0 wc -l"
//TODO document
//TODO fix second layer where the charachters are just staying in random positions...
public class Main extends JFrame implements KeyListener{

    /**
     * Game data object has all the data of the game
     */
    private GameData data;


    /**
     * Main class initializes the game data, adds the key listener, adds the panel and packs, and sets
     * default scene
     */
    public Main(){
        super();
        data = new GameData();
        addKeyListener(this);
        add(data.getPanel());
        pack();
        data.getManager().getCurrentState().updateScreen();
    }


    /**
     * Key pressed. Unused
     * @param e
     */
    public void keyPressed(KeyEvent e) {

    }


    /**
     * Handles the main game loop
     * First gets the keycode and passes it to the process command and update screen of current state.
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        data.getManager().getCurrentState().processCommand(e.getKeyCode());
        data.getManager().getCurrentState().updateScreen();
    }


    /**
     * Key typed.  Unused
     * @param event
     */
    public void keyTyped(KeyEvent event){}


    /**
     * Main function creates new Main object and sets close op and visibility
     * @param args
     */
    public static void main(String[] args) {
        Main game = new Main();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
