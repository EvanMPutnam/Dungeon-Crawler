package Placement;

import Items.Item;

/**
 * Class that acts as an item on the map that is dropped by the enemy.
 */
public class Drop implements Placeable {

    /**
     * X location of the drop
     */
    private int x;

    /**
     * Y location of the drop
     */
    private int y;

    /**
     * Charachter to represent a drop
     */
    private static final char ITEM_CHAR = 'I';

    /**
     * Item that is stored on the given space
     */
    private Item item;


    /**
     * Constructor
     * @param x x location coordinate
     * @param y y location coordinate
     * @param item item to store
     */
    public Drop(int x, int y, Item item){
        this.x = x;
        this.y = y;
        this.item = item;
    }


    /**
     * Gets the x location on the screen
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y location on the screen
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x location of the object
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y location of the object
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the character to display for the placeable
     * @return I character
     */
    public char getChar(){
        return ITEM_CHAR;
    }

    /**
     * Returns the item that the placeable represents.
     * @return
     */
    public Item getItem() {
        return item;
    }
}
