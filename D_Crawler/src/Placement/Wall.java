package Placement;

/**
 * Created by evanputnam on 8/17/17.
 */
public class Wall implements Placeable{

    private int x;
    private int y;

    private static final char WALL_CHAR = '#';

    public Wall(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public char getChar(){
        return WALL_CHAR;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }





}
