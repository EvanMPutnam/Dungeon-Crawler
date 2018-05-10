package Placement;

/**
 * Created by evanputnam on 8/17/17.
 */
public class Floor implements Placeable {

    private int x;
    private int y;

    private static final char FLOOR_CHAR = '.';


    public Floor(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getChar(){
        return FLOOR_CHAR;
    }
}
