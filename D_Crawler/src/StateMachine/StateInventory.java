package StateMachine;

import Backend.GameData;
import Items.Item;
import Placement.Player;
import StateMachine.StateManager.StateManager;
import asciiPanel.AsciiPanel;

import java.awt.*;



/**
 * State that handles all inventory logic for a given character
 * Cursor setup like this:
 * 0  1
 * 2  3
 * 4  5
 * .  .
 * .  .
 * 38 39
 *
 * TODO need to add use and display current items user has on.
 * TODO need to add equipment equip and un-equip
 */
public class StateInventory implements State {


    /**
     * Backend game object that has all the games required information
     */
    private GameData data;

    /**
     * Integer values to represent cursor positions.
     * Formulas to calculate positions based on this value are used in this states update screen method
     */
    private int cursor;

    /**
     * Constructor for the inventory state.
     * @param data
     */
    public StateInventory(GameData data){
        this.data = data;
    }


    /**
     * Process the commands that occur during inventory state
     * @param com
     */
    public void processCommand(int com){
        //Set state to game with new hero
        if(com == State.ESCAPE_KEY || com == State.I_KEY){
            System.out.println("Going to game");
            this.cursor = 0;
            data.getManager().setGameState(StateManager.GAME_STATE);
        }


        //Handle cursor movement.
        if(com == State.RIGHT_KEY){
            if (this.cursor < Player.MAX_INVENTORY_SLOTS-1){
                this.cursor += 1;
            }
        }

        if(com == State.LEFT_KEY){
            if (this.cursor > 0){
                this.cursor -= 1;
            }
        }

        if(com == State.DOWN_KEY){
            if (this.cursor + 1 < Player.MAX_INVENTORY_SLOTS-1){
                this.cursor += 2;
            }
        }

        if(com == State.UP_KEY){
            if (this.cursor-1 > 0){
                this.cursor -= 2;
            }
        }

        if(com == State.ENTER_KEY){
            try {
                data.getPlayer().getItems().get(cursor).useItem(data.getPlayer());
            }catch (IndexOutOfBoundsException e){
                System.out.println("Invalid");
            }

        }




    }

    /**
     * Same as update screen but gives an AsciiPanel object instead
     * @param panel
     */
    public void updateScreen(AsciiPanel panel){}



    /**
     * Update screen with text.
     * Has a cursor, items, and information on those items.
     */
    public void updateScreen(){
        //Clears the panel before writing
        data.getPanel().clear();


        int yOffset = 1; //Y offset for operations
        boolean right = true; //Boolean to switch between left and right
        int count = 0; //Count value to temporarily keep track of item count
        int max = 20;  //Max number of rows
        int xStart = 5;  //Start x value on screen
        int xEnd = 20;  //Start y value on screen

        //Display each item that is available
        for (Item i: data.getPlayer().getItems()) {
            Color color = Color.WHITE;
            if (i.isEquiped()){
                color = Color.cyan;
            }
            if(right){
                data.getPanel().write(i.getItemName(), xStart, yOffset+count, color);
                right = false;
            }else{
                data.getPanel().write(i.getItemName(), xEnd, yOffset+count, color);
                count += 1;
                right = true;
            }
        }

        //Display not filled items.
        for (int i = count; i < max; i++){
            if(right == true){
                data.getPanel().write("---", xStart, yOffset+i);
                right = false;
            }
            if (right != true){
                data.getPanel().write("---", xEnd, yOffset+i);
                right = true;
            }
        }

        //Display the cursor next to each item
        int x = 0;
        int y = 0;
        //Left right
        if(cursor % 2 == 0){
            x = xStart-1; //Calculate x value if even
            y = cursor/2+yOffset; //Calculate y value if even
        }else{
            x = xEnd-1;
            y = ((cursor-1)/2)+yOffset;
        }
        //
        data.getPanel().write("#", x, y, Color.cyan);


        //Display information about hovered over item
        if(data.getPlayer().getItems().size() > cursor) {
            String s = data.getPlayer().getItems().get(cursor).getStatReadOff();
            String str[] = s.split("\n");
            int ct = 0;
            for (String sN: str) {
                data.getPanel().write(sN, xEnd+10, 5+ct);
                ct += 1;
            }
        }else{
            data.getPanel().write("Empty", xEnd+10, 5);
        }



        data.getPanel().updateUI();
    }
}
