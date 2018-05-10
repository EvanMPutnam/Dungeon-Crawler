package Items;

import Builders.ArmorBuilder.ArmorType;
import Placement.Player;

/**
 * Created by evanputnam on 8/18/17.
 */
public class Armor implements Item{


    private ArmorType type;
    private int defense;
    private String name;
    private boolean equiped = false;

    public Armor(String name, int defense, ArmorType type){
        this.name = name;
        this.defense = defense;
        this.type = type;
    }


    public ArmorType getType() {
        return type;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public String getItemName(){
        return name;
    }

    public void useItem(Player player){
        equiped = true;
        switch (type){
            case CHEST:
                player.equipBody(this);
                break;
            case LEGS:
                player.equipHead(this);
                break;
            case HEAD:
                player.equipLegs(this);
                break;
        }
    }

    public boolean isEquiped(){
        return equiped;
    }

    public void setEquiped(boolean eq){
        this.equiped = eq;
    }

    public String getStatReadOff(){
        return this.name +"\n"
                +"Item: " +"Armor"+"\n"
                +"Def: " + this.defense+"\n"
                +"Type: " + this.type;
    }
}
