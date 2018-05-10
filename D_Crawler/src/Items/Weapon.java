package Items;

import Builders.WeaponBuilder;
import Placement.Player;

/**
 * Created by evanputnam on 8/18/17.
 */
public class Weapon implements Item{

    private int minDamage;
    private int maxDamage;
    private WeaponBuilder.WEAPON_DESC weaponDesc;
    private WeaponBuilder.WEAPON_MATERIAL weaponMat;
    private boolean equiped = false;

    private String name;

    public Weapon(String name, int minDam, int maxDam,
                  WeaponBuilder.WEAPON_DESC weaponDesc, WeaponBuilder.WEAPON_MATERIAL weaponMat){
        this.name = name;
        this.minDamage = minDam;
        this.maxDamage = maxDam;
        this.weaponDesc = weaponDesc;
        this.weaponMat = weaponMat;
    }

    public int getMinDamage(){
        return this.minDamage;
    }

    public int getMaxDamage(){
        return this.maxDamage;
    }

    public String getItemName(){
        return name;
    }

    public void useItem(Player player){
        equiped = true;
        player.equipWeapon(this);
    }

    public boolean isEquiped(){
        return equiped;
    }

    public void setEquiped(boolean eq){
        this.equiped = eq;
    }



    public String getStatReadOff(){
        return this.name +"\n"
                +"Item: " + "Weapon"+"\n"
                +"Max Dam: " + this.maxDamage+"\n"
                +"Min Dam: " + this.minDamage+"\n"
                +"Type: " + weaponDesc.toString()+"\n"
                +"Material: " + weaponMat.toString();
    }



}
