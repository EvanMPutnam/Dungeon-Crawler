package Backend;

import Builders.*;
import GameMap.GameMap;
import Placement.Player;
import StateMachine.StateManager.StateManager;
import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

/**
 * Created by evanputnam on 8/19/17.
 */
public class GameData {

    /**
     * Constant for width of game(not counting menu);
     */
    public static final int X_WIDTH = 60;

    /**
     * Constant for height of game(not counting menu);
     */
    public static final int Y_HEIGHT = 30;

    /**
     * Offset x-value for the stats menu
     */
    public static final int STATS_X_OFFSET = 20;

    /**
     * World size is X_WIDTH * Y_HEIGHT;
     */
    public static final int WORLD_SIZE = X_WIDTH * Y_HEIGHT;

    /**
     * Some name for the game
     */
    public static final String GAME_NAME = "Rouge-Lite Crawler";


    /**
     * Map object that holds information about the map of the game
     */
    private GameMap gameMap;






    /**
     * Armor builder class
     */
    private ArmorBuilder armorBuilder;

    /**
     * Weapon builder class
     */
    private WeaponBuilder weaponBuilder;

    /**
     * Enemy builder class
     */
    private EnemyBuilder enemyBuilder;

    /**
     * Food builder class
     */
    private FoodBuilder foodBuilder;

    /**
     * World builder class
     */
    private WorldBuilder worldBuilder;






    /**
     * Stores the player object if want to get it quickly
     */
    private Player player;

    /**
     * Stores the ascii panel object if wanted to access it directly
     */
    private AsciiPanel panel;

    /**
     * State machine that handles scene transitions.
     */
    private StateManager manager;


    /**
     * Initializer for game data
     */
    public GameData(){

        //Player
        this.player = new Player(X_WIDTH/2,Y_HEIGHT/2);

        //Main panel
        this.panel = new AsciiPanel(X_WIDTH+STATS_X_OFFSET, Y_HEIGHT, AsciiFont.CP437_16x16);



        //Builders
        this.armorBuilder = new ArmorBuilder();
        this.weaponBuilder = new WeaponBuilder();
        this.enemyBuilder = new EnemyBuilder();
        this.foodBuilder = new FoodBuilder();
        this.worldBuilder = new WorldBuilder(this);

        //Game map
        this.gameMap = new GameMap(worldBuilder, player);

        //State Manager
        manager = new StateManager(this);
    }


    /**
     * Resets the game data on a losing condition
     */
    public void resetData(){
        //Player
        this.player = new Player(X_WIDTH/2,Y_HEIGHT/2);

        //Builders
        this.armorBuilder = new ArmorBuilder();
        this.weaponBuilder = new WeaponBuilder();
        this.enemyBuilder = new EnemyBuilder();
        this.foodBuilder = new FoodBuilder();
        this.worldBuilder = new WorldBuilder(this);

        //Game map
        this.gameMap = new GameMap(worldBuilder, player);
    }


    /**
     * Ascii panel object
     * @return
     */
    public AsciiPanel getPanel(){
        return this.panel;
    }

    /**
     * Game map of current level
     * @return
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * Updates the game map with a new level
     * @param gameMap
     */
    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Gets the armor builder that builds armor objects
     * @return
     */
    public ArmorBuilder getArmorBuilder() {
        return armorBuilder;
    }

    /**
     * Gets the weapon builder that builds weapon objects
     * @return
     */
    public WeaponBuilder getWeaponBuilder() {
        return weaponBuilder;
    }

    /**
     * Gets the enemy builder which builds enemy objects
     * @return
     */
    public EnemyBuilder getEnemyBuilder() {
        return enemyBuilder;
    }

    /**
     * Gets the food builder which builds food objects
     * @return
     */
    public FoodBuilder getFoodBuilder() {
        return foodBuilder;
    }

    /**
     * Get the world builder which creates a world given a level
     * @return
     */
    public WorldBuilder getWorldBuilder() {
        return worldBuilder;
    }

    /**
     * Get the player, helpful for player update move operations
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the state manager that handles all movement between scenes
     * @return
     */
    public StateManager getManager() {
        return manager;
    }
}
