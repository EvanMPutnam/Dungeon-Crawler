package Builders;

import Items.Food;

import java.util.Random;

/**
 * Created by evanputnam on 8/18/17.
 */
public class FoodBuilder {

    private static Random random = new Random();

    public enum FoodName{
        Pizza, Gum, Potato, Eye, Bladder, Slime, Potion, Kidney;

        public static FoodName getRandomFood() {
            return values()[random.nextInt(values().length)];
        }

        public static int getFoodHitpoints(int playerMax){
            return 1+random.nextInt(playerMax+5);
        }
    }

    public static Food generateFood(int playerMax) {
        return new Food(FoodName.getRandomFood().toString(), FoodName.getFoodHitpoints(playerMax));
    }


}
