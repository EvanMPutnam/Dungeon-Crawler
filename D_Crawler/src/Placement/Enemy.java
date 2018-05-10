package Placement;

import GameMap.Coordinate;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by evanputnam on 8/18/17.
 */
public class Enemy implements Placeable{

    /**
     * X location of enemy
     */
    private int x;

    /**
     * Y location of enemy
     */
    private int y;

    /**
     * Min damage of enemy
     */
    private int minDam;

    /**
     * Max damage of enemy
     */
    private int maxDam;

    /**
     * Health of enemy
     */
    private int health;

    /**
     * Name of enemy
     */
    private String name;

    /**
     * Random object to calculate attack values
     */
    private Random random;

    /**
     * Boolean to represent if boss or not
     */
    private boolean isBoss;

    /**
     * Static charachter for regular enemy
     */
    private static final char ENEMY_CHAR = 'E';

    /**
     * Static charachter for boss enemy
     */
    private static final char BOSS_ENEMY_CHAR = 'B';





    /**
     * Enemy contructor
     * @param name
     * @param x
     * @param y
     * @param minDam
     * @param maxDam
     * @param health
     */
    public Enemy(String name, int x, int y, int minDam, int maxDam, int health, boolean isBoss){
        this.x = x;
        this.y = y;
        this.minDam = minDam;
        this.maxDam = maxDam;
        this.name = name;
        this.random = new Random();
        this.health = health;
        this.isBoss = isBoss;
        random.setSeed(System.currentTimeMillis());
    }


    /**
     * Get x location
     * @return
     */
    public int getX(){
        return this.x;
    }

    /**
     * Get y location
     * @return
     */
    public int getY(){
        return this.y;
    }

    /**
     * Set the x location
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y location
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Get the constant charachter for the given placeable
     * @return
     */
    public char getChar(){
        if(isBoss){
            return BOSS_ENEMY_CHAR;
        }
        return ENEMY_CHAR;
    }

    /**
     * Get the minimum damage
     * @return
     */
    public int getMinDam() {
        return minDam;
    }

    /**
     * Get the maximum damage
     * @return
     */
    public int getMaxDam() {
        return maxDam;
    }

    /**
     * Get the name of the enemy
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the health of the enemy
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Adds damage to an enemy.
     * If dead returns true, if alive returns false
     * @param damage
     * @return
     */
    public boolean damageEnemy(int damage){
        this.health = this.health - damage;
        if(this.health <= 0){
            return true;
        }
        return false;
    }


    /**
     * Get a random damage value from min-max of enemy.
     * @return
     */
    public int getDamage(Player p){
        int def = p.getDefenseBonus();
        int hitSuccess = random.nextInt(200);
        if (hitSuccess <= def){
            System.out.println(def);
            return 0;
        }
        return random.nextInt((maxDam - minDam)+1) + minDam;
    }


    /**
     * Equals if x and y are the same.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enemy enemy = (Enemy) o;

        if (x != enemy.x) return false;
        return y == enemy.y;
    }

    /**
     * Hash code based on x and y
     * @return
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
