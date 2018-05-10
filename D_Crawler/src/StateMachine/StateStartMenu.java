package StateMachine;

import Backend.GameData;
import StateMachine.StateManager.StateManager;
import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

/**
 * State that handles the starting of a new game
 */
public class StateStartMenu implements State {

    /**
     * Integer value for keyevent command key that represents delete key
     */
    private static final int DELETE_KEY = 8;

    /**
     * Backend data
     */
    private GameData data;

    /**
     * Constructor for start menu
     * @param data
     */
    public StateStartMenu(GameData data){
        this.data = data;
    }


    /**
     * Processes the given command based on its keyEvent int value
     * @param com
     */
    public void processCommand(int com){
        //Set state to game with new hero
        if(com == State.ENTER_KEY){
            System.out.println("CHANGE SCENE HERE!");
            data.getManager().setGameState(StateManager.GAME_STATE);
        }

        //Add char to name
        if(KeyEvent.getKeyText(com).matches("^[-a-zA-Z0-9._]+")){
            data.getPlayer().addPlayerNameCh(KeyEvent.getKeyText(com).charAt(0));
        }

        //Remove char from name
        if(com == DELETE_KEY){
            data.getPlayer().removePlayerNameCh();
        }

    }

    /**
     * Method to update the screen with the given ascii panel if so chose
     * @param panel
     */
    public void updateScreen(AsciiPanel panel){}

    /**
     * Update the screen without the given ascii panel.  Takes it from data
     */
    public void updateScreen(){
        data.getPanel().clear();
        data.getPanel().writeCenter(GameData.GAME_NAME, GameData.Y_HEIGHT/2-5);
        data.getPanel().writeCenter("Type your name then press enter", GameData.Y_HEIGHT/2+5);
        data.getPanel().writeCenter("Name: " + data.getPlayer().getPlayerName(), GameData.Y_HEIGHT/2+7);
        data.getPanel().updateUI();
    }


}
