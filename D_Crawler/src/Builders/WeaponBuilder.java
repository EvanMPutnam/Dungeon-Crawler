package Builders;

import Items.Weapon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class that creates a new weapon.
 */
public class WeaponBuilder {


    /**
     * Enum to store the different types of weapons
     */
    private enum WEAPON_TYPE{
        Sword, Axe, Spear, Hammer
    }

    /**
     * Enum to store a weapon description
     */
    public enum WEAPON_DESC{
        Holy, Savage, Awesome, Strong, Weak
    }

    /**
     * Enum to store a weapon material
     */
    public enum WEAPON_MATERIAL{
        Wood, Bronze, Iron, Steel, Dwarvish, Elvish, Adamant, Runite
    }





    /**
     * List of all possible WEAPON_TYPE values
     */
    private static final List<WEAPON_TYPE> WEAPON_T =
            Collections.unmodifiableList(Arrays.asList(WEAPON_TYPE.values()));

    /**
     * List of all possible WEAPON_DESC values
     */
    private static final List<WEAPON_DESC> WEAPON_D =
            Collections.unmodifiableList(Arrays.asList(WEAPON_DESC.values()));

    /**
     * List of all possible WEAPON_MATERIAL values
     */
    private static final List<WEAPON_MATERIAL> WEAPON_M =
            Collections.unmodifiableList(Arrays.asList(WEAPON_MATERIAL.values()));


    /**
     * Random generator to generate new weapons.
     */
    private static final Random ranWepStatGen = new Random();


    //TODO: Handle weapon generation. Also figure out level crap
    /**
     * Static function to generate a weapon
     * @return
     */
    public static Weapon genWeapon(){

        int minDamage = 0;
        int maxDamage = 0;

        String desc = "";

        //Process weapon type
        WEAPON_TYPE wt = WEAPON_T.get(ranWepStatGen.nextInt(WEAPON_T.size()));
        switch (wt){
            case Axe: desc = "Axe"; break;
            case Spear: desc = "Spear"; break;
            case Sword: desc = "Sword"; break;
            case Hammer: desc = "Hammer"; break;
        }

        //Process weapon material
        WEAPON_MATERIAL wm = WEAPON_M.get(ranWepStatGen.nextInt(WEAPON_M.size()));
        switch (wm){
            case Wood: minDamage = 1; maxDamage = 3; break;
            case Bronze: minDamage = 2; maxDamage = 5; break;
            case Iron: minDamage = 4; maxDamage = 7; break;
            case Steel: minDamage = 6; maxDamage = 9; break;
            case Dwarvish: minDamage = 8; maxDamage = 12; break;
            case Elvish: minDamage = 12; maxDamage = 17; break;
            case Adamant: minDamage = 18; maxDamage = 22; break;
            case Runite: minDamage = 22; maxDamage = 30; break;
        }

        //Process weapon description
        WEAPON_DESC wd = WEAPON_D.get(ranWepStatGen.nextInt(WEAPON_D.size()));
        switch (wd){
            case Weak: maxDamage -=1; break;
            case Strong: minDamage +=1; break;
            case Awesome: maxDamage += 1;break;
            case Holy: maxDamage += 2;break;
            case Savage: minDamage += 1; break;
        }

        return new Weapon(desc, minDamage, maxDamage, wd, wm);
    }
}
