package Items;

import Placement.Player;

/**
 * Created by evanputnam on 8/18/17.
 */
public class Food implements Item{


    private String name;
    private int hitpoints;


    public Food(String name, int hitpoints){
        this.name = name;
        this.hitpoints = hitpoints;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public String getItemName(){
        return this.name;
    }


    public String getStatReadOff(){
        return this.name +"\n"
                +"Item: " + "Food" +'\n'
                +"Hitpoints: " + this.hitpoints;
    }

    public void useItem(Player player){
        player.heal(hitpoints);
        player.getItems().remove(this);
    }

    public boolean isEquiped(){
        return false;
    }

}
