package StateMachine;

import Backend.GameData;
import StateMachine.StateManager.StateManager;
import asciiPanel.AsciiPanel;

/**
 * Created by evanputnam on 8/19/17.
 */
public class StatePause implements State {

    //Backend
    private GameData data;

    public StatePause(GameData data){
        this.data = data;
    }


    public void processCommand(int com){
        //Set state to game with new hero
        if(com == State.ESCAPE_KEY){
            System.out.println("Going to game");
            data.getManager().setGameState(StateManager.GAME_STATE);
        }
    }


    public void updateScreen(AsciiPanel panel){}
    public void updateScreen(){
        //Clears the panel before writing
        data.getPanel().clear();
        data.getPanel().writeCenter("Press escape to resume", data.Y_HEIGHT/2);
        data.getPanel().updateUI();
    }


}
