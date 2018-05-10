package GameMap;

/**
 * Created by evanputnam on 8/17/17.
 */
public class Coordinate implements Comparable<Coordinate>{

    /**
     * X coordinate for game position on map.  Can be pos or negative
     */
    private int x;

    /**
     * Y coordinate for game position on map. Can be pos or negative
     */
    private int y;


    /**
     * Constructor for coordinate given an x and y position
     * @param x
     * @param y
     */
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }





    /**
     * Get x location
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Get y location
     * @return
     */
    public int getY() {
        return y;
    }







    /**
     * Equals if x and y are equivilant
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;
        return that.x == this.x && that.y == this.y;
    }

    @Override
    public int compareTo(Coordinate c){
        //System.out.println("COMPARISON!");
        if(this.equals(c)){
            return 0;
        }
        int i = Integer.compare(this.getX(), c.getX());
        if(i != 0){
            return i;
        }
        int i2 = Integer.compare(this.getY(), c.getY());
        return i2;

    }

    /**
     * Hash code for hashmap.  Default given with intellij auto gen.
     * @return
     */
    @Override
    public int hashCode() {
        //System.out.println("HASH!");
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
