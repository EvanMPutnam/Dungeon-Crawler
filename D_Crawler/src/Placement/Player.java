package Placement;

import Builders.WeaponBuilder;
import Items.Armor;
import Items.Food;
import Items.Item;
import Items.Weapon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by evanputnam on 8/17/17.
 */
public class Player implements Placeable {

    private static final char PLAYER_CHAR = '@';

    public static final int MAX_INVENTORY_SLOTS = 40;

    private Random diceRoller;

    private int x;
    private int y;
    private int hitPoints;
    private int maxHitPoints;

    private ArrayList<Item> items;


    private Armor headPiece;
    private Armor chestPiece;
    private Armor legPiece;

    private Weapon weapon;

    private String playerName;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.items = new ArrayList<>();
        this.headPiece = null;
        this.chestPiece = null;
        this.legPiece = null;
        this.weapon = null;
        this.diceRoller = new Random();
        this.playerName = "";
        diceRoller.setSeed(System.currentTimeMillis());

        this.hitPoints = 10;
        this.maxHitPoints = 10;

        this.items.add(new Weapon("POKER", 1, 4, WeaponBuilder.WEAPON_DESC.Weak, WeaponBuilder.WEAPON_MATERIAL.Bronze));
        this.items.add(new Food("POTION", 100));

    }

    public String getPlayerName(){
        if(this.playerName == null){
            return "";
        }else{
            return playerName;
        }
    }


    public boolean addPlayerNameCh(char c){
        if(this.playerName.length() < 10){
            this.playerName += c;
            return true;
        }else{
            return false;
        }
    }

    public boolean removePlayerNameCh(){
        if(this.playerName.length() != 0){
            this.playerName = this.playerName.substring(0, this.playerName.length()-1);
            return true;
        }
        return false;
    }

    public int getAttackDamage(){
        if(weapon == null){
            //Max fist damage is 3
            return diceRoller.nextInt(3);
        }
        return diceRoller.nextInt((weapon.getMaxDamage() - weapon.getMinDamage()) + 1)
                + weapon.getMinDamage();
    }

    public int getDefenseBonus(){
        int def = 0;
        if(headPiece != null){
            def += headPiece.getDefense();
        }

        if(chestPiece != null){
            def += chestPiece.getDefense();
        }

        if(legPiece != null){
            def += legPiece.getDefense();
        }

        return def;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getChar(){
        return PLAYER_CHAR;
    }

    public int getHitPoints(){return this.hitPoints;}

    public int getMaxHitPoints(){return this.maxHitPoints;}

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public void equipWeapon(Weapon weapon){
        if (this.weapon != null){
            this.weapon.setEquiped(false);
        }
        this.weapon = weapon;
    }

    public void equipHead(Armor armor){
        if (this.headPiece != null){
            this.headPiece.setEquiped(false);
        }
        this.headPiece = armor;
    }

    public void equipBody(Armor armor){
        if (this.chestPiece != null){
            this.chestPiece.setEquiped(false);
        }
        this.chestPiece = armor;
    }

    public void equipLegs(Armor armor){
        if(this.legPiece != null){
            this.legPiece.setEquiped(false);
        }
        this.legPiece = armor;
    }

    public boolean takeDamage(int damage){
        this.hitPoints -= damage;
        if (this.hitPoints <= 0){
            return true;
        }
        return false;
    }

    public void heal(int health){
        this.hitPoints += health;
        if (this.hitPoints > this.maxHitPoints){
            this.hitPoints = this.maxHitPoints;
        }
    }



    /**
     * Adds an item to the inventory if under the required slots.
     * @param item
     * @return
     */
    public boolean addItem(Item item){
        if(this.items.size() >= MAX_INVENTORY_SLOTS){
            return false;
        }else{
            this.items.add(item);
            return true;
        }
    }


    public String getReadoff(){
        String s = "";
        if (headPiece != null){
            s += headPiece.getName();
            s += '\n';
        }
        if (legPiece != null){
            s += legPiece.getName();
            s += '\n';
        }
        if (chestPiece != null){
            s += chestPiece.getName();
            s += '\n';
        }
        if (weapon != null){
            s += weapon.getItemName();
            s += '\n';
        }
        return s;
    }



}
