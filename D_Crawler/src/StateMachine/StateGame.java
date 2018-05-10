package StateMachine;

import Backend.GameData;
import GameMap.Coordinate;
import GameMap.GameMap;
import Items.Item;
import Placement.*;
import StateMachine.StateManager.StateManager;
import asciiPanel.AsciiPanel;

import java.util.*;


/**
 * Created by evanputnam on 8/19/17.
 * Game state
 * TODO: Add status effects to enemies like blue for frozen, yellow stunned(after a crit hit),
 * TODO: green for poison, red for bloody???
 * TODO: Add a queue for things happening in the world in the status bar...
 * TODO: Make enemies drop stuff
 */
public class StateGame implements State {


    /**
     * Backend object
     */
    private GameData data;

    /**
     * Random object for movement
     */
    private Random randomMove;

    /**
     * Initializes the object with the game data backend
     *
     * @param data
     */
    public StateGame(GameData data) {
        this.randomMove = new Random();
        this.data = data;
    }

    /**
     * Processes each of the commands that comes in.
     *
     * @param com
     */
    public void processCommand(int com) {
        HashMap<Coordinate, Placeable> map = data.getGameMap().getMap();

        //State changes here
        if (com == State.I_KEY) {
            System.out.println("Changing to inventory");
            data.getManager().setGameState(StateManager.INVENTORY_STATE);
        }

        if (com == State.ESCAPE_KEY) {
            System.out.println("Going to Pause");
            data.getManager().setGameState(StateManager.PAUSE_STATE);
        }


        //Update movement here
        //Variable to check if did move
        boolean didMove = false;

        if (com == LEFT_KEY) {
            didMove = checkCollisionMove(map, LEFT_KEY);
        }

        if (com == RIGHT_KEY) {
            didMove = checkCollisionMove(map, RIGHT_KEY);
        }

        if (com == UP_KEY) {
            didMove = checkCollisionMove(map, UP_KEY);
        }

        if (com == DOWN_KEY) {
            didMove = checkCollisionMove(map, DOWN_KEY);
        }


        //Update enemies here
        //TODO Implement enemy movement.  Also note... That attacking enemy should count as movement.
        if (didMove) {
            for (Enemy e: data.getGameMap().getEnemies()) {
                Coordinate c = getEnemyMovement(data.getPlayer(), e);
                if(map.get(c) != null && (map.get(c) instanceof Floor)){
                    map.put(new Coordinate(e.getX(), e.getY()), new Floor(e.getX(), e.getY()));
                    map.put(c, e);
                    e.setX(c.getX());
                    e.setY(c.getY());
                }
            }
        }


         if (data.getPlayer().takeDamage(0)){
            data.getManager().setGameState(StateManager.START_MENU);
            data.resetData();
         }




    }


    /**
     * Checks collisions with specific objects and checks values
     *
     * @param map
     * @param direction
     */
    private boolean checkCollisionMove(HashMap<Coordinate, Placeable> map, int direction) {

        //Get player placable
        Placeable player = map.get(new Coordinate(data.getPlayer().getX(), data.getPlayer().getY()));

        //Get where the player wants to move
        Placeable next;
        switch (direction) {
            case LEFT_KEY:
                next = map.get(new Coordinate(player.getX() - 1, player.getY()));
                break;
            case RIGHT_KEY:
                next = map.get(new Coordinate(player.getX() + 1, player.getY()));
                break;
            case UP_KEY:
                next = map.get(new Coordinate(player.getX(), player.getY() - 1));
                break;
            case DOWN_KEY:
                next = map.get(new Coordinate(player.getX(), player.getY() + 1));
                break;
            default:
                next = null;
        }

        //If not null then process each option for move type
        if (next != null) {
            if (next instanceof Floor) {
                //Merely swap the values in the hash map.
                map.put(new Coordinate(next.getX(), next.getY()), player);
                map.put(new Coordinate(player.getX(), player.getY()), next);
                int xP = next.getX();
                int yP = next.getY();
                int xN = player.getX();
                int yN = player.getY();
                player.setX(xP);
                player.setY(yP);
                next.setX(xN);
                next.setY(yN);
                return true;
            } else if (next instanceof Ladder) {
                GameMap gameMap = new GameMap(data.getWorldBuilder(), data.getWorldBuilder().getPreviousLevel() + 1, this.data.getPlayer());
                data.setGameMap(gameMap);
                return true;
            }else if (next instanceof Enemy){
                this.data.getGameMap().attackEnemy(this.data.getPlayer().getAttackDamage(), (Enemy)next);
                return true;
            }else if (next instanceof Drop){
                data.getPlayer().addItem(((Drop) next).getItem());
                map.put(new Coordinate(next.getX(), next.getY()), new Floor(next.getX(), next.getY()));
                return true;
            }
        }

        return false;


    }

    /**
     * Updates screen if given an ascii panel object
     *
     * @param panel
     */
    public void updateScreen(AsciiPanel panel) {
    }


    /**
     * Updates the screen so that the main charachter is always in the middle.
     */
    public void updateScreen() {

        //Clears the panel before writing
        data.getPanel().clear();

        //Gets the player location
        int xS = data.getPlayer().getX();
        int yS = data.getPlayer().getY();

        //Gets the offset to calculate
        int offSetX = xS - GameData.X_WIDTH / 2;
        int offSetY = yS - GameData.Y_HEIGHT / 2;

        //Draws the board with each object
        for (int y = 0; y < GameData.Y_HEIGHT; y++) {
            for (int x = 0; x < GameData.X_WIDTH; x++) {
                Placeable p = data.getGameMap().getMap().get(new Coordinate(x + offSetX, y + offSetY));

                if (p != null) {
                    data.getPanel().write(p.getChar(), x, y);
                }
            }
        }


        //Write to the right hand portion of the screen for status indicators.
        data.getPanel().write("Name: " + data.getPlayer().getPlayerName(), GameData.X_WIDTH + 1, 0);
        data.getPanel().write("Hit Points: "+data.getPlayer().getHitPoints()
                        +"/"+data.getPlayer().getMaxHitPoints()
                , GameData.X_WIDTH + 1, 2);
        data.getPanel().write("Other Stuff: ", GameData.X_WIDTH + 1, 4);
        data.getPanel().write("Level: " + String.valueOf(data.getWorldBuilder().getPreviousLevel()),
                GameData.X_WIDTH + 1, 6);

        data.getPanel().write("------------------", GameData.X_WIDTH + 1, 14);

        int y = 15;
        String s[] = this.data.getPlayer().getReadoff().split("\n");
        for (String st:s) {
            data.getPanel().write(st, GameData.X_WIDTH + 1, y);
            y += 1;
        }



        data.getPanel().updateUI();


    }


    /**
     * Value for attack radius of an enemy
     */
    private static final int ATTACK_RADIUS = 5;

    /**
     * Handles the enemy movement given the player and the enemy to calc movement for
     * @param p player
     * @param e enemy to calc movement for
     * @return
     */
    private Coordinate getEnemyMovement(Player p, Enemy e) {
        //BFS if close to player or within radius
        if (Math.abs(p.getX() - e.getX()) < ATTACK_RADIUS && Math.abs(p.getY() - e.getY()) < ATTACK_RADIUS) {


            ArrayList<Coordinate> c = nextMovePath(e.getX(), e.getY(), p.getX(), p.getY());
            if(c == null || c.size() <= 1){
                return null;
            }

            return c.get(1);


            //Random move if not within radius
        } else {

            //0, 1, 2 you move randomly if not then stay the same.
            if (randomMove.nextInt(4) != 3) {

                //Get storage for coordinates
                ArrayList<Coordinate> coordinatesAvailable = new ArrayList<>();

                //Get coordinates to check
                Coordinate left = new Coordinate(e.getX() - 1, e.getY());
                Coordinate right = new Coordinate(e.getX() + 1, e.getY());
                Coordinate up = new Coordinate(e.getX(), e.getY() - 1);
                Coordinate down = new Coordinate(e.getX(), e.getY() + 1);

                //Check coordinates and add to list if available
                if (data.getGameMap().getMap().get(left) != null) {
                    coordinatesAvailable.add(left);
                }
                if (data.getGameMap().getMap().get(right) != null) {
                    coordinatesAvailable.add(right);
                }
                if (data.getGameMap().getMap().get(up) != null) {
                    coordinatesAvailable.add(up);
                }
                if (data.getGameMap().getMap().get(down) != null) {
                    coordinatesAvailable.add(down);
                }

                if (coordinatesAvailable.size() != 0) {
                    //Get coordinate from available
                    Coordinate c = coordinatesAvailable.get(
                            randomMove.nextInt(coordinatesAvailable.size()));
                    return c;
                } else {
                    return new Coordinate(e.getX(), e.getY());
                }
            } else {
                return new Coordinate(e.getX(), e.getY());
            }

        }

    }

    /**
     * Search for player with bfs
     * @param currX current x of enemy
     * @param currY current y of enemy
     * @param goalX current goal of enemy
     * @param goalY current goal of enemy
     * @return
     */
    private ArrayList<Coordinate> nextMovePath(int currX, int currY, int goalX, int goalY){
        //Acts as a queue for those that are open
        ArrayList<Coordinate> openSet = new ArrayList<>();

        //Acts as a set for those that are not open
        Set<Coordinate> closedSet = new TreeSet<Coordinate>();

        //Coordinate mapping to later build paths
        HashMap<Coordinate, Coordinate> mapTrace = new HashMap<>();


        //Put path
        mapTrace.put(new Coordinate(currX, currY), null);


        //Initialize
        openSet.add(new Coordinate(currX, currY));

        //While the set is not empty iterate over it
        while(!openSet.isEmpty()){

            Coordinate c = openSet.remove(0);

            //Goal met then return a constructed path
            if(c.getX() == goalX && c.getY() == goalY){
                return constructPath(c, mapTrace);
            }

            //Children array to store next entry nodes
            ArrayList<Coordinate> child = new ArrayList<>();


            //Get possible coordinates
            Coordinate cLeft = new Coordinate(c.getX() - 1, c.getY());
            Coordinate cRight = new Coordinate(c.getX() + 1, c.getY());
            Coordinate cDown = new Coordinate(c.getX(), c.getY()+1);
            Coordinate cUp = new Coordinate(c.getX(), c.getY()-1);

            //Make sure the tile is either the player or the floor
            if(data.getGameMap().getMap().get(cLeft)!= null &&
                    (data.getGameMap().getMap().get(cLeft) instanceof Floor ||
                    data.getGameMap().getMap().get(cLeft) instanceof Player)){
                child.add(new Coordinate(c.getX() - 1, c.getY()));
            }
            if(data.getGameMap().getMap().get(cRight)!= null &&
                    (data.getGameMap().getMap().get(cRight) instanceof Floor ||
                            data.getGameMap().getMap().get(cRight) instanceof Player)){
                child.add(new Coordinate(c.getX() + 1, c.getY()));
            }
            if(data.getGameMap().getMap().get(cDown)!= null &&
                    (data.getGameMap().getMap().get(cDown) instanceof Floor ||
                    data.getGameMap().getMap().get(cDown) instanceof Player)){
                child.add(new Coordinate(c.getX(), c.getY()+1));
            }
            if(data.getGameMap().getMap().get(cUp) != null &&
                    (data.getGameMap().getMap().get(cUp) instanceof Floor ||
                            data.getGameMap().getMap().get(cUp) instanceof Player)){
                child.add(new Coordinate(c.getX(), c.getY()-1));
            }

            //For each possible move
            for(Coordinate s:child){
                //If already handled move on.
                if(closedSet.contains(s)){
                    continue;
                }
                //If the open set does not contain the next move add it
                if(!openSet.contains(s)){
                    mapTrace.put(s, c);
                    openSet.add(s);

                }

            }
            //System.out.println(closedSet.size());
            closedSet.add(c);
        }

        return null;


    }

    /**
     * Constructs a path between enemy and player if can be found
     * @param state player coordinate to go backwards in hashmap to find
     * @param mainMap Hash map of coordinates and corresponding child-parent pairs.
     * @return array list of coordinate path.
     */
    private ArrayList<Coordinate> constructPath(Coordinate state, HashMap<Coordinate, Coordinate> mainMap){
        ArrayList<Coordinate> lst = new ArrayList<>();
        while(true){
            Coordinate c = mainMap.get(state);
            if(c != null){
                state = c;
                lst.add(state);
            }else{
                break;
            }
        }
        Collections.reverse(lst);
        return lst;
    }
    


}
