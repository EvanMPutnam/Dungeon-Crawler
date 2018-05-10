package StateMachine.StateManager;

import StateMachine.*;
import asciiPanel.AsciiPanel;
import Backend.GameData;

import java.util.HashMap;

/**
 * Created by evanputnam on 8/19/17.
 */
public class StateManager {


    /**Integers for the menu selection scene*/
    public static final int START_MENU = 0;
    public static final int GAME_STATE = 1;
    public static final int INVENTORY_STATE = 2;
    public static final int PAUSE_STATE =  3;

    /**
     * Hash map containing all of the different scene objects
     */
    private HashMap<Integer, State> stateHashMap;

    /**
     * Current state in the the state manager.
     */
    private State currentState;

    /**
     * Game data object which holds all information about player, map, enemies, etc.
     */
    private GameData gameData;

    public StateManager(GameData data){

        //Backend game data
        this.gameData = data;

        //Initialize different states here.
        stateHashMap = new HashMap<Integer, State>();
        stateHashMap.put(START_MENU, new StateStartMenu(data));
        stateHashMap.put(GAME_STATE, new StateGame(data));
        stateHashMap.put(INVENTORY_STATE, new StateInventory(data));
        stateHashMap.put(PAUSE_STATE, new StatePause(data));

        //Current state is by default start menu
        this.currentState = stateHashMap.get(START_MENU);
    }


    public void setGameState(int key){
        if (stateHashMap.containsKey(key)){
            currentState = stateHashMap.get(key);
        }
    }


    public State getCurrentState(){
        return this.currentState;
    }

    void processCommand(int com){
        currentState.processCommand(com);
    }

    void updateScreen(AsciiPanel panel){
        currentState.updateScreen(panel);
    }







}
