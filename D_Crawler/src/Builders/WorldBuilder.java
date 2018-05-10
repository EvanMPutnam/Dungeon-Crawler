package Builders;

import Backend.GameData;
import GameMap.Coordinate;
import GameMap.GameMap;
import Placement.Enemy;
import Placement.Floor;
import Placement.Ladder;
import Placement.Placeable;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by evanputnam on 8/18/17.
 */
public class WorldBuilder {


    /**
     * Random object for unique dungeon layout
     */
    private Random random;

    /**
     * Game data backend
     */
    private GameData data;

    /**
     * Previous level value
     */
    private int previousLevel;

    /**
     * Constructor merely takes in game data backend
     * @param data
     */
    public WorldBuilder(GameData data){
        this.data = data;
        this.random = new Random();
        this.previousLevel = 1;
    }


    /**
     * Get a new level based on the current world.
     * @param level
     * @return
     */
    public HashMap<Coordinate, Placeable> getWorld(int level, GameMap gameMap){

        HashMap<Coordinate, Placeable> world = genWorld(level, gameMap);
        this.previousLevel = level;
        return world;
    }

    /**
     * Generates a world map for a given level
     * @param level
     * @param gameMap
     * @return
     */
    private HashMap<Coordinate, Placeable> genWorld(int level, GameMap gameMap){

        //Map
        HashMap<Coordinate, Placeable> map = new HashMap<>();

        //Count of tiles to place
        int count = 1000+(100*level);

        //Sets the values back to the start
        //data.getPlayer().setX(0);
        //data.getPlayer().setY(0);

        //Start x position
        int startX = data.getPlayer().getX();
        //Start y position
        int startY = data.getPlayer().getY();

        //Starting player coord
        Coordinate cord = new Coordinate(startX,startY);
        data.getPlayer().setX(startX);
        data.getPlayer().setY(startY);

        //Put player into map
        map.put(cord, data.getPlayer());


        //While more tiles to place
        while(count != 0){
            //Get next location and change x/y accordingly
            int randNum = random.nextInt(4);
            switch (randNum){
                case 0:
                    startX -= 1;
                    break;
                case 1:
                    startX += 1;
                    break;
                case 2:
                    startY -= 1;
                    break;
                case 3:
                    startY += 1;
                    break;
            }
            //System.out.println(count);

            //New Coordinate
            Coordinate c = new Coordinate(startX, startY);

            //If map does not contain coordinate already
            if(!map.containsKey(c)){
                //Alter placeables here with order of precedence at bottom


                //Floor tile
                if(count != 1) {
                    map.put(c, new Floor(startX, startY));
                }

                //Enemy tile
                if(count % 30 == 0){
                    map.put(c, this.data.getEnemyBuilder().getEnemy(startX, startY, false, level));
                }

                //Ladder
                if(count == 1){
                    map.put(c, new Ladder(startX, startY));
                }


                //Add to lists here
                if(map.get(new Coordinate(startX, startY)) instanceof Enemy){
                    gameMap.getEnemies().add((Enemy)map.get(new Coordinate(startX, startY)));
                }



                count -= 1;

            }
        }

        //Add walls here maybe...




        return map;
    }


    public int getPreviousLevel() {
        return previousLevel;
    }
}
