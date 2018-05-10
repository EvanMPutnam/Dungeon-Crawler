package Placement;

/**
 * Ladder to move onto the next level.  Once you move on you can not move back!!!
 */
public class Ladder implements Placeable  {


    private int x;
    private int y;

    private static final char LADDER_CHAR = 'L';


    public Ladder(int x, int y){
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
        return LADDER_CHAR;
    }

}
