package GameMap;

import Builders.ArmorBuilder;
import Builders.FoodBuilder;
import Builders.WeaponBuilder;
import Builders.WorldBuilder;
import Items.Weapon;
import Placement.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by evanputnam on 8/17/17.
 */
public class GameMap {


    /**
     * Level
     */
    private static int LEVEL = 0;

    /**
     * Hash map that stores a coordinate pair to locate a placeable on the map
     */
    private HashMap<Coordinate, Placeable> map;

    /**
     * Stores references to all the enemies in the map
     */
    private ArrayList<Enemy> enemies;

    /**
     * Random generating object
     */
    private static Random generator = new Random();

    /**
     * Keeps track of player
     */
    private Player player;

    /**
     * Constructor for the game map object
     */
    public GameMap(Player player){
        this.enemies = new ArrayList<>();
        this.map = new HashMap<Coordinate, Placeable>();
        this.player = player;
        LEVEL += 1;
    }

    /**
     * Construct a game map object with a given map
     * @param map
     */
    public GameMap(HashMap<Coordinate, Placeable> map, Player player){
        this.enemies = new ArrayList<>();
        this.map = map;
        this.player = player;
        LEVEL += 1;
    }

    /**
     * Create a level with a world builder object
     * @param worldBuilder
     */
    public GameMap(WorldBuilder worldBuilder, Player player){
        this.enemies = new ArrayList<>();
        this.map = worldBuilder.getWorld(0, this);
        this.player = player;
        LEVEL += 1;
    }

    /**
     * Create a level with a worldbuilder object AND a level.
     * @param worldBuilder
     * @param level
     */
    public GameMap(WorldBuilder worldBuilder, int level, Player player){
        this.enemies = new ArrayList<>();
        this.map = worldBuilder.getWorld(level, this);
        this.player = player;
        LEVEL = level;
    }


    /**
     * Call a game map to attack an enemy
     * @param damage
     * @param enemy
     * return boolean to indicate if the current player is dead
     */
    public boolean attackEnemy(int damage, Enemy enemy){

        boolean dead = enemy.damageEnemy(damage);
        //Simulates player attacking first
        if(dead){
            int x = generator.nextInt(4);
            if(x == 0){
                this.getMap().put(new Coordinate(enemy.getX(), enemy.getY()),
                        new Drop(enemy.getX(), enemy.getY(), ArmorBuilder.generateArmor(LEVEL)));
            }else if (x == 1){
                this.getMap().put(new Coordinate(enemy.getX(), enemy.getY()),
                        new Drop(enemy.getX(), enemy.getY(), WeaponBuilder.genWeapon()));
            }else if (x == 2){
                this.getMap().put(new Coordinate(enemy.getX(), enemy.getY()),
                        new Drop(enemy.getX(), enemy.getY(), FoodBuilder.generateFood(this.player.getMaxHitPoints())));
            }else{
                this.getMap().put(new Coordinate(enemy.getX(), enemy.getY()), new Floor(enemy.getX(), enemy.getY()));
            }

            enemies.remove(enemy);
            return false;
        }else{
            int enemyDam = enemy.getDamage(this.player);
            return player.takeDamage(enemyDam);
        }

    }


    /**
     * Gets the map hashmap
     * @return
     */
    public HashMap<Coordinate, Placeable> getMap(){
        return this.map;
    }


    /**
     * Gets the enemies of in the hashmap
     * @return
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    /**
     * Adds an coordinate-placeable pair to the map
     * @param c
     * @param p
     * @return
     */
    private boolean addObj(Coordinate c, Placeable p){
        if(!map.containsKey(c)){
            map.put(c, p);
            return true;
        }
        return false;
    }

    /**
     * Adds an enemy to the enemy array list
     * @param e
     */
    private void addEnemy(Enemy e){
        enemies.add(e);
    }

    /**
     * Removes a given enemy from the array list
     * @param e
     * @return
     */
    private boolean removeEnemy(Enemy e){
        return enemies.remove(e);
    }


}
