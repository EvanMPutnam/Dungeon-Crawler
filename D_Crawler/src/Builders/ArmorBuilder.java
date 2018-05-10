package Builders;

import Items.Armor;

import java.util.Random;

/**
 * Created by evanputnam on 8/18/17.
 */
public class ArmorBuilder {


    private static Random random = new Random();

    public enum ArmorType{
        CHEST, LEGS, HEAD;

        public static ArmorType generateArmor(){
            return values()[random.nextInt(values().length)];
        }

    }

    private static int generateDef(ArmorType t, int level){
        switch (t){
            case HEAD:
                return 1+random.nextInt(level+1);
            case LEGS:
                return 2+random.nextInt(level+1);
            case CHEST:
                return 3+random.nextInt(level+1);
        }
        return 1;
    }

    public static Armor generateArmor(int level) {
        ArmorType t = ArmorBuilder.ArmorType.generateArmor();
        return new Armor(t.toString(), generateDef(t, level), t);
    }

}
